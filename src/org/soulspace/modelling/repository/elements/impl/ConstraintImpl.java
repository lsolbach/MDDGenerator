package org.soulspace.modelling.repository.elements.impl;

import java.util.Set;
import java.util.TreeSet;

import org.soulspace.modelling.repository.elements.AbstractConstraint;
import org.soulspace.modelling.repository.elements.Element;
import org.soulspace.modelling.repository.elements.Expression;
import org.soulspace.modelling.repository.elements.Stereotype;
import org.soulspace.modelling.repository.elements.TaggedValue;

public class ConstraintImpl extends AbstractConstraint {

	private static final long serialVersionUID = 1L;

	Set<TaggedValue> taggedValueSet = null;
	Set<Stereotype> stereotypeSet = null;
	
	public ConstraintImpl() {

	}

	public ConstraintImpl(String id, boolean isProfileElement, boolean initialized, String name, String namespace,
			String qualifiedName, Expression expression, Element parentElement) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, expression, parentElement);
	}

	public ConstraintImpl(String id, boolean isProfileElement, boolean initialized, String name, String namespace,
			String qualifiedName, Expression expression) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, expression);
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
