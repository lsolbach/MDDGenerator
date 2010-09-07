package org.soulspace.modelling.repository.builder.uml14.impl;

import org.soulspace.modelling.base.XmiObject;
import org.soulspace.modelling.repository.builder.ModelFactory;
import org.soulspace.modelling.repository.elements.Element;
import org.soulspace.modelling.repository.elements.Stereotype;
import org.soulspace.modelling.repository.elements.TaggedValue;
import org.soulspace.modelling.uml14.elements.ModelElement;

public aspect ElementInitializerAspect {

	pointcut elementInitialisation(Element element, ModelElement xmi) :
		execution(Element+ ModelFactory+.init*(Element+, XmiObject+))
		&&
		args(element, xmi)
		;

	before(Element element, XmiObject xmi) : elementInitialisation(element, xmi) {
//		element.setName(xmi.getName());
//
//		for(org.soulspace.modelling.uml14.elements.Stereotype xmiStereotype : xmiElement.getStereotypeSet()) {
//			Stereotype stereotype;
//			stereotype = (Stereotype) repository.findById(xmiStereotype.getXmiIdRef());
//			// stereotype = createStereotype(xmiStereotype);
//			element.addStereotype(stereotype);
//		}
//
//		for(org.soulspace.modelling.uml14.elements.TaggedValue xmiTaggedValue : xmiElement.getTaggedValueSet()) {
//			TaggedValue taggedValue = createTaggedValue(xmiTaggedValue);
//			element.addTaggedValue(taggedValue);
//		}
//
	}
		
}
