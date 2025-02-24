package com.buildingblocks.shared.application.combat.domain.deckOfCards;

import com.buildingblocks.shared.application.combat.domain.character.values.DeckOfCardsId;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.entities.SkillCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardAdded;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardDiscarded;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardRemoved;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.ImprovedCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.LostCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.RecoveredCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.RestedCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.EffectType;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.InitiativeCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.Objetives;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.Scope;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.SkillCardId;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.SkillCardName;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckOfCardsTest {

    private DeckOfCards deck;

    @BeforeEach
    void setUp() {
        deck = new DeckOfCards();
    }

    @Test
    void createDeckSuccess() {
        assertNotNull(deck.getIdentity().getValue());
        assertNotNull(deck.getSkillCards(), "El mazo debe inicializarse vacío");
        assertEquals(0, deck.getUncommittedEvents().size());

    }

    @Test
    void testReorganizeDeck() {
        DeckOfCards newDeck = new DeckOfCards();
        SkillCardId cardId = SkillCardId.of("card455");
        SkillCard card = new SkillCard(cardId, SkillCardName.of("Ice Spike"),
                InitiativeCard.of(5), EffectType.of("Freeze", 3, 1),
                Objetives.of(2, false), Scope.of(1));
        newDeck.addCard(card);
        SkillCardId cardId2 = SkillCardId.of("card456");
        SkillCard card2 = new SkillCard(cardId2, SkillCardName.of("Ice Spike"),
                InitiativeCard.of(5), EffectType.of("Freeze", 3, 1),
                Objetives.of(2, false), Scope.of(1));
        newDeck.addCard(card2);

        List<SkillCard> originalOrder = new ArrayList<>(newDeck.getSkillCards());
        newDeck.reorganizeDeck();

        assertNotEquals(originalOrder, deck.getSkillCards());

    }

    @Test
    void testAddCard() {
        SkillCardId cardId = SkillCardId.of("card123");
        SkillCard card = new SkillCard(cardId, SkillCardName.of("Fireball"),
                InitiativeCard.of(10), EffectType.of("Damage", 5, 2),
                Objetives.of(2, false), Scope.of(1));

        deck.addCard(card);

        assertEquals("Fireball", deck.getSkillCards().get(0).getSkillCardName().getValue());
        assertEquals(10, deck.getSkillCards().get(0).getIniciative().getValue());
        assertEquals("Damage", deck.getSkillCards().get(0).getEffectType().getNameEffect());
        assertEquals(2, deck.getSkillCards().get(0).getObjetives().getValue());
        assertEquals(1, deck.getUncommittedEvents().size());
        assertInstanceOf(CardAdded.class, deck.getUncommittedEvents().get(0));

    }

    @Test
    void testRemoveCard() {
        SkillCardId cardId = SkillCardId.of("card456");
        SkillCard card = new SkillCard(cardId, SkillCardName.of("Ice Spike"),
                InitiativeCard.of(5), EffectType.of("Freeze", 3, 1),
                Objetives.of(2, false), Scope.of(1));
        deck.setSkillCards(new ArrayList<>(List.of(card)));
        deck.removeCard(cardId.getValue());
        assertFalse(deck.getSkillCards().contains(card));
        assertInstanceOf(CardRemoved.class,deck.getUncommittedEvents().get(0));

    }


    @Test
    void testRestCards() {

        SkillCardId cardId = SkillCardId.of("card455");
        SkillCard card = new SkillCard(cardId, SkillCardName.of("Ice Spike"),
                InitiativeCard.of(5), EffectType.of("Freeze", 3, 1),
                Objetives.of(2, false), Scope.of(1));
        deck.addCard(card);

        deck.restCards(true);

        assertTrue(deck.getDiscardedCards().isEmpty() || deck.getSkillCards().size() > 0);
        assertInstanceOf(RestedCard.class,deck.getUncommittedEvents().get(1));

    }


    @Test
    void testImproveCard() {
        SkillCardId cardId = SkillCardId.of("card789");
        SkillCard card = new SkillCard(cardId, SkillCardName.of("Heal"),
                InitiativeCard.of(2), EffectType.of("Heal", 3, 2),
                Objetives.of(2, false), Scope.of(2));
        deck.setSkillCards(new ArrayList<>(List.of(card)));

        deck.improveCard(cardId.getValue(), "Upgrade: Strong Heal");

        assertEquals("Heal", card.getEffectType().getNameEffect());
        assertInstanceOf(ImprovedCard.class,deck.getUncommittedEvents().get(0));

    }

    @Test
    void testRecoverCard() {
        SkillCardId cardId = SkillCardId.of("card111");
        SkillCard card = new SkillCard(cardId, SkillCardName.of("Blast"),
                InitiativeCard.of(6), EffectType.of("Explosion", 7, 3),
                Objetives.of(1, false), Scope.of(3));
        deck.setSkillCards(new ArrayList<>(List.of(card)));
        deck.setDiscardedCards(new ArrayList<>(List.of(card)));
        deck.recoverCard(cardId.getValue());

        assertTrue(deck.getSkillCards().contains(card));
        assertFalse(deck.getDiscardedCards().contains(card));
        assertInstanceOf(RecoveredCard.class,deck.getUncommittedEvents().get(0));

    }

    @Test
    void testFindCardById() {
        SkillCardId cardId = SkillCardId.of("card222");
        SkillCard card = new SkillCard(cardId, SkillCardName.of("Slash"),
                InitiativeCard.of(4), EffectType.of("Cut", 5, 1),
                Objetives.of(1, false), Scope.of(1));
        deck.setSkillCards(new ArrayList<>(List.of(card)));

        int foundCard = deck.findCardById(cardId.getValue());
        assertEquals(0, foundCard);
    }

    @Test
    void testLostCards() {

        SkillCardId cardId = SkillCardId.of("card455");
        SkillCard card = new SkillCard(cardId, SkillCardName.of("Ice Spike"),
                InitiativeCard.of(5), EffectType.of("Freeze", 3, 1),
                Objetives.of(2, false), Scope.of(1));
        deck.setSkillCards(new ArrayList<>(List.of(card)));

        deck.loseCard(cardId.getValue());

        assertTrue(deck.getDiscardedCards().isEmpty() || deck.getSkillCards().size() > 0);
        assertInstanceOf(LostCard.class,deck.getUncommittedEvents().get(0));

    }

    @Test
    void testDescardCard() {
        SkillCardId cardId = SkillCardId.of("card456");
        SkillCard card = new SkillCard(cardId, SkillCardName.of("Ice Spike"),
                InitiativeCard.of(5), EffectType.of("Freeze", 3, 1),
                Objetives.of(2, false), Scope.of(1));
        deck.setSkillCards(new ArrayList<>(List.of(card)));
        deck.discardCard(cardId.getValue());
        assertFalse(deck.getSkillCards().contains(card));
        assertInstanceOf(CardDiscarded.class,deck.getUncommittedEvents().get(0));

    }

    @Test
    void cardNotFound() {
        SkillCardId cardId = SkillCardId.of("card456");
        SkillCard card = new SkillCard(cardId, SkillCardName.of("Ice Spike"),
                InitiativeCard.of(5), EffectType.of("Freeze", 3, 1),
                Objetives.of(2, false), Scope.of(1));
        deck.addCard(card);


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deck.findCardById("ID_QUE_NO_EXISTE");
        });

        assertEquals("Carta no encontrada", exception.getMessage());

    }
    @Test
    void testFrom() {
        String deckId = "deck-123";
        List<DomainEvent> events = new ArrayList<>();


        events.add(new CardAdded(
                "card-001", "Fireball", 5, "Damage", 2,
                1,  10, 2
        ));

        events.add(new CardRemoved(deckId, "card-001"));

        DeckOfCards deck = DeckOfCards.from(deckId, events);

        assertNotNull(deck, "El mazo no debe ser null");
        assertEquals(DeckOfCardsId.of(deckId).getValue(), deck.getIdentity().getValue(), "El ID del mazo debe coincidir");
        assertTrue(deck.getSkillCards().isEmpty(), "El mazo no debe tener cartas después de eliminar la única añadida");
    }
}