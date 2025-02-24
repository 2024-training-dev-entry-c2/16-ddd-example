package com.buildingblocks.appointments.application.creategame;

import com.buildingblocks.appointments.application.shared.repositories.IEventsRepository;
import com.buildingblocks.appointments.application.shared.table.TableResponse;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.buildingblocks.appointments.application.shared.table.TableMapper.mapToTable;

public class CreateGameUseCase implements ICommandUseCase<CreateGameRequest, Mono<TableResponse>> {
  private final IEventsRepository repository;

  public CreateGameUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<TableResponse> execute(CreateGameRequest request) {
    com.buildingblocks.appointments.domain.table.Table table = new com.buildingblocks.appointments.domain.table.Table(request.getTableName());
    request.getPlayers().forEach(table::addPlayer);
    table.starGame();

    table.getUncommittedEvents().forEach(repository::save);
    table.markEventsAsCommitted();

    return Mono.just(mapToTable(table));
  }
}
