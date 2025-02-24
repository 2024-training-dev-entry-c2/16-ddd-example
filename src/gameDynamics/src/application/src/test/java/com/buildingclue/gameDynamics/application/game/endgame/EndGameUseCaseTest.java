package com.buildingclue.gameDynamics.application.game.endgame;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EndGameUseCaseTest {
  private EndGameUseCase useCase;
  private IEventsRepository eventsRepository;

  @BeforeEach
  void setUp() {
    eventsRepository = Mockito.mock(IEventsRepository.class);
    useCase = new EndGameUseCase(eventsRepository);
  }

  @Test
  void shouldEndGameSuccessfully() {
    String gameId = "game-123";
    String winnerId = "player-1";
    boolean wasCaseSolved = true;
    EndGameRequest request = new EndGameRequest(gameId, winnerId, wasCaseSolved);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());
    doNothing().when(eventsRepository).save(any(DomainEvent.class));

    Mono<EndGameResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(winnerId, response.getWinnerPlayerId());
              assertTrue(response.getWasCaseSolved());
              assertEquals(States.FINISHED.toString(), response.getGameState().toString());
            })
            .verifyComplete();

    verify(eventsRepository, times(2)).save(any(DomainEvent.class));
  }

}