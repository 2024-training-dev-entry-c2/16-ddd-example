package com.buildingblocks.industries.domain.resourceMarket.values;

import com.buildingblocks.industries.domain.industry.values.Level;
import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

import java.util.ArrayList;
import java.util.List;

public class AvailableResources implements IValueObject {
    private final List<String> value;

    private AvailableResources(List<String> value) {
        this.value = value;
        validate();
    }

    public static AvailableResources of(List<String> value) {
        return new AvailableResources(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateListNotEmpty(value, "AvailableResources");
    }

    public List<String> getValue() {
        return value;
    }

    public AvailableResources reduceQuantity(int quantityToRemove) {
        if (quantityToRemove > 0 && quantityToRemove <= value.size()) {
            List<String> newValue = new ArrayList<>(value);
            for (int i = 0; i < quantityToRemove; i++) newValue.remove(0);
            return new AvailableResources(newValue);
        }
        return this;
    }

    public AvailableResources increaseQuantity(int quantityToAdd, String resourceType) {
        List<String> newValue = new ArrayList<>(value);
        for (int i = 0; i < quantityToAdd; i++) newValue.add(resourceType);
        return new AvailableResources(newValue);
    }
}
