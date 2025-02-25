package com.buildingblocks.industries.application.industry.exhaustindustry;

import com.buildingblocks.shared.application.Request;

public class ExhaustIndustryRequest extends Request {
    private String id;
    private String location;
    private String type;
    private Boolean isFlipped;

    public ExhaustIndustryRequest() {
        super(null);
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFlipped(Boolean flipped) {
        isFlipped = flipped;
    }
}
