package com.buildingclue.shared.domain.utils;

import java.util.List;

public class ValidationUtils {

  public static void validateString(String input, String errorMessage) {
    if (input == null || input.trim().isEmpty()) {
      throw new IllegalArgumentException(errorMessage);
    }
  }

  public static void validateInteger(Integer input, String errorMessage) {
    if (input == null || input < 0) {
      throw new IllegalArgumentException(errorMessage);
    }
  }

  public static void validatePositiveInteger(Integer input, String errorMessage) {
    if (input == null || input <= 0) {
      throw new IllegalArgumentException(errorMessage);
    }
  }

  public static void validateNotNull(Object input, String errorMessage) {
    if (input == null) {
      throw new IllegalArgumentException(errorMessage);
    }
  }

  public static void validateStringList(List<String> input, String errorMessage) {
    if (input == null || input.isEmpty()) {
      throw new IllegalArgumentException(errorMessage);
    }
    for (String item : input) {
      validateString(item, errorMessage);
    }
  }

  public static void validateObjectList(List<?> input, String errorMessage) {
    if (input == null || input.isEmpty()) {
      throw new IllegalArgumentException(errorMessage);
    }
  }

  public static void validateRange(int value, int min, int max, String errorMessage) {
    if (value < min || value > max) {
      throw new IllegalArgumentException(errorMessage);
    }
  }

  public static void validateBooleanCondition(boolean condition, String errorMessage) {
    if (!condition) {
      throw new IllegalArgumentException(errorMessage);
    }
  }
}
