package org.soulspace.modelling.repository.elements.impl;

import java.util.Set;
import java.util.TreeSet;

import org.soulspace.modelling.repository.elements.AbstractSendAction;
import org.soulspace.modelling.repository.elements.Element;
import org.soulspace.modelling.repository.elements.Signal;
import org.soulspace.modelling.repository.elements.Stereotype;
import org.soulspace.modelling.repository.elements.TaggedValue;

public class SendActionImpl extends AbstractSendAction {

	private static final long serialVersionUID = 1L;

	Set<TaggedValue> taggedValueSet = null;
	Set<Stereotype> stereotypeSet = null;
	

	public SendActionImpl() {
		super();
	}

	public SendActionImpl(String id, boolean isProfileElement, boolean initialized, String name, String namespace,
			String qualifiedName, Element parentElement, Signal signal) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, parentElement, signal);
	}

	public SendActionImpl(String id, boolean isProfileElement, boolean initialized, String name, String namespace,
			String qualifiedName, Signal signal) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, signal);
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
