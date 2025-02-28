package com.buildingblocks.industries.application.shared.ResourceMarketUtils;

import com.buildingblocks.industries.domain.resourceMarket.ResourceMarket;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceMarketMapper {

    public static ResourceMarketResponse mapToResourceMarket(ResourceMarket resourceMarket) {
        return new ResourceMarketResponse(
                resourceMarket.getIdentity().getValue(),
                resourceMarket.getTradeExchange() != null ?
                        resourceMarket.getTradeExchange().stream().map(trade ->
                        new ResourceMarketResponse.TradeExchange(
                                trade.getIdentity().getValue(),
                                trade.getTradeType().name(),
                                trade.getResourceType().getValue(),
                                trade.getResourceQuantity().getValue(),
                                trade.getResourcePrice().getValue(),
                                trade.getAvailableResources().getValue()
                        )
                ).collect(Collectors.toList()) :
                        Collections.emptyList(),
                resourceMarket.getAvailableResources() != null ? resourceMarket.getAvailableResources().getValue() : List.of(),
                resourceMarket.getResourcePrice() != null ? resourceMarket.getResourcePrice().getValue() : 0
        );
    }
}
