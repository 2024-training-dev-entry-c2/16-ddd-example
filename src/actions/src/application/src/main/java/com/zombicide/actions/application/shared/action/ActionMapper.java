package com.zombicide.actions.application.shared.action;

import com.zombicide.actions.domain.action.Action;

public class ActionMapper {
  public static ActionResponse mapToAction(Action action) {
    return new ActionResponse(
      action.getIdentity().getValue(),
      action.getAffects().stream().map(affected -> new ActionResponse.Affected(
        affected.getTypeAffected().getValue(),
        affected.getName().getValue(),
        affected.getPosition().getX(),
        affected.getPosition().getY(),
        affected.getDamage().getValue(),
        affected.getCurrentState().getValue()
      )).toList()
    );
  }
}
