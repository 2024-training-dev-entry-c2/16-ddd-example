package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ActivatedIndustryMarketLink extends DomainEvent {
    private final String id;
    private final Boolean isConnectedToNetwork;

    public ActivatedIndustryMarketLink(String id, Boolean isConnectedToNetwork) {
        super(EventsEnum.ACTIVATED_INDUSTRY_MARKETLINK.name());
        this.id = id;
        this.isConnectedToNetwork = isConnectedToNetwork;
    }

    public String getId() {
        return id;
    }

    public Boolean getConnectedToNetwork() {
        return isConnectedToNetwork;
    }
}
