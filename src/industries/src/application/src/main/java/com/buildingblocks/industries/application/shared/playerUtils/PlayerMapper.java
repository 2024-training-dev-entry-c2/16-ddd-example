package com.buildingblocks.industries.application.shared.playerUtils;

import com.buildingblocks.industries.domain.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {

    public static PlayerResponse mapToPlayer(Player player) {
        return new PlayerResponse(
                player.getIdentity().getValue(),
                player.getLoan() != null
                        ? player.getLoan().stream().map(loan ->
                        new PlayerResponse.Loan(
                                loan.getIdentity().getValue(),
                                loan.getAmount().getValue(),
                                loan.getQuantity().getValue()
                        )
                ).collect(Collectors.toList()) : List.of(),
                player.getTransaction() != null
                        ? player.getTransaction().stream().map(transaction ->
                        new PlayerResponse.Transaction(
                                transaction.getIdentity().getValue(),
                                transaction.getAmount().getValue(),
                                transaction.getReason().getValue(),
                                transaction.getResourceType().getValue(),
                                transaction.getQuantity().getValue()
                        )
                ).collect(Collectors.toList()) : List.of(),
                player.getBudget() != null ? player.getBudget().getValue() : 0,
                player.getIncome() != null ? player.getIncome().getValue() : 0
        );
    }
}
