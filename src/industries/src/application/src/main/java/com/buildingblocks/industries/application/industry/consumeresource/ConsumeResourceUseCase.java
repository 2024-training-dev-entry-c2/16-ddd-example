package com.buildingblocks.industries.application.industry.consumeresource;

import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.industry.Industry;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.industryUtils.IndustryMapper.mapToIndustry;

public class ConsumeResourceUseCase implements ICommandUseCase<ConsumeResourceRequest, Mono<IndustryResponse>> {
    private final IEventRepository repository;

    public ConsumeResourceUseCase(IEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<IndustryResponse> execute(ConsumeResourceRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Industry industry = Industry.from(request.getAggregateId(), events);
                    industry.consumeResource(request.getId(), request.getRequiredResource(), request.getQuantityRequiredResource());

                    industry.getUncommttedEvents().forEach(repository::save);
                    industry.markEventsAsCommitted();

                    return mapToIndustry(industry);
                });
    }
}
