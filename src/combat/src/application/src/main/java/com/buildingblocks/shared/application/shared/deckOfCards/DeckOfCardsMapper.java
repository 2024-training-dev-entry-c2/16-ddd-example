package com.buildingblocks.shared.application.shared.deckOfCards;

import com.buildingblocks.shared.application.combat.domain.deckOfCards.DeckOfCards;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.entities.SkillCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.SkillCardId;

import java.util.stream.Collectors;

public class DeckOfCardsMapper {
    public static DeckOfCardsResponse mapToResponse(DeckOfCards deck, Object eventDetails) {
        return new DeckOfCardsResponse(
                deck.getIdentity().getValue(),
                deck.getSkillCards().stream().map(SkillCard::getIdentity).map(SkillCardId::getValue).collect(Collectors.toList()),
                deck.getDiscardedCards().stream().map(SkillCard::getIdentity).map(SkillCardId::getValue).collect(Collectors.toList()),
                deck.getLostCards().stream().map(SkillCard::getIdentity).map(SkillCardId::getValue).collect(Collectors.toList()),
                eventDetails
        );
    }
}