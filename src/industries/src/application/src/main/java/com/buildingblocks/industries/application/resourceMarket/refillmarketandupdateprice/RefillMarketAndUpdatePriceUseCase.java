package com.buildingblocks.industries.application.resourceMarket.refillmarketandupdateprice;

import com.buildingblocks.industries.application.shared.ResourceMarketUtils.ResourceMarketResponse;
import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.resourceMarket.ResourceMarket;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.ResourceMarketUtils.ResourceMarketMapper.mapToResourceMarket;

public class RefillMarketAndUpdatePriceUseCase implements ICommandUseCase<RefillMarketAndUpdatePriceRequest, Mono<ResourceMarketResponse>> {
    private final IEventRepository repository;

    public RefillMarketAndUpdatePriceUseCase(IEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ResourceMarketResponse> execute(RefillMarketAndUpdatePriceRequest request) {
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
