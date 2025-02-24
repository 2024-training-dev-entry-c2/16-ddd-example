package com.buildingclue.gameDynamics.application.game.turn;

import com.buildingclue.gameDynamics.application.game.shared.IEventsRepository;
import com.buildingclue.gameDynamics.domain.game.Game;
import com.buildingclue.gameDynamics.domain.game.entities.Board;
import com.buildingclue.gameDynamics.domain.game.entities.Rule;
import com.buildingclue.gameDynamics.domain.game.entities.Turn;
import com.buildingclue.gameDynamics.domain.game.values.Dimensions;
import com.buildingclue.gameDynamics.domain.game.values.GameId;
import com.buildingclue.gameDynamics.domain.game.values.GameState;
import com.buildingclue.gameDynamics.domain.game.values.NumberPlayers;
import com.buildingclue.gameDynamics.domain.game.values.PlayerId;
import com.buildingclue.gameDynamics.domain.game.values.Positions;
import com.buildingclue.shared.application.ICommandUseCase;
import com.buildingclue.shared.domain.constants.States;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

public class StartTurnUseCase implements ICommandUseCase<StartTurnRequest, Mono<StartTurnResponse>> {

  private final IEventsRepository eventsRepository;

  public StartTurnUseCase(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<StartTurnResponse> execute(StartTurnRequest request) {
    return eventsRepository.findEventsByAggregateId(request.getAggregateId())
            .collectList()
            .map(events -> {
              List<Rule> rules = Collections.emptyList();
              List<Turn> turns = Collections.emptyList();
              List<PlayerId> players = List.of(PlayerId.of("player-1"), PlayerId.of("player-2"));

              Game game = Game.createGame(
                      GameId.of(request.getAggregateId()),
                      GameState.of(States.IN_PROGRESS),
                      new Board(new Dimensions(10, 10), new Positions(0, 0), players),
                      rules,
                      turns,
                      new NumberPlayers(2)
              );

              boolean playerExists = players.stream()
                      .anyMatch(player -> player.getValue().equals(request.getPlayerId()));

              if (!playerExists) {
                return new StartTurnResponse(game.getIdentity().getValue(), request.getPlayerId(), request.getTurnNumber(), false);
              }

              game.startTurn(PlayerId.of(request.getPlayerId()), request.getTurnNumber());

              game.getUncommittedEvents().forEach(eventsRepository::save);
              game.markEventsAsCommitted();

              return new StartTurnResponse(
                      game.getIdentity().getValue(),
                      request.getPlayerId(),
                      request.getTurnNumber(),
                      true
              );
            });
  }
}
