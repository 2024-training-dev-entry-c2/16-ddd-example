package com.buildingblocks.industries.application.resourceMarket.executetradeandupdateprice;

import com.buildingblocks.industries.application.shared.ResourceMarketUtils.ResourceMarketResponse;
import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.resourceMarket.ResourceMarket;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.ResourceMarketUtils.ResourceMarketMapper.mapToResourceMarket;

public class ExecuteTradeAndUpdatePriceUseCase implements ICommandUseCase<ExecuteTradeAndUpdatePriceRequest, Mono<ResourceMarketResponse>> {
    private final IEventRepository repository;

    public ExecuteTradeAndUpdatePriceUseCase(IEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ResourceMarketResponse> execute(ExecuteTradeAndUpdatePriceRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    ResourceMarket resourceMarket = ResourceMarket.from(request.getAggregateId(), events);

                    resourceMarket.executeTrade(
                            request.getTradeId(), request.getTradeType(), request.getResourceType(),
                            request.getTotalResourcesPrice(), request.getResourceQuantity()
                    );

                    resourceMarket.updateResourcePrice(
                            request.getResourceId(), request.getResourceType(), request.getOldResourcePrice(), request.getNewResourcePrice()
                    );

                    resourceMarket.getUncommttedEvents().forEach(repository::save);
                    resourceMarket.markEventsAsCommitted();

                    return mapToResourceMarket(resourceMarket);
                });
    }
}
