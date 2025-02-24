package com.buildingblocks.appointments.domain.table.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class CreatedTable extends DomainEvent {
  private String tableName;

  public CreatedTable() {
    super(EventsEnum.CREATED_TABLE.name());
  }

  public CreatedTable(String tableName) {
    super(EventsEnum.CREATED_TABLE.name());
    this.tableName = tableName;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }
}
