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

public  class ExpressionImpl extends AbstractExpression implements Expression {

	private static final long serialVersionUID = 1L;

	public ExpressionImpl() {
		super();
	}
	
	public ExpressionImpl( String id,  boolean isProfileElement,  boolean initialized,  String language,  String body,  Element parentElement) {
		super(id, isProfileElement, initialized, language, body, parentElement);
	}
	
	public ExpressionImpl( String id,  boolean isProfileElement,  boolean initialized,  String language,  String body) {
		super(id, isProfileElement, initialized, language, body);
	}

	@Override
	protected String doGetElementType() {		
		return "Expression";
	}

	@Override
	protected Element doGetThis() {
		return this;
	}

}