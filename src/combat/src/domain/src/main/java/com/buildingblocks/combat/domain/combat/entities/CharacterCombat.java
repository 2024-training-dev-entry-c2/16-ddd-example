package com.buildingblocks.combat.domain.combat.entities;

import com.buildingblocks.combat.domain.combat.values.CharacterCombatID;
import com.buildingblocks.combat.domain.combat.values.GameTurnId;
import com.buildingblocks.combat.domain.combat.values.Health;
import com.buildingblocks.combat.domain.combat.values.Initiative;
import com.buildingblocks.shared.domain.generic.Entity;

import java.util.ArrayList;
import java.util.List;

public class CharacterCombat extends Entity<CharacterCombatID> {
    private String name;
    private Health health;
    private Initiative initiative;
    private List<String> conditions;
    private boolean isDefeated;

    public CharacterCombat(String name, Health health, Initiative initiative, List<String> conditions) {
        super(new CharacterCombatID());
        this.name = name;
        this.health = health;
        this.initiative = initiative;
        this.conditions = conditions;
    }

    public CharacterCombat(CharacterCombatID identity, String name, Health health, Initiative initiative, List<String> conditions) {
        super(identity);
        this.name = name;
        this.health = health;
        this.initiative = initiative;
        this.conditions = conditions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
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

    public List<String> getConditions() {
        return new ArrayList<>(conditions); // Devuelve una copia para mantener la inmutabilidad
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative.");
        }
        int newHealth = this.health.getValue() - damage;
        this.health = Health.of(Math.max(newHealth, 0)); // La salud no puede ser negativa
        if (this.health.getValue() == 0) {
            this.isDefeated = true;
        }
    }

    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Heal amount cannot be negative.");
        }
        int newHealth = this.health.getValue() + amount;
        this.health = Health.of(newHealth);
    }

    public void applyCondition(String condition) {
        if (condition == null || condition.trim().isEmpty()) {
            throw new IllegalArgumentException("Condition cannot be null or empty.");
        }
        this.conditions.add(condition);
    }

    public void removeCondition(String condition) {
        this.conditions.remove(condition);
    }

    public void clearConditions() {
        this.conditions.clear();
    }

    public boolean isAlive() {
        return this.health.getValue() > 0;
    }

    public boolean isDefeated() {
        return isDefeated;
    }

    public void setDefeated(boolean defeated) {
        isDefeated = defeated;
    }
}
