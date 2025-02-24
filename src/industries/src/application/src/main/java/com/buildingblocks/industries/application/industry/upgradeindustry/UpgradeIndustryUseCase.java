package com.buildingblocks.industries.application.industry.upgradeindustry;

import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.industry.Industry;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.industryUtils.IndustryMapper.mapToIndustry;

public class UpgradeIndustryUseCase implements ICommandUseCase<UpgradeIndustryRequest, Mono<IndustryResponse>> {
    private final IEventRepositoryPort repository;

    public UpgradeIndustryUseCase(IEventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<IndustryResponse> execute(UpgradeIndustryRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Industry industry = Industry.from(request.getAggregateId(), events);
                    industry.upgrade(request.getId(), request.getType(), request.getLevel(), request.getLocation(), request.getFlipped(), request.getRequiredResource(), request.getQuantityRequiredResource(), request.getCost(), request.getTechLevelRequired(), request.getRequiredResearch(), request.getEra());

                    industry.getUncommttedEvents().forEach(repository::save);
                    industry.markEventsAsCommitted();

                    return mapToIndustry(industry);
                });
    }
}
