package org.soulspace.xmi.uml14.elements.impl;

import org.soulspace.xmi.uml14.elements.BooleanExpression;
import org.soulspace.xmi.uml14.elements.Constraint;
import org.soulspace.xmi.uml14.elements.Stereotype;

public class ConstraintImpl extends AbstractConstraint implements Constraint {

	public ConstraintImpl() {
		super();
	}

	public ConstraintImpl(BooleanExpression body,
			Stereotype constraintStereotype) {
		super(body, constraintStereotype);
	}

	public ConstraintImpl(BooleanExpression body) {
		super(body);
	}

}
