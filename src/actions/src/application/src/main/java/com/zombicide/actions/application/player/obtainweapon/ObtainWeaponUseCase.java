package com.zombicide.actions.application.player.obtainweapon;

import com.zombicide.actions.application.shared.player.PlayerResponse;
import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.player.Player;
import com.zombicide.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.zombicide.actions.application.shared.player.PlayerMapper.mapToPlayer;

public class ObtainWeaponUseCase implements ICommandUseCase<ObtainWaponRequest, Mono<PlayerResponse>> {
  private final IEventsRepository repository;

  public ObtainWeaponUseCase(final IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PlayerResponse> execute(ObtainWaponRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        Player player = Player.from(request.getAggregateId(), events);
        player.obtainWeapon(request.getSurvivorId());

        player.getUncommittedEvents().forEach(repository::save);
        player.markEventsAsCommitted();

        return mapToPlayer(player);
      });
  }
}