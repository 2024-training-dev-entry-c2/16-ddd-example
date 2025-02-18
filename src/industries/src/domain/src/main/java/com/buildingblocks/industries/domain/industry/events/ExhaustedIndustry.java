package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ExhaustedIndustry extends DomainEvent {
    private final String id;
    private final String location;
    private final String type;
    private final Boolean isFlipped;

    public ExhaustedIndustry(String id, String location, String type, Boolean isFlipped) {
        super(EventsEnum.EXHAUSTED_INDUSTRY.name());
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
