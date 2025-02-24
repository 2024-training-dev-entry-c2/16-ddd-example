package com.buildingblocks.industries.application.player.takeloan;

import com.buildingblocks.industries.application.shared.playerUtils.PlayerResponse;
import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.player.Player;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.playerUtils.PlayerMapper.mapToPlayer;

public class TakeLoanUseCase implements ICommandUseCase<TakeLoanRequest, Mono<PlayerResponse>> {
    private final IEventRepositoryPort repository;

    public TakeLoanUseCase(IEventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PlayerResponse> execute(TakeLoanRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Player player = Player.from(request.getAggregateId(), events);
                    player.takeLoan(request.getId(), request.getAmount(), request.getReductionbudget(), request.getUpdatedBudget());

                    player.getUncommttedEvents().forEach(repository::save);
                    player.markEventsAsCommitted();

                    return mapToPlayer(player);
                });
    }
}
