package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class IndustryId extends Identity {

    public IndustryId() {
        super();
    }

    private IndustryId(String value) {
        super(value);
    }

    public static IndustryId of(String value) {
        return new IndustryId(value);
    }
}
