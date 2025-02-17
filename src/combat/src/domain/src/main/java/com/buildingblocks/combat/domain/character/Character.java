package com.buildingblocks.combat.domain.character;

import com.buildingblocks.combat.domain.character.entities.ActionTaken;
import com.buildingblocks.combat.domain.character.entities.Object;
import com.buildingblocks.combat.domain.character.entities.StatusActivated;
import com.buildingblocks.combat.domain.character.events.EquippedObject;
import com.buildingblocks.combat.domain.character.events.UsedObject;
import com.buildingblocks.combat.domain.character.values.CharacterId;
import com.buildingblocks.combat.domain.character.values.DeckOfCardsId;
import com.buildingblocks.combat.domain.character.values.Health;
import com.buildingblocks.combat.domain.character.values.Impact;
import com.buildingblocks.combat.domain.character.values.Name;
import com.buildingblocks.combat.domain.character.values.ObjectId;
import com.buildingblocks.combat.domain.character.values.StatusActivateId;
import com.buildingblocks.combat.domain.deckOfCards.values.EffectType;
import com.buildingblocks.combat.domain.enemy.events.AppliedStatus;
import com.buildingblocks.combat.domain.enemy.events.RegisteredAction;
import com.buildingblocks.combat.domain.enemy.events.RemovedStatus;
import com.buildingblocks.combat.domain.enemy.events.SufferedDamage;
import com.buildingblocks.combat.domain.enemy.events.TerminatedTurn;
import com.buildingblocks.combat.domain.enemy.events.UsedCard;
import com.buildingblocks.combat.domain.enemy.events.beCured;
import com.buildingblocks.shared.domain.generic.AggregateRoot;

import java.util.List;
import java.util.logging.Level;

public class Character extends AggregateRoot<CharacterId> {
    private DeckOfCardsId deck;
    private Name name;
    private Health health;
    private Level level;
    private List<StatusActivated> effectActives;
    private List<ActionTaken> historyActions;
    private List<Object> objects;

    //region Constructors
    public Character(CharacterId identity, DeckOfCardsId deck, Name name, Health health, Level level, List<StatusActivated> efectosActivos, List<ActionTaken> historialAcciones, List<Object> objetos) {
        super(identity);
        this.deck = deck;
        this.name = name;
        this.health = health;
        this.level = level;
        this.effectActives = efectosActivos;
        this.historyActions = historialAcciones;
        this.objects = objetos;
    }
    public Character(DeckOfCardsId deck, Name name, Health health, Level level, List<StatusActivated> efectosActivos, List<ActionTaken> historialAcciones, List<Object> objetos) {
        super(new CharacterId());
        this.deck = deck;
        this.name = name;
        this.health = health;
        this.level = level;
        this.effectActives = efectosActivos;
        this.historyActions = historialAcciones;
        this.objects = objetos;
    }
    //endregion
    //region Getters & Setter

    public DeckOfCardsId getDeck() {
        return deck;
    }

    public void setDeck(DeckOfCardsId deck) {
        this.deck = deck;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
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

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    //endregion
    //region Domain Events



    public void applyEffect(EffectType effect) {
        StatusActivated effectActive = new StatusActivated(
                new StatusActivateId(),
                new Name(effect.getNameEffect()),
                new Impact(effect.getImpact()),
                Integer.parseInt(effect.getDuration())
        );

        effectActives.add(effectActive);
        effectActive.apply();
        apply(new AppliedStatus(
                this.getIdentity().getValue(),
                effectActive.getIdentity().getValue(),
                effectActive.getName().getValue(),
                effectActive.getRemainingTurns()
        ));
    }

    public void removeEffect(String effectId) {
        effectActives.removeIf(effect -> effect.getIdentity().equals(effectId));
        apply(new RemovedStatus(this.getIdentity().getValue(), effectId));
    }
//
    public void registerAction(ActionTaken action) {
        historyActions.add(action);
        apply(new RegisteredAction(
                this.getIdentity().getValue(),
                action.getIdentity().getValue(),
                action.getTypeEffect(),
                action.getObjetive(),
                action.getDamage(),
                action.getTypeEffect(),
                action.getResult().getValue()
        ));
    }

    public void useObject(ObjectId objectId) {
        Object object = objects.stream()
                .filter(o -> o.getIdentity().equals(objectId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Objeto no encontrado."));
        object.use();
        apply(new UsedObject(this.getIdentity().getValue(), objectId.getValue(), object.getName().getValue()));
    }
//
    public void equipObject(ObjectId objectId) {
        Object object = objects.stream()
                .filter(o -> o.getIdentity().equals(objectId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Objeto no encontrado."));
        object.equip();
        apply(new EquippedObject(this.getIdentity().getValue(), objectId.getValue(), object.getName().getValue()));
    }

    public void endTurn() {
        // Reducir los turnos restantes de los efectos activos
        effectActives.forEach(StatusActivated::reduceTurn);
        // Eliminar efectos que hayan expirado
        effectActives.removeIf(effect -> effect.getRemainingTurns() <= 0);
        apply(new TerminatedTurn(this.getIdentity().getValue()));
    }

    public void receiveDamage(int amount) {
        health = Health.of(Math.max(health.getValue() - amount, 0)); // La salud no puede ser negativa
        apply(new SufferedDamage(this.getIdentity().getValue(), amount));
    }



    public void beCured(int amount) {
        health = Health.of(health.getValue() + amount);
        apply(new beCured(this.getIdentity().getValue(), amount));
    }

    //endregion
}
