
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
import org.soulspace.modelling.repository.elements.Multiplicity;
import org.soulspace.modelling.repository.elements.Attribute;
import org.soulspace.modelling.repository.elements.Expression;
import org.soulspace.modelling.repository.elements.TaggedValue;
import org.soulspace.modelling.repository.elements.Stereotype;
import org.soulspace.modelling.repository.elements.StateMachine;
import java.util.*;
import org.soulspace.modelling.repository.elements.Dependency;
import org.soulspace.modelling.repository.elements.*;
import org.soulspace.modelling.repository.elements.Constraint;
import org.soulspace.modelling.repository.elements.Comment;
import org.soulspace.modelling.repository.elements.Element;

public  class AttributeImpl extends AbstractAttribute implements Attribute {

	Set<TaggedValue> taggedValueSet = null;
	
	Set<Stereotype> stereotypeSet = null;

	public AttributeImpl() {
		super();
	}
	
	public AttributeImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  String ownerScope,  String visibility,  boolean isDerived,  String changeability,  String ordering,  String targetScope,  boolean derived,  Expression initialValue,  Element parentElement,  Classifier type,  Multiplicity multiplicity) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, ownerScope, visibility, isDerived, changeability, ordering, targetScope, derived, initialValue, parentElement, type, multiplicity);
	}
	
	public AttributeImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  String ownerScope,  String visibility,  boolean isDerived,  String changeability,  String ordering,  String targetScope,  boolean derived,  Classifier type,  Multiplicity multiplicity) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, ownerScope, visibility, isDerived, changeability, ordering, targetScope, derived, type, multiplicity);
	}

	@Override
	protected String doGetElementType() {		
		return "Attribute";
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