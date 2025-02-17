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
import com.buildingblocks.combat.domain.combat.values.CombatId;
import com.buildingblocks.combat.domain.combat.values.CombatStatus;
import com.buildingblocks.combat.domain.combat.values.EnemiesId;
import com.buildingblocks.combat.domain.combat.values.Health;
import com.buildingblocks.combat.domain.combat.values.Initiative;
import com.buildingblocks.combat.domain.combat.values.ScenarioId;
import com.buildingblocks.shared.domain.generic.AggregateRoot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Combat extends AggregateRoot<CombatId> {
    private CombatStatus state;
    private ScenarioId scenarioId;
    private List<GameTurn> turns;
    private List<EnemyCombat> enemies;
    private List<CharacterCombat> characterTeam;
    private int currentTurnIndex;
    //region Constructor

    public Combat(CombatId identity, CombatStatus state, ScenarioId scenarioId, List<GameTurn> turns, List<EnemyCombat> enemies, List<CharacterCombat> team) {
        super(identity);
        this.state = state;
        this.scenarioId = scenarioId;
        this.turns = turns;
        this.enemies = enemies;
        this.characterTeam = team;
    }

    public Combat(CombatStatus state, ScenarioId scenarioId, List<GameTurn> turns, List<EnemyCombat> enemies, List<CharacterCombat> team) {
        super(new CombatId());
        this.state = state;
        this.scenarioId = scenarioId;
        this.turns = turns;
        this.enemies = enemies;
        this.characterTeam = team;
    }

    //endregion
    //region Getter & Setter


    public CombatStatus getState() {
        return state;
    }

    public void setState(CombatStatus state) {
        if (state == null) {
            throw new IllegalArgumentException("Combat state cannot be null.");
        }
        this.state = state;
    }

    public ScenarioId getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(ScenarioId scenarioId) {
        if (scenarioId == null) {
            throw new IllegalArgumentException("Scenario ID cannot be null.");
        }
        this.scenarioId = scenarioId;
    }

    public List<GameTurn> getTurns() {
        return new ArrayList<>(turns); // Devuelve una copia para mantener la inmutabilidad
    }

    public List<EnemyCombat> getEnemies() {
        return new ArrayList<>(enemies); // Devuelve una copia para mantener la inmutabilidad
    }

    public List<CharacterCombat> getCharacterTeam() {
        return new ArrayList<>(characterTeam); // Devuelve una copia para mantener la inmutabilidad
    }

    //endregion
    // region Domain Event
    public void addEnemy(String combatId, String enemyId, String name, int healt, int initiative) {
        EnemyCombat enemy = new EnemyCombat(new EnemiesId(enemyId), name, Health.of(healt), Initiative.of(initiative), new ArrayList<>());
        this.enemies.add(enemy);
        apply(new EnemyAdded(combatId, enemyId));
    }
    public void removeEnemy(String combatId, String enemyId) {
        EnemyCombat enemy = enemies.stream()
                .filter(e -> e.getIdentity().getValue().equals(enemyId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Enemy not found."));
        this.enemies.remove(enemy);
        apply(new EnemyRemoved(combatId, enemyId));
    }
    public void addCharacter(String combatId, String characterId, String name, int healt, int initiative) {
        CharacterCombat character = new CharacterCombat(new CharacterCombatID(characterId), name, Health.of(healt), Initiative.of(initiative), new ArrayList<>());
        this.characterTeam.add(character);
        apply(new CharacterAdded(combatId, characterId));
    }
    public void removeCharacter(String combatId, String characterId) {
        CharacterCombat character = characterTeam.stream()
                .filter(c -> c.getIdentity().getValue().equals(characterId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Character not found."));
        this.characterTeam.remove(character);
        apply(new CharacterRemoved(combatId, characterId));
    }
    public void startCombat() {
        if (this.state.equals(CombatStatus.of("InProgress"))) {
            throw new IllegalStateException("Combat is already in progress.");
        }
        this.state = CombatStatus.of("InProgress");
        this.currentTurnIndex = 0;
        apply(new CombatInitiated(this.getIdentity().getValue(),new Date()));
        apply(new TurnStarted(this.getIdentity().getValue(), currentTurnIndex, new Date()));
    }
    public void endCombat() {
        this.state = CombatStatus.of("Ended");
        apply(new CombatFinished(this.getIdentity().getValue(),new Date()));
        boolean charactersWon = enemies.stream().allMatch(EnemyCombat::isDefeated);
        boolean charactersLost = characterTeam.stream().allMatch(CharacterCombat::isDefeated);

        if (charactersWon) {
           this.state=  CombatStatus.of("Game Win");
        } else if (charactersLost) {
            this.state=  CombatStatus.of("Game Lost");

        }
    }
    public void nextTurn() {
        if (currentTurnIndex < turns.size() - 1) {
            currentTurnIndex++;
            apply(new TurnStarted(this.getIdentity().getValue(), currentTurnIndex, new Date()));
            // Obtener el combatante actual


        } else {
            endCombat();
        }
    }
    public void previousTurn() {
        if (currentTurnIndex > 0) {
            currentTurnIndex--;
            apply(new TurnEnded(this.getIdentity().getValue(), currentTurnIndex, new Date()));
        }
    }
    //endregion
}
