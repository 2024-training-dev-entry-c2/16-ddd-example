package com.buildingblocks.appointments.domain.table.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class CheckedPoint extends DomainEvent {
  public CheckedPoint() {
    super(EventsEnum.CHECKED_POINT.name());
  }
}
