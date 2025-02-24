package com.buildingblocks.appointments.application.creategame;

import com.buildingblocks.appointments.application.shared.ports.IEventsRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateGameUseCaseTest {
  private final CreateGameUseCase useCase;
  private final IEventsRepositoryPort repository;

  public CreateGameUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new CreateGameUseCase(repository);
  }

  @Test
  void executeSuccess() {
    CreateGameRequest request = new CreateGameRequest("Table 1", List.of("Player 1", "Player 2"));
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getTableName(), response.getBoardName());
        assertEquals(request.getPlayers().size(), response.getPlayers().size());
        assertEquals(request.getPlayers().get(0), response.getPlayers().get(0).getNickname());
      })
      .verifyComplete();
  }
}