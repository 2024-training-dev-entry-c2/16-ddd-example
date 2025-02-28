package com.buildingblocks.shared.application.combat.removeEnemy;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.buildingblocks.shared.application.combat.domain.combat.Combat;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import com.buildingblocks.shared.application.shared.ports.IEventsRepositoryPort;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import reactor.core.publisher.Mono;

import java.util.Comparator;

import static com.buildingblocks.shared.application.shared.combat.CombatMapper.mapToResponse;

public class RemoveEnemyUseCase implements ICommandUseCase<RemoveEnemyRequest, Mono<CombatResponse>> {
    private final IEventsRepositoryPort repository;

    public RemoveEnemyUseCase(IEventsRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<CombatResponse> execute(RemoveEnemyRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    System.out.println("enemigo borrado"+request.getEnemyId());
                    events.sort(Comparator.comparing(DomainEvent::getWhen));
                    Combat combat = Combat.from(request.getAggregateId(), events);
                    combat.removeEnemy(request.getEnemyId());
                    combat.getUncommittedEvents().forEach(repository::save);
                    combat.markEventAsCommited();

                    return mapToResponse(combat);
                });
    }
}
