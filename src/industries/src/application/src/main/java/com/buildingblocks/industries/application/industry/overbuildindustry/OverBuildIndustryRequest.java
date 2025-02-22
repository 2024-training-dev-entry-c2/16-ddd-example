package com.buildingblocks.industries.application.industry.overbuildindustry;

import com.buildingblocks.shared.application.Request;

public class OverBuildIndustryRequest extends Request {
    private final String id;
    private final String type;
    private final Integer level;
    private final Integer cost;

    public OverBuildIndustryRequest(String aggregateId, String id, String type, Integer level, Integer cost) {
        super(aggregateId);
        this.id = id;
        this.type = type;
        this.level = level;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getCost() {
        return cost;
    }
}
