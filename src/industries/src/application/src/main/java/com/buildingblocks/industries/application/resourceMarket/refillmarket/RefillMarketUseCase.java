package com.buildingblocks.industries.application.resourceMarket.refillmarket;

import com.buildingblocks.industries.application.shared.ResourceMarketUtils.ResourceMarketResponse;
import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.resourceMarket.ResourceMarket;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.ResourceMarketUtils.ResourceMarketMapper.mapToResourceMarket;

public class RefillMarketUseCase implements ICommandUseCase<RefillMarketRequest, Mono<ResourceMarketResponse>> {
    private final IEventRepositoryPort repository;

    public RefillMarketUseCase(IEventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ResourceMarketResponse> execute(RefillMarketRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    ResourceMarket resourceMarket = ResourceMarket.from(request.getAggregateId(), events);
                    resourceMarket.refillMarketSupply(request.getId(), request.getResourceType(), request.getAddedResourceQuantity(), request.getUpdatedAvailableResources(), request.getUpdatedResourcePrice());
                    resourceMarket.updateResourcePrice(request.getResourceId(), request.getResourceType(), request.getOldResourcePrice(), request.getNewResourcePrice());

                    resourceMarket.getUncommttedEvents().forEach(repository::save);
                    resourceMarket.markEventsAsCommitted();

                    return mapToResourceMarket(resourceMarket);
                });
    }
}
