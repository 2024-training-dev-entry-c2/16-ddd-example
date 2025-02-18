package com.buildingblocks.combat.domain.deckOfCards;

import com.buildingblocks.combat.domain.character.values.DeckOfCardsId;
import com.buildingblocks.combat.domain.deckOfCards.entities.SkillCard;
import com.buildingblocks.combat.domain.deckOfCards.events.CardAdded;
import com.buildingblocks.combat.domain.deckOfCards.events.CardRemoved;
import com.buildingblocks.combat.domain.deckOfCards.events.ImprovedCard;
import com.buildingblocks.combat.domain.deckOfCards.events.RecoveredCard;
import com.buildingblocks.combat.domain.deckOfCards.events.ReorganizedDeck;
import com.buildingblocks.combat.domain.deckOfCards.events.RestedCard;
import com.buildingblocks.combat.domain.deckOfCards.values.ParticipantId;
import com.buildingblocks.combat.domain.deckOfCards.values.StatusDeck;
import com.buildingblocks.shared.domain.generic.AggregateRoot;

import java.util.Collections;
import java.util.List;

public class DeckOfCards extends AggregateRoot<DeckOfCardsId> {
    private ParticipantId participantId;
    private StatusDeck statusDeck;
    private List<SkillCard> skillCards;
    private List<SkillCard> discardedCards;
    private List<SkillCard> lostCards;

    //region Constructors

    public DeckOfCards() {
        super(new DeckOfCardsId());
        subscribe(new DeckOfCardsHandler(this));
    }
    private DeckOfCards(DeckOfCardsId id) {
        super(id);
        subscribe(new DeckOfCardsHandler(this));
    }

    //endregion
    //region Getter & Setter

    public ParticipantId getParticipantId() {
        return participantId;
    }

    public void setParticipantId(ParticipantId participantId) {
        this.participantId = participantId;
    }

    public StatusDeck getStatusDeck() {
        return statusDeck;
    }

    public void setStatusDeck(StatusDeck statusDeck) {
        this.statusDeck = statusDeck;
    }

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
        apply(new CardAdded(this.getIdentity().getValue(),
                card.getIdentity().getValue(),
                card.getSkillCardName().getValue(),
                card.getIniciative().getValue(),
                card.getEffectType().getNameEffect(),
                card.getObjetives().getValue(),
                card.getScope().getValue(),
                this.getIdentity().getValue(),
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
        apply(new CardRemoved(this.getIdentity().getValue(), cardId));
    }

    public void loseCard(String cardId) {
        apply(new CardRemoved(this.getIdentity().getValue(), cardId));
    }

    public SkillCard findCardById(String cardId) {
        return skillCards.stream()
                .filter(c -> c.getIdentity().getValue().equals(cardId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Carta no encontrada"));
    }
    //endregion
}
