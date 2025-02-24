package com.buildingclue.gameDynamics.domain.incident;

import com.buildingclue.gameDynamics.domain.incident.entities.Location;
import com.buildingclue.gameDynamics.domain.incident.entities.Suspect;
import com.buildingclue.gameDynamics.domain.incident.entities.Weapon;
import com.buildingclue.gameDynamics.domain.incident.values.Clue;
import com.buildingclue.gameDynamics.domain.incident.values.Description;
import com.buildingclue.gameDynamics.domain.incident.values.IncidentId;
import com.buildingclue.gameDynamics.domain.incident.values.Name;
import com.buildingclue.gameDynamics.domain.incident.values.Occupation;
import com.buildingclue.gameDynamics.domain.incident.values.StatusCase;
import com.buildingclue.gameDynamics.domain.incident.values.Type;
import com.buildingclue.shared.domain.constants.States;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IncidentTest {
  private Incident incident;

  @BeforeEach
  void setUp() {
    incident = new Incident();
  }

  @Test
  void testStartInvestigation() {
    assertEquals(States.OPEN, incident.getStatus().getState(), "El estado inicial del caso debe ser OPEN");
  }


  @Test
  void testAddClue() {
    Clue clue = new Clue("Huella digital");
    assertDoesNotThrow(() -> incident.addClue(clue), "Agregar una pista no debe lanzar excepciones");
  }

  @Test
  void testIdentifySuspect() {
    Name name = new Name("John Doe");
    Occupation occupation = new Occupation("Thief");
    Suspect suspect = new Suspect(name, occupation);

    assertDoesNotThrow(() -> incident.identifySuspect(suspect), "Identificar un sospechoso no debe lanzar excepciones");
  }

  @Test
  void testIdentifyWeapon() {
    Name weaponName = new Name("Cuchillo");
    Type weaponType = new Type("Sharp");
    Weapon weapon = new Weapon(weaponName, weaponType);

    assertDoesNotThrow(() -> incident.identifyWeapon(weapon), "Identificar un arma no debe lanzar excepciones");
  }

  @Test
  void testDetermineLocation() {
    Name locationName = new Name("Cocina");
    Type locationType = new Type("Indoor");
    Location location = new Location(locationName, new Description("Lugar cerrado"));

    assertDoesNotThrow(() -> incident.determineLocation(location), "Determinar una ubicación no debe lanzar excepciones");
  }

  @Test
  void testEliminateSuspect() {
    Name name = new Name("John Doe");
    Occupation occupation = new Occupation("Thief");
    Suspect suspect = new Suspect(name, occupation);
    incident.setSuspect(suspect);

    assertDoesNotThrow(() -> incident.eliminateSuspect(), "Eliminar un sospechoso no debe lanzar excepciones");

    assertNull(incident.getSuspect(), "El sospechoso debe ser eliminado correctamente");
  }


  @Test
  void testSolveCase() {
    Suspect suspect = new Suspect(new Name("John Doe"), new Occupation("Thief"));
    Weapon weapon = new Weapon(new Name("Cuchillo"), new Type("Sharp"));
    Location location = new Location(new Name("Cocina"), new Description("Lugar cerrado"));

    incident.setSuspect(suspect);
    incident.setWeapon(weapon);
    incident.setLocation(location);

    assertDoesNotThrow(() -> incident.solveCase(), "Resolver un caso con información completa no debe lanzar excepciones");

    assertEquals(States.SOLVED, incident.getStatus().getState(), "El caso debe marcarse como cerrado después de resolverse");

  }

  @Test
  void testSetCluesWithNull() {
    assertDoesNotThrow(() -> incident.setClues(null), "Establecer pistas como null no debe lanzar excepciones");
  }

  @Test
  void testSolveCaseWithoutAllData() {
    assertDoesNotThrow(() -> incident.solveCase(), "Resolver un caso sin toda la información no debe lanzar excepciones");
  }

  @Test
  void testSetClues() {
    List<Clue> clues = new ArrayList<>();
    clues.add(new Clue("Huella digital"));
    assertDoesNotThrow(() -> incident.setClues(clues), "Establecer pistas no debe lanzar excepciones");
  }

  @Test
  void testGetWeapon() {
    Weapon weapon = new Weapon(new Name("Cuchillo"), new Type("Sharp"));
    incident.setWeapon(weapon);

    assertEquals(weapon, incident.getWeapon(), "El arma recuperada debe coincidir con la establecida");
  }

  @Test
  void testSetWeapon() {
    Weapon weapon = new Weapon(new Name("Cuchillo"), new Type("Sharp"));
    assertDoesNotThrow(() -> incident.setWeapon(weapon), "Establecer arma no debe lanzar excepciones");
  }

  @Test
  void testGetLocation() {
    Location location = new Location(new Name("Cocina"), new Description("Lugar cerrado"));
    incident.setLocation(location);

    assertEquals(location, incident.getLocation(), "La ubicación recuperada debe coincidir con la establecida");
  }

  @Test
  void testSetLocation() {
    Location location = new Location(new Name("Cocina"), new Description("Lugar cerrado"));
    assertDoesNotThrow(() -> incident.setLocation(location), "Establecer ubicación no debe lanzar excepciones");
  }

  @Test
  void testSetStatus() {
    StatusCase status = StatusCase.of(States.FINISHED);
    assertDoesNotThrow(() -> incident.setStatus(status), "Establecer estado del caso no debe lanzar excepciones");
  }

  @Test
  void testInitializeWithParameters() {
    IncidentId incidentId = new IncidentId();
    StatusCase status = StatusCase.of(States.OPEN);
    Suspect suspect = new Suspect(new Name("John Doe"), new Occupation("Thief"));
    Weapon weapon = new Weapon(new Name("Cuchillo"), new Type("Sharp"));
    Location location = new Location(new Name("Cocina"), new Description("Lugar cerrado"));
    List<String> clues = List.of("Huella digital");

    Incident incidentWithParams = Incident.createWithParams(incidentId, status, suspect, weapon, location, clues);

    assertNotNull(incidentWithParams, "El incidente inicializado con parámetros no debe ser null");
  }
}
