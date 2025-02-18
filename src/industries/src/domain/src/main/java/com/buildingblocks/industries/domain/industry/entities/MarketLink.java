package com.buildingblocks.industries.domain.industry.entities;

import com.buildingblocks.industries.domain.industry.values.Destination;
import com.buildingblocks.industries.domain.industry.values.IsConnectedToNetwork;
import com.buildingblocks.industries.domain.industry.values.MarketLinkId;
import com.buildingblocks.industries.domain.industry.values.Origin;
import com.buildingblocks.shared.domain.generic.Entity;

public class MarketLink extends Entity<MarketLinkId> {
    private Origin origin;
    private Destination destination;
    private IsConnectedToNetwork isConnectedToNetwork;

    // region Constructors
    public MarketLink(Origin origin, Destination destination, IsConnectedToNetwork isConnectedToNetwork) {
        super(new MarketLinkId());
        this.origin = origin;
        this.destination = destination;
        this.isConnectedToNetwork = isConnectedToNetwork;
    }

    public MarketLink(MarketLinkId identity, Origin origin, Destination destination, IsConnectedToNetwork isConnectedToNetwork) {
        super(identity);
        this.origin = origin;
        this.destination = destination;
        this.isConnectedToNetwork = isConnectedToNetwork;
    }
    // endregion

    // region Public Methods
    public void activate() {
        if (this.isConnectedToNetwork.getValue()) {
            throw new IllegalStateException("Market link is already active.");
        }
        this.isConnectedToNetwork = IsConnectedToNetwork.of(true);
    }

    public boolean isValidLink() {
        return this.origin != null && this.destination != null;
    }
    // endregion

    // region Getters and Setters
    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public IsConnectedToNetwork getIsConnectedToNetwork() {
        return isConnectedToNetwork;
    }

    public void setIsConnectedToNetwork(IsConnectedToNetwork isConnectedToNetwork) {
        this.isConnectedToNetwork = isConnectedToNetwork;
    }
    // endregion
}
