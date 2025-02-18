package com.buildingblocks.industries.domain.resourceMarket.values;

import com.buildingblocks.industries.domain.industry.values.IndustryId;
import com.buildingblocks.shared.domain.generic.Identity;

public class TradeExchangeId extends Identity {

    public TradeExchangeId() {
        super();
    }

    private TradeExchangeId(String value) {
        super(value);
    }

    public static TradeExchangeId of(String value) {
        return new TradeExchangeId(value);
    }
}
