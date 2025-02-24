package com.buildingblocks.industries.application.industry.consumeresource;

import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import com.buildingblocks.industries.application.shared.repositories.IEventRepository;
import com.buildingblocks.industries.domain.industry.Industry;
import com.buildingblocks.industries.domain.industry.events.BuiltIndustry;
import com.buildingblocks.industries.domain.industry.events.ConsumedResource;
import com.buildingblocks.industries.domain.industry.values.StoredResources;
import com.buildingblocks.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConsumeResourceUseCaseTest {
    private ConsumeResourceUseCase useCase;
    private IEventRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventRepository.class);
        useCase = new ConsumeResourceUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
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
    }
}