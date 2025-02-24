package com.buildingclue.gameDynamics.domain.game.entities;

import com.buildingclue.gameDynamics.domain.game.values.ListRules;
import com.buildingclue.gameDynamics.domain.game.values.RuleId;
import com.buildingclue.shared.domain.generic.Entity;

public class Rule extends Entity<RuleId> {

  private ListRules rules;

  public Rule( ListRules rules) {
    super(new RuleId());
    this.rules = rules;
  }

  public Rule(RuleId id, ListRules rules) {
    super(id);
    this.rules = rules;
  }

  public ListRules getRules() {
    return rules;
  }

  public void setRules(ListRules rules) {
    this.rules = rules;
  }
}
