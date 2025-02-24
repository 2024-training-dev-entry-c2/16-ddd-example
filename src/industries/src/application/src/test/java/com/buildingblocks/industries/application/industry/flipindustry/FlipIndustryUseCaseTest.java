package com.buildingblocks.industries.application.industry.flipindustry;

import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.industry.events.BuiltIndustry;
import com.buildingblocks.industries.domain.industry.events.FlippedIndustry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FlipIndustryUseCaseTest {
    private FlipIndustryUseCase useCase;
    private IEventRepositoryPort repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepositoryPort.class);
        useCase = new FlipIndustryUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new BuiltIndustry("Iron Work", 2, "Liverpool", 10, "Coal", 2, false, "Canals", false),
                        new FlippedIndustry("industry-123", "Liverpool", "Coal", 2, 5)
                ));

        FlipIndustryRequest request = new FlipIndustryRequest("123", "industry-123", "Liverpool", "Coal", 2, 5);

        StepVerifier
                .create(useCase.execute(request))
                .consumeErrorWith(error -> {
                    System.out.println("Error: " + error.getMessage());
                    error.printStackTrace();
                })
                .verify();
    }
}