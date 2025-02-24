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

class EndTurnUseCaseTest {
  private EndTurnUseCase useCase;
  private IEventsRepository eventsRepository;

  @BeforeEach
  void setUp() {
    eventsRepository = Mockito.mock(IEventsRepository.class);
    useCase = new EndTurnUseCase(eventsRepository);
  }

  @Test
  void shouldEndTurnSuccessfully() {
    String gameId = "game-123";
    String playerId = "player-1";
    int turnNumber = 3;
    String reason = "Turno completado";

    EndTurnRequest request = new EndTurnRequest(gameId, playerId, turnNumber, reason);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());
    doNothing().when(eventsRepository).save(any(DomainEvent.class));

    Mono<EndTurnResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(playerId, response.getPlayerId());
              assertEquals(turnNumber, response.getTurnNumber());
              assertEquals(reason, response.getReason());
              assertTrue(response.getIsTurnEnded());
            })
            .verifyComplete();

    verify(eventsRepository, atLeast(1)).save(any(DomainEvent.class));
  }

  @Test
  void shouldNotEndTurnWhenInvalidPlayer() {
    String gameId = "game-456";
    String playerId = "invalid-player";
    int turnNumber = 4;
    String reason = "Jugador no válido";

    EndTurnRequest request = new EndTurnRequest(gameId, playerId, turnNumber, reason);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());

    Mono<EndTurnResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(playerId, response.getPlayerId());
              assertEquals(turnNumber, response.getTurnNumber());
              assertEquals(reason, response.getReason());
              assertFalse(response.getIsTurnEnded());
            })
            .verifyComplete();

    verify(eventsRepository, never()).save(any(DomainEvent.class));
  }

  @Test
  void shouldHandleErrorFromRepository() {
    String gameId = "game-789";
    String playerId = "player-2";
    int turnNumber = 2;
    String reason = "Finalización manual";

    EndTurnRequest request = new EndTurnRequest(gameId, playerId, turnNumber, reason);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.error(new RuntimeException("DB Error")));

    Mono<EndTurnResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                    throwable.getMessage().equals("DB Error"))
            .verify();

    verify(eventsRepository, never()).save(any(DomainEvent.class));
  }
}