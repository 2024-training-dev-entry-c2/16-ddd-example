package com.arknova.zoo_administrator.domain.zoological.values;

import com.arknova.shared.domain.generic.IValueObject;
import com.arknova.shared.domain.utils.Validators;

public class Description implements IValueObject {
    private final String value;

    private Description(final String value) {
        this.value = value;
        validate();
    }

    public static Description of(final String value) {
        return new Description(value);
    }

    @Override
    public void validate() {
        Validators.validateTextNotEmptyOrBlank(value);
    }

    public String getValue() {
        return value;
    }
}
