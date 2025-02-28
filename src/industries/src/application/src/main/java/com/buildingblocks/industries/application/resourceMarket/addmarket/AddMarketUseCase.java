package com.buildingblocks.industries.application.resourceMarket.addmarket;

import com.buildingblocks.industries.application.shared.ResourceMarketUtils.ResourceMarketResponse;
import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.resourceMarket.ResourceMarket;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.ResourceMarketUtils.ResourceMarketMapper.mapToResourceMarket;

public class AddMarketUseCase implements ICommandUseCase<AddMarketRequest, Mono<ResourceMarketResponse>> {
    private final IEventRepositoryPort repository;

    public AddMarketUseCase(IEventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ResourceMarketResponse> execute(AddMarketRequest request) {
        ResourceMarket resourceMarket = new ResourceMarket(request.getAvailableResources(), request.getResourcePrice(), request.getResourceQuantity(), request.getResourceType());

        resourceMarket.getUncommttedEvents().forEach(repository::save);
        resourceMarket.markEventsAsCommitted();

        return Mono.just(mapToResourceMarket(resourceMarket));
    }
}
