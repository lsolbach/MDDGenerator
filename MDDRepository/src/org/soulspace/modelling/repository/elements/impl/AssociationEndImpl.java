
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


import org.soulspace.modelling.repository.elements.Classifier;
import org.soulspace.modelling.repository.elements.AssociationEnd;
import org.soulspace.modelling.repository.elements.Multiplicity;
import org.soulspace.modelling.repository.elements.Association;
import org.soulspace.modelling.repository.elements.Attribute;
import org.soulspace.modelling.repository.elements.TaggedValue;
import org.soulspace.modelling.repository.elements.Stereotype;
import java.util.*;
import org.soulspace.modelling.repository.elements.StateMachine;
import org.soulspace.modelling.repository.elements.Dependency;
import org.soulspace.modelling.repository.elements.*;
import org.soulspace.modelling.repository.elements.Constraint;
import org.soulspace.modelling.repository.elements.Comment;
import org.soulspace.modelling.repository.elements.ModelElement;
import org.soulspace.modelling.repository.elements.Element;

public  class AssociationEndImpl extends AbstractAssociationEnd implements AssociationEnd {

	Set<TaggedValue> taggedValueSet = null;
	
	Set<Stereotype> stereotypeSet = null;

	public AssociationEndImpl() {
		super();
	}
	
	public AssociationEndImpl( String id,  boolean isProfileElement,  boolean initialized,  String namespace,  String qualifiedName,  boolean navigable,  String name,  String visibility,  String changeability,  String aggregation,  String targetScope,  String ordering,  boolean derived,  Element parentElement,  Classifier type,  Multiplicity multiplicity,  AssociationEnd sourceEnd,  Association association) {
		super(id, isProfileElement, initialized, namespace, qualifiedName, navigable, name, visibility, changeability, aggregation, targetScope, ordering, derived, parentElement, type, multiplicity, sourceEnd, association);
	}
	
	public AssociationEndImpl( String id,  boolean isProfileElement,  boolean initialized,  String namespace,  String qualifiedName,  boolean navigable,  String name,  String visibility,  String changeability,  String aggregation,  String targetScope,  String ordering,  boolean derived,  Classifier type,  Multiplicity multiplicity,  AssociationEnd sourceEnd,  Association association) {
		super(id, isProfileElement, initialized, namespace, qualifiedName, navigable, name, visibility, changeability, aggregation, targetScope, ordering, derived, type, multiplicity, sourceEnd, association);
	}

	@Override
	protected String doGetElementType() {		
		return "AssociationEnd";
	}

	@Override
	protected Element doGetThis() {
		return this;
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