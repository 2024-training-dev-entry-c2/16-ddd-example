package com.buildingblocks.industries.infra.mainservice.config;

import com.buildingblocks.industries.application.industry.activatemarketlink.ActivateMarketLinkUseCase;
import com.buildingblocks.industries.application.industry.buildindustry.BuildIndustryUseCase;
import com.buildingblocks.industries.application.industry.consumeresource.ConsumeResourceUseCase;
import com.buildingblocks.industries.application.industry.exhaustindustry.ExhaustIndustryUseCase;
import com.buildingblocks.industries.application.industry.flipindustry.FlipIndustryUseCase;
import com.buildingblocks.industries.application.industry.overbuildindustry.OverBuildIndustryUseCase;
import com.buildingblocks.industries.application.industry.upgradeindustry.UpgradeIndustryUseCase;
import com.buildingblocks.industries.application.player.adjustincome.AdjustIncomeUseCase;
import com.buildingblocks.industries.application.player.earnmoney.EarnMoneyUseCase;
import com.buildingblocks.industries.application.player.makepurchase.MakePurchaseUseCase;
import com.buildingblocks.industries.application.player.takeloan.TakeLoanUseCase;
import com.buildingblocks.industries.application.resourceMarket.depletemarket.DepleteMarketUseCase;
import com.buildingblocks.industries.application.resourceMarket.executetrade.ExecuteTradeUseCase;
import com.buildingblocks.industries.application.resourceMarket.refillmarket.RefillMarketUseCase;
import com.buildingblocks.industries.infra.mongo.adapters.MongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    // region Industry
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

    @Bean
    public ExhaustIndustryUseCase exhaustIndustryUseCase(MongoAdapter adapter) {
        return new ExhaustIndustryUseCase(adapter);
    }

    @Bean
    public FlipIndustryUseCase flipIndustryUseCase(MongoAdapter adapter) {
        return new FlipIndustryUseCase(adapter);
    }

    @Bean
    public OverBuildIndustryUseCase overBuildIndustryUseCase(MongoAdapter adapter) {
        return new OverBuildIndustryUseCase(adapter);
    }

    @Bean
    public UpgradeIndustryUseCase upgradeIndustryUseCase(MongoAdapter adapter) {
        return new UpgradeIndustryUseCase(adapter);
    }
    // endregion

    // region Player
    @Bean
    public AdjustIncomeUseCase adjustIncomeUseCase(MongoAdapter adapter) {
        return new AdjustIncomeUseCase(adapter);
    }

    @Bean
    public EarnMoneyUseCase earnMoneyUseCase(MongoAdapter adapter) {
        return new EarnMoneyUseCase(adapter);
    }

    @Bean
    public MakePurchaseUseCase makePurchaseUseCase(MongoAdapter adapter) {
        return new MakePurchaseUseCase(adapter);
    }

    @Bean
    public TakeLoanUseCase takeLoanUseCase(MongoAdapter adapter) {
        return new TakeLoanUseCase(adapter);
    }
    // endregion

    // region ResourceMarket
    @Bean
    public DepleteMarketUseCase depleteMarketUseCase(MongoAdapter adapter) {
        return new DepleteMarketUseCase(adapter);
    }

    @Bean
    public ExecuteTradeUseCase executeTradeUseCase(MongoAdapter adapter) {
        return new ExecuteTradeUseCase(adapter);
    }

    @Bean
    public RefillMarketUseCase refillMarketUseCase(MongoAdapter adapter) {
        return new RefillMarketUseCase(adapter);
    }
    // endregion
}
