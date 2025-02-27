package com.buildingblocks.industries.application.player.addplayer;

import com.buildingblocks.industries.application.shared.playerUtils.PlayerResponse;
import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.player.Player;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.playerUtils.PlayerMapper.mapToPlayer;

public class AddPlayerUseCase  implements ICommandUseCase<AddPlayerRequest, Mono<PlayerResponse>> {
    private final IEventRepositoryPort repository;

    public AddPlayerUseCase(IEventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PlayerResponse> execute(AddPlayerRequest request) {
        Player player = new Player(request.getBudget(), request.getIncome());

        player.getUncommttedEvents().forEach(repository::save);
        player.markEventsAsCommitted();

        return Mono.just(mapToPlayer(player));
    }
}
