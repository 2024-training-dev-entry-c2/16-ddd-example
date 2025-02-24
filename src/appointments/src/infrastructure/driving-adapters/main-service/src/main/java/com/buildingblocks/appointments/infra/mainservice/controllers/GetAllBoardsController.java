package com.buildingblocks.appointments.infra.mainservice.controllers;

import com.buildingblocks.appointments.application.getallboards.GetAllBoardsUseCase;
import com.buildingblocks.appointments.application.shared.table.TableResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/get-boards")
public class GetAllBoardsController {
  private final GetAllBoardsUseCase useCase;


  public GetAllBoardsController(GetAllBoardsUseCase useCase) {
    this.useCase = useCase;
  }

  @GetMapping
  public Flux<TableResponse> execute() {
    return useCase.execute();
  }
}
