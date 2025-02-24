package com.buildingblocks.shared.application.combat.domain.character.values;

import com.buildingblocks.shared.application.shared.domain.generic.Identity;

public class DeckOfCardsId extends Identity {

    public DeckOfCardsId() {
        super();
    }

    public DeckOfCardsId(String value) {
        super(value);
    }

    public static DeckOfCardsId of(String value) {
        return new DeckOfCardsId(value);
    }
}
