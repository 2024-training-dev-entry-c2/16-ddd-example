package com.buildingclue.gameDynamics.application.game.endgame;

import com.buildingclue.gameDynamics.application.game.shared.IEventsRepository;
import com.buildingclue.gameDynamics.domain.game.Game;
import com.buildingclue.gameDynamics.domain.game.entities.Board;
import com.buildingclue.gameDynamics.domain.game.values.Dimensions;
import com.buildingclue.gameDynamics.domain.game.values.GameId;
import com.buildingclue.gameDynamics.domain.game.values.GameState;
import com.buildingclue.gameDynamics.domain.game.values.NumberPlayers;
import com.buildingclue.gameDynamics.domain.game.values.Positions;
import com.buildingclue.shared.application.ICommandUseCase;
import com.buildingclue.shared.domain.constants.States;
import reactor.core.publisher.Mono;

import java.util.Collections;

public class EndGameUseCase implements ICommandUseCase<EndGameRequest, Mono<EndGameResponse>> {

  private final IEventsRepository eventsRepository;

  public EndGameUseCase(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<EndGameResponse> execute(EndGameRequest request) {
    return eventsRepository.findEventsByAggregateId(request.getAggregateId())
            .collectList()
            .map(events -> {
              Game game = Game.createGame(
                      GameId.of(request.getAggregateId()),
                      GameState.of(States.IN_PROGRESS),
                      new Board(new Dimensions(10, 10), new Positions(0, 0), Collections.emptyList()),
                      Collections.emptyList(),
                      Collections.emptyList(),
                      new NumberPlayers(2)
              );

              game.endGame(request.getWinnerPlayerId(), request.getWasCaseSolved());

              game.getUncommittedEvents().forEach(eventsRepository::save);
              game.markEventsAsCommitted();

              return new EndGameResponse(
                      game.getIdentity().getValue(),
                      request.getWinnerPlayerId(),
                      request.getWasCaseSolved(),
                      game.getState().getState().toString()
              );
            });
  }
}
