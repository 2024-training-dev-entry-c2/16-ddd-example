package com.buildingblocks.appointments.domain.table.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class TableId extends Identity {

  public TableId() {
    super();
  }

  private TableId(String value) {
    super(value);
  }

  public static TableId of(String value) {
    return new TableId(value);
  }
}