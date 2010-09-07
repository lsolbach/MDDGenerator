package org.soulspace.modelling.repository.elements.impl;

import org.soulspace.modelling.repository.elements.AbstractActor;
import org.soulspace.modelling.repository.elements.Actor;
import org.soulspace.modelling.repository.elements.AssociationEnd;
import org.soulspace.modelling.repository.elements.Element;

public class ActorImpl extends AbstractActor {

	public ActorImpl() {
		super();
	}

	public ActorImpl(Actor subActor, Actor superActor) {
		super(subActor, superActor);
	}

}
