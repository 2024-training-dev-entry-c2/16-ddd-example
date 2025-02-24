package com.zombicide.actions.application.makemovement;

import com.zombicide.actions.application.shared.action.ActionResponse;
import com.zombicide.actions.application.shared.player.PlayerResponse;

public record MakeMovementResponse(ActionResponse actionResponse, PlayerResponse playerResponse) { }
