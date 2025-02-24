package com.buildingblocks.industries.application.industry.consumeresource;

import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.industry.events.BuiltIndustry;
import com.buildingblocks.industries.domain.industry.events.ConsumedResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConsumeResourceUseCaseTest {
    private ConsumeResourceUseCase useCase;
    private IEventRepositoryPort repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepositoryPort.class);
        useCase = new ConsumeResourceUseCase(repository);
    }

    @Test
    void executeSuccess() {
        when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new BuiltIndustry("Iron Work", 1, "Birmingham", 10, "Coal", 2, false, "Canals", false),
                        new ConsumedResource("industry-123", "Coal", 2)
                ));

        ConsumeResourceRequest request = new ConsumeResourceRequest("23", "industry-123","Coal", 2);

        StepVerifier
                .create(useCase.execute(request))
                .consumeErrorWith(error -> {
                    System.out.println("Error: " + error.getMessage());
                    error.printStackTrace();
                })
                .verify();

        Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
    }

    @Test
    void executeWithNoEventsFound() {
        when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.empty());
        ConsumeResourceRequest request = new ConsumeResourceRequest("23", "industry-123", "Coal", 2);

        StepVerifier
                .create(useCase.execute(request))
                .consumeErrorWith(error -> {
                    System.out.println("Error: " + error.getMessage());
                    error.printStackTrace();
                })
                .verify();
    }
}