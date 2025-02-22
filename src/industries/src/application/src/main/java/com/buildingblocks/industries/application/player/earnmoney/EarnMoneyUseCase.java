package com.buildingblocks.industries.application.player.earnmoney;

import com.buildingblocks.industries.application.shared.playerUtils.PlayerResponse;
import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.player.Player;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.playerUtils.PlayerMapper.mapToPlayer;

public class EarnMoneyUseCase implements ICommandUseCase<EarnMoneyRequest, Mono<PlayerResponse>> {
    private final IEventRepository repository;

    public EarnMoneyUseCase(IEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PlayerResponse> execute(EarnMoneyRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Player player = Player.from(request.getAggregateId(), events);
                    player.earnMoney(request.getId(), request.getAmount(), request.getNewBudget());

                    player.getUncommttedEvents().forEach(repository::save);
                    player.markEventsAsCommitted();

                    return mapToPlayer(player);
                });
    }
}
