package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class UpgradeStageId extends Identity {

    public UpgradeStageId() {
        super();
    }

    private UpgradeStageId(String value) {
        super(value);
    }

    public static UpgradeStageId of(String value) {
        return new UpgradeStageId(value);
    }
}
