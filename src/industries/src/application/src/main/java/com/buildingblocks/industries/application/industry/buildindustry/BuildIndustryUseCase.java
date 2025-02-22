package com.buildingblocks.industries.application.industry.buildindustry;

import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.industry.Industry;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.industries.application.shared.industryUtils.IndustryMapper.mapToIndustry;

public class BuildIndustryUseCase implements ICommandUseCase<BuildIndustryRequest, Mono<IndustryResponse>> {
    private final IEventRepository repository;

    public BuildIndustryUseCase(IEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<IndustryResponse> execute(BuildIndustryRequest request) {
        Industry industry = new Industry(request.getType(), request.getLevel(), request.getLocation(), request.getCost(),
                request.getRequiredResource(), request.getTechLevelRequired(), request.getConnectedToNetwork(),
                request.getEra(), request.getFlipped());

        industry.getUncommttedEvents().forEach(repository::save);
        industry.markEventsAsCommitted();

        return Mono.just(mapToIndustry(industry));
    }
}
