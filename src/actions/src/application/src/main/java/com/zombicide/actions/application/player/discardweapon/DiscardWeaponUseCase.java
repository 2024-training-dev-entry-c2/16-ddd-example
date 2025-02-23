package com.zombicide.actions.application.player.discardweapon;

import com.zombicide.actions.application.shared.player.PlayerResponse;
import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.player.Player;
import com.zombicide.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.zombicide.actions.application.shared.player.PlayerMapper.mapToPlayer;

public class DiscardWeaponUseCase implements ICommandUseCase<DiscardWeaponRequest, Mono<PlayerResponse>> {
  private final IEventsRepository repository;

  public DiscardWeaponUseCase(final IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PlayerResponse> execute(DiscardWeaponRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        Player player = Player.from(request.getAggregateId(), events);
        player.discardWeapon(request.getWeaponId(), request.getSurvivorId());

        player.getUncommittedEvents().forEach(repository::save);
        player.markEventsAsCommitted();

        return mapToPlayer(player);
      });
  }
}
