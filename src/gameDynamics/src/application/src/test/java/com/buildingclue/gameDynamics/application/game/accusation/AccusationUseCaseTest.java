package com.buildingclue.gameDynamics.application.game.accusation;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccusationUseCaseTest {
  private AccusationUseCase useCase;
  private IEventsRepository eventsRepository;

  @BeforeEach
  void setUp() {
    eventsRepository = Mockito.mock(IEventsRepository.class);
    useCase = new AccusationUseCase(eventsRepository);
  }

  @Test
  void shouldMakeAccusationIncorrectly() {
    String gameId = "game-123";
    String playerId = "player-1";
    String suspect = "Prof. Plum";
    String weapon = "Rope";
    String location = "Library";

    AccusationRequest request = new AccusationRequest(gameId, playerId, suspect, weapon, location);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());
    doNothing().when(eventsRepository).save(any(DomainEvent.class));

    Mono<AccusationResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(playerId, response.getPlayerId());
              assertEquals(suspect, response.getSuspect());
              assertEquals(weapon, response.getWeapon());
              assertEquals(location, response.getLocation());
              assertFalse(response.getIsAccusationCorrect());
            })
            .verifyComplete();

    verify(eventsRepository, times(2)).save(any(DomainEvent.class));
  }

  @Test
  void shouldMakeAccusationCorrectlyAndEndGame() {
    String gameId = "game-123";
    String playerId = "player-2";
    String suspect = "Miss Scarlet";
    String weapon = "Candlestick";
    String location = "Ballroom";

    AccusationRequest request = new AccusationRequest(gameId, playerId, suspect, weapon, location);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.empty());
    doNothing().when(eventsRepository).save(any(DomainEvent.class));

    Mono<AccusationResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(gameId, response.getGameId());
              assertEquals(playerId, response.getPlayerId());
              assertEquals(suspect, response.getSuspect());
              assertEquals(weapon, response.getWeapon());
              assertEquals(location, response.getLocation());
              assertTrue(response.getIsAccusationCorrect());
            })
            .verifyComplete();

    verify(eventsRepository, atLeast(2)).save(any(DomainEvent.class));
  }

  @Test
  void shouldThrowErrorWhenMakingAccusation() {
    String gameId = "game-456";
    String playerId = "player-3";
    String suspect = "Col. Mustard";
    String weapon = "Dagger";
    String location = "Dining Room";

    AccusationRequest request = new AccusationRequest(gameId, playerId, suspect, weapon, location);

    when(eventsRepository.findEventsByAggregateId(gameId)).thenReturn(Flux.error(new RuntimeException("DB Connection Error")));

    Mono<AccusationResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                    throwable.getMessage().equals("DB Connection Error"))
            .verify();

    verify(eventsRepository, never()).save(any(DomainEvent.class));
  }

}