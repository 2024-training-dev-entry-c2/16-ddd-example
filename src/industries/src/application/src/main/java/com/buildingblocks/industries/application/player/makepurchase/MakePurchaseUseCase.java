package com.buildingblocks.industries.application.player.makepurchase;

import com.buildingblocks.industries.application.shared.playerUtils.PlayerResponse;
import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.player.Player;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.playerUtils.PlayerMapper.mapToPlayer;

public class MakePurchaseUseCase implements ICommandUseCase<MakePurchaseRequest, Mono<PlayerResponse>> {
    private final IEventRepository repository;

    public MakePurchaseUseCase(IEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PlayerResponse> execute(MakePurchaseRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Player player = Player.from(request.getAggregateId(), events);
                    player.spendBudget(request.getBudgetId(), request.getAmount(), request.getNewBudget(), "Purchase");
                    player.ExecuteTransaction(request.getTransactionId(), request.getResourceType(), request.getAmount(), request.getUpdatedBudget());

                    player.getUncommttedEvents().forEach(repository::save);
                    player.markEventsAsCommitted();

                    return mapToPlayer(player);
                });
    }
}
