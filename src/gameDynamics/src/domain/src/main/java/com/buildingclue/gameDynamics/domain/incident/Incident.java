package com.buildingclue.gameDynamics.domain.incident;

import com.buildingclue.gameDynamics.domain.incident.entities.Location;
import com.buildingclue.gameDynamics.domain.incident.entities.Suspect;
import com.buildingclue.gameDynamics.domain.incident.entities.Weapon;
import com.buildingclue.gameDynamics.domain.incident.events.CaseSolved;
import com.buildingclue.gameDynamics.domain.incident.events.ClueDiscovered;
import com.buildingclue.gameDynamics.domain.incident.events.InvestigationStarted;
import com.buildingclue.gameDynamics.domain.incident.events.LocationDetermined;
import com.buildingclue.gameDynamics.domain.incident.events.SuspectEliminated;
import com.buildingclue.gameDynamics.domain.incident.events.SuspectIdentified;
import com.buildingclue.gameDynamics.domain.incident.events.WeaponIdentified;
import com.buildingclue.gameDynamics.domain.incident.values.Clue;
import com.buildingclue.gameDynamics.domain.incident.values.IncidentId;
import com.buildingclue.gameDynamics.domain.incident.values.StatusCase;
import com.buildingclue.shared.domain.constants.States;
import com.buildingclue.shared.domain.generic.AggregateRoot;

import java.util.ArrayList;
import java.util.List;

public class Incident extends AggregateRoot<IncidentId> {

  private StatusCase status;
  private Suspect suspect;
  private Weapon weapon;
  private Location location;
  private List<Clue> clues;

  public Incident() {
    super(new IncidentId());
    this.status = StatusCase.of(States.OPEN);
    this.clues = new ArrayList<>();
    subscribe(new IncidentHandler(this));
  }

  private Incident(IncidentId id, StatusCase status, Suspect suspect, Weapon weapon, Location location, List<String> clues) {
    super(id);
    this.status = status;
    this.suspect = suspect;
    this.weapon = weapon;
    this.location = location;
    this.clues = (clues != null)
            ? clues.stream().map(Clue::new).toList()
            : new ArrayList<>();
    subscribe(new IncidentHandler(this));
    apply(new InvestigationStarted(id.getValue()));
  }

  public static Incident createWithParams(IncidentId id, StatusCase status, Suspect suspect, Weapon weapon, Location location, List<String> clues) {
    return new Incident(id, status, suspect, weapon, location, clues);
  }

  public StatusCase getStatus() {
    return status;
  }

  public void setStatus(StatusCase status) {
    this.status = status;
  }

  public Suspect getSuspect() {
    return suspect;
  }

  public void setSuspect(Suspect suspect) {
    this.suspect = suspect;
  }

  public Weapon getWeapon() {
    return weapon;
  }

  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public List<Clue> getClues() {
    return clues;
  }

  public void setClues(List<Clue> clues) {
    this.clues = clues;
  }

  public void addClue(Clue clue) {
    if (!this.clues.contains(clue)) {
      this.clues.add(clue);
      apply(new ClueDiscovered(this.getIdentity().getValue(), clue.getValue()));
    }
  }

  public void identifySuspect(Suspect suspect) {
    apply(new SuspectIdentified(this.getIdentity().getValue(), suspect.getName().getValue()));
  }

  public void identifyWeapon(Weapon weapon) {
    apply(new WeaponIdentified(this.getIdentity().getValue(), weapon.getName().getValue()));
  }

  public void determineLocation(Location location) {
    apply(new LocationDetermined(this.getIdentity().getValue(), location.getName().getValue()));
  }

  public void eliminateSuspect() {
    apply(new SuspectEliminated(this.getIdentity().getValue(), this.suspect.getName().getValue()));
  }

  public void solveCase() {
    if (!this.status.equals(StatusCase.of(States.SOLVED))) {
      apply(new CaseSolved(this.getIdentity().getValue()));
      this.status = StatusCase.of(States.SOLVED);
    }
  }
}
