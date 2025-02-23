package com.zombicide.actions.application.shared.action;

import java.util.List;

public record ActionResponse(String actionId, List<Affected> affects) {
  public record Affected(String typeAffected, String name, Integer positionX, Integer positionY, Integer damage, String currentState) { }
}