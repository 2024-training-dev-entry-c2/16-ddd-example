package com.buildingblocks.industries.application.shared.playerUtils;

import com.buildingblocks.industries.domain.player.Player;

import java.util.stream.Collectors;

public class PlayerMapper {

    public static PlayerResponse mapToPlayer(Player player) {
        return new PlayerResponse(
                player.getIdentity().getValue(),
                player.getLoan().stream().map(loan ->
                        new PlayerResponse.Loan(
                                loan.getIdentity().getValue(),
                                loan.getAmount().getValue(),
                                loan.getQuantity().getValue()
                        )
                ).collect(Collectors.toList()),
                player.getTransaction().stream().map(transaction ->
                        new PlayerResponse.Transaction(
                                transaction.getIdentity().getValue(),
                                transaction.getAmount().getValue(),
                                transaction.getReason().getValue(),
                                transaction.getResourceType().getValue(),
                                transaction.getQuantity().getValue()
                        )
                ).collect(Collectors.toList()),
                player.getBudget().getValue(),
                player.getIncome().getValue()
        );
    }
}
