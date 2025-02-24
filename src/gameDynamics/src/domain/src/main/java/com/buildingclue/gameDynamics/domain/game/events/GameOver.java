package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class GameOver extends DomainEvent {
  private final String winnerPlayerId;
  private final Boolean wasCaseSolved;

  public GameOver(String winnerPlayerId, Boolean wasCaseSolved) {
    super(EventsEnum.GAME_OVER.name());
    this.winnerPlayerId = winnerPlayerId;
    this.wasCaseSolved = wasCaseSolved;
  }

  public String getWinnerPlayerId() {
    return winnerPlayerId;
  }

  public Boolean getWasCaseSolved() {
    return wasCaseSolved;
  }
}
