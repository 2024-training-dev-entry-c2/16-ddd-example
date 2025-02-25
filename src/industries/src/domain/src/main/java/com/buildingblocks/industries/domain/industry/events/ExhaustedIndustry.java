package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ExhaustedIndustry extends DomainEvent {
    private String id;
    private String location;
    private String type;
    private Boolean isFlipped;

    public ExhaustedIndustry() {
        super(EventsEnum.EXHAUSTED_INDUSTRY.name());
    }

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
