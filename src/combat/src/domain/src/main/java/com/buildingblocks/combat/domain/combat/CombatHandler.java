package com.buildingblocks.combat.domain.combat;

import com.buildingblocks.combat.domain.combat.entities.CharacterCombat;
import com.buildingblocks.combat.domain.combat.entities.EnemyCombat;
import com.buildingblocks.combat.domain.combat.entities.GameTurn;
import com.buildingblocks.combat.domain.combat.events.CharacterAdded;
import com.buildingblocks.combat.domain.combat.events.CharacterRemoved;
import com.buildingblocks.combat.domain.combat.events.CombatFinished;
import com.buildingblocks.combat.domain.combat.events.CombatInitiated;
import com.buildingblocks.combat.domain.combat.events.EnemyAdded;
import com.buildingblocks.combat.domain.combat.events.EnemyRemoved;
import com.buildingblocks.combat.domain.combat.events.TurnEnded;
import com.buildingblocks.combat.domain.combat.events.TurnStarted;
import com.buildingblocks.combat.domain.combat.values.CharacterCombatID;
import com.buildingblocks.combat.domain.combat.values.CombatStatus;
import com.buildingblocks.combat.domain.combat.values.CurrentTurn;
import com.buildingblocks.combat.domain.combat.values.EnemiesId;
import com.buildingblocks.combat.domain.combat.values.Health;
import com.buildingblocks.combat.domain.combat.values.Initiative;
import com.buildingblocks.combat.domain.combat.values.Name;
import com.buildingblocks.combat.domain.combat.values.OrderInitiative;
import com.buildingblocks.combat.domain.combat.values.ScenarioId;
import com.buildingblocks.combat.domain.combat.values.StatusTurn;
import com.buildingblocks.shared.domain.generic.DomainActionsContainer;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CombatHandler extends DomainActionsContainer {
    public CombatHandler(Combat combat) {
        add(addCharacter(combat));
        add(removeCharacter(combat));
        add(addEnemy(combat));
        add(removeEnemy(combat));
        add(startCombat(combat));
        add(endCombat(combat));
        add(startTurn(combat));
        add(endTurn(combat));
    }

    public Consumer<? extends DomainEvent> addCharacter(Combat combat) {
        return (CharacterAdded event) -> {
            CharacterCombat character = new CharacterCombat(new CharacterCombatID(
                    event.getCharacterId()),
                    Name.of(event.getName()), Health.of(event.getHeal()), Initiative.of(event.getInitiative()), new ArrayList<>());
            combat.getCharacterTeam().add(character);
        };
    }

    public Consumer<? extends DomainEvent> removeCharacter(Combat combat) {
        return (CharacterRemoved event) -> {
            CharacterCombat character = combat.getCharacterTeam().stream()
                    .filter(c -> c.getIdentity().getValue().equals(event.getCharacterId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Character not found."));
            combat.getCharacterTeam().remove(character);
        };
    }


    public Consumer<? extends DomainEvent> addEnemy(Combat combat) {
        return (EnemyAdded event) -> {
            EnemyCombat character = new EnemyCombat(new EnemiesId(
                    event.getEnemyId()),
                    Name.of(event.getName()), Health.of(event.getHeal()), Initiative.of(event.getInitiative()), new ArrayList<>());
            combat.getEnemies().add(character);
        };

    }

    public Consumer<? extends DomainEvent> removeEnemy(Combat combat) {
        return (EnemyRemoved event) -> {
            EnemyCombat enemy = combat.getEnemies().stream()
                    .filter(e -> e.getIdentity().getValue().equals(event.getEnemyId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Enemy not found."));
            combat.getEnemies().remove(enemy);
        };
    }

    public Consumer<? extends DomainEvent> startCombat(Combat combat) {
        return (CombatInitiated event) -> {
            if (combat.getState().equals(CombatStatus.of("InProgress"))) {
                throw new IllegalStateException("Combat is already in progress.");
            }
            combat.setState(CombatStatus.of("InProgress"));
            combat.setScenarioId(ScenarioId.of(event.getScenarioId()));
            combat.setCurrentTurnIndex(CurrentTurn.of(0));
        };
    }

    public Consumer<? extends DomainEvent> endCombat(Combat combat) {
        return (CombatFinished event) -> {
            combat.setState(CombatStatus.of("Ended"));
            boolean charactersWon = combat.getEnemies().stream().allMatch(EnemyCombat::isDefeated);
            boolean charactersLost = combat.getCharacterTeam().stream().allMatch(CharacterCombat::isDefeated);

            if (charactersWon) {
                combat.setState(CombatStatus.of("Game Win"));
            } else if (charactersLost) {
                combat.setState(CombatStatus.of("Game Lost"));

            }
        };
    }

    public Consumer<? extends DomainEvent> startTurn(Combat combat) {
        return (TurnStarted event) -> {
            List<Object> orderedCombatants = new ArrayList<>();
            orderedCombatants.addAll(combat.getEnemies());       // Agregar enemigos
            orderedCombatants.addAll(combat.getCharacterTeam());

            orderedCombatants.sort((a, b) -> {
                int initiativeA = Combat.getInitiative(a).getValue();
                int initiativeB = Combat.getInitiative(b).getValue();
                return Integer.compare(initiativeB, initiativeA); // Orden descendente
            });
            combat.getTurns().add(new GameTurn(OrderInitiative.of(orderedCombatants), StatusTurn.of("Started")));

        };
    }

    public Consumer<? extends DomainEvent> endTurn(Combat combat) {
        return (TurnEnded event) -> {
            int newTurn = combat.getCurrentTurnIndex().getTurnNumber() + 1;
            combat.setCurrentTurnIndex(CurrentTurn.of(newTurn));

        };
    }


}
