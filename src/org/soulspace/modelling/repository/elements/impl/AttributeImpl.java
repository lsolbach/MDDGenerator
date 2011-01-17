package org.soulspace.modelling.repository.elements.impl;

import org.soulspace.modelling.repository.elements.*;

public class AttributeImpl extends AbstractAttribute {

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
