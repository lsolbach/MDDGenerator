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

import java.util.*;
import org.soulspace.modelling.repository.elements.*;
import org.soulspace.modelling.repository.elements.Element;

public  class MultiplicityImpl extends AbstractMultiplicity implements Multiplicity {

	private static final long serialVersionUID = 1L;

	public MultiplicityImpl() {
		super();
	}
	
	public MultiplicityImpl( String id,  boolean isProfileElement,  boolean initialized,  String low,  String high,  Element parentElement) {
		super(id, isProfileElement, initialized, low, high, parentElement);
	}
	
	public MultiplicityImpl( String id,  boolean isProfileElement,  boolean initialized,  String low,  String high) {
		super(id, isProfileElement, initialized, low, high);
	}

	@Override
	protected String doGetElementType() {		
		return "Multiplicity";
	}

	@Override
	protected Element doGetThis() {
		return this;
	}

}