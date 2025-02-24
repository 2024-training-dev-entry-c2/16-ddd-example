package com.buildingclue.gameDynamics.application.game.endgame;

import com.buildingclue.shared.application.Request;

public class EndGameRequest extends Request {

  private final String winnerPlayerId;
  private final Boolean wasCaseSolved;

  public EndGameRequest(String aggregateId, String winnerPlayerId, Boolean wasCaseSolved) {
    super(aggregateId);
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
