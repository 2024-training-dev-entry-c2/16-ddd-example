package com.buildingblocks.shared.application.combat.domain.combat;

import com.buildingblocks.shared.application.combat.domain.character.values.CharacterId;
import com.buildingblocks.shared.application.combat.domain.combat.entities.CharacterCombat;
import com.buildingblocks.shared.application.combat.domain.combat.entities.EnemyCombat;
import com.buildingblocks.shared.application.combat.domain.combat.entities.GameTurn;
import com.buildingblocks.shared.application.combat.domain.combat.events.CharacterAdded;
import com.buildingblocks.shared.application.combat.domain.combat.events.CharacterRemoved;
import com.buildingblocks.shared.application.combat.domain.combat.events.CombatFinished;
import com.buildingblocks.shared.application.combat.domain.combat.events.CombatInitiated;
import com.buildingblocks.shared.application.combat.domain.combat.events.EnemyAdded;
import com.buildingblocks.shared.application.combat.domain.combat.events.EnemyRemoved;
import com.buildingblocks.shared.application.combat.domain.combat.events.TurnEnded;
import com.buildingblocks.shared.application.combat.domain.combat.events.TurnStarted;
import com.buildingblocks.shared.application.combat.domain.combat.values.CharacterCombatID;
import com.buildingblocks.shared.application.combat.domain.combat.values.CombatStatus;
import com.buildingblocks.shared.application.combat.domain.combat.values.CurrentTurn;
import com.buildingblocks.shared.application.combat.domain.combat.values.EnemiesId;
import com.buildingblocks.shared.application.combat.domain.combat.values.Health;
import com.buildingblocks.shared.application.combat.domain.combat.values.Initiative;
import com.buildingblocks.shared.application.combat.domain.combat.values.Name;
import com.buildingblocks.shared.application.combat.domain.combat.values.OrderInitiative;
import com.buildingblocks.shared.application.combat.domain.combat.values.ScenarioId;
import com.buildingblocks.shared.application.combat.domain.combat.values.StatusTurn;
import com.buildingblocks.shared.application.shared.domain.generic.DomainActionsContainer;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class CombatHandler extends DomainActionsContainer {
    public CombatHandler(Combat combat) {
        add(addCharacter(combat));
        add(removeCharacter(combat));
        add(addEnemy(combat));
        add(removeEnemy(combat));
        add(startCombat(combat));
        add(endCombat(combat));
        add(nextTurn(combat));
        add(endTurn(combat));
    }

    public Consumer<? extends DomainEvent> addCharacter(Combat combat) {
        return (CharacterAdded event) -> {
            if (event.getId() == null) {
                CharacterCombat character = new CharacterCombat(
                        Name.of(event.getName()),
                        Health.of(event.getHeal()),
                        Initiative.of(event.getInitiative()),
                        new ArrayList<>()
                );
                System.out.println(character.getName().getValue());
                combat.getCharacterTeam().add(character);
            }
            else {
                CharacterCombat character = new CharacterCombat(
                        CharacterCombatID.of(event.getId()),
                        Name.of(event.getName()),
                        Health.of(event.getHeal()),
                        Initiative.of(event.getInitiative()),
                        new ArrayList<>()
                );
                System.out.println(character.getName().getValue());
                combat.getCharacterTeam().add(character);
            }
        };
    }

    public Consumer<? extends DomainEvent> removeCharacter(Combat combat) {
        return (CharacterRemoved event) -> {
            List<CharacterCombat> enemies = combat.getCharacterTeam();
            int index = IntStream.range(0, enemies.size())
                    .filter(i -> enemies.get(i).getIdentity().getValue().equals(event.getCharacterId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Enemy not found."));

            combat.getCharacterTeam().remove(index);
        };
    }


    public Consumer<? extends DomainEvent> addEnemy(Combat combat) {
        return (EnemyAdded event) -> {
            if (event.getId() == null) {
                EnemyCombat enemy = new EnemyCombat(
                        Name.of(event.getName()),
                        Health.of(event.getHeal()),
                        Initiative.of(event.getInitiative()),
                        new ArrayList<>()
                );
                combat.getEnemies().add(enemy);
            }
            else {
                EnemyCombat enemy = new EnemyCombat(
                        EnemiesId.of(event.getId()),
                        Name.of(event.getName()),
                        Health.of(event.getHeal()),
                        Initiative.of(event.getInitiative()),
                        new ArrayList<>()
                );
                combat.getEnemies().add(enemy);
            }
            // Agregar el enemigo a la lista
        };
    }
    public Consumer<? extends DomainEvent> addEnemyWithId(Combat combat) {
        return (EnemyAdded event) -> {
            EnemyCombat enemy = new EnemyCombat(
                    EnemiesId.of(event.getId()),
                    Name.of(event.getName()),
                    Health.of(event.getHeal()),
                    Initiative.of(event.getInitiative()),
                    new ArrayList<>()
            );
            combat.getEnemies().add(enemy);
        };
    }


    public Consumer<? extends DomainEvent> removeEnemy(Combat combat) {
        return (EnemyRemoved event) -> {
            List<EnemyCombat> enemies = combat.getEnemies();
            System.out.println(combat.getEnemies().size());
            int index = IntStream.range(0, enemies.size())
                    .filter(i -> enemies.get(i).getIdentity().getValue().equals(event.getEnemyId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Enemy not found."));
            enemies.remove(index);
        };
    }

    public Consumer<? extends DomainEvent> startCombat(Combat combat) {
        return (CombatInitiated event) -> {

            combat.setState(CombatStatus.of("InProgress"));
            combat.setScenarioId(ScenarioId.of(event.getScenarioId()));
            combat.setCurrentTurnIndex(CurrentTurn.of(0));
        };
    }

    public Consumer<? extends DomainEvent> endCombat(Combat combat) {
        return (CombatFinished event) -> {
            boolean charactersWon = combat.getEnemies().stream().allMatch(EnemyCombat::isDefeated);
            boolean charactersLost = combat.getCharacterTeam().stream().allMatch(CharacterCombat::isDefeated);

            if (charactersWon) {
                combat.setState(CombatStatus.of("Game Win"));
            } else if (charactersLost) {
                combat.setState(CombatStatus.of("Game Lost"));

            }
        };
    }

    public Consumer<? extends DomainEvent> nextTurn(Combat combat) {
        return (TurnStarted event) -> {
            System.out.println("TurnStarted event received");

            List<Object> orderedCombatants = new ArrayList<>();
            orderedCombatants.addAll(combat.getEnemies());
            orderedCombatants.addAll(combat.getCharacterTeam());

            orderedCombatants.sort((a, b) -> {
                int initiativeA = Combat.getInitiative(a).getValue();
                int initiativeB = Combat.getInitiative(b).getValue();
                return Integer.compare(initiativeB, initiativeA);           });

            GameTurn newTurn = new GameTurn(OrderInitiative.of(orderedCombatants), StatusTurn.of("Started"));

            combat.getTurns().add(newTurn);
            System.out.println("New turn added: " + newTurn);
        };
    }

    public Consumer<? extends DomainEvent> endTurn(Combat combat) {
        return (TurnEnded event) -> {
            int newTurn = combat.getCurrentTurnIndex().getTurnNumber() + 1;
            combat.setCurrentTurnIndex(CurrentTurn.of(newTurn));

        };
    }


}
