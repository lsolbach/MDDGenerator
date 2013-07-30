package org.soulspace.modelling.repository.elements.impl;

import org.soulspace.modelling.repository.elements.AbstractExpression;
import org.soulspace.modelling.repository.elements.Element;

public class ExpressionImpl extends AbstractExpression {

	private static final long serialVersionUID = 1L;

	public ExpressionImpl() {

	}

	public ExpressionImpl(String id, boolean isProfileElement, boolean initialized, String language, String body,
			Element parentElement) {
		super(id, isProfileElement, initialized, language, body, parentElement);
	}

	public ExpressionImpl(String id, boolean isProfileElement, boolean initialized, String language, String body) {
		super(id, isProfileElement, initialized, language, body);
	}

}
