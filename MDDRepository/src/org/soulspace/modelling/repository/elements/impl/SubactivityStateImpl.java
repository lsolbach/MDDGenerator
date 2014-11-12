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

import org.soulspace.modelling.repository.elements.Transition;
import org.soulspace.modelling.repository.elements.Event;
import org.soulspace.modelling.repository.elements.Multiplicity;
import org.soulspace.modelling.repository.elements.TaggedValue;
import org.soulspace.modelling.repository.elements.StateVertex;
import org.soulspace.modelling.repository.elements.Stereotype;
import java.util.*;
import org.soulspace.modelling.repository.elements.StateMachine;
import org.soulspace.modelling.repository.elements.Action;
import org.soulspace.modelling.repository.elements.Dependency;
import org.soulspace.modelling.repository.elements.*;
import org.soulspace.modelling.repository.elements.Constraint;
import org.soulspace.modelling.repository.elements.Comment;
import org.soulspace.modelling.repository.elements.Element;

public  class SubactivityStateImpl extends AbstractSubactivityState implements SubactivityState {

	private static final long serialVersionUID = 1L;

	public SubactivityStateImpl() {
		super();
	}
	
	public SubactivityStateImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  boolean isConccurent,  boolean isDynamic,  Multiplicity dynamicMultiplicity,  Element parentElement,  Action entryAction,  Action exitAction,  Action doActivity,  StateMachine submachine) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, isConccurent, isDynamic, dynamicMultiplicity, parentElement, entryAction, exitAction, doActivity, submachine);
	}
	
	public SubactivityStateImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  boolean isConccurent,  boolean isDynamic,  Multiplicity dynamicMultiplicity,  StateMachine submachine) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, isConccurent, isDynamic, dynamicMultiplicity, submachine);
	}

	@Override
	protected String doGetElementType() {		
		return "SubactivityState";
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