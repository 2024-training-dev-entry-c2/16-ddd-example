package com.buildingblocks.shared.domain.utils;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ValueValidator {
    private static final Set<String> VALID_TYPES = Set.of("Cotton Mill", "Coal Mine", "Iron Work", "Manufacturer", "Pottery", "Brewery");
    private static final Set<String> VALID_ERAS = Set.of("Canals", "Rails");
    private static final Set<String> VALID_RESOURCES = Set.of("Iron", "Coal");
    private static final Set<String> VALID_TRADE_TYPES = Set.of("Buy", "Sell");

    public static void validateNotNull(Object value, String fieldName) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }

    public static void validatePositive(Integer value, String fieldName) {
        if (value != null && value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be greater than zero");
        }
    }

    public static void validateNonNegative(Integer value, String fieldName) {
        if (value != null && value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
    }

    public static void validateStringNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty or null");
        }
    }

    public static void validateBooleanNotNull(Boolean value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }

    public static void validateListNotEmpty(List<?> value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty or null");
        }
    }

    public static void validateStringNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
    }

    public static void validateType(String value, String fieldName) {
        if (!VALID_TYPES.contains(value)) {
            throw new IllegalArgumentException(fieldName + " must be one of: " + VALID_TYPES);
        }
    }

    public static void validateEra(String value, String fieldName) {
        if (!VALID_ERAS.contains(value)) {
            throw new IllegalArgumentException(fieldName + " must be either 'Canals' or 'Rails'");
        }
    }

    public static void validateResource(String value, String fieldName) {
        if (!VALID_RESOURCES.contains(value)) {
            throw new IllegalArgumentException(fieldName + " must be either 'Iron' or 'Coal'");
        }
    }

    public static void validateSpecialCharacters(String value, String fieldName) {
        if (!value.matches("^[a-zA-Z ]+$")) {
            throw new IllegalArgumentException(fieldName + " must contain only letters and spaces");
        }
    }

    public static void validateTradeType(String value, String fieldName) {
        if (!VALID_TRADE_TYPES.contains(value)) {
            throw new IllegalArgumentException(fieldName + " must be either 'Buy' or 'Sell'");
        }
    }

}
