package com.buildingblocks.industries.domain.player.values;

import com.buildingblocks.industries.domain.industry.values.IndustryId;
import com.buildingblocks.shared.domain.generic.Identity;

public class LoanId extends Identity {

    public LoanId() {
        super();
    }

    private LoanId(String value) {
        super(value);
    }

    public static LoanId of(String value) {
        return new LoanId(value);
    }
}
