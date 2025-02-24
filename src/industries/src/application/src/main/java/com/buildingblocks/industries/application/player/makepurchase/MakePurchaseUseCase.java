package com.buildingblocks.industries.application.player.makepurchase;

import com.buildingblocks.industries.application.shared.playerUtils.PlayerResponse;
import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.player.Player;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.playerUtils.PlayerMapper.mapToPlayer;

public class MakePurchaseUseCase implements ICommandUseCase<MakePurchaseRequest, Mono<PlayerResponse>> {
    private final IEventRepositoryPort repository;

    public MakePurchaseUseCase(IEventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PlayerResponse> execute(MakePurchaseRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Player player = Player.from(request.getAggregateId(), events);
                    player.spendBudget(request.getBudgetId(), request.getAmount(), request.getNewBudget(), request.getReason());
                    player.ExecuteTransaction(request.getTransactionId(), request.getResourceType(), request.getAmount(), request.getUpdatedBudget());

                    player.getUncommttedEvents().forEach(repository::save);
                    player.markEventsAsCommitted();

                    return mapToPlayer(player);
                });
    }
}
