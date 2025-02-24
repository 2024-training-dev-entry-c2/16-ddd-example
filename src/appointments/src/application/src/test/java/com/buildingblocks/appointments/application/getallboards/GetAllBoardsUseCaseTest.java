package com.buildingblocks.appointments.application.getallboards;

import com.buildingblocks.appointments.application.shared.ports.IEventsRepositoryPort;
import com.buildingblocks.appointments.domain.table.events.AddedPlayer;
import com.buildingblocks.appointments.domain.table.events.CreatedTable;
import com.buildingblocks.appointments.domain.table.events.StartedGame;
import com.buildingblocks.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetAllBoardsUseCaseTest {
  private final GetAllBoardsUseCase useCase;
  private final IEventsRepositoryPort repository;

  public GetAllBoardsUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new GetAllBoardsUseCase(repository);
  }

  @Test
  void executeSuccess() {
    List<String> tableNames = List.of("Table A", "Table B", "Table C", "Table D");
    Mockito.when(repository.findAllAggregates()).thenReturn(getEvents());
    StepVerifier
      .create(useCase.execute())
      .assertNext(response -> {
        assertNotNull(response);
        assertTrue(tableNames.contains(response.getBoardName()));
        assertEquals(2, response.getPlayers().size());
        assertTrue(response.getActive());
      })
      .assertNext(response -> {
        assertNotNull(response);
        assertTrue(tableNames.contains(response.getBoardName()));
        assertEquals(2, response.getPlayers().size());
        assertTrue(response.getActive());
      })
      .assertNext(response -> {
        assertNotNull(response);
        assertTrue(tableNames.contains(response.getBoardName()));
        assertEquals(2, response.getPlayers().size());
        assertTrue(response.getActive());
      })
      .assertNext(response -> {
        assertNotNull(response);
        assertTrue(tableNames.contains(response.getBoardName()));
        assertEquals(2, response.getPlayers().size());
        assertTrue(response.getActive());
      })
      .verifyComplete();

    Mockito.verify(repository).findAllAggregates();
  }

  private Flux<DomainEvent> getEvents() {
    String aggregateId1 = UUID.randomUUID().toString();
    CreatedTable table1 = new CreatedTable("Table A");
    AddedPlayer player1_1 = new AddedPlayer("Alice");
    AddedPlayer player1_2 = new AddedPlayer("Bob");
    StartedGame game1 = new StartedGame();

    table1.setAggregateRootId(aggregateId1);
    player1_1.setAggregateRootId(aggregateId1);
    player1_2.setAggregateRootId(aggregateId1);
    game1.setAggregateRootId(aggregateId1);

    String aggregateId2 = UUID.randomUUID().toString();
    CreatedTable table2 = new CreatedTable("Table B");
    AddedPlayer player2_1 = new AddedPlayer("Charlie");
    AddedPlayer player2_2 = new AddedPlayer("David");
    StartedGame game2 = new StartedGame();

    table2.setAggregateRootId(aggregateId2);
    player2_1.setAggregateRootId(aggregateId2);
    player2_2.setAggregateRootId(aggregateId2);
    game2.setAggregateRootId(aggregateId2);

    String aggregateId3 = UUID.randomUUID().toString();
    CreatedTable table3 = new CreatedTable("Table C");
    AddedPlayer player3_1 = new AddedPlayer("Eve");
    AddedPlayer player3_2 = new AddedPlayer("Frank");
    StartedGame game3 = new StartedGame();

    table3.setAggregateRootId(aggregateId3);
    player3_1.setAggregateRootId(aggregateId3);
    player3_2.setAggregateRootId(aggregateId3);
    game3.setAggregateRootId(aggregateId3);

    String aggregateId4 = UUID.randomUUID().toString();
    CreatedTable table4 = new CreatedTable("Table D");
    AddedPlayer player4_1 = new AddedPlayer("Grace");
    AddedPlayer player4_2 = new AddedPlayer("Henry");
    StartedGame game4 = new StartedGame();

    table4.setAggregateRootId(aggregateId4);
    player4_1.setAggregateRootId(aggregateId4);
    player4_2.setAggregateRootId(aggregateId4);
    game4.setAggregateRootId(aggregateId4);

    return Flux.just(
      table1, player1_1, player1_2, game1,
      table2, player2_1, player2_2, game2,
      table3, player3_1, player3_2, game3,
      table4, player4_1, player4_2, game4
    );
  }
}