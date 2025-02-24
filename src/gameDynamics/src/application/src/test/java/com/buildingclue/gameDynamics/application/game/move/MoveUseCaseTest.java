package com.buildingclue.gameDynamics.application.game.move;

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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MoveUseCaseTest {
  private MoveUseCase useCase;
  private IEventsRepository eventsRepository;

  @BeforeEach
  void setUp() {
    eventsRepository = Mockito.mock(IEventsRepository.class);
    useCase = new MoveUseCase(eventsRepository);
  }

  @Test
  void shouldMoveSuccessfully() {
    String gameId = "game-123";
    String playerId = "player-1";
    String fromPosition = "0,0";
    String toPosition = "1,1";

    MoveRequest request = new MoveRequest(gameId, playerId, fromPosition, toPosition);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());
    doNothing().when(eventsRepository).save(any(DomainEvent.class));

    Mono<MoveResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(playerId, response.getPlayerId());
              assertEquals(fromPosition, response.getFromPosition());
              assertEquals(toPosition, response.getToPosition());
              assertTrue(response.getIsMoveValid());
            })
            .verifyComplete();

    verify(eventsRepository, times(2)).save(any(DomainEvent.class));
  }

  @Test
  void shouldNotMoveWhenInvalid() {
    String gameId = "game-456";
    String playerId = "player-2";
    String fromPosition = "5,5";
    String toPosition = "20,20";

    MoveRequest request = new MoveRequest(gameId, playerId, fromPosition, toPosition);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());
    doNothing().when(eventsRepository).save(any(DomainEvent.class));

    Mono<MoveResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(playerId, response.getPlayerId());
              assertEquals(fromPosition, response.getFromPosition());
              assertEquals(toPosition, response.getToPosition());
              assertFalse(response.getIsMoveValid());
            })
            .verifyComplete();

    verify(eventsRepository, times(1)).save(any(DomainEvent.class));
  }

  @Test
  void shouldThrowErrorWhenDatabaseFails() {
    String gameId = "game-789";
    String playerId = "player-3";
    String fromPosition = "3,3";
    String toPosition = "4,4";

    MoveRequest request = new MoveRequest(gameId, playerId, fromPosition, toPosition);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.error(new RuntimeException("DB Connection Error")));

    Mono<MoveResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                    throwable.getMessage().equals("DB Connection Error"))
            .verify();

    verify(eventsRepository, never()).save(any(DomainEvent.class));
  }

}