package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ActivatedIndustryMarketLink extends DomainEvent {
    private String id;
    private Boolean isConnectedToNetwork;

    public ActivatedIndustryMarketLink() {
        super(EventsEnum.ACTIVATED_INDUSTRY_MARKETLINK.name());
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setConnectedToNetwork(Boolean connectedToNetwork) {
        isConnectedToNetwork = connectedToNetwork;
    }
}
