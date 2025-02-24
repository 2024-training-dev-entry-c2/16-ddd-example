package com.buildingclue.gameDynamics.domain.game.entities;

import com.buildingclue.gameDynamics.domain.game.values.BoardId;
import com.buildingclue.gameDynamics.domain.game.values.Dimensions;
import com.buildingclue.gameDynamics.domain.game.values.PlayerId;
import com.buildingclue.gameDynamics.domain.game.values.Positions;
import com.buildingclue.gameDynamics.domain.game.values.Room;
import com.buildingclue.shared.domain.generic.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board extends Entity<BoardId> {
  private Dimensions dimensions;
  private Map<PlayerId, Positions> playerPositions;
  private List<Room> rooms;

  public Board(Dimensions dimensions, Positions positions, List<PlayerId> players) {
    super(new BoardId());
    this.dimensions = dimensions;
    this.rooms = rooms;
    this.playerPositions = new HashMap<>();
  }

  public Board(BoardId id, Dimensions dimensions, Positions positions, List<PlayerId> players) {
    super(id);
    this.dimensions = dimensions;
    this.rooms = rooms;
    this.playerPositions = new HashMap<>();
  }

  public Dimensions getDimensions() {
    return dimensions;
  }

  public void setDimensions(Dimensions dimensions) {
    this.dimensions = dimensions;
  }

  public Map<PlayerId, Positions> getPlayerPositions() {
    return playerPositions;
  }

  public void setPlayerPositions(Map<PlayerId, Positions> playerPositions) {
    this.playerPositions = playerPositions;
  }

  public List<Room> getRooms() {
    return rooms;
  }

  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
  }

  //Valido que la nueva posición sea válida dentro de la dimensión del tablero
  public boolean canMove(PlayerId playerId, Positions newPosition) {
    return newPosition.getX() >= 0 && newPosition.getX() < this.dimensions.getWidth() &&
            newPosition.getY() >= 0 && newPosition.getY() < this.dimensions.getHeight();
  }

  public void movePlayer(PlayerId playerId, Positions newPosition) {
    if (canMove(playerId, newPosition)) {
      playerPositions.put(playerId, newPosition);
    } else {
      throw new IllegalArgumentException("Invalid position");
    }
  }
}
