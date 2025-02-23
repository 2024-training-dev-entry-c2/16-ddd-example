package com.zombicide.actions.application.shared.player;

import java.util.List;

public record PlayerResponse(String playerId, String playerName, List<Survivor> survivors) {
  public record Survivor(String id, String name, Integer experience, Integer positionX, Integer positionY, String currentState, List<Skill> skills, List<Weapon> weapons) {
    public record Skill(String id, String name, String description, Integer unlockPoints) { }
    public record Weapon(String id, String name, Integer minScope, Integer maxScope, Integer damage, Integer dice, Integer precision, Boolean isNoisy, Boolean openDoor) { }
  }
}