package com.buildingblocks.shared.application.combat.domain.combat.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.Date;

public class CombatFinished extends DomainEvent {
    private  String result;
    private  Date dateEnd;

    public CombatFinished() {
    super(EventsEnum.COMBAT_FINISHED.name());
    }
    public CombatFinished(String resultado, Date dateStart) {
        super(EventsEnum.COMBAT_FINISHED.name());
        result = resultado;
        this.dateEnd = dateStart;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
