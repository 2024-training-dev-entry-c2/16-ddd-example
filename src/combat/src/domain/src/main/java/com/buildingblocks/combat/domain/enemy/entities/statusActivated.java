package com.buildingblocks.combat.domain.enemy.entities;



import com.buildingblocks.combat.domain.character.values.StatusActivateId;
import com.buildingblocks.combat.domain.enemy.values.Impact;
import com.buildingblocks.combat.domain.enemy.values.Name;
import com.buildingblocks.combat.domain.enemy.values.RemainingTurn;
import com.buildingblocks.combat.domain.enemy.values.StatusActivatedId;
import com.buildingblocks.shared.domain.generic.Entity;

public class StatusActivated extends Entity<StatusActivatedId> {
    private Name name;
    private Impact impact;
    private RemainingTurn remainingTurns;

    public StatusActivated(StatusActivatedId identity, Name name, Impact impact, RemainingTurn remainingTurns) {
        super(identity);
        this.name = name;
        this.impact = impact;
        this.remainingTurns = remainingTurns;
    }

    public StatusActivated(Name name, Impact impact, RemainingTurn remainingTurns) {
        super(new StatusActivatedId());
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

    public RemainingTurn getRemainingTurns() {
        return remainingTurns;
    }

    public void setRemainingTurns(RemainingTurn remainingTurns) {
        this.remainingTurns = remainingTurns;
    }

    public void apply() {
        System.out.println("Efecto aplicado: " + name.getValue() + ". Impacto: " + impact.getImpact());
    }

    public void reduceTurn() {
        if (remainingTurns.getValue() > 0) {
            remainingTurns = RemainingTurn.of(remainingTurns.getValue() - 1);
            System.out.println("Turnos restantes para el efecto " + name.getValue() + ": " + remainingTurns);
        }
    }


}
