package com.buildingblocks.appointments.application.flipcard;

import com.buildingblocks.appointments.application.shared.ports.IEventsRepositoryPort;
import com.buildingblocks.appointments.application.shared.table.TableResponse;
import com.buildingblocks.appointments.domain.table.Table;
import com.buildingblocks.shared.application.ICommandUseCase;
import com.buildingblocks.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.Comparator;

import static com.buildingblocks.appointments.application.shared.table.TableMapper.mapToTable;

public class FlipCardUseCase implements ICommandUseCase<FlipCardRequest, Mono<TableResponse>> {
  private final IEventsRepositoryPort repository;

  public FlipCardUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<TableResponse> execute(FlipCardRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        events.sort(Comparator.comparing(DomainEvent::getWhen));
        Table table = Table.from(request.getAggregateId(), events);
        table.flipCard(request.getCardId());
        table.getUncommittedEvents().forEach(repository::save);
        table.markEventsAsCommitted();

        return mapToTable(table);
      });
  }
}
