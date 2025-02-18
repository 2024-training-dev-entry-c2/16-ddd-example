package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

import java.util.List;

public class StoredResources implements IValueObject {
    private final List<String> value;

    private StoredResources(List<String> value) {
        this.value = value;
        validate();
    }

    public static StoredResources of(List<String> value) {
        return new StoredResources(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateListNotEmpty(value, "StoredResources");
    }

    public List<String> getValue() {
        return value;
    }
}
