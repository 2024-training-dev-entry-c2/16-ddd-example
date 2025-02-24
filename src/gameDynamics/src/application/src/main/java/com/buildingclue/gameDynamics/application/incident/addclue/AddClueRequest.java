package com.buildingclue.gameDynamics.application.incident.addclue;

import com.buildingclue.shared.application.Request;

public class AddClueRequest extends Request {

  private final String clue;

  public AddClueRequest(String aggregateId, String clue) {
    super(aggregateId);
    this.clue = clue;
  }

  public String getClue() {
    return clue;
  }
}
