package org.soulspace.modelling.repository.elements.impl;

import org.soulspace.modelling.repository.elements.*;

public class AssociationEndImpl extends AbstractAssociationEnd {

	@Override
	protected boolean doCheckOverride(AssociationEnd ae) {
		if(!getName().equals(ae.getName())) {
			return false;
		}
		if(!getType().equals(ae.getType())) {
			// TODO check type compatibility (IClassifier.isCompatible()/isAssignableFrom()?)
			return false;
		}
		if(!getVisibility().equals(ae.getVisibility())) {
			// TODO implement check?
		}
		if(!getMultiplicity().getHigh().equals(ae.getMultiplicity().getHigh())) {
			// TODO handle getLow()
			return false;
		}
		// TODO handle other attributes (ownerScope, changeability)
		return true;
	}

}
