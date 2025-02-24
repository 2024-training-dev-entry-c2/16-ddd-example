package com.zombicide.actions.application.action.makeattack;

import com.zombicide.shared.application.Request;

import java.util.List;

public class MakeAttackRequest extends Request {
  private final Integer positionX;
  private final Integer positionY;
  private final Boolean isNoisy;
  private final List<Affected> affects;

  public MakeAttackRequest(String aggregateId, Integer positionX, Integer positionY, Boolean isNoisy, List<Affected> affects) {
    super(aggregateId);
    this.positionX = positionX;
    this.positionY = positionY;
    this.isNoisy = isNoisy;
    this.affects = affects;
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

  public List<Affected> getAffects() {
    return affects;
  }

  public record Affected(String affectedId, String typeAffected, String name, Integer positionX, Integer positionY, Integer damage, String currentState) { }
}
