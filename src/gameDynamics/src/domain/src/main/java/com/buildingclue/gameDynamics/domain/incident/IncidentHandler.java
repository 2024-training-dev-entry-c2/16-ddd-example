package com.buildingclue.gameDynamics.domain.incident;

import com.buildingclue.gameDynamics.domain.incident.events.CaseSolved;
import com.buildingclue.gameDynamics.domain.incident.events.ClueDiscovered;
import com.buildingclue.gameDynamics.domain.incident.events.SuspectEliminated;
import com.buildingclue.gameDynamics.domain.incident.values.Clue;
import com.buildingclue.gameDynamics.domain.incident.values.StatusCase;
import com.buildingclue.shared.domain.constants.States;
import com.buildingclue.shared.domain.generic.DomainActionsContainer;
import com.buildingclue.shared.domain.generic.DomainEvent;

import java.util.function.Consumer;

public class IncidentHandler extends DomainActionsContainer {
  public IncidentHandler(Incident incident) {
    addAction(handleClueDiscovered(incident));
    addAction(handleCaseSolved(incident));
    addAction(handleSuspectEliminated(incident));
  }

  private Consumer<? super DomainEvent> handleClueDiscovered(Incident incident) {
    return event -> {
      if (event instanceof ClueDiscovered) {
        ClueDiscovered clueEvent = (ClueDiscovered) event;
        Clue clue = Clue.of(clueEvent.getClue());
        if (!incident.getClues().contains(clue)) {
          incident.getClues().add(clue);
        }
      }
    };
  }

  private Consumer<? super DomainEvent> handleCaseSolved(Incident incident) {
    return event -> {
      if (event instanceof CaseSolved) {
        incident.setStatus(StatusCase.of(States.SOLVED));
      }
    };
  }

  private Consumer<? super DomainEvent> handleSuspectEliminated(Incident incident) {
    return event -> {
      if (event instanceof SuspectEliminated) {
        System.out.println("Sospechoso eliminado: " + ((SuspectEliminated) event).getSuspectName());

        incident.setSuspect(null);
      }
    };
  }

}
