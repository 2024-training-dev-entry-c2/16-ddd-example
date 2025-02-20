package com.buildingblocks.appointments.application.shared.table;

public class TableMapper {
  public static TableResponse mapToTable(com.buildingblocks.appointments.domain.table.Table table) {
    return new TableResponse(
      table.getIdentity().getValue(),
      table.getPlayers().values().stream().map(player -> new TableResponse.Player(player.getIdentity().getValue(), player.getNickname().getValue(), player.getScore().getValue())).toList(),
      table.getTurn().getPlayerId(),
      table.getName().getValue(),
      table.getCards().stream().map(item -> new TableResponse.Card(item.getIdentity().getValue(), item.getEmoji().getValue(), item.getIsFlipped().getValue(), item.getIsActive().getValue())).toList(),
      table.getIsActive().getValue()
    );
  }
}
