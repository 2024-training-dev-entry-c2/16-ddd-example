package com.buildingblocks.industries.application.industry.exhaustindustry;

import com.buildingblocks.shared.application.Request;

public class ExhaustIndustryRequest extends Request {
    private final String id;
    private final String location;
    private final String type;
    private final Boolean isFlipped;

    public ExhaustIndustryRequest(String aggregateId, String id, String location, String type, Boolean isFlipped) {
        super(aggregateId);
        this.id = id;
        this.location = location;
        this.type = type;
        this.isFlipped = isFlipped;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public Boolean getFlipped() {
        return isFlipped;
    }
}
