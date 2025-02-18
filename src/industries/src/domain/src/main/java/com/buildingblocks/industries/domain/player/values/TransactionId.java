package com.buildingblocks.industries.domain.player.values;

import com.buildingblocks.industries.domain.industry.values.IndustryId;
import com.buildingblocks.shared.domain.generic.Identity;

public class TransactionId extends Identity {

    public TransactionId() {
        super();
    }

    private TransactionId(String value) {
        super(value);
    }

    public static TransactionId of(String value) {
        return new TransactionId(value);
    }
}