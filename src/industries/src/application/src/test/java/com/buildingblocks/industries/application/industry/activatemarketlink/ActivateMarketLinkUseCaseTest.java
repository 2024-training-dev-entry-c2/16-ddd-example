package com.buildingblocks.industries.application.industry.activatemarketlink;

import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.industry.events.ActivatedIndustryMarketLink;
import com.buildingblocks.industries.domain.industry.events.BuiltIndustry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class ActivateMarketLinkUseCaseTest {
    private ActivateMarketLinkUseCase useCase;
    private IEventRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepository.class);
        useCase = new ActivateMarketLinkUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new BuiltIndustry("Iron Work", 1, "Birmingham", 10, "Coal", 2, false, "Canals", false),
                        new ActivatedIndustryMarketLink("1", true)
                ));

        ActivateMarketLinkRequest request = new ActivateMarketLinkRequest("aggregate123", "1", true);

        StepVerifier
                .create(useCase.execute(request))
                .consumeErrorWith(error -> {
                    System.out.println("Error: " + error.getMessage());
                    error.printStackTrace();
                })
                .verify();
    }
}