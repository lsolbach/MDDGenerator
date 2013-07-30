package org.soulspace.modelling.repository.elements.impl;

import java.util.Set;
import java.util.TreeSet;

import org.soulspace.modelling.repository.elements.*;

public class AttributeImpl extends AbstractAttribute {

	private static final long serialVersionUID = 1L;

	Set<TaggedValue> taggedValueSet = null;
	Set<Stereotype> stereotypeSet = null;
	
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

	@Override
	protected boolean doCheckOverride(Attribute attr) {
		if (attr == null || attr.getType() == null) {
			return false;
		}
		if (!getType().equals(attr.getType())) {
			// TODO check type compatibility
			// (IClassifier.isCompatible()/isAssignableFrom()?)
			return false;
		}
		if (!getName().equals(attr.getName())) {
			return false;
		}
		if (!getVisibility().equals(attr.getVisibility())) {
			// TODO implement check?
		}
		// TODO handle other attributes (ownerScope, changeability)
		return true;
	}

}
