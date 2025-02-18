package com.buildingblocks.combat.domain.combat;

import com.buildingblocks.combat.domain.character.values.DeckOfCardsId;
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
import com.buildingblocks.combat.domain.combat.values.CurrentTurn;
import com.buildingblocks.combat.domain.combat.values.EnemiesId;
import com.buildingblocks.combat.domain.combat.values.Health;
import com.buildingblocks.combat.domain.combat.values.Initiative;
import com.buildingblocks.combat.domain.combat.values.Name;
import com.buildingblocks.combat.domain.combat.values.ScenarioId;
import com.buildingblocks.combat.domain.deckOfCards.DeckOfCards;
import com.buildingblocks.combat.domain.deckOfCards.DeckOfCardsHandler;
import com.buildingblocks.shared.domain.generic.AggregateRoot;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Combat extends AggregateRoot<CombatId> {
    private CombatStatus state;
    private ScenarioId scenarioId;
    private List<GameTurn> turns;
    private List<EnemyCombat> enemies;
    private List<CharacterCombat> characterTeam;
    private CurrentTurn currentTurnIndex;
    //region Constructor


    private Combat(CombatId identity) {
        super(identity);
        subscribe(new CombatHandler(this));
    }

    public Combat() {
        super(new CombatId());
        subscribe(new CombatHandler(this));
    }

    private Combat(CombatId identity, CombatStatus state, ScenarioId scenarioId, List<GameTurn> turns, List<EnemyCombat> enemies, List<CharacterCombat> team) {
        super(identity);
        this.state = state;
        this.scenarioId = scenarioId;
        this.turns = turns;
        this.enemies = enemies;
        this.characterTeam = team;
        subscribe(new CombatHandler(this));
    }

    public Combat(CombatStatus state, ScenarioId scenarioId, List<GameTurn> turns, List<EnemyCombat> enemies, List<CharacterCombat> team) {
        super(new CombatId());
        this.state = state;
        this.scenarioId = scenarioId;
        this.turns = turns;
        this.enemies = enemies;
        this.characterTeam = team;
        subscribe(new CombatHandler(this));
    }

    //endregion
    //region Getter & Setter


    public void setTurns(List<GameTurn> turns) {
        this.turns = turns;
    }

    public void setEnemies(List<EnemyCombat> enemies) {
        this.enemies = enemies;
    }

    public void setCharacterTeam(List<CharacterCombat> characterTeam) {
        this.characterTeam = characterTeam;
    }

    public CurrentTurn getCurrentTurnIndex() {
        return currentTurnIndex;
    }

    public void setCurrentTurnIndex(CurrentTurn currentTurnIndex) {
        this.currentTurnIndex = currentTurnIndex;
    }

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
        apply(new EnemyAdded(combatId, enemyId, name, healt, initiative));
    }

    public void removeEnemy(String combatId, String enemyId) {
        apply(new EnemyRemoved(combatId, enemyId));
    }

    public void addCharacter(String combatId, String characterId, String name, int health, int initiative) {
        apply(new CharacterAdded(combatId, characterId, name, health, initiative));
    }

    public void removeCharacter(String combatId, String characterId) {
        apply(new CharacterRemoved(combatId, characterId));
    }

    public void startCombat() {
        apply(new CombatInitiated(this.getIdentity().getValue(), new Date()));
        apply(new TurnStarted(this.getIdentity().getValue(), currentTurnIndex.getTurnNumber(), new Date()));
    }

    public void endCombat() {
        apply(new CombatFinished(this.getIdentity().getValue(), new Date()));
    }

    public void nextTurn() {
        if (currentTurnIndex.getTurnNumber() < turns.size() - 1) {
            apply(new TurnStarted(this.getIdentity().getValue(), currentTurnIndex.getTurnNumber(), new Date()));
        } else {
            endCombat();
        }
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
