package org.soulspace.modelling.repository.elements.impl;

import java.util.Set;
import java.util.TreeSet;

import org.soulspace.modelling.repository.elements.AbstractActor;
import org.soulspace.modelling.repository.elements.Actor;
import org.soulspace.modelling.repository.elements.Element;
import org.soulspace.modelling.repository.elements.Stereotype;
import org.soulspace.modelling.repository.elements.TaggedValue;

public class ActorImpl extends AbstractActor {

	private static final long serialVersionUID = 1L;

	Set<TaggedValue> taggedValueSet = null;
	Set<Stereotype> stereotypeSet = null;
	
	public ActorImpl() {
		super();
	}

	public ActorImpl(String id, boolean isProfileElement, boolean initialized, String name, String namespace, String qualifiedName,
			Actor subActor, Actor superActor) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, subActor, superActor);
	}

	public ActorImpl(String id, boolean isProfileElement, boolean initialized, String name, String namespace, String qualifiedName,
			Element parentElement, Actor subActor, Actor superActor) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, parentElement, subActor, superActor);
	}


	@Override
	protected Set<TaggedValue> doGetTaggedValueSet() {
		if(taggedValueSet == null) {
			taggedValueSet = new TreeSet<TaggedValue>();
			for(String key : getTaggedValueMap().keySet()) {
				taggedValueSet.add(getTaggedValue(key));
			}
		}
		return taggedValueSet;
	}

	@Override
	protected Set<Stereotype> doGetStereotypeSet() {
		if(stereotypeSet == null) {
			stereotypeSet = new TreeSet<Stereotype>();
			for(String key : getStereotypeMap().keySet()) {
				stereotypeSet.add(getStereotype(key));
			}
			
		}
		return stereotypeSet;
	}

}
