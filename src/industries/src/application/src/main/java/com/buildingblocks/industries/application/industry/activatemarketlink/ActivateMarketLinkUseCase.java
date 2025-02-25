package com.buildingblocks.industries.application.industry.activatemarketlink;

import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.industry.Industry;
import com.buildingblocks.shared.application.ICommandUseCase;
import com.buildingblocks.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.Comparator;

import static com.buildingblocks.industries.application.shared.industryUtils.IndustryMapper.mapToIndustry;

public class ActivateMarketLinkUseCase implements ICommandUseCase<ActivateMarketLinkRequest, Mono<IndustryResponse>> {
    private final IEventRepositoryPort repository;

    public ActivateMarketLinkUseCase(IEventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<IndustryResponse> execute(ActivateMarketLinkRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    events.sort(Comparator.comparing(DomainEvent::getWhen));
                    Industry industry = Industry.from(request.getAggregateId(), events);
                    industry.activateMarketLink(request.getId(), request.getConnectedToNetwork());

                    industry.getUncommttedEvents().forEach(repository::save);
                    industry.markEventsAsCommitted();

                    return mapToIndustry(industry);
                });
    }
}
