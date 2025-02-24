package com.buildingclue.gameDynamics.domain.game;

import com.buildingclue.gameDynamics.domain.game.entities.Board;
import com.buildingclue.gameDynamics.domain.game.entities.Rule;
import com.buildingclue.gameDynamics.domain.game.entities.Turn;
import com.buildingclue.gameDynamics.domain.game.events.AccusationMade;
import com.buildingclue.gameDynamics.domain.game.events.GameOver;
import com.buildingclue.gameDynamics.domain.game.events.GameStarted;
import com.buildingclue.gameDynamics.domain.game.events.MoveMade;
import com.buildingclue.gameDynamics.domain.game.events.TurnEnded;
import com.buildingclue.gameDynamics.domain.game.events.TurnStarted;
import com.buildingclue.gameDynamics.domain.game.values.GameId;
import com.buildingclue.gameDynamics.domain.game.values.GameState;
import com.buildingclue.gameDynamics.domain.game.values.NumberPlayers;
import com.buildingclue.gameDynamics.domain.game.values.PlayerId;
import com.buildingclue.shared.domain.constants.States;
import com.buildingclue.shared.domain.generic.AggregateRoot;

import java.util.List;

public class Game extends AggregateRoot<GameId> {
  private GameState state;
  private Board board;
  private List<Rule> rules;
  private List<Turn> turns;
  private NumberPlayers players;

  public Game() {
    super(new GameId());
    this.state = GameState.of(States.WAITING);
    subscribe(new GameHandler(this));
  }

  private Game(GameId id, GameState state, Board board, List<Rule> rules, List<Turn> turns, NumberPlayers players) {
    super(id);
    this.state = state;
    this.board = board;
    this.rules = rules;
    this.turns = turns;
    this.players = players;
    subscribe(new GameHandler(this));
    apply(new GameStarted(id.getValue()));
  }

  public static Game createGame(GameId id, GameState state, Board board, List<Rule> rules, List<Turn> turns, NumberPlayers players) {
    return new Game(id, state, board, rules, turns, players);
  }


  public GameState getState() {
    return state;
  }

  public void setState(GameState state) {
    this.state = state;
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public List<Rule> getRules() {
    return rules;
  }

  public void setRules(List<Rule> rules) {
    this.rules = rules;
  }

  public List<Turn> getTurns() {
    return turns;
  }

  public void setTurns(List<Turn> turns) {
    this.turns = turns;
  }

  public NumberPlayers getPlayers() {
    return players;
  }

  public void setPlayers(NumberPlayers players) {
    this.players = players;
  }


  public void startGame() {
    this.state = GameState.of(States.IN_PROGRESS);
    apply(new GameStarted(this.getIdentity().getValue()));
  }

  public void endGame(String winnerPlayerId, Boolean wasCaseSolved) {
    this.state = GameState.of(States.FINISHED);
    apply(new GameOver(winnerPlayerId, wasCaseSolved));
  }

  public void startTurn(PlayerId playerId, Integer turnNumber) {
    if (this.state.equals(GameState.of(States.FINISHED))) {
      return;
    }
    apply(new TurnStarted(this.getIdentity().getValue(), playerId.getValue(), turnNumber));
  }


  public void endTurn(PlayerId playerId, Integer turnNumber, String reason) {
    if (this.state.equals(GameState.of(States.FINISHED))) {
      return;
    }
    apply(new TurnEnded(playerId.getValue(), turnNumber, reason));
  }


  public void makeMove(PlayerId playerId, String fromPosition, String toPosition) {
    apply(new MoveMade(playerId.getValue(), fromPosition, toPosition));
  }

  public void makeAccusation(PlayerId playerId, String suspect, String weapon, String location) {
    apply(new AccusationMade(playerId.getValue(), suspect, weapon, location));
  }
}
