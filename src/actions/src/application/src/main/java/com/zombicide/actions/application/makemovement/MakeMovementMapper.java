package com.zombicide.actions.application.makemovement;

import com.zombicide.actions.application.shared.action.ActionMapper;
import com.zombicide.actions.application.shared.player.PlayerMapper;
import com.zombicide.actions.domain.action.Action;
import com.zombicide.actions.domain.player.Player;

public class MakeMovementMapper {
  public static MakeMovementResponse mapToMakeMovement(Action action, Player player) {
    return new MakeMovementResponse(
      ActionMapper.mapToAction(action),
      PlayerMapper.mapToPlayer(player)
    );
  }
}
