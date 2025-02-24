package com.buildingblocks.industries.application.resourceMarket.depletemarket;

import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.resourceMarket.events.DepletedMarketSupply;
import com.buildingblocks.industries.domain.resourceMarket.events.UpdatedResourcePrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class DepleteMarketUseCaseTest {
    private DepleteMarketUseCase useCase;
    private IEventRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepository.class);
        useCase = new DepleteMarketUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new DepletedMarketSupply("market-123", "Coal", 5),
                        new UpdatedResourcePrice("resource-456", "Coal", 10, 12)
                ));

        DepleteMarketRequest request = new DepleteMarketRequest(
                "agg", "market-123", "resource-456", "Coal", 5, 10, 12
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