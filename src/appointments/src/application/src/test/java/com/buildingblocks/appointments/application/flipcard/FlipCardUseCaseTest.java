package com.buildingblocks.appointments.application.flipcard;

import com.buildingblocks.appointments.application.shared.repositories.IEventsRepository;
import com.buildingblocks.appointments.application.shared.table.TableResponse;
import com.buildingblocks.appointments.domain.table.events.AddedPlayer;
import com.buildingblocks.appointments.domain.table.events.CreatedTable;
import com.buildingblocks.appointments.domain.table.events.FlippedCard;
import com.buildingblocks.appointments.domain.table.events.StartedGame;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FlipCardUseCaseTest {
  private final FlipCardUseCase useCase;
  private final IEventsRepository repository;

  public FlipCardUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new FlipCardUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new CreatedTable("Table 1"),
        new AddedPlayer("Player 1"),
        new AddedPlayer("Player 2"),
        new StartedGame()
      ));

    FlipCardRequest request = new FlipCardRequest("aggregatexyz", "1");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(1, response.getCards().stream().filter(TableResponse.Card::getFlipped).toList().size());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }

  @Test
  void executeWhenThereAraTwoCardsFlipped() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new CreatedTable("Table 1"),
        new AddedPlayer("Player 1"),
        new AddedPlayer("Player 2"),
        new StartedGame(),
        new FlippedCard("1")
      ));

    FlipCardRequest request = new FlipCardRequest("aggregatexyz", "2");

    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(2, response.getCards().stream().filter(TableResponse.Card::getFlipped).toList().size());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }
}