package com.buildingclue.gameDynamics.application.game.move;

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

public class MoveUseCase implements ICommandUseCase<MoveRequest, Mono<MoveResponse>> {

  private final IEventsRepository eventsRepository;

  public MoveUseCase(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<MoveResponse> execute(MoveRequest request) {
    return eventsRepository.findEventsByAggregateId(request.getAggregateId())
            .collectList()
            .map(events -> {
              List<Rule> rules = Collections.emptyList();
              List<Turn> turns = Collections.emptyList();
              List<PlayerId> players = Collections.emptyList();

              Game game = Game.createGame(
                      GameId.of(request.getAggregateId()),
                      GameState.of(States.IN_PROGRESS),
                      new Board(new Dimensions(10, 10), new Positions(0, 0), players),
                      rules,
                      turns,
                      new NumberPlayers(2)
              );

              Positions fromPosition = new Positions(Integer.parseInt(request.getFromPosition().split(",")[0]),
                      Integer.parseInt(request.getFromPosition().split(",")[1]));
              Positions toPosition = new Positions(Integer.parseInt(request.getToPosition().split(",")[0]),
                      Integer.parseInt(request.getToPosition().split(",")[1]));

              boolean isMoveValid = game.getBoard().canMove(PlayerId.of(request.getPlayerId()), toPosition);

              if (isMoveValid) {
                game.makeMove(PlayerId.of(request.getPlayerId()), request.getFromPosition(), request.getToPosition());
              }

              game.getUncommittedEvents().forEach(eventsRepository::save);
              game.markEventsAsCommitted();

              return new MoveResponse(
                      game.getIdentity().getValue(),
                      request.getPlayerId(),
                      request.getFromPosition(),
                      request.getToPosition(),
                      isMoveValid
              );
            });
  }
}
