package com.buildingblocks.industries.application.industry.flipindustry;

import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.industry.Industry;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.industryUtils.IndustryMapper.mapToIndustry;

public class FlipIndustryUseCase implements ICommandUseCase<FlipIndustryRequest, Mono<IndustryResponse>> {
    private final IEventRepositoryPort repository;

    public FlipIndustryUseCase(IEventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<IndustryResponse> execute(FlipIndustryRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Industry industry = Industry.from(request.getAggregateId(), events);
                    industry.flip(request.getId(), request.getLocation(), request.getRequiredResource(), request.getQuantityRequiredResource(), request.getIncome());

                    industry.getUncommttedEvents().forEach(repository::save);
                    industry.markEventsAsCommitted();

                    return mapToIndustry(industry);
                });
    }
}
