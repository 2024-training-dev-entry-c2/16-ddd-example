package com.zombicide.actions.application.shared.player;

import com.zombicide.actions.domain.player.Player;

public class PlayerMapper {
  public static PlayerResponse mapToPlayer(Player player) {
    return new PlayerResponse(
      player.getIdentity().getValue(),
      player.getName().getValue(),
      player.getSurvivors().stream().map(survivor -> new PlayerResponse.Survivor(
        survivor.getIdentity().getValue(),
        survivor.getName().getValue(),
        survivor.getExperience().getValue(),
        survivor.getPosition().getX(),
        survivor.getPosition().getY(),
        survivor.getCurrentState().getValue(),
        survivor.getSkills().stream().map(skill -> new PlayerResponse.Survivor.Skill(
          skill.getIdentity().getValue(),
          skill.getName().getValue(),
          skill.getDescription().getValue(),
          skill.getUnlockPoints().getValue()
        )).toList(),
        survivor.getWeapons().stream().map(weapon -> new PlayerResponse.Survivor.Weapon(
          weapon.getIdentity().getValue(),
          weapon.getName().getValue(),
          weapon.getScope().getMin(),
          weapon.getScope().getMax(),
          weapon.getDamage().getValue(),
          weapon.getDice().getValue(),
          weapon.getPrecision().getValue(),
          weapon.getIsNoisy().getValue(),
          weapon.getOpenDoor().getValue()
        )).toList()
      )).toList()
    );
  }
}
