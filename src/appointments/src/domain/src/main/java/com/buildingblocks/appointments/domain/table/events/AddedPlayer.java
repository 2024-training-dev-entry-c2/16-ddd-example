package com.buildingblocks.appointments.domain.table.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class AddedPlayer extends DomainEvent {
  private String nickname;

  public AddedPlayer() {
    super(EventsEnum.ADDED_PLAYER.name());
  }

  public AddedPlayer(String nickname) {
    super(EventsEnum.ADDED_PLAYER.name());
    this.nickname = nickname;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
}
