package com.buildingclue.gameDynamics.application.incident.identifyweapon;

import com.buildingclue.gameDynamics.application.game.shared.IEventsRepository;
import com.buildingclue.gameDynamics.domain.incident.Incident;
import com.buildingclue.gameDynamics.domain.incident.entities.Weapon;
import com.buildingclue.gameDynamics.domain.incident.values.IncidentId;
import com.buildingclue.gameDynamics.domain.incident.values.Name;
import com.buildingclue.gameDynamics.domain.incident.values.Type;
import com.buildingclue.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class IdentifyWeaponUseCase implements ICommandUseCase<IdentifyWeaponRequest, Mono<IdentifyWeaponResponse>> {

  private final IEventsRepository eventsRepository;

  public IdentifyWeaponUseCase(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<IdentifyWeaponResponse> execute(IdentifyWeaponRequest request) {
    return eventsRepository.findEventsByAggregateId(request.getAggregateId())
            .collectList()
            .map(events -> {
              Incident incident = Incident.createWithParams(
                      IncidentId.of(request.getAggregateId()),
                      null, null, null, null,
                      null
              );

              incident.identifyWeapon(new Weapon(
                      new Name(request.getWeaponName()),
                      new Type("Unknown")
              ));


              incident.getUncommittedEvents().forEach(eventsRepository::save);
              incident.markEventsAsCommitted();

              return new IdentifyWeaponResponse(
                      incident.getIdentity().getValue(),
                      request.getWeaponName(),
                      true
              );
            });
  }
}
