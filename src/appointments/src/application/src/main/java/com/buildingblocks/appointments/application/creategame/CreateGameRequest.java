package com.buildingblocks.appointments.application.creategame;

import com.buildingblocks.shared.application.Request;

import java.util.List;

public class CreateGameRequest extends Request {
  private final String tableName;
  private final List<String> players;

  public CreateGameRequest(String tableName, List<String> players) {
    super(null);
    this.tableName = tableName;
    this.players = players;
  }

  public String getTableName() {
    return tableName;
  }

  public List<String> getPlayers() {
    return players;
  }
}
