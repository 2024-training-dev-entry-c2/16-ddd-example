package com.buildingblocks.industries.application.shared.ResourceMarketUtils;

import java.util.List;

public class ResourceMarketResponse {
    private final String ResourceMarketId;
    private final List<TradeExchange> tradeExchange;
    private final List<String> availableResources;
    private final Integer resourcePrice;

    public ResourceMarketResponse(String resourceMarketId, List<TradeExchange> tradeExchange, List<String> availableResources, Integer resourcePrice) {
        ResourceMarketId = resourceMarketId;
        this.tradeExchange = tradeExchange;
        this.availableResources = availableResources;
        this.resourcePrice = resourcePrice;
    }


    public String getResourceMarketId() {
        return ResourceMarketId;
    }

    public List<TradeExchange> getTradeExchange() {
        return tradeExchange;
    }

    public List<String> getAvailableResources() {
        return availableResources;
    }

    public Integer getResourcePrice() {
        return resourcePrice;
    }

    public static class TradeExchange {
        private final String id;
        private final String tradeType;
        private final String resourceType;
        private final Integer resourceQuantity;
        private final Integer resourcePrice;
        private final List<String> availableResources;

        public TradeExchange(String id, String tradeType, String resourceType, Integer resourceQuantity, Integer resourcePrice, List<String> availableResources) {
            this.id = id;
            this.tradeType = tradeType;
            this.resourceType = resourceType;
            this.resourceQuantity = resourceQuantity;
            this.resourcePrice = resourcePrice;
            this.availableResources = availableResources;
        }

        public String getId() {
            return id;
        }

        public String getTradeType() {
            return tradeType;
        }

        public String getResourceType() {
            return resourceType;
        }

        public Integer getResourceQuantity() {
            return resourceQuantity;
        }

        public Integer getResourcePrice() {
            return resourcePrice;
        }

        public List<String> getAvailableResources() {
            return availableResources;
        }
    }
}
