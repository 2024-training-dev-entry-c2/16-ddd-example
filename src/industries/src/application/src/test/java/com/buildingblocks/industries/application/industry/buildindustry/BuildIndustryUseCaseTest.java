package com.buildingblocks.industries.application.industry.buildindustry;

import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

class BuildIndustryUseCaseTest {
    private BuildIndustryUseCase useCase;
    private IEventRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepository.class);
        useCase = new BuildIndustryUseCase(repository);
    }

    @Test
    void executeSuccess() {
        BuildIndustryRequest request = new BuildIndustryRequest(
                "Iron Work", 1, "Birmingham", 10, "Coal", 2, false, "Canals", false
        );

        StepVerifier.create(useCase.execute(request))
                .expectNextMatches(response ->
                        response.getType().equals(request.getType()) &&
                                response.getLevel().equals(request.getLevel()) &&
                                response.getLocation().equals(request.getLocation()) &&
                                response.getCost().equals(request.getCost()) &&
                                response.getRequiredResource().equals(request.getRequiredResource()) &&
                                response.getTechLevelRequired().equals(request.getTechLevelRequired()) &&
                                response.getConnectedToNetwork().equals(request.getConnectedToNetwork()) &&
                                response.getEra().equals(request.getEra()) &&
                                response.getFlipped().equals(request.getFlipped()))
                .verifyComplete();

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }
}