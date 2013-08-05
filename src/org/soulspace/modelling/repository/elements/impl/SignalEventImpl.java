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

import org.soulspace.modelling.repository.elements.AbstractSignalEvent;
import org.soulspace.modelling.repository.elements.Element;
import org.soulspace.modelling.repository.elements.Signal;
import org.soulspace.modelling.repository.elements.SignalEvent;
import org.soulspace.modelling.repository.elements.Stereotype;
import org.soulspace.modelling.repository.elements.TaggedValue;

public class SignalEventImpl extends AbstractSignalEvent implements SignalEvent {

	private static final long serialVersionUID = 1L;

	Set<TaggedValue> taggedValueSet = null;
	Set<Stereotype> stereotypeSet = null;
	
	public SignalEventImpl() {
		super();
	}

	public SignalEventImpl(String id, boolean isProfileElement, boolean initialized, String name, String namespace,
			String qualifiedName, Element parentElement, Signal signal) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, parentElement, signal);
	}

	public SignalEventImpl(String id, boolean isProfileElement, boolean initialized, String name, String namespace,
			String qualifiedName, Signal signal) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, signal);
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

}
