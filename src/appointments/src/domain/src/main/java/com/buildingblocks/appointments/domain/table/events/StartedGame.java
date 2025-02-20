package com.buildingblocks.appointments.domain.table.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class StartedGame extends DomainEvent {

  public StartedGame() {
    super(EventsEnum.STARTED_GAME.name());
  }
}
