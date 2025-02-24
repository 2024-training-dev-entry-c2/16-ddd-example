package com.buildingblocks.appointments.infra.mainservice.config;

import com.buildingblocks.appointments.application.creategame.CreateGameUseCase;
import com.buildingblocks.appointments.application.flipcard.FlipCardUseCase;
import com.buildingblocks.appointments.application.getallboards.GetAllBoardsUseCase;
import com.buildingblocks.appointments.infra.mongo.adapters.MongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
  @Bean
  public CreateGameUseCase createGameUseCase(MongoAdapter adapter) {
    return new CreateGameUseCase(adapter);
  }

  @Bean
  public FlipCardUseCase flipCardUseCase(MongoAdapter adapter) {
    return new FlipCardUseCase(adapter);
  }

  @Bean
  public GetAllBoardsUseCase getAllBoardsUseCase(MongoAdapter adapter) {
    return new GetAllBoardsUseCase(adapter);
  }
}
