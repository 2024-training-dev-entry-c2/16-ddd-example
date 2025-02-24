package com.buildingblocks.industries.application.industry.exhaustindustry;

import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.industry.Industry;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.industryUtils.IndustryMapper.mapToIndustry;

public class ExhaustIndustryUseCase implements ICommandUseCase<ExhaustIndustryRequest, Mono<IndustryResponse>> {
    private final IEventRepositoryPort repository;

    public ExhaustIndustryUseCase(IEventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<IndustryResponse> execute(ExhaustIndustryRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Industry industry = Industry.from(request.getAggregateId(), events);
                    industry.isExhausted(request.getId(), request.getLocation(), request.getType(), request.getFlipped());

                    industry.getUncommttedEvents().forEach(repository::save);
                    industry.markEventsAsCommitted();

                    return mapToIndustry(industry);
                });
    }
}
