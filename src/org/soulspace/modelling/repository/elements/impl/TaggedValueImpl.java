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

import org.soulspace.modelling.repository.elements.Stereotype;
import java.util.*;
import org.soulspace.modelling.repository.elements.StateMachine;
import org.soulspace.modelling.repository.elements.Dependency;
import org.soulspace.modelling.repository.elements.*;
import org.soulspace.modelling.repository.elements.Constraint;
import org.soulspace.modelling.repository.elements.TagDefinition;
import org.soulspace.modelling.repository.elements.Comment;
import org.soulspace.modelling.repository.elements.Element;
import org.soulspace.modelling.repository.elements.TaggedValue;

public  class TaggedValueImpl extends AbstractTaggedValue implements TaggedValue {

	private static final long serialVersionUID = 1L;

	public TaggedValueImpl() {
		super();
	}
	
	public TaggedValueImpl( String id,  boolean isProfileElement,  boolean initialized,  String namespace,  String qualifiedName,  String value,  String name,  Element parentElement,  TagDefinition type) {
		super(id, isProfileElement, initialized, namespace, qualifiedName, value, name, parentElement, type);
	}
	
	public TaggedValueImpl( String id,  boolean isProfileElement,  boolean initialized,  String namespace,  String qualifiedName,  String value,  String name,  TagDefinition type) {
		super(id, isProfileElement, initialized, namespace, qualifiedName, value, name, type);
	}

	@Override
	protected String doGetElementType() {		
		return "TaggedValue";
	}

	@Override
	protected Element doGetThis() {
		return this;
	}

	@Override
	protected Set<TaggedValue> doGetTaggedValueSet() {
		Set<TaggedValue> taggedValueSet = new TreeSet<TaggedValue>();
		for(String key : getTaggedValueMap().keySet()) {
			taggedValueSet.add(getTaggedValue(key));
		}
		return Collections.unmodifiableSet(taggedValueSet);
	}

	@Override
	protected Set<Stereotype> doGetStereotypeSet() {
		Set<Stereotype> stereotypeSet = new TreeSet<Stereotype>();
		for(String key : getStereotypeMap().keySet()) {
			stereotypeSet.add(getStereotype(key));
		}
		return Collections.unmodifiableSet(stereotypeSet);
	}

}