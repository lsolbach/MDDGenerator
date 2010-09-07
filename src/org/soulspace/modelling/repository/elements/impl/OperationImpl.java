package org.soulspace.modelling.repository.elements.impl;

import org.soulspace.modelling.repository.elements.*;

public class OperationImpl extends AbstractOperation {

	@Override
	protected boolean doCheckOverride(Operation op) {
		if(!getName().equals(op.getName())) {
			return false;
		}
		if(getParameterList().size() != op.getParameterList().size()) {
			return false;
		}
		for(int i = 0; i < getParameterList().size(); i++) {
			// TODO check type compatibility (IClassifier.isCompatible()/isAssignableFrom()?)
			// TODO handle return type
			if(!getParameterList().get(i).getType().equals(op.getParameterList().get(i).getType())) {
				return false;
			}
		}
		if(!getVisibility().equals(op.getVisibility())) {
			// TODO implement check?
		}
		// TODO handle other attributes (isQuery, ownerScope, concurrency, ...)
		return true;
	}

}
