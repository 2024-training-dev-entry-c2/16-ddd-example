package com.zombicide.actions.application.makemovement;

import com.zombicide.shared.application.Request;

public class MakeMovementRequest extends Request {
  private final String nameAction;
  private final String description;
  private final String effect;
  private final Integer positionX;
  private final Integer positionY;
  private final Boolean isNoisy;
  private final String aggregatePlayerId;
  private final String survivorId;

  public MakeMovementRequest(String nameAction, String description, String effect, Integer positionX, Integer positionY, Boolean isNoisy, String aggregatePlayerId, String survivorId) {
    super(null);
    this.nameAction = nameAction;
    this.description = description;
    this.effect = effect;
    this.positionX = positionX;
    this.positionY = positionY;
    this.isNoisy = isNoisy;
    this.aggregatePlayerId = aggregatePlayerId;
    this.survivorId = survivorId;
  }

  public String getNameAction() {
    return nameAction;
  }

  public String getDescription() {
    return description;
  }

  public String getEffect() {
    return effect;
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
