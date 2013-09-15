/*
 * Class AssociationClassImpl
 * Classifier: Class 127-0-0-1-199e833f:124331bfc01:-8000:0000000000001309
 * Template:   java/class
 * Includes:   lib, model/lib, java/lib, java/class, java/impl-class, java/bean-impl-class, mdd/java/lib, mdd/java/element-impl-class
 * Timestamp:  Sun Sep 15 10:37:20 CEST 2013
 */
package org.soulspace.modelling.repository.elements.impl;


import org.soulspace.modelling.repository.elements.Interface;
import org.soulspace.modelling.repository.elements.AssociationEnd;
import java.util.List;
import org.soulspace.modelling.repository.elements.Attribute;
import org.soulspace.modelling.repository.elements.Class;
import org.soulspace.modelling.repository.elements.Operation;
import org.soulspace.modelling.repository.elements.TaggedValue;
import org.soulspace.modelling.repository.elements.Stereotype;
import java.util.*;
import org.soulspace.modelling.repository.elements.StateMachine;
import org.soulspace.modelling.repository.elements.Dependency;
import org.soulspace.modelling.repository.elements.*;
import org.soulspace.modelling.repository.elements.Constraint;
import org.soulspace.modelling.repository.elements.Comment;
import org.soulspace.modelling.repository.elements.ModelElement;
import org.soulspace.modelling.repository.elements.Element;

public  class AssociationClassImpl extends ClassImpl implements AssociationClass {

	private List<AssociationEnd> connectionList = new ArrayList<AssociationEnd>();

	Set<TaggedValue> taggedValueSet = null;
	
	Set<Stereotype> stereotypeSet = null;

	public AssociationClassImpl() {
		super();
	}
	
	public AssociationClassImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  String visibility,  boolean isAbstract,  Element parentElement) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, visibility, isAbstract, parentElement);
	}
	
	public AssociationClassImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  String visibility,  boolean isAbstract) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, visibility, isAbstract);
	}

	public List<AssociationEnd> getConnectionList() {
		return connectionList;
	}

	public void setConnectionList(List<AssociationEnd> connectionList) {
		this.connectionList = connectionList;
	}

	public void addConnection(AssociationEnd connection) {
		connectionList.add(connection);
	}

	public void removeConnection(AssociationEnd connection) {
		connectionList.remove(connection);
	}

	protected String doGetElementType() {		
		return "AssociationClass";
	}

	protected Element doGetThis() {
		return this;
	}

	protected Set<TaggedValue> doGetTaggedValueSet() {
		if(taggedValueSet == null) {
			taggedValueSet = new TreeSet<TaggedValue>();
			for(String key : getTaggedValueMap().keySet()) {
				taggedValueSet.add(getTaggedValue(key));
			}
		}
		return taggedValueSet;
	}

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