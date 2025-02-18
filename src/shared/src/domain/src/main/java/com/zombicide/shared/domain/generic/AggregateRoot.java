package com.zombicide.shared.domain.generic;

import java.util.List;

public abstract class AggregateRoot<I extends Identity> extends Entity<I>{
	private final DomainActionsHandler actionsHandler = new DomainActionsHandler();

	protected AggregateRoot(final I identity) {
		super(identity);
	}

	public List<DomainEvent> getUncommittedEvents() {
		return List.copyOf(actionsHandler.getEvents());
	}

	public void markEventsAsCommitted() {
		actionsHandler.getEvents().clear();
	}

	public void apply(final DomainEvent event) {
		actionsHandler.apply(event);
	}

	protected void subscribe(final DomainActionsContainer container) {
		actionsHandler.subscribe(container);
	}

	protected void unsubscribe(final DomainEvent event) {
		final String aggregateName = getIdentity()
			.getClass()
			.getSimpleName()
			.replace("Id", "")
			.toLowerCase();

		event.setAggregateName(aggregateName);
		event.setAggregateRootId(getIdentity().getValue());

		actionsHandler.getEvents().add(event);
	}
}