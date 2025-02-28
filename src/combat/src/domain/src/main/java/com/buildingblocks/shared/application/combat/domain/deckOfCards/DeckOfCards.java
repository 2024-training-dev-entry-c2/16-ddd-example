package com.buildingblocks.shared.application.combat.domain.deckOfCards;

import com.buildingblocks.shared.application.combat.domain.character.values.DeckOfCardsId;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.entities.SkillCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardAdded;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardDiscarded;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardRemoved;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.ImprovedCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.LostCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.RecoveredCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.ReorganizedDeck;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.RestedCard;
import com.buildingblocks.shared.application.shared.domain.generic.AggregateRoot;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;


public class DeckOfCards extends AggregateRoot<DeckOfCardsId> {
    private List<SkillCard> skillCards;
    private List<SkillCard> discardedCards;
    private List<SkillCard> lostCards;
    //region Constructors

    public DeckOfCards() {
        super(new DeckOfCardsId());
        subscribe(new DeckOfCardsHandler(this));

        skillCards = new ArrayList<>();
        discardedCards = new ArrayList<>();
        lostCards = new ArrayList<>();
        clearState();
    }
    private DeckOfCards(DeckOfCardsId id) {
        super(id);
        subscribe(new DeckOfCardsHandler(this));
        skillCards = new ArrayList<>();
        discardedCards = new ArrayList<>();
        lostCards = new ArrayList<>();
    }

    //endregion
    //region Getter & Setter

    public List<SkillCard> getSkillCards() {
        return skillCards;
    }

    public void setSkillCards(List<SkillCard> skillCards) {
        this.skillCards = skillCards;
    }

    public List<SkillCard> getDiscardedCards() {
        return discardedCards;
    }

    public void setDiscardedCards(List<SkillCard> discardedCards) {
        this.discardedCards = discardedCards;
    }

    public List<SkillCard> getLostCards() {
        return lostCards;
    }

    public void setLostCards(List<SkillCard> lostCards) {
        this.lostCards = lostCards;
    }

    //endregion
    //region Domain Events
    public void addCard(SkillCard card) {
        boolean cardExists = skillCards.stream()
                .anyMatch(c -> c.getIdentity().getValue().equals(card.getIdentity().getValue()));
        apply(new CardAdded(
                card.getIdentity().getValue(),
                card.getSkillCardName().getValue(),
                card.getIniciative().getValue(),
                card.getEffectType().getNameEffect(),
                card.getObjetives().getValue(),
                card.getScope().getValue(),
                card.getEffectType().getImpact(),
                card.getEffectType().getDuration()
        ));
    }
    public void removeCard(String cardId) {

        apply(new CardRemoved(this.getIdentity().getValue(), cardId));
    }

    public void reorganizeDeck() {
        apply(new ReorganizedDeck(this.getIdentity().getValue()));
    }

    public void restCards(boolean isLongRest) {

        apply(new RestedCard(this.getIdentity().getValue(),isLongRest));
    }

    public void recoverCard(String cardId) {
            apply(new RecoveredCard(this.getIdentity().getValue(), cardId));
    }

    public void improveCard(String cardId, String upgrade) {
        apply(new ImprovedCard(this.getIdentity().getValue(), cardId, upgrade));
    }

    public void discardCard(String cardId) {
        apply(new CardDiscarded(cardId));
    }

    public void loseCard(String cardId) {
        apply(new LostCard(cardId));
    }

    public int findCardById(String cardId) {
        System.out.println(cardId);
        System.out.println(skillCards.get(0).getIdentity().getValue());
        for (int i = 0; i < skillCards.size(); i++) {
            if (skillCards.get(i).getIdentity().getValue().equals(cardId)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Carta no encontrada");
    }
    public void clearState() {
        skillCards.clear();
        discardedCards.clear();
        lostCards.clear();
    }
    public  static DeckOfCards from(final String identity, final List<DomainEvent> events){
        DeckOfCards deck = new DeckOfCards(DeckOfCardsId.of(identity));
        events.forEach(deck::apply);
        return deck;
    }
    //endregion
}
