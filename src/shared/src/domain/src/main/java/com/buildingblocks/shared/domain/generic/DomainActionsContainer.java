package com.buildingblocks.shared.domain.generic;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public  abstract class DomainActionsContainer {
    protected Set<Consumer<? super  DomainEvent>> actions =new HashSet<>();//el argumenot tiene que ser hijo de DomainEvent

    protected void add(final Consumer<? extends DomainEvent> consumer){
        actions.add((Consumer<? super  DomainEvent>)consumer);
    }

}
