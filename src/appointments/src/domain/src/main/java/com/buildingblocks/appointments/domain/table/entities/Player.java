package com.buildingblocks.appointments.domain.table.entities;

import com.buildingblocks.appointments.domain.table.values.Nickname;
import com.buildingblocks.appointments.domain.table.values.PlayerId;
import com.buildingblocks.appointments.domain.table.values.Score;
import com.buildingblocks.shared.domain.generic.Entity;

public class Player extends Entity<PlayerId> {
  private final Integer CONSTANT_TO_INCREASE_SCORE = 1;
  private Nickname nickname;
  private Score score;

  public Player(Nickname nickname, Score score) {
    super(new PlayerId());
    this.nickname = nickname;
    this.score = score;
  }

  public Player(PlayerId identity, Nickname nickname, Score score) {
    super(identity);
    this.nickname = nickname;
    this.score = score;
  }

  public Nickname getNickname() {
    return nickname;
  }

  public Score getScore() {
    return score;
  }

  public void setNickname(Nickname nickname) {
    this.nickname = nickname;
  }

  public void setScore(Score score) {
    this.score = score;
  }

  public void increaseScore() {
    this.score = Score.of(this.score.getValue() + CONSTANT_TO_INCREASE_SCORE);
  }
}
