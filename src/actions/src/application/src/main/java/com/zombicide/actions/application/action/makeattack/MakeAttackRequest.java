package com.zombicide.actions.application.action.makeattack;

import com.zombicide.shared.application.Request;

import java.util.List;

public class MakeAttackRequest extends Request {
  private final String nameAction;
  private final String description;
  private final String effect;
  private final Integer positionX;
  private final Integer positionY;
  private final Boolean isNoisy;
  private final List<Affected> affects;

  public MakeAttackRequest( String nameAction, String description, String effect, Integer positionX, Integer positionY, Boolean isNoisy, List<Affected> affects) {
    super(null);
    this.nameAction = nameAction;
    this.description = description;
    this.effect = effect;
    this.positionX = positionX;
    this.positionY = positionY;
    this.isNoisy = isNoisy;
    this.affects = affects;
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

  public List<Affected> getAffects() {
    return affects;
  }

  public record Affected(String affectedId, String typeAffected, String name, Integer positionX, Integer positionY, Integer damage, String currentState) { }
}
