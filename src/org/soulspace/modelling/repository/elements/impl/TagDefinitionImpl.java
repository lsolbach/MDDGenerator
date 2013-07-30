/*
 * Java bean TagDefinitionImpl
 * Implementation of interface TagDefinition.
 * Template:  java/element-impl-class
 * Imports:   lib,model/lib,java/lib,java/class
 * Timestamp: Wed Sep 28 00:15:51 CEST 2011
 */

package org.soulspace.modelling.repository.elements.impl;

import java.util.*;

import org.soulspace.modelling.repository.elements.*;

public class TagDefinitionImpl extends AbstractTagDefinition {

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

}
