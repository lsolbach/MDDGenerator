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
import org.soulspace.modelling.repository.elements.Operation;
import org.soulspace.modelling.repository.elements.TaggedValue;
import org.soulspace.modelling.repository.elements.Stereotype;
import org.soulspace.modelling.repository.elements.StateMachine;
import java.util.*;
import org.soulspace.modelling.repository.elements.Dependency;
import org.soulspace.modelling.repository.elements.*;
import org.soulspace.modelling.repository.elements.Constraint;
import org.soulspace.modelling.repository.elements.Comment;
import org.soulspace.modelling.repository.elements.Element;

public  class OperationImpl extends AbstractOperation implements Operation {

	private static final long serialVersionUID = 1L;

	public OperationImpl() {
		super();
	}
	
	public OperationImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  String ownerScope,  String visibility,  boolean isDerived,  boolean isQuery,  String concurrency,  boolean isAbstract,  Element parentElement,  Classifier returnType) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, ownerScope, visibility, isDerived, isQuery, concurrency, isAbstract, parentElement, returnType);
	}
	
	public OperationImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  String ownerScope,  String visibility,  boolean isDerived,  boolean isQuery,  String concurrency,  boolean isAbstract,  Classifier returnType) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, ownerScope, visibility, isDerived, isQuery, concurrency, isAbstract, returnType);
	}

	@Override
	protected String doGetElementType() {		
		return "Operation";
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