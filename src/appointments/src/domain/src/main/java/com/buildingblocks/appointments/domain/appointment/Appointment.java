package com.buildingblocks.appointments.domain.appointment;

import com.buildingblocks.appointments.domain.appointment.entities.MedicalOffice;
import com.buildingblocks.appointments.domain.appointment.entities.Patient;
import com.buildingblocks.appointments.domain.appointment.entities.Specialist;
import com.buildingblocks.appointments.domain.appointment.events.SpecialistAssigned;
import com.buildingblocks.appointments.domain.appointment.values.AppointmentId;
import com.buildingblocks.appointments.domain.appointment.values.Details;
import com.buildingblocks.appointments.domain.appointment.values.Schedule;
import com.buildingblocks.shared.domain.generic.AggregateRoot;

public class Appointment extends AggregateRoot<AppointmentId> {
  private Specialist specialist;
  private Patient patient;
  private MedicalOffice medicalOffice;
  private Schedule schedule;
  private Details details;

  // region Constructors
  public Appointment() {
    super(new AppointmentId());
  }

  private Appointment(AppointmentId identity) {
    super(identity);
  }
  // endregion

  // region Getters and Setters
  public Specialist getSpecialist() {
    return specialist;
  }

  public void setSpecialist(Specialist specialist) {
    this.specialist = specialist;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public MedicalOffice getMedicalOffice() {
    return medicalOffice;
  }

  public void setMedicalOffice(MedicalOffice medicalOffice) {
    this.medicalOffice = medicalOffice;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
  }

  public Details getDetails() {
    return details;
  }

  public void setDetails(Details details) {
    this.details = details;
  }
  // endregion

  // region Domain Actions
  public void assignSpecialist(String firstName, String lastName, String email, String specialty) {
    apply(new SpecialistAssigned(firstName, lastName, email, specialty));
  }
  // endregion

  // region Public Methods
  // endregion

  // region Private Methods
  // endregion
}
