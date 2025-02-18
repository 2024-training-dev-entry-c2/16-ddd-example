package com.buildingblocks.industries.domain.industry;

import com.buildingblocks.industries.domain.industry.values.IndustryId;
import com.buildingblocks.industries.domain.industry.values.StoredResources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IndustryTest {

    private Industry industry;

    @BeforeEach
    void setUp() {
        industry = new Industry("Iron Work", 1, "Birmingham", 10, "Coal", 2, true, "Canals", false);
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
}