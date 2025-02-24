package com.buildingclue.gameDynamics.domain.game;

import com.buildingclue.gameDynamics.domain.game.events.AccusationMade;
import com.buildingclue.gameDynamics.domain.game.events.GameOver;
import com.buildingclue.gameDynamics.domain.game.events.GameStarted;
import com.buildingclue.gameDynamics.domain.game.events.MoveMade;
import com.buildingclue.gameDynamics.domain.game.events.TurnEnded;
import com.buildingclue.gameDynamics.domain.game.events.TurnStarted;
import com.buildingclue.gameDynamics.domain.game.values.GameState;
import com.buildingclue.gameDynamics.domain.game.values.PlayerId;
import com.buildingclue.shared.domain.constants.States;
import com.buildingclue.shared.domain.generic.DomainActionsContainer;
import com.buildingclue.shared.domain.generic.DomainEvent;

import java.util.function.Consumer;

public class GameHandler extends DomainActionsContainer {
  public GameHandler(Game game) {
    addAction(handleGameStarted(game));
    addAction(handleGameOver(game));
    addAction(handleTurnStarted(game));
    addAction(handleTurnEnded(game));
    addAction(handleMoveMade(game));
    addAction(handleAccusationMade(game));
  }

  private Consumer<? super DomainEvent> handleGameStarted(Game game) {
    return event -> {
      if (event instanceof GameStarted) {
        game.setState(GameState.of(States.IN_PROGRESS));
      }
    };
  }

  private Consumer<? super DomainEvent> handleGameOver(Game game) {
    return event -> {
      if (event instanceof GameOver) {
        game.setState(GameState.of(States.FINISHED));
      }
    };
  }

  private Consumer<? super DomainEvent> handleTurnStarted(Game game) {
    return event -> {
      if (event instanceof TurnStarted) {
        System.out.println("Turno iniciado: " + ((TurnStarted) event).getTurnNumber());
      }
    };
  }


  private Consumer<? super DomainEvent> handleTurnEnded(Game game) {
    return event -> {
      if (event instanceof TurnEnded) {
        TurnEnded turnEvent = (TurnEnded) event;
        System.out.println("Turno finalizado correctamente para el jugador: " + turnEvent.getPlayerId());
      }
    };
  }



  private Consumer<? super DomainEvent> handleMoveMade(Game game) {
    return event -> {
      if (event instanceof MoveMade) {
        MoveMade moveEvent = (MoveMade) event;
        System.out.println("Movimiento realizado por el jugador: " + moveEvent.getPlayerId() +
                " desde " + moveEvent.getFromPosition() +
                " hacia " + moveEvent.getToPosition());
      }
    };
  }


  private Consumer<? super DomainEvent> handleAccusationMade(Game game) {
    return event -> {
      if (event instanceof AccusationMade) {
        AccusationMade accEvent = (AccusationMade) event;
        System.out.println("Acusación realizada por el jugador: " + accEvent.getPlayerId() +
                " contra " + accEvent.getAccusedSuspect() +
                " con el arma " + accEvent.getAccusedWeapon() +
                " en " + accEvent.getAccusedLocation());
      }
    };
  }
}
