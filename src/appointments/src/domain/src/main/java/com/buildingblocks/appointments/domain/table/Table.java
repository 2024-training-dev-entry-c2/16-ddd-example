package com.buildingblocks.appointments.domain.table;

import com.buildingblocks.appointments.domain.table.entities.Card;
import com.buildingblocks.appointments.domain.table.entities.Player;
import com.buildingblocks.appointments.domain.table.events.AddedPlayer;
import com.buildingblocks.appointments.domain.table.events.CheckedPoint;
import com.buildingblocks.appointments.domain.table.events.CheckedWinner;
import com.buildingblocks.appointments.domain.table.events.CreatedTable;
import com.buildingblocks.appointments.domain.table.events.FlippedCard;
import com.buildingblocks.appointments.domain.table.events.StartedGame;
import com.buildingblocks.appointments.domain.table.values.IsActive;
import com.buildingblocks.appointments.domain.table.values.Name;
import com.buildingblocks.appointments.domain.table.values.TableId;
import com.buildingblocks.appointments.domain.table.values.Turn;
import com.buildingblocks.shared.domain.generic.AggregateRoot;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.List;
import java.util.Map;

public class Table extends AggregateRoot<TableId> {
  private Name name;
  private Map<String, Player> players;
  private List<Card> cards;
  private Turn turn;
  private IsActive isActive;
  private Player winner;

  // region Constructors
  public Table(String name) {
    super(new TableId());
    subscribe(new TableHandler(this));
    apply(new CreatedTable(name));
  }

  private Table(TableId identity) {
    super(identity);
    subscribe(new TableHandler(this));
  }
  // endregion

  // region Getters and Setters
  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Map<String, Player> getPlayers() {
    return players;
  }

  public void setPlayers(Map<String, Player> players) {
    this.players = players;
  }

  public List<Card> getCards() {
    return cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }

  public Turn getTurn() {
    return turn;
  }

  public void setTurn(Turn turn) {
    this.turn = turn;
  }

  public IsActive getIsActive() {
    return isActive;
  }

  public void setIsActive(IsActive isActive) {
    this.isActive = isActive;
  }

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {
    this.winner = winner;
  }
  // endregion

  // region Domain Actions
  public void addPlayer(String nickname) {
    apply(new AddedPlayer(nickname));
  }

  public void starGame() {
    apply(new StartedGame());
  }

  public void flipCard(String cardId) {
    apply(new FlippedCard(cardId));
  }

  public void checkPoint() {
    apply(new CheckedPoint());
  }

  public void checkWinner() {
    apply(new CheckedWinner());
  }
  // endregion

  // region Public Methods
  public void validatePlayersQuantity() {
    if (this.players.size() == 2) {
      throw new IllegalStateException("A table must have two players");
    }
  }

  public void validatePlayersToStartGame() {
    if (this.players.size() < 2) {
      throw new IllegalStateException("A table must have at least two players");
    }
  }
  // endregion

  // region Static Methods
  public static Table from(final String identity, final List<DomainEvent> events) {
    Table table = new Table(TableId.of(identity));

    events.forEach(table::apply);
    return table;
  }
  // endregion
}
