package com.zombicide.actions.application.action.findobject;

import com.zombicide.shared.application.Request;

public class FindObjectRequest extends Request {
  private final Integer positionX;
  private final Integer positionY;
  private final Boolean isNoisy;

  public FindObjectRequest(String aggregateId, Integer positionX, Integer positionY, Boolean isNoisy) {
    super(aggregateId);
    this.positionX = positionX;
    this.positionY = positionY;
    this.isNoisy = isNoisy;
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
}
