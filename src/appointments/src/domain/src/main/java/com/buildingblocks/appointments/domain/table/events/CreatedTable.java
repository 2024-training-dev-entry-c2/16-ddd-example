package com.buildingblocks.appointments.domain.table.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class CreatedTable extends DomainEvent {
  private final String name;

  public CreatedTable(String name) {
    super(EventsEnum.CREATED_TABLE.name());
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
