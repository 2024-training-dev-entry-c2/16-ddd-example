package com.buildingblocks.shared.application.shared.combat;

import com.buildingblocks.shared.application.combat.domain.combat.Combat;
import com.buildingblocks.shared.application.combat.domain.combat.entities.Participants;

import java.util.stream.Collectors;

public class CombatMapper {
    public static CombatResponse mapToResponse(Combat combat ) {
        return new CombatResponse(
                combat.getIdentity().getValue(),
                combat.getState().getValue(),
                combat.getCharacterTeam().stream()
                        .map(character -> new CombatResponse.CharacterDetails(
                                character.getIdentity().getValue(),
                                character.getName().getValue(),
                                character.getHealth().getValue(),
                                character.getInitiative().getValue()
                        ))
                        .collect(Collectors.toList()),
                combat.getEnemies().stream()
                        .map(enemy -> new CombatResponse.EnemyDetails(
                                enemy.getIdentity().getValue(),
                                enemy.getName().getValue(),
                                enemy.getHealth().getValue(),
                                enemy.getInitiative().getValue()
                        ))
                        .collect(Collectors.toList()),
                combat.getTurns().stream()
                        .map(turn -> new CombatResponse.TurnDetails(
                                turn.getIdentity().getValue(),
                                turn.getOrder().getParticipants().stream().map(Participants::getNameValue).collect(Collectors.joining(",")),
                                turn.getStatus().getValue()
                        ))
                        .collect(Collectors.toList())


        );
    }
}
