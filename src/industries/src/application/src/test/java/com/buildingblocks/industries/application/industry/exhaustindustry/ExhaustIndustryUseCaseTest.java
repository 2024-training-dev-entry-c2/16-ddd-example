package com.buildingblocks.industries.application.industry.exhaustindustry;

import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.industry.events.BuiltIndustry;
import com.buildingblocks.industries.domain.industry.events.ExhaustedIndustry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class ExhaustIndustryUseCaseTest {
    private ExhaustIndustryUseCase useCase;
    private IEventRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepository.class);
        useCase = new ExhaustIndustryUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new BuiltIndustry( "Cotton Mill", 1, "Manchester", 8, "Coal", 3, false, "Canals", false),
                        new ExhaustedIndustry("industry-123",  "Manchester", "Cotton Mill", true)
                ));

        ExhaustIndustryRequest request = new ExhaustIndustryRequest("123", "industry-123",  "Manchester", "Cotton Mill",true);

        StepVerifier
                .create(useCase.execute(request))
                .consumeErrorWith(error -> {
                    System.out.println("Error: " + error.getMessage());
                    error.printStackTrace();
                })
                .verify();
    }
}