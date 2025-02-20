package com.buildingblocks.appointments.application.flipcard;

import com.buildingblocks.appointments.application.shared.repositories.IEventsRepository;
import com.buildingblocks.appointments.application.shared.table.TableResponse;
import com.buildingblocks.appointments.domain.table.Table;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.appointments.application.shared.table.TableMapper.mapToTable;

public class FlipCardUseCase implements ICommandUseCase<FlipCardRequest, Mono<TableResponse>> {
  private final IEventsRepository repository;

  public FlipCardUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<TableResponse> execute(FlipCardRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        Table table = Table.from(request.getAggregateId(), events);
        table.flipCard(request.getCardId());
        table.getUncommittedEvents().forEach(repository::save);
        table.markEventsAsCommitted();

        return mapToTable(table);
      });
  }
}
