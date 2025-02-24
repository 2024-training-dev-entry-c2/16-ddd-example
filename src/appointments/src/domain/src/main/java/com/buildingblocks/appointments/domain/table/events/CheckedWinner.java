package com.buildingblocks.appointments.domain.table.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class CheckedWinner extends DomainEvent {
  public CheckedWinner() {
    super(EventsEnum.CHECKED_WINNER.name());
  }
}
