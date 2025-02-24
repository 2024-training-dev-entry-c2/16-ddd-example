package com.buildingblocks.industries.application.player.adjustincome;

import com.buildingblocks.industries.application.shared.playerUtils.PlayerResponse;
import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.player.Player;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.playerUtils.PlayerMapper.mapToPlayer;

public class AdjustIncomeUseCase implements ICommandUseCase<AdjustIncomeRequest, Mono<PlayerResponse>> {
    private final IEventRepositoryPort repository;

    public AdjustIncomeUseCase(IEventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PlayerResponse> execute(AdjustIncomeRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Player player = Player.from(request.getAggregateId(), events);
                    player.adjustIncome(request.getId(), request.getChangedIncome(), request.getReason(), request.getUpdatedIncome());

                    player.getUncommttedEvents().forEach(repository::save);
                    player.markEventsAsCommitted();

                    return mapToPlayer(player);
                });
    }
}
