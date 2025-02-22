package com.buildingblocks.industries.application.industry.overbuildindustry;

import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.industry.Industry;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.industryUtils.IndustryMapper.mapToIndustry;

public class OverBuildIndustryUseCase implements ICommandUseCase<OverBuildIndustryRequest, Mono<IndustryResponse>> {
    private final IEventRepository repository;

    public OverBuildIndustryUseCase(IEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<IndustryResponse> execute(OverBuildIndustryRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Industry industry = Industry.from(request.getAggregateId(), events);
                    industry.overBuild(request.getId(), request.getType(), request.getLevel(), request.getCost());

                    industry.getUncommttedEvents().forEach(repository::save);
                    industry.markEventsAsCommitted();

                    return mapToIndustry(industry);
                });
    }
}
