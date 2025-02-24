package com.buildingblocks.appointments.application.getallboards;

import com.buildingblocks.appointments.application.shared.ports.IEventsRepositoryPort;
import com.buildingblocks.appointments.application.shared.table.TableMapper;
import com.buildingblocks.appointments.application.shared.table.TableResponse;
import com.buildingblocks.appointments.domain.table.Table;
import com.buildingblocks.shared.application.IQueryUseCase;
import com.buildingblocks.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.stream.Collectors;

public class GetAllBoardsUseCase implements IQueryUseCase<Flux<TableResponse>> {
  private final IEventsRepositoryPort repository;

  public GetAllBoardsUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Flux<TableResponse> execute() {
    return repository
      .findAllAggregates()
      .collectList()
      .map(events -> events.stream().collect(Collectors.groupingBy(DomainEvent::getAggregateRootId)))
      .map(aggregates -> aggregates.entrySet().stream().map(entry -> {
        entry.getValue().sort(Comparator.comparing(DomainEvent::getWhen));
        return Table.from(entry.getKey(), entry.getValue());
      }).toList())
      .map(tables -> tables.stream().map(TableMapper::mapToTable).toList())
      .flatMapMany(Flux::fromIterable);
  }
}
