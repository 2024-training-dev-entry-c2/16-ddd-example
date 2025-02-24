package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.generic.IValueObject;

import java.util.List;

import static com.buildingclue.shared.domain.utils.ValidationUtils.validateStringList;

public class ListRules implements IValueObject {

  private final List<String> rules;

  public ListRules(List<String> rules) {
    this.rules = rules;
    validate();
  }

  public static ListRules of(List<String> rules) {
    return new ListRules(rules);
  }

  @Override
  public void validate() {
    validateStringList(rules, "rules cannot be null or empty");
  }

  public List<String> getRules() {
    return rules;
  }
}
