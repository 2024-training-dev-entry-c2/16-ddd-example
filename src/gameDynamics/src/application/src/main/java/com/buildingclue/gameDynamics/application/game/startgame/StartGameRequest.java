package com.buildingclue.gameDynamics.application.game.startgame;

import com.buildingclue.shared.application.Request;

public class StartGameRequest extends Request {
  protected StartGameRequest(String aggregateId) {
    super(aggregateId);
  }
}
