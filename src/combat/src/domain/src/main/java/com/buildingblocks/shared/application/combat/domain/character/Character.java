package com.buildingblocks.shared.application.combat.domain.character;

import com.buildingblocks.shared.application.combat.domain.character.entities.ActionTaken;
import com.buildingblocks.shared.application.combat.domain.character.entities.StatusActivated;
import com.buildingblocks.shared.application.combat.domain.character.events.AppliedStatus;
import com.buildingblocks.shared.application.combat.domain.character.events.RegisteredAction;
import com.buildingblocks.shared.application.combat.domain.character.events.RemovedStatus;
import com.buildingblocks.shared.application.combat.domain.character.events.SufferedDamage;
import com.buildingblocks.shared.application.combat.domain.character.events.TerminatedTurn;
import com.buildingblocks.shared.application.combat.domain.character.events.beCured;
import com.buildingblocks.shared.application.combat.domain.character.values.CharacterId;
import com.buildingblocks.shared.application.combat.domain.character.values.DeckOfCardsId;
import com.buildingblocks.shared.application.combat.domain.character.values.EffectType;
import com.buildingblocks.shared.application.combat.domain.character.values.Health;
import com.buildingblocks.shared.application.combat.domain.character.values.Name;
import com.buildingblocks.shared.application.combat.domain.character.values.StatusActivateId;


import com.buildingblocks.shared.application.shared.domain.generic.AggregateRoot;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.List;
import java.util.logging.Level;

public class Character extends AggregateRoot<CharacterId> {
    private DeckOfCardsId deck;
    private Name name;
    private Health health;
    private Level level;
    private List<StatusActivated> effectActives;
    private List<ActionTaken> historyActions;

    //region Constructors

    private Character(CharacterId identity) {
        super(identity);
        subscribe(new CharacterHandler(this));
    }

    public Character() {
        super(new CharacterId());
        subscribe(new CharacterHandler(this));
    }
    //endregion
    //region Getters & Setter



    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }


    public List<StatusActivated> getEffectActives() {
        return effectActives;
    }

    public void setEffectActives(List<StatusActivated> effectActives) {
        this.effectActives = effectActives;
    }

    public List<ActionTaken> getHistoryActions() {
        return historyActions;
    }

    public void setHistoryActions(List<ActionTaken> historyActions) {
        this.historyActions = historyActions;
    }



    //endregion
    //region Domain Events
    public void applyEffect(EffectType effect) {
        apply(new AppliedStatus(
                this.getIdentity().getValue(),
                new StatusActivateId().getValue(),
                effect.getNameEffect(),
                effect.getImpact(),
                effect.getDuration()

        ));
    }

    public void removeEffect(String effectId) {
        apply(new RemovedStatus(this.getIdentity().getValue(), effectId));
    }
    public void registerAction(ActionTaken action) {
        apply(new RegisteredAction(
                this.getIdentity().getValue(),
                action.getIdentity().getValue(),
                action.getTypeEffect().getNameEffect(),
                action.getObjetive().getValue(),
                action.getDamage().getValue(),
                action.getTypeEffect().getNameEffect(),
                action.getResult().getValue(),
                action.getTypeEffect().getImpact(),
                action.getTypeEffect().getDuration()
        ));
    }


    public void endTurn() {
        apply(new TerminatedTurn(this.getIdentity().getValue()));
    }

    public void receiveDamage(int amount) {
        apply(new SufferedDamage(this.getIdentity().getValue(), amount));
    }


    public void beCured(int amount) {

        apply(new beCured(this.getIdentity().getValue(), amount));
    }
    public  static Character from(final String identity, final List<DomainEvent> events){
        Character character = new Character(CharacterId.of(identity));
        events.forEach(character::apply);
        return character;
    }

    //endregion
}
