package com.buildingblocks.industries.application.industry.overbuildindustry;

import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.industry.events.BuiltIndustry;
import com.buildingblocks.industries.domain.industry.events.OverBuiltIndustry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class OverBuildIndustryUseCaseTest {
    private OverBuildIndustryUseCase useCase;
    private IEventRepositoryPort repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepositoryPort.class);
        useCase = new OverBuildIndustryUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new BuiltIndustry("Iron Work", 2,"Liverpool", 10, "Coal", 2, false, "Canals", false),
                        new OverBuiltIndustry("industry-123", "Iron Work", 2, 15)
                ));

        OverBuildIndustryRequest request = new OverBuildIndustryRequest("123", "industry-123", "Iron Work",2,  15);

        StepVerifier
                .create(useCase.execute(request))
                .consumeErrorWith(error -> {
                    System.out.println("Error: " + error.getMessage());
                    error.printStackTrace();
                })
                .verify();
    }
}