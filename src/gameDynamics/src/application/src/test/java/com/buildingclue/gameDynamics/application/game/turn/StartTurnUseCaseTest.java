package com.buildingclue.gameDynamics.application.game.turn;

import com.buildingclue.gameDynamics.application.game.shared.IEventsRepository;
import com.buildingclue.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StartTurnUseCaseTest {
  private StartTurnUseCase useCase;
  private IEventsRepository eventsRepository;

  @BeforeEach
  void setUp() {
    eventsRepository = Mockito.mock(IEventsRepository.class);
    useCase = new StartTurnUseCase(eventsRepository);
  }

  @Test
  void shouldStartTurnSuccessfully() {
    String gameId = "game-123";
    String playerId = "player-1";
    int turnNumber = 1;

    StartTurnRequest request = new StartTurnRequest(gameId, playerId, turnNumber);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());
    doNothing().when(eventsRepository).save(any(DomainEvent.class));

    Mono<StartTurnResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(playerId, response.getPlayerId());
              assertEquals(turnNumber, response.getTurnNumber());
              assertTrue(response.getIsTurnStarted());
            })
            .verifyComplete();

    verify(eventsRepository, atLeast(1)).save(any(DomainEvent.class));
  }

  @Test
  void shouldNotStartTurnWhenInvalidPlayer() {
    String gameId = "game-456";
    String playerId = "invalid-player";
    int turnNumber = 2;

    StartTurnRequest request = new StartTurnRequest(gameId, playerId, turnNumber);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());

    Mono<StartTurnResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(playerId, response.getPlayerId());
              assertEquals(turnNumber, response.getTurnNumber());
              assertFalse(response.getIsTurnStarted());
            })
            .verifyComplete();

    verify(eventsRepository, never()).save(any(DomainEvent.class));
  }

  @Test
  void shouldThrowErrorWhenDatabaseFails() {
    String gameId = "game-789";
    String playerId = "player-3";
    int turnNumber = 3;

    StartTurnRequest request = new StartTurnRequest(gameId, playerId, turnNumber);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.error(new RuntimeException("DB Connection Error")));

    Mono<StartTurnResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                    throwable.getMessage().equals("DB Connection Error"))
            .verify();

    verify(eventsRepository, never()).save(any(DomainEvent.class));
  }

}