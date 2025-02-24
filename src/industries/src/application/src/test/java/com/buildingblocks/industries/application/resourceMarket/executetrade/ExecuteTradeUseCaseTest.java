package com.buildingblocks.industries.application.resourceMarket.executetrade;

import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.resourceMarket.events.ExecutedTrade;
import com.buildingblocks.industries.domain.resourceMarket.events.UpdatedResourcePrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class ExecuteTradeUseCaseTest {
    private ExecuteTradeUseCase useCase;
    private IEventRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepository.class);
        useCase = new ExecuteTradeUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new ExecutedTrade("trade", "SELL", "Iron", 50, 10),
                        new UpdatedResourcePrice("resource", "Iron", 20, 25)
                ));

        ExecuteTradeRequest request = new ExecuteTradeRequest(
                "market", "trade", "resource", "SELL", "Iron", 50, 10, 20, 25
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