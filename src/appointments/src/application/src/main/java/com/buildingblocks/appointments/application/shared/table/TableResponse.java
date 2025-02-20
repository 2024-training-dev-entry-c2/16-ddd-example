package com.buildingblocks.appointments.application.shared.table;

import java.util.List;

public class TableResponse {
  private final String boardId;
  private final List<Player> players;
  private final String turn;
  private final String boardName;
  private final List<Card> cards;
  private final Boolean isActive;

  public TableResponse(String boardId, List<Player> players, String turn, String boardName, List<Card> cards, Boolean isActive) {
    this.boardId = boardId;
    this.players = players;
    this.turn = turn;
    this.boardName = boardName;
    this.cards = cards;
    this.isActive = isActive;
  }

  public String getBoardId() {
    return boardId;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public String getTurn() {
    return turn;
  }

  public String getBoardName() {
    return boardName;
  }

  public List<Card> getCards() {
    return cards;
  }

  public Boolean getActive() {
    return isActive;
  }

  public static class Player {
    private final String id;
    private final String nickname;
    private final Integer score;

    public Player(String id, String nickname, Integer score) {
      this.id = id;
      this.nickname = nickname;
      this.score = score;
    }

    public String getNickname() {
      return nickname;
    }

    public Integer getScore() {
      return score;
    }

    public String getId() {
      return id;
    }
  }

  public static class Card {
    private final String id;
    private final String emoji;
    private final Boolean isFlipped;
    private final Boolean isActive;

    public Card(String id, String emoji, Boolean isFlipped, Boolean isActive) {
      this.id = id;
      this.emoji = emoji;
      this.isFlipped = isFlipped;
      this.isActive = isActive;
    }

    public String getId() {
      return id;
    }

    public String getEmoji() {
      return emoji;
    }

    public Boolean getFlipped() {
      return isFlipped;
    }

    public Boolean getActive() {
      return isActive;
    }
  }
}
