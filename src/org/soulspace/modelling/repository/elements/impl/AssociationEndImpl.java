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

public class AssociationEndImpl extends AbstractAssociationEnd {

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
	protected boolean doCheckOverride(AssociationEnd ae) {
		if((getName() == null) 
				|| (getName() != null && ae.getName() == null)) {
			return false;
		}
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
