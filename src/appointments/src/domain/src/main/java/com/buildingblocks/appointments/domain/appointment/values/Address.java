package com.buildingblocks.appointments.domain.appointment.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Address implements IValueObject {
  private final String street;
  private final String number;
  private final String zip;

  private Address(String street, String number, String zip) {
    this.street = street;
    this.number = number;
    this.zip = zip;
    validate();
  }

  public static Address of(String street, String number, String zip) {
    return new Address(street, number, zip);
  }

  @Override
  public void validate() {
    if (this.street == null) {
      throw new IllegalArgumentException("The street cant be null");
    }

    if (this.street.isBlank()) {
      throw new IllegalArgumentException("The street cant be blank");
    }

    if (!this.street.matches("^[a-zA-Z0-9\\s]*$")) {
      throw new IllegalArgumentException("The street can only contain alphanumeric characters and spaces");
    }
  }

  public String getStreet() {
    return street;
  }

  public String getNumber() {
    return number;
  }

  public String getZip() {
    return zip;
  }
}
