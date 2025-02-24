package com.buildingblocks.appointments.domain.table;

import com.buildingblocks.appointments.domain.table.entities.Player;
import com.buildingblocks.appointments.domain.table.events.AddedPlayer;
import com.buildingblocks.appointments.domain.table.events.CreatedTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
  private Table table;

  @BeforeEach
  void setUp() {
    table = new Table("Table SofkaU");
  }

  @Test
  void createTableSuccess() {
    assertEquals("Table SofkaU", table.getName().getValue());
    assertNotNull(table.getCards());
    assertNotNull(table.getPlayers());
    assertEquals(4, table.getCards().size());
    assertEquals(4, table.getCards().get(0).size());
    assertEquals("😈", table.getCards().get(0).get(0).getEmoji().getValue());
    assertEquals(1, table.getUncommittedEvents().size());
    assertInstanceOf(CreatedTable.class, table.getUncommittedEvents().get(0));
  }

  @Test
  void createTableFailed() {
    assertThrows(IllegalArgumentException.class, () -> new Table(""));
  }

  @Test
  void addPlayerSuccess() {
    String nickname = "Larry Capija";

    table.addPlayer(nickname);

    List<Player> players = List.copyOf(table.getPlayers().values());
    assertEquals(1, table.getPlayers().size());
    assertEquals(nickname, players.get(0).getNickname().getValue());
    assertEquals(2, table.getUncommittedEvents().size());
    assertInstanceOf(AddedPlayer.class, table.getUncommittedEvents().get(1));
  }

  @Test
  void addPlayerWithTwoPlayers() {
    String nickname = "Larry Capija";

    table.addPlayer(nickname);
    table.addPlayer(nickname);

    assertThrows(IllegalStateException.class, () -> table.addPlayer(nickname));
  }
}