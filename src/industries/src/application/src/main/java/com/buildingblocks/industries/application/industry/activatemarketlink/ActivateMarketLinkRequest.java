package com.buildingblocks.industries.application.industry.activatemarketlink;

import com.buildingblocks.shared.application.Request;

public class ActivateMarketLinkRequest extends Request {
    private final String id;
    private final Boolean isConnectedToNetwork;

    public ActivateMarketLinkRequest(String aggregateId, String id, Boolean isConnectedToNetwork) {
        super(aggregateId);
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
