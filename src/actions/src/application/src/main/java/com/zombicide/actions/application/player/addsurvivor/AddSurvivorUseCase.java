package com.zombicide.actions.application.player.addsurvivor;

import com.zombicide.actions.application.shared.player.PlayerResponse;
import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.player.Player;
import com.zombicide.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.zombicide.actions.application.shared.player.PlayerMapper.mapToPlayer;

public class AddSurvivorUseCase implements ICommandUseCase<AddSurvivorRequest, Mono<PlayerResponse>> {
  private final IEventsRepository repository;

  public AddSurvivorUseCase(final IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PlayerResponse> execute(AddSurvivorRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        Player player = Player.from(request.getAggregateId(), events);
        player.addSurvivor(request.getSurvivorId(), request.getPlayerName());

        player.getUncommittedEvents().forEach(repository::save);
        player.markEventsAsCommitted();

        return mapToPlayer(player);
      });
  }
}