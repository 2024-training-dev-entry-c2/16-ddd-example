package com.buildingclue.gameDynamics.application.game.startgame;

import com.buildingclue.gameDynamics.application.game.shared.IEventsRepository;
import com.buildingclue.shared.domain.constants.States;
import com.buildingclue.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StartGameUseCaseTest {
  private StartGameUseCase useCase;
  private IEventsRepository eventsRepository;

  @BeforeEach
  void setUp() {
    eventsRepository = Mockito.mock(IEventsRepository.class);
    useCase = new StartGameUseCase(eventsRepository);
  }

  @Test
  void shouldStartGameSuccessfully() {
    String gameId = "game-123";
    StartGameRequest request = new StartGameRequest(gameId);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());

    doNothing().when(eventsRepository).save(any(DomainEvent.class));

    Mono<StartGameResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(States.IN_PROGRESS.toString(), response.getGameState().toString());
            })
            .verifyComplete();

    verify(eventsRepository, times(2)).save(any(DomainEvent.class));
  }

  @Test
  void shouldStartGameWithPlayersSuccessfully() {
    String gameId = "game-123";
    StartGameRequest request = new StartGameRequest(gameId);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());

    doNothing().when(eventsRepository).save(any(DomainEvent.class));

    Mono<StartGameResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(States.IN_PROGRESS.toString(), response.getGameState().toString());
            })
            .verifyComplete();

    verify(eventsRepository, times(2)).save(any(DomainEvent.class));
  }

  @Test
  void shouldStartGameWithPlayersAndTurnsSuccessfully() {
    String gameId = "game-123";
    StartGameRequest request = new StartGameRequest(gameId);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());

    doNothing().when(eventsRepository).save(any(DomainEvent.class));

    Mono<StartGameResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(States.IN_PROGRESS.toString(), response.getGameState().toString());
            })
            .verifyComplete();

    verify(eventsRepository, times(2)).save(any(DomainEvent.class));
  }

  @Test
  void shouldStartGameWithPlayersAndTurnsAndRulesSuccessfully() {
    String gameId = "game-123";
    StartGameRequest request = new StartGameRequest(gameId);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());

    doNothing().when(eventsRepository).save(any(DomainEvent.class));

    Mono<StartGameResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(States.IN_PROGRESS.toString(), response.getGameState().toString());
            })
            .verifyComplete();

    verify(eventsRepository, times(2)).save(any(DomainEvent.class));
  }
}