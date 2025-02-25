package com.buildingblocks.industries.application.industry.activatemarketlink;

import com.buildingblocks.shared.application.Request;

public class ActivateMarketLinkRequest extends Request {
    private String id;
    private Boolean isConnectedToNetwork;

    public ActivateMarketLinkRequest() {
        super(null);
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setConnectedToNetwork(Boolean connectedToNetwork) {
        isConnectedToNetwork = connectedToNetwork;
    }
}
