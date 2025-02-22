package com.buildingblocks.industries.application.player.adjustincome;

import com.buildingblocks.shared.application.Request;

public class AdjustIncomeRequest extends Request {
    private final String id;
    private final Integer changedIncome;
    private final String reason;
    private final Integer updatedIncome;

    public AdjustIncomeRequest(String aggregateId, String id, Integer changedIncome, String reason, Integer updatedIncome) {
        super(aggregateId);
        this.id = id;
        this.changedIncome = changedIncome;
        this.reason = reason;
        this.updatedIncome = updatedIncome;
    }

    public String getId() {
        return id;
    }

    public Integer getChangedIncome() {
        return changedIncome;
    }

    public String getReason() {
        return reason;
    }

    public Integer getUpdatedIncome() {
        return updatedIncome;
    }
}
