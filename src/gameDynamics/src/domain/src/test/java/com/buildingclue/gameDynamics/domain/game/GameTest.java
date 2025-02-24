package com.buildingclue.gameDynamics.domain.game;

import com.buildingclue.gameDynamics.domain.game.entities.Board;
import com.buildingclue.gameDynamics.domain.game.entities.Rule;
import com.buildingclue.gameDynamics.domain.game.entities.Turn;
import com.buildingclue.gameDynamics.domain.game.values.CurrentPlayer;
import com.buildingclue.gameDynamics.domain.game.values.Dimensions;
import com.buildingclue.gameDynamics.domain.game.values.GameId;
import com.buildingclue.gameDynamics.domain.game.values.GameState;
import com.buildingclue.gameDynamics.domain.game.values.ListRules;
import com.buildingclue.gameDynamics.domain.game.values.NumberPlayers;
import com.buildingclue.gameDynamics.domain.game.values.PlayerId;
import com.buildingclue.gameDynamics.domain.game.values.Positions;
import com.buildingclue.shared.domain.constants.States;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
  private Game game;

  @BeforeEach
  void setUp() {
    game = new Game();
  }

  @Test
  void testStartGame() {
    game.startGame();
    assertEquals(GameState.of(States.IN_PROGRESS), game.getState(), "El estado del juego debe ser IN_PROGRESS");
  }

  @Test
  void testEndGame() {
    game.endGame("winner-1", true);
    assertEquals(GameState.of(States.FINISHED), game.getState(), "El estado del juego debe ser FINISHED");
  }

  @Test
  void testStartTurn() {
    PlayerId playerId = PlayerId.of("player-1");

    game.startGame();
    game.startTurn(playerId, 1);

    assertEquals(GameState.of(States.IN_PROGRESS), game.getState(), "El juego debe estar en progreso al iniciar un turno");
  }

  @Test
  void testEndTurn() {
    PlayerId playerId = PlayerId.of("player-1");

    game.startGame();
    game.startTurn(playerId, 1);

    assertDoesNotThrow(() -> game.endTurn(playerId, 1, "Tiempo agotado"), "Finalizar un turno no debe lanzar excepciones");
  }

  @Test
  void testStartTurnWhenGameFinished() {
    PlayerId playerId = PlayerId.of("player-1");

    game.startGame();
    game.endGame("winner-1", true); // Finaliza el juego

    game.startTurn(playerId, 1); // Intento de iniciar turno

    assertEquals(GameState.of(States.FINISHED), game.getState(),
            "No se debe permitir iniciar un turno cuando el juego ya terminó");
  }

  @Test
  void testMakeMove() {
    PlayerId playerId = PlayerId.of("player-1");

    game.startGame();
    game.startTurn(playerId, 1);

    assertDoesNotThrow(() -> game.makeMove(playerId, "A1", "B2"),
            "Hacer un movimiento no debe lanzar excepciones");
  }


  @Test
  void testMakeAccusation() {
    PlayerId playerId = PlayerId.of("player-1");

    game.startGame();
    game.startTurn(playerId, 1);

    assertDoesNotThrow(() -> game.makeAccusation(playerId, "Mr. Black", "Knife", "Kitchen"),
            "Hacer una acusación no debe lanzar excepciones");
  }

  @Test
  void testGetState() {
    assertEquals(GameState.of(States.WAITING), game.getState(), "El estado inicial del juego debe ser WAITING");
  }

  @Test
  void testSetAndGetBoard() {
    Board board = new Board(new Dimensions(10, 10), new Positions(0, 0), List.of(PlayerId.of("player-1")));
    game.setBoard(board);
    assertEquals(board, game.getBoard(), "El tablero debe coincidir con el asignado");
  }

  @Test
  void testSetAndGetRules() {
    List<Rule> rules = List.of(new Rule(new ListRules(List.of("Regla 1", "Regla 2"))));
    game.setRules(rules);
    assertEquals(rules, game.getRules(), "Las reglas deben coincidir con las asignadas");
  }

  @Test
  void testSetAndGetTurns() {
    List<Turn> turns = List.of(new Turn(PlayerId.of("player-1"), CurrentPlayer.of("player-1"), GameState.of(States.IN_PROGRESS)));
    game.setTurns(turns);
    assertEquals(turns, game.getTurns(), "Los turnos deben coincidir con los asignados");
  }

  @Test
  void testSetAndGetPlayers() {
    NumberPlayers players = new NumberPlayers(4);
    game.setPlayers(players);
    assertEquals(players, game.getPlayers(), "El número de jugadores debe coincidir con el asignado");
  }

  @Test
  void testInitializeGameWithParameters() {
    GameId gameId = new GameId();
    GameState gameState = GameState.of(States.IN_PROGRESS);
    Board board = new Board(new Dimensions(10, 10), new Positions(0, 0), List.of(PlayerId.of("player-1")));
    List<Rule> rules = List.of(new Rule(new ListRules(List.of("Regla 1", "Regla 2"))));
    List<Turn> turns = List.of(new Turn(PlayerId.of("player-1"), CurrentPlayer.of("player-1"), GameState.of(States.IN_PROGRESS)));
    NumberPlayers players = new NumberPlayers(4);

    Game gameWithParams = Game.createGame(gameId, gameState, board, rules, turns, players);

    assertNotNull(gameWithParams, "El juego inicializado con parámetros no debe ser null");
    assertEquals(gameId, gameWithParams.getIdentity(), "El GameId debe coincidir");
    assertEquals(gameState, gameWithParams.getState(), "El estado del juego debe coincidir con el asignado");
    assertEquals(board, gameWithParams.getBoard(), "El tablero debe coincidir con el asignado");
    assertEquals(rules, gameWithParams.getRules(), "Las reglas deben coincidir con las asignadas");
    assertEquals(turns, gameWithParams.getTurns(), "Los turnos deben coincidir con los asignados");
    assertEquals(players, gameWithParams.getPlayers(), "El número de jugadores debe coincidir con el asignado");
  }
}
