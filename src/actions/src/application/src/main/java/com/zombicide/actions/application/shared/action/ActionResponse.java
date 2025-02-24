package com.zombicide.actions.application.shared.action;

import java.util.List;

public record ActionResponse(String actionId, Type type, List<Affected> affects) {
  public record Type(String name, String description, String effect, Integer positionX, Integer positionY, Boolean isNoisy, Integer amountNoise) { }
  public record Affected(String typeAffected, String name, Integer positionX, Integer positionY, Integer damage, String currentState) { }
}