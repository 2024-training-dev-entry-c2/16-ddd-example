package com.buildingblocks.combat.domain.combat.entities;

import com.buildingblocks.combat.domain.combat.values.ActionTaken;
import com.buildingblocks.combat.domain.combat.values.GameTurnId;
import com.buildingblocks.combat.domain.combat.values.OrderInitiative;

import com.buildingblocks.shared.domain.generic.Entity;



public class GameTurn extends Entity<GameTurnId> {
    private OrderInitiative order;
    private ActionTaken action;
    private String status;

    public GameTurn(GameTurnId identity, OrderInitiative order, ActionTaken action) {
        super(identity);
        this.order = order;
        this.action = action;
    }
    public GameTurn(OrderInitiative order, ActionTaken action) {
        super(new GameTurnId());
        this.order = order;
        this.action = action;
    }

    public OrderInitiative getOrder() {
        return order;
    }

    public void setOrder(OrderInitiative order) {
        this.order = order;
    }

    public ActionTaken getAction() {
        return action;
    }

    public void setAction(ActionTaken action) {
        this.action = action;
    }
    //metodos de la entidad

    public void addAction(String newAction) {
        this.action.addAction(newAction);
    }

    public void startTurn() {
        if (!this.status.equals("Pending")) {
            throw new IllegalStateException("Turn cannot be started. Current status: " + this.status);
        }
        this.status = "InProgress";
    }

    public void finalizeTurn() {
        if (!this.status.equals("InProgress")) {
            throw new IllegalStateException("Turn cannot be finalized. Current status: " + this.status);
        }
        this.action.clearActions();
        this.status = "Completed";
    }
}
