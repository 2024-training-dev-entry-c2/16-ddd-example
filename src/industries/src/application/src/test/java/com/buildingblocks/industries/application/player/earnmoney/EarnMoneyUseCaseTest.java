package com.buildingblocks.industries.application.player.earnmoney;

import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.industries.domain.player.events.EarnedMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class EarnMoneyUseCaseTest {
    private EarnMoneyUseCase useCase;
    private IEventRepositoryPort repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepositoryPort.class);
        useCase = new EarnMoneyUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new EarnedMoney("player-123", 50, 70)
                ));

        EarnMoneyRequest request = new EarnMoneyRequest(
                "1","player-123",  20, 70
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