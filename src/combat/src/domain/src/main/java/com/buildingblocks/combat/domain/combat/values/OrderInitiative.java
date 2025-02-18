package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

import java.util.List;

public class OrderInitiative implements IValueObject {
    private final List<Object> participants;

    private OrderInitiative(List<Object> participants) {
        this.participants = participants;
    }
    public  static  OrderInitiative of(List<Object> participants){
        return new OrderInitiative(participants);
    }

    @Override
    public void validate() {
        if (participants == null || participants.isEmpty()) {
            throw new IllegalArgumentException("the participant cant be null or empty.");
        }
    }

    public List<Object> getParticipants() {
        return participants;
    }
}
