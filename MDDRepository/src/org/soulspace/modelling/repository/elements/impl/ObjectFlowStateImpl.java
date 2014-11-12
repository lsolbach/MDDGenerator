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

import org.soulspace.modelling.repository.elements.Parameter;
import org.soulspace.modelling.repository.elements.Classifier;
import org.soulspace.modelling.repository.elements.Transition;
import org.soulspace.modelling.repository.elements.Event;
import org.soulspace.modelling.repository.elements.TaggedValue;
import org.soulspace.modelling.repository.elements.Stereotype;
import java.util.*;
import org.soulspace.modelling.repository.elements.StateMachine;
import org.soulspace.modelling.repository.elements.Action;
import org.soulspace.modelling.repository.elements.Dependency;
import org.soulspace.modelling.repository.elements.*;
import org.soulspace.modelling.repository.elements.Constraint;
import org.soulspace.modelling.repository.elements.Comment;
import org.soulspace.modelling.repository.elements.Element;

public  class ObjectFlowStateImpl extends AbstractObjectFlowState implements ObjectFlowState {

	private static final long serialVersionUID = 1L;

	public ObjectFlowStateImpl() {
		super();
	}
	
	public ObjectFlowStateImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  boolean isSynch,  Element parentElement,  Action entryAction,  Action exitAction,  Action doActivity,  Classifier type) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, isSynch, parentElement, entryAction, exitAction, doActivity, type);
	}
	
	public ObjectFlowStateImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  boolean isSynch,  Classifier type) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, isSynch, type);
	}

	@Override
	protected String doGetElementType() {		
		return "ObjectFlowState";
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