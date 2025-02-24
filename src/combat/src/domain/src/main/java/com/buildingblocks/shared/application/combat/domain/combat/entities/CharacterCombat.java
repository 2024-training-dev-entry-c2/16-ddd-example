package com.buildingblocks.shared.application.combat.domain.combat.entities;

import com.buildingblocks.shared.application.combat.domain.combat.values.CharacterCombatID;
import com.buildingblocks.shared.application.combat.domain.combat.values.Condition;
import com.buildingblocks.shared.application.combat.domain.combat.values.Health;
import com.buildingblocks.shared.application.combat.domain.combat.values.Initiative;
import com.buildingblocks.shared.application.combat.domain.combat.values.Name;
import com.buildingblocks.shared.application.combat.domain.combat.values.IsDefeated;
import com.buildingblocks.shared.application.shared.domain.generic.Entity;

import java.util.ArrayList;
import java.util.List;

public class CharacterCombat extends Entity<CharacterCombatID> {
    private Name name;
    private Health health;
    private Initiative initiative;
    private List<Condition> conditions;
    private IsDefeated isDefeated;

    public CharacterCombat(Name name, Health health, Initiative initiative, List<Condition> conditions) {
        super(new CharacterCombatID());
        this.name = name;
        this.health = health;
        this.initiative = initiative;
        this.conditions = conditions;
    }

    public CharacterCombat(CharacterCombatID identity, Name name, Health health, Initiative initiative, List<Condition> conditions) {
        super(identity);
        this.name = name;
        this.health = health;
        this.initiative = initiative;
        this.conditions = conditions;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        if (name == null || name.getValue().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        if (health == null) {
            throw new IllegalArgumentException("Health cannot be null.");
        }
        this.health = health;
    }

    public Initiative getInitiative() {
        return initiative;
    }

    public void setInitiative(Initiative initiative) {
        if (initiative == null) {
            throw new IllegalArgumentException("Initiative cannot be null.");
        }
        this.initiative = initiative;
    }

    public List<Condition> getConditions() {
        return new ArrayList<>(conditions); // Devuelve una copia para mantener la inmutabilidad
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative.");
        }
        int newHealth = this.health.getValue() - damage;
        this.health = Health.of(Math.max(newHealth, 0)); // La salud no puede ser negativa
        if (this.health.getValue() == 0) {
            this.isDefeated = IsDefeated.defeated() ;
        }
    }

    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Heal amount cannot be negative.");
        }
        int newHealth = this.health.getValue() + amount;
        this.health = Health.of(newHealth);
    }

    public void applyCondition(Condition condition) {
        if (condition == null ) {
            throw new IllegalArgumentException("Condition cannot be null or empty.");
        }
        this.conditions.add(condition);
    }

    public void removeCondition(Condition condition) {
        this.conditions.remove(condition);
    }

    public void clearConditions() {
        this.conditions.clear();
    }

    public boolean isAlive() {
        return this.health.getValue() > 0;
    }

    public boolean isDefeated() {
        return isDefeated.getValue();
    }

    public void setDefeated(IsDefeated defeated) {
        isDefeated = defeated;
    }
}
