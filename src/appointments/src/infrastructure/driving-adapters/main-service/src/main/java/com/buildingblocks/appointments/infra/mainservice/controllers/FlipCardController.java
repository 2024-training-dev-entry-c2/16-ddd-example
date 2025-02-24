package com.buildingblocks.appointments.infra.mainservice.controllers;

import com.buildingblocks.appointments.application.flipcard.FlipCardRequest;
import com.buildingblocks.appointments.application.flipcard.FlipCardUseCase;
import com.buildingblocks.appointments.application.shared.table.TableResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flip-card")
public class FlipCardController {
  private final FlipCardUseCase useCase;


  public FlipCardController(FlipCardUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<TableResponse> execute(@RequestBody FlipCardRequest request) {
    return useCase.execute(request);
  }
}
