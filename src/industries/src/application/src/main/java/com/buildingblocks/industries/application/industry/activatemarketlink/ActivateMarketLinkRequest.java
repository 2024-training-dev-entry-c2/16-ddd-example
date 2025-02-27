package com.buildingblocks.industries.application.industry.activatemarketlink;

import com.buildingblocks.shared.application.Request;

public class ActivateMarketLinkRequest extends Request {
    private String id;

    private Boolean connectedToNetwork;

    public ActivateMarketLinkRequest() {
        super(null);
    }

    public ActivateMarketLinkRequest(String aggregateId, String id, Boolean connectedToNetwork) {
        super(aggregateId);
        this.id = id;
        this.connectedToNetwork = connectedToNetwork;
    }

    public String getId() {
        return id;
    }

    public Boolean getConnectedToNetwork() {
        return connectedToNetwork;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setConnectedToNetwork(Boolean connectedToNetwork) {
        this.connectedToNetwork = connectedToNetwork;
    }
}
