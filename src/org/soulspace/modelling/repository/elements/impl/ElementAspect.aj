package org.soulspace.modelling.repository.elements.impl;

import java.util.Map;

import org.soulspace.modelling.repository.elements.Element;
import org.soulspace.modelling.repository.elements.Stereotype;
import org.soulspace.modelling.repository.elements.TaggedValue;

public aspect ElementAspect {

	Map<String, Stereotype> Element.stereotypeMap;	
	Map<String, TaggedValue> Element.taggedValueMap;	

	public Map<String, Stereotype> Element.doGetStereotypeMap() {
		return stereotypeMap;
	}
	
	public Map<String, TaggedValue> Element.doGetTaggedValueMap() {
		return taggedValueMap;
	}
	
	pointcut stereotypeAdd(Stereotype stereotype) :
		execution(void Element+.addStereotype(Stereotype))
		&&
		args(stereotype)
	;
	
	pointcut stereotypeRemove(Stereotype stereotype) :
		execution(void Element+.removeStereotype(Stereotype))
		&&
		args(stereotype)
	;
	
	pointcut taggedValueAdd(TaggedValue taggedValue) :
		execution(void Element+.addTaggedValue(TaggedValue))
		&&
		args(taggedValue)
	;
	
	pointcut taggedValueRemove(TaggedValue taggedValue) :
		execution(void Element+.removeTaggedValue(TaggedValue))
		&&
		args(taggedValue)
	;
	
}
