package org.soulspace.modelling.repository.elements.impl;

import org.soulspace.modelling.repository.elements.*;

public class ElementImpl extends AbstractElement {

	private static final long serialVersionUID = 1L;

	public ElementImpl() {
		super();
	}
	
	public ElementImpl(String id, boolean isProfileElement, boolean initialized) {
		super(id, isProfileElement, initialized);
	}
	
	public ElementImpl(String id, boolean isProfileElement, boolean initialized, Element parentElement) {
		super(id, isProfileElement, initialized, parentElement);
	}

}
