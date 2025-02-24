package com.buildingclue.gameDynamics.application.game.accusation;

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

public class AccusationUseCase implements ICommandUseCase<AccusationRequest, Mono<AccusationResponse>> {

  private final IEventsRepository eventsRepository;

  public AccusationUseCase(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<AccusationResponse> execute(AccusationRequest request) {
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

              game.makeAccusation(PlayerId.of(request.getPlayerId()), request.getSuspect(), request.getWeapon(), request.getLocation());

              boolean isAccusationCorrect = request.getSuspect().equals("Miss Scarlet") &&
                      request.getWeapon().equals("Candlestick") &&
                      request.getLocation().equals("Ballroom");


              if (isAccusationCorrect) {
                game.endGame(request.getPlayerId(), true);
              }

              game.getUncommittedEvents().forEach(eventsRepository::save);
              game.markEventsAsCommitted();

              return new AccusationResponse(
                      game.getIdentity().getValue(),
                      request.getPlayerId(),
                      request.getSuspect(),
                      request.getWeapon(),
                      request.getLocation(),
                      isAccusationCorrect
              );
            });
  }
}
