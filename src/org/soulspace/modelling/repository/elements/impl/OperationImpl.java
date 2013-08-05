/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.modelling.repository.elements.impl;

import java.util.Set;
import java.util.TreeSet;

import org.soulspace.modelling.repository.elements.*;

public class OperationImpl extends AbstractOperation {

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
