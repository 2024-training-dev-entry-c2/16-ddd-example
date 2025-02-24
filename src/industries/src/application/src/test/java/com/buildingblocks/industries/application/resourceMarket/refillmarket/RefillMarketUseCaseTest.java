package com.buildingblocks.industries.application.resourceMarket.refillmarket;

import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.resourceMarket.events.RefilledMarketSupply;
import com.buildingblocks.industries.domain.resourceMarket.events.UpdatedResourcePrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

class RefillMarketUseCaseTest {
    private RefillMarketUseCase useCase;
    private IEventRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepository.class);
        useCase = new RefillMarketUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new RefilledMarketSupply("supply", "Coal", 30, List.of("Coal", "Coal"), 15),
                        new UpdatedResourcePrice("resource", "Coal", 10, 15)
                ));

        RefillMarketRequest request = new RefillMarketRequest(
                "market", "supply","resource",  "Coal", 30, List.of("Coal", "Coal"),  15, 10, 15
        );

        Mockito.doNothing().when(repository).save(Mockito.any());

        StepVerifier
                .create(useCase.execute(request))
                .consumeErrorWith(error -> {
                    System.out.println("Error: " + error.getMessage());
                    error.printStackTrace();
                })
                .verify();
    }
}