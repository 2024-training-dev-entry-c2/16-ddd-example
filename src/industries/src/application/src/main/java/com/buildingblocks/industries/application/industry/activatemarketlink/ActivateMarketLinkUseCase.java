package com.buildingblocks.industries.application.industry.activatemarketlink;

import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.industry.Industry;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.industryUtils.IndustryMapper.mapToIndustry;

public class ActivateMarketLinkUseCase implements ICommandUseCase<ActivateMarketLinkRequest, Mono<IndustryResponse>> {
    private final IEventRepository repository;

    public ActivateMarketLinkUseCase(IEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<IndustryResponse> execute(ActivateMarketLinkRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Industry industry = Industry.from(request.getAggregateId(), events);
                    industry.activateMarketLink(request.getId(), request.getConnectedToNetwork());

                    industry.getUncommttedEvents().forEach(repository::save);
                    industry.markEventsAsCommitted();

                    return mapToIndustry(industry);
                });
    }
}
