package com.zombicide.actions.application.player.chooseskill;

import com.zombicide.shared.application.Request;

public class ChooseSkillRequest extends Request {
  private final String survivorId;
  private final String skillId;

  public ChooseSkillRequest(String aggregateId, String survivorId, String skillId) {
    super(aggregateId);
    this.survivorId = survivorId;
    this.skillId = skillId;
  }

  public String getSurvivorId() {
    return survivorId;
  }

  public String getSkillId() {
    return skillId;
  }
}