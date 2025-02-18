package com.buildingblocks.industries.domain.resourceMarket.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class TradeType implements IValueObject {
    private final String value;

    private TradeType(String value) {
        this.value = value;
        validate();
    }

    public static TradeType of(String value) {
        return new TradeType(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateStringNotEmpty(value, "TradeType");
        ValueValidator.validateStringNotBlank(value, "TradeType");
        ValueValidator.validateSpecialCharacters(value, "TradeType");
        ValueValidator.validateTradeType(value, "TradeType");
    }

    public String getValue() {
        return value;
    }
}
