package com.buildingblocks.appointments.domain.table;

import com.buildingblocks.appointments.domain.table.entities.Card;
import com.buildingblocks.appointments.domain.table.entities.Player;
import com.buildingblocks.appointments.domain.table.events.AddedPlayer;
import com.buildingblocks.appointments.domain.table.events.CreatedTable;
import com.buildingblocks.appointments.domain.table.values.Name;
import com.buildingblocks.appointments.domain.table.values.PlayerId;
import com.buildingblocks.appointments.domain.table.values.TableId;
import com.buildingblocks.shared.domain.generic.AggregateRoot;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.List;
import java.util.Map;

public class Table extends AggregateRoot<TableId> {
  private Name name;
  private Map<PlayerId, Player> players;
  private List<List<Card>> cards;

  public Table(String name) {
    super(new TableId());
    subscribe(new TableHandler(this));
    apply(new CreatedTable(name));
  }

  private Table(TableId identity) {
    super(identity);
    subscribe(new TableHandler(this));
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Map<PlayerId, Player> getPlayers() {
    return players;
  }

  public void setPlayers(Map<PlayerId, Player> players) {
    this.players = players;
  }

  public List<List<Card>> getCards() {
    return cards;
  }

  public void setCards(List<List<Card>> cards) {
    this.cards = cards;
  }

  public void addPlayer(String nickname) {
    apply(new AddedPlayer(nickname));
  }

  public void validatePlayersQuantity() {
    if (this.players.size() == 2) {
      throw new IllegalStateException("A table must have at maximo 2 players");
    }
  }

  public static Table from(final String identity, final List<DomainEvent> events) {
    Table table = new Table(TableId.of(identity));

    events.forEach(table::apply);
    return table;
  }
}
