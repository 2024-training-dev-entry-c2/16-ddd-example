package com.buildingblocks.shared.application.combat.domain.combat.values;

import com.buildingblocks.shared.application.combat.domain.combat.entities.Participants;
import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

import java.util.List;

public class OrderInitiative implements IValueObject {
    private final List<Participants> participants;

    private OrderInitiative(List<Participants> participants) {
        this.participants = participants;
    }
    public  static  OrderInitiative of(List<Participants> participants){
        return new OrderInitiative(participants);
    }

    @Override
    public void validate() {
        Validator.validateNotNull(participants);

    }

    public List<Participants> getParticipants() {
        return participants;
    }
}
