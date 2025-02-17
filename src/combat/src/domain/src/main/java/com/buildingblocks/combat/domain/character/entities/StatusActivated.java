package com.buildingblocks.combat.domain.character.entities;

import com.buildingblocks.combat.domain.character.values.Impact;
import com.buildingblocks.combat.domain.character.values.Name;
import com.buildingblocks.combat.domain.character.values.StatusActivateId;
import com.buildingblocks.shared.domain.generic.Entity;

public class StatusActivated extends Entity<StatusActivateId> {
    private Name name;
    private Impact impact;
    private int remainingTurns;

    public StatusActivated(StatusActivateId identity, Name name, Impact impact, int remainingTurns) {
        super(identity);
        this.name = name;
        this.impact = impact;
        this.remainingTurns = remainingTurns;
    }

    public StatusActivated(Name name, Impact impact, int remainingTurns) {
        super(new StatusActivateId());
        this.name = name;
        this.impact = impact;
        this.remainingTurns = remainingTurns;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Impact getImpact() {
        return impact;
    }

    public void setImpact(Impact impact) {
        this.impact = impact;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }

    public void setRemainingTurns(int remainingTurns) {
        this.remainingTurns = remainingTurns;
    }
    public void apply() {
        System.out.println("Efecto aplicado: " + name.getValue() + ". Impacto: " + impact.getValue());
    }

    public void reduceTurn() {
        if (remainingTurns > 0) {
            remainingTurns--;
            System.out.println("Turnos restantes para el efecto " + name.getValue() + ": " + remainingTurns);
        }
    }



}
