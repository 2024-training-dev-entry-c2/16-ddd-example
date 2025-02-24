package com.buildingblocks.shared.application.shared.domain.utils;

import java.util.List;

public class Validator {

  public static void validateNotNull(Object value) {
    if (value == null) {
      throw new IllegalArgumentException( " can't be null");
    }
  }
  public static void validateTextNotNull(String value) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException( " can't be null");
    }
  }

  public static void validatePositive(Integer value) {
    if (value == null || value < 0) {
      throw new IllegalArgumentException(  " must be a positive number");
    }
  }

  public static void validateNotBlank(String value) {
    if (value.isBlank()) {
      throw new IllegalArgumentException( " can't be blank");
    }
  }

  public static void validateAlphanumericCharacters(String value) {
    if (!value.matches("^[a-zA-Z0-9\\s]*$")) {
      throw new IllegalArgumentException( " can only contain alphanumeric characters spaces");
    }
  }

  public static void validateListNotEmpty(List<?> list) {
    if (list == null || list.isEmpty()) {
      throw new IllegalArgumentException( " can't be empty");
    }
  }




}
