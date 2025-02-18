package com.buildingblocks.industries.domain.resourceMarket.values;

import com.buildingblocks.industries.domain.industry.values.IndustryId;
import com.buildingblocks.shared.domain.generic.Identity;

public class ResourceMarketId extends Identity {

    public ResourceMarketId() {
        super();
    }

    private ResourceMarketId(String value) {
        super(value);
    }

    public static ResourceMarketId of(String value) {
        return new ResourceMarketId(value);
    }
}
