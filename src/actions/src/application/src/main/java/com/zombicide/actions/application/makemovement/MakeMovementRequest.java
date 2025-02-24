package com.zombicide.actions.application.makemovement;

import com.zombicide.shared.application.Request;

public class MakeMovementRequest extends Request {
  private final Integer positionX;
  private final Integer positionY;
  private final Boolean isNoisy;
  private final String aggregatePlayerId;
  private final String survivorId;

  public MakeMovementRequest(String aggregateId, Integer positionX, Integer positionY, Boolean isNoisy, String aggregatePlayerId, String survivorId) {
    super(aggregateId);
    this.positionX = positionX;
    this.positionY = positionY;
    this.isNoisy = isNoisy;
    this.aggregatePlayerId = aggregatePlayerId;
    this.survivorId = survivorId;
  }

  public Integer getPositionX() {
    return positionX;
  }

  public Integer getPositionY() {
    return positionY;
  }

  public Boolean getNoisy() {
    return isNoisy;
  }

  public String getAggregatePlayerId() {
    return aggregatePlayerId;
  }

  public String getSurvivorId() {
    return survivorId;
  }
}
