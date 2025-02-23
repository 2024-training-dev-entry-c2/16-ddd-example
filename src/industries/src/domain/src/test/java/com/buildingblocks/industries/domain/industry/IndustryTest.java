package com.buildingblocks.industries.domain.industry;

import com.buildingblocks.industries.domain.industry.entities.MarketLink;
import com.buildingblocks.industries.domain.industry.entities.UpgradeStage;
import com.buildingblocks.industries.domain.industry.events.ExhaustedIndustry;
import com.buildingblocks.industries.domain.industry.values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IndustryTest {

    private Industry industry;

    private MarketLink marketLink;
    private UpgradeStage upgradeStage;

    @BeforeEach
    void setUp() {
        industry = new Industry("Iron Work", 1, "Birmingham", 10, "Coal", 2, true, "Canals", false);


        marketLink = new MarketLink(
                Origin.of("Liverpool"),
                Destination.of("Manchester"),
                IsConnectedToNetwork.of(false)
        );

        upgradeStage = new UpgradeStage(
                Level.of(1),
                TechLevelRequired.of(2),
                Type.of("Iron Work"),
                RequiredResource.of("Iron"),
                QuantityRequiredResource.of(3)
        );
    }

    @Test
    void shouldBuildIndustryCorrectly() {
        assertEquals("Iron Work", industry.getType().getValue());
        assertEquals(1, industry.getLevel().getValue());
        assertEquals("Birmingham", industry.getLocation().getValue());
        assertEquals(10, industry.getCost().getValue());
        assertEquals("Coal", industry.getRequiredResource().getValue());
        assertEquals(2, industry.getTechLevelRequired().getValue());
        assertTrue(industry.getIsConnectedToNetwork().getValue());
        assertEquals("Canals", industry.getEra().getValue());
        assertFalse(industry.getIsFlipped().getValue());
    }

    @Test
    void shouldActivateMarketLink() {
        IndustryId industryId = IndustryId.of("12545555adadasd");

        industry.activateMarketLink(industryId.getValue(), true);
        assertTrue(industry.getIsConnectedToNetwork().getValue());
    }

    @Test
    void shouldConsumeResource() {
        IndustryId industryId = IndustryId.of("12545555adadasd");

        industry.setStoredResources(StoredResources.of(new ArrayList<>(List.of("Coal", "Coal", "Iron"))));
        industry.consumeResource(industryId.getValue(), "Coal", 2);
        assertEquals(List.of("Iron"), industry.getStoredResources().getValue());
    }

    @Test
    void shouldThrowExceptionWhenNotEnoughResources() {
        IndustryId industryId = IndustryId.of("12545555adadasd");

        industry.setStoredResources(StoredResources.of(List.of("Iron")));
        Exception exception = assertThrows(IllegalStateException.class, () ->
                industry.consumeResource(industryId.getValue(), "Coal", 1)
        );
        assertEquals("Not enough resources to consume", exception.getMessage());
    }

    @Test
    void shouldFlipIndustry() {
        IndustryId industryId = IndustryId.of("12545555adadasd");

        industry.flip(industryId.getValue(), "Manchester", "Coal", 2, 5);
        assertTrue(industry.getIsFlipped().getValue());
        assertEquals(5, industry.getIncome().getValue());
    }

    @Test
    void shouldOverBuildIndustry() {
        IndustryId industryId = IndustryId.of("12545555adadasd");

        industry.overBuild(industryId.getValue(), "Iron Work", 2, 15);
        assertEquals("Iron Work", industry.getType().getValue());
        assertEquals(2, industry.getLevel().getValue());
        assertEquals(15, industry.getCost().getValue());
    }

    @Test
    void shouldUpgradeIndustry() {
        IndustryId industryId = IndustryId.of("12545555adadasd");

        industry.upgrade(
                industryId.getValue(), "Cotton Mill", 2, "Liverpool", false,
                "Iron", 3, 15, 2, true, "Rails"
        );

        assertEquals("Cotton Mill", industry.getType().getValue());
        assertEquals(2, industry.getLevel().getValue());
        assertEquals("Liverpool", industry.getLocation().getValue());
        assertFalse(industry.getIsFlipped().getValue());
        assertEquals("Iron", industry.getRequiredResource().getValue());
        assertEquals(3, industry.getQuantityRequiredResource().getValue());
        assertEquals(15, industry.getCost().getValue());
        assertEquals(2, industry.getTechLevelRequired().getValue());
        assertTrue(industry.getIsRequiredResearch().getValue());
        assertEquals("Rails", industry.getEra().getValue());
    }

    @Test
    void shouldExhaustIndustry() {
        IndustryId industryId = IndustryId.of("12545555adadasd");

        industry.isExhausted(industryId.getValue(), "Birmingham", "Iron Work", true);
        assertTrue(industry.getIsFlipped().getValue(), "Industry should be flipped when exhausted");

        List<ExhaustedIndustry> events = industry.getUncommttedEvents().stream()
                .filter(event -> event instanceof ExhaustedIndustry)
                .map(event -> (ExhaustedIndustry) event)
                .toList();

        assertEquals(1, events.size(), "An ExhaustedIndustry event should be registered");
        ExhaustedIndustry event = events.get(0);
        assertEquals("12545555adadasd", event.getId());
        assertEquals("Birmingham", event.getLocation());
        assertEquals("Iron Work", event.getType());
        assertTrue(event.getFlipped());
    }

    @Test
    void testIndustryConstructor() {
        IndustryId industryId = IndustryId.of("12545555adadasd");
        Industry industry = Industry.from(industryId.getValue(), List.of());

        assertNotNull(industry);
        assertNotNull(industryId.getValue());
        assertEquals("12545555adadasd", industryId.getValue());
        assertTrue(industry.getUncommttedEvents().isEmpty());
    }

    @Test
    void testSetAndGetMarketLink() {
        industry.setMarketLink((List<MarketLink>) marketLink);
        assertEquals(marketLink, industry.getMarketLink());
    }

    @Test
    void testSetAndGetUpgradeStage() {
        industry.setUpgradeStage((List<UpgradeStage>) upgradeStage);
        assertEquals(upgradeStage, industry.getUpgradeStage());
    }
}