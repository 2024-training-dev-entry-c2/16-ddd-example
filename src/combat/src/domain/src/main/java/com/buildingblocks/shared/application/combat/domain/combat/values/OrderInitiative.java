package com.buildingblocks.shared.application.combat.domain.combat.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

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
        Validator.validateNotNull(participants);
    }

    public List<Object> getParticipants() {
        return participants;
    }
}
