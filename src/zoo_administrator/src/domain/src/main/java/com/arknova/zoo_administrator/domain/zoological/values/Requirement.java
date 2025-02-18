package com.arknova.zoo_administrator.domain.zoological.values;

import com.arknova.shared.domain.generic.IValueObject;
import com.arknova.shared.domain.utils.Validators;

public class Requirement implements IValueObject {
    private final Integer value;

    private Requirement(final Integer value) {
        this.value = value;
        validate();
    }

    public static Requirement of(final Integer value) {
        return new Requirement(value);
    }

    @Override
    public void validate() {
        Validators.validateValueGreaterThanZero(Double.valueOf(value));
    }

    public Integer getValue() {
        return value;
    }
}
