package com.buildingblocks.appointments.infra.mainservice.controllers;

import com.buildingblocks.appointments.application.creategame.CreateGameRequest;
import com.buildingblocks.appointments.application.creategame.CreateGameUseCase;
import com.buildingblocks.appointments.application.shared.table.TableResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/create-game")
public class CreateGameController {
  private final CreateGameUseCase useCase;

  public CreateGameController(CreateGameUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<TableResponse> execute(@RequestBody CreateGameRequest request) {
    return useCase.execute(request);
  }
}
