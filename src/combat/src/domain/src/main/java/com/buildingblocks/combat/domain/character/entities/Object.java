package com.buildingblocks.combat.domain.character.entities;

import com.buildingblocks.combat.domain.character.values.EffectObject;
import com.buildingblocks.combat.domain.character.values.Name;
import com.buildingblocks.combat.domain.character.values.ObjectId;
import com.buildingblocks.combat.domain.character.values.TypeObject;
import com.buildingblocks.shared.domain.generic.Entity;

public class Object extends Entity<ObjectId> {
    private Name name;
    private TypeObject typeObject;
    private EffectObject effectObject;

    public Object(ObjectId identity, Name name, TypeObject tipo, EffectObject efecto) {
        super(identity);
        this.name = name;
        this.typeObject = tipo;
        this.effectObject = efecto;
    }
    public Object(Name name, TypeObject tipo, EffectObject efecto) {
        super(new ObjectId());
        this.name = name;
        this.typeObject = tipo;
        this.effectObject = efecto;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public TypeObject getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(TypeObject typeObject) {
        this.typeObject = typeObject;
    }

    public EffectObject getEffectObject() {
        return effectObject;
    }

    public void setEffectObject(EffectObject effectObject) {
        this.effectObject = effectObject;
    }
    public void use() {
        System.out.println("Objeto usado: " + name.getValue() + ". Efecto: " + effectObject.getValue());
    }

    public void equip() {
        System.out.println("Objeto equipado: " + name.getValue() + ". Tipo: " + typeObject.getValue());
    }
}
