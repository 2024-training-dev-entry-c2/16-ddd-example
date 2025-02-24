package com.buildingblocks.industries.application.player.makepurchase;

import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.player.events.ExecutedTransaction;
import com.buildingblocks.industries.domain.player.events.SpentBudget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class MakePurchaseUseCaseTest {
    private MakePurchaseUseCase useCase;
    private IEventRepositoryPort repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepositoryPort.class);
        useCase = new MakePurchaseUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new SpentBudget("asdasd", 30, 70, "Purchase"),
                        new ExecutedTransaction("111", "coal", 30, 70)
                ));

        MakePurchaseRequest request = new MakePurchaseRequest(
                "123", "asdasd", "111", 30, 70, "Purchase", "coal", 70
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