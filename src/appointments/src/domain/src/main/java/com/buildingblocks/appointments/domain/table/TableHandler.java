package com.buildingblocks.appointments.domain.table;

import com.buildingblocks.appointments.domain.table.entities.Card;
import com.buildingblocks.appointments.domain.table.entities.Player;
import com.buildingblocks.appointments.domain.table.events.AddedPlayer;
import com.buildingblocks.appointments.domain.table.events.CheckedPoint;
import com.buildingblocks.appointments.domain.table.events.CheckedWinner;
import com.buildingblocks.appointments.domain.table.events.CreatedTable;
import com.buildingblocks.appointments.domain.table.events.FlippedCard;
import com.buildingblocks.appointments.domain.table.events.StartedGame;
import com.buildingblocks.appointments.domain.table.values.CardId;
import com.buildingblocks.appointments.domain.table.values.Emoji;
import com.buildingblocks.appointments.domain.table.values.IsActive;
import com.buildingblocks.appointments.domain.table.values.IsFlipped;
import com.buildingblocks.appointments.domain.table.values.Name;
import com.buildingblocks.appointments.domain.table.values.Nickname;
import com.buildingblocks.appointments.domain.table.values.Score;
import com.buildingblocks.appointments.domain.table.values.Turn;
import com.buildingblocks.shared.domain.generic.DomainActionsContainer;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class TableHandler extends DomainActionsContainer {
  public TableHandler(Table table) {
    add(createTable(table));
    add(addPlayer(table));
    add(startGame(table));
    add(flipCard(table));
    add(checkPoint(table));
    add(checkWinner(table));
  }

  private Consumer<? extends DomainEvent> createTable(Table table) {
    return (CreatedTable event) -> {
      table.setName(Name.of(event.getTableName()));

      List<Card> cards = Arrays.asList(
        new Card(CardId.of("1"), Emoji.of("😈"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("2"), Emoji.of("🎮"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("3"), Emoji.of("🌟"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("4"), Emoji.of("🎨"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("5"), Emoji.of("🎮"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("6"), Emoji.of("🎨"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("7"), Emoji.of("🌟"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("8"), Emoji.of("😈"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("9"), Emoji.of("🎭"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("10"), Emoji.of("🎪"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("11"), Emoji.of("🎯"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("12"), Emoji.of("🎲"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("13"), Emoji.of("🎲"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("14"), Emoji.of("🎭"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("15"), Emoji.of("🎪"), IsActive.of(true), IsFlipped.of(false)),
        new Card(CardId.of("16"), Emoji.of("🎯"), IsActive.of(true), IsFlipped.of(false))
      );

      Collections.shuffle(cards);

      table.setCards(cards);
      table.setPlayers(new HashMap<>());
    };
  }

  private Consumer<? extends DomainEvent> addPlayer(Table table) {
    return (AddedPlayer event) -> {
      table.validatePlayersQuantity();

      Player player = new Player(Nickname.of(event.getNickname()), Score.of(0));

      table.getPlayers().put(player.getIdentity().getValue(), player);
    };
  }

  private Consumer<? extends DomainEvent> startGame(Table table) {
    return (StartedGame event) -> {
      table.validatePlayersToStartGame();

      table.setIsActive(IsActive.of(true));
      List<Player> players = new ArrayList<>(table.getPlayers().values());
      Collections.shuffle(players);
      table.setTurn(Turn.of(players.get(0).getIdentity().getValue()));
    };
  }

  private Consumer<? extends DomainEvent> flipCard(Table table) {
    return (FlippedCard event) -> {
      List<Card> cards = table
        .getCards()
        .stream()
        .map(card -> {
          if (!card.getIdentity().getValue().equals(event.getCardId())) {
            return card;
          }

          if (Boolean.TRUE.equals(card.getIsFlipped().getValue())) {
            return card;
          }

          if (Boolean.FALSE.equals(card.getIsActive().getValue())) {
            return card;
          }

          card.setIsFlipped(IsFlipped.of(true));
          return card;
        })
        .toList();
      table.setCards(cards);
    };
  }

  private Consumer<? extends DomainEvent> checkPoint(Table table) {
    return (CheckedPoint event) -> {
      List<Card> flippedCards = table
        .getCards()
        .stream()
        .filter(card -> Boolean.TRUE.equals(card.getIsFlipped().getValue()))
        .toList();

      if (flippedCards.size() < 2) {
        throw new IllegalStateException("There must be at least two flipped cards");
      }

      if (!flippedCards.get(0).getEmoji().getValue().equals(flippedCards.get(1).getEmoji().getValue())) {
        List<Player> players = new ArrayList<>(table.getPlayers().values());
        if (table.getTurn().getPlayerId().equals(players.get(0).getIdentity().getValue())) {
          table.setTurn(Turn.of(players.get(1).getIdentity().getValue()));
        } else {
          table.setTurn(Turn.of(players.get(0).getIdentity().getValue()));
        }
        unFlipCards(table);
        return;
      }

      Player player = table.getPlayers().get(table.getTurn().getPlayerId());
      player.increaseScore();

      List<Card> cards = table
        .getCards()
        .stream()
        .map(card -> {
          if (Boolean.TRUE.equals(card.getIsFlipped().getValue())) {
            card.setIsFlipped(IsFlipped.of(false));
            card.setIsActive(IsActive.of(false));
          }
          return card;
        })
        .toList();
      table.setCards(cards);
    };
  }

  private Consumer<? extends DomainEvent> checkWinner(Table table) {
    return (CheckedWinner event) -> {
      List<Player> players = new ArrayList<>(table.getPlayers().values());

      if (players.get(0).getScore().getValue().equals(players.get(1).getScore().getValue())) {
        throw new IllegalStateException("It's a draw");
      }

      if (players.get(0).getScore().getValue() > players.get(1).getScore().getValue()) {
        table.setWinner(players.get(0));
      } else {
        table.setWinner(players.get(1));
      }

      table.setIsActive(IsActive.of(false));
    };
  }

  private void unFlipCards(Table table) {
    List<Card> cards = table
      .getCards()
      .stream()
      .map(card -> {
        if (Boolean.TRUE.equals(card.getIsFlipped().getValue())) {
          card.setIsFlipped(IsFlipped.of(false));
        }
        return card;
      })
      .toList();
    table.setCards(cards);
  }
}
