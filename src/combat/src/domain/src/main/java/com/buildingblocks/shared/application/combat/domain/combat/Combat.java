package com.buildingblocks.shared.application.combat.domain.combat;

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
import com.buildingblocks.shared.application.combat.domain.combat.values.CombatId;
import com.buildingblocks.shared.application.combat.domain.combat.values.CombatStatus;
import com.buildingblocks.shared.application.combat.domain.combat.values.CurrentTurn;
import com.buildingblocks.shared.application.combat.domain.combat.values.Initiative;
import com.buildingblocks.shared.application.combat.domain.combat.values.ScenarioId;

import com.buildingblocks.shared.application.shared.domain.generic.AggregateRoot;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Combat extends AggregateRoot<CombatId> {
    private CombatStatus state= CombatStatus.of("Ready");
    private ScenarioId scenarioId;
    private List<GameTurn> turns;
    private List<EnemyCombat> enemies;
    private List<CharacterCombat> characterTeam;
    private CurrentTurn currentTurnIndex;
    //region Constructor


    private Combat(CombatId identity) {
        super(identity);
        subscribe(new CombatHandler(this));
        enemies= new ArrayList<>();
        characterTeam= new ArrayList<>();
        turns= new ArrayList<>();
        currentTurnIndex = CurrentTurn.of(0);
        state = CombatStatus.of("creating");
    }

    public Combat() {
        super(new CombatId());
        subscribe(new CombatHandler(this));
        enemies = new ArrayList<>();
        characterTeam = new ArrayList<>();
        turns = new ArrayList<>();
        currentTurnIndex = CurrentTurn.of(0);

    }

    //endregion
    //region Getter & Setter


    public CombatStatus getState() {
        return state;
    }

    public void setTurns(List<GameTurn> turns) {
        this.turns = turns;
    }


    public CurrentTurn getCurrentTurnIndex() {
        return currentTurnIndex;
    }

    public void setCurrentTurnIndex(CurrentTurn currentTurnIndex) {
        this.currentTurnIndex = currentTurnIndex;
    }

    public void setState(CombatStatus state) {
        if (state == null) {
            throw new IllegalArgumentException("Combat state cannot be null.");
        }
        this.state = state;
    }


    public void setScenarioId(ScenarioId scenarioId) {
        if (scenarioId == null) {
            throw new IllegalArgumentException("Scenario ID cannot be null.");
        }
        this.scenarioId = scenarioId;
    }

    public List<GameTurn> getTurns() {
        return turns;
    }

    public List<EnemyCombat> getEnemies() {
        return enemies;
    }

    public List<CharacterCombat> getCharacterTeam() {
        return characterTeam;
    }

    //endregion
    // region Domain Event
    public void addEnemy(String id, String name, int healt, int initiative) {
        apply(new EnemyAdded( id,name, healt, initiative));
    }


    public void removeEnemy(String enemyId) {
        apply(new EnemyRemoved( enemyId));
    }

    public void addCharacter(String id, String name, int health, int initiative) {
        apply(new CharacterAdded( id, name, health, initiative));
    }

    public void removeCharacter(String characterId) {
        apply(new CharacterRemoved(characterId));
    }

    public void startCombat() {
        apply(new CombatInitiated(this.getIdentity().getValue(), new Date()));
    }

    public void endCombat() {
        apply(new CombatFinished(this.getIdentity().getValue(), new Date()));
    }

    public void nextTurn() {
        apply(new TurnStarted(this.getIdentity().getValue(), currentTurnIndex.getTurnNumber(), new Date()));
    }

    public void previousTurn() {
        apply(new TurnEnded(this.getIdentity().getValue(), currentTurnIndex.getTurnNumber(), new Date()));
    }

    public static Initiative getInitiative(Object entity) {
        if (entity instanceof EnemyCombat) {
            return ((EnemyCombat) entity).getInitiative();
        } else if (entity instanceof CharacterCombat) {
            return ((CharacterCombat) entity).getInitiative();
        }
        return Initiative.of(0); // Valor por defecto si no es un tipo esperado
    }

    public  static Combat from(final String identity, final List<DomainEvent> events){
        Combat combat = new Combat(CombatId.of(identity));
        events.forEach(combat::apply);
        return combat;
    }
    //endregion
}
