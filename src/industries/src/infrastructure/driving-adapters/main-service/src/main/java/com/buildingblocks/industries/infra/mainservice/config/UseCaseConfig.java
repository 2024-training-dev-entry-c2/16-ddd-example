package com.buildingblocks.industries.infra.mainservice.config;

import com.buildingblocks.industries.application.industry.activatemarketlink.ActivateMarketLinkUseCase;
import com.buildingblocks.industries.application.industry.buildindustry.BuildIndustryUseCase;
import com.buildingblocks.industries.application.industry.consumeresource.ConsumeResourceUseCase;
import com.buildingblocks.industries.infra.mongo.adapters.MongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public BuildIndustryUseCase buildIndustryUseCase(MongoAdapter adapter) {
        return new BuildIndustryUseCase(adapter);
    }

    @Bean
    public ActivateMarketLinkUseCase activateMarketLinkUseCase(MongoAdapter adapter) {
        return new ActivateMarketLinkUseCase(adapter);
    }

    @Bean
    public ConsumeResourceUseCase consumeResourceUseCase(MongoAdapter adapter) {
        return new ConsumeResourceUseCase(adapter);
    }
}
