package com.buildingblocks.shared.application.combat.domain.combat.entities;

import com.buildingblocks.shared.application.combat.domain.combat.values.ActionTaken;
import com.buildingblocks.shared.application.combat.domain.combat.values.GameTurnId;
import com.buildingblocks.shared.application.combat.domain.combat.values.OrderInitiative;

import com.buildingblocks.shared.application.combat.domain.combat.values.StatusTurn;
import com.buildingblocks.shared.application.shared.domain.generic.Entity;

import java.util.ArrayList;


public class GameTurn extends Entity<GameTurnId> {
    private OrderInitiative order;
    private ActionTaken action;
    private StatusTurn status;

    public GameTurn( OrderInitiative order, StatusTurn status) {
        super(new GameTurnId());
        this.order = order;
        this.status= status;
        action =  ActionTaken.of(new ArrayList<>());
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

    public StatusTurn getStatus() {
        return status;
    }

    public void setStatus(StatusTurn status) {
        this.status = status;
    }
    //metodos de la entidad

    public void addAction(String newAction) {
        this.action.addAction(newAction);
    }

    public void startTurn() {

        this.status = StatusTurn.of("InProgress");
    }

    public void finalizeTurn() {
        this.action.clearActions();
        this.status = StatusTurn.of("Completed");
    }

}
