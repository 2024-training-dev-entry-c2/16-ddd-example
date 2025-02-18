package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class MarketLinkId extends Identity {

    public MarketLinkId() {
        super();
    }

    private MarketLinkId(String value) {
        super(value);
    }

    public static MarketLinkId of(String value) {
        return new MarketLinkId(value);
    }
}
