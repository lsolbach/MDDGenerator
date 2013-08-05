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
