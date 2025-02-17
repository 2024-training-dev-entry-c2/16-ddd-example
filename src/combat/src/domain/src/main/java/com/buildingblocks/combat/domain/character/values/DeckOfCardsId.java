package com.buildingblocks.combat.domain.character.values;

import com.buildingblocks.combat.domain.combat.values.CombatId;
import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.generic.Identity;

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
