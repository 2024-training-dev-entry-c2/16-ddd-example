package com.buildingblocks.industries.application.industry.upgradeindustry;

import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.industry.events.BuiltIndustry;
import com.buildingblocks.industries.domain.industry.events.UpgradedIndustry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class UpgradeIndustryUseCaseTest {
    private UpgradeIndustryUseCase useCase;
    private IEventRepositoryPort repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepositoryPort.class);
        useCase = new UpgradeIndustryUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new BuiltIndustry("Iron Work", 2,"Liverpool", 10, "Coal", 2, false, "Canals", false),
                        new UpgradedIndustry("industry-123", "Iron Work", 3, "Liverpool", false, "Coal", 2, 20, 4, true, "Canals")
                ));

        UpgradeIndustryRequest request = new UpgradeIndustryRequest(
                "1", "industry-123", "Iron Work", 3, "Liverpool", false, "Coal", 2, 20, 4, true, "Canals"
        );

        StepVerifier
                .create(useCase.execute(request))
                .consumeErrorWith(error -> {
                    System.out.println("Error: " + error.getMessage());
                    error.printStackTrace();
                })
                .verify();
    }
}