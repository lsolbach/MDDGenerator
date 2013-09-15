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


import org.soulspace.modelling.repository.elements.Interface;
import org.soulspace.modelling.repository.elements.AssociationEnd;
import java.util.List;
import org.soulspace.modelling.repository.elements.Class;
import org.soulspace.modelling.repository.elements.Attribute;
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

public  class ClassImpl extends AbstractClass implements Class {

	Set<TaggedValue> taggedValueSet = null;
	
	Set<Stereotype> stereotypeSet = null;

	public ClassImpl() {
		super();
	}
	
	public ClassImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  String visibility,  boolean isAbstract,  Element parentElement) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, visibility, isAbstract, parentElement);
	}
	
	public ClassImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  String visibility,  boolean isAbstract) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, visibility, isAbstract);
	}

	@Override
	protected String doGetElementType() {		
		return "Class";
	}

	@Override
	protected Element doGetThis() {
		return this;
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

	@Override
	protected List<Attribute> doGetAllAttributeList() {
		List<Attribute> allAttributeList = new ArrayList<Attribute>();
		for (Class c : getSuperClassList()) {
			allAttributeList = addUnique(allAttributeList, c.getAllAttributeList());
		}
		for (Attribute attr : getAttributeList()) {
			if (allAttributeList.contains(attr)) {
//				System.out
//						.println("DEBUG: dropping duplicate attribute " + attr);
				break;
			} else {
				Attribute overridden = null;
				for (Attribute superAttr : allAttributeList) {
					if (attr.checkOverride(superAttr)) {
						overridden = superAttr;
					}
				}
				if (overridden != null) {
//					System.out.println("DEBUG: overriding attribute "
//							+ overridden + " with " + attr);
					allAttributeList.remove(overridden);
				}
				allAttributeList.add(attr);
			}
		}

		return allAttributeList;
	}

	@Override
	protected List<AssociationEnd> doGetAllAssociationList() {
		List<AssociationEnd> allAssociationList = new ArrayList<AssociationEnd>();
		for (Class c : getSuperClassList()) {
			allAssociationList = addUnique(allAssociationList, c.getAllAssociationList());
		}

		for (AssociationEnd ae : getAssociationList()) {
			if (allAssociationList.contains(ae)) {
//				System.out
//						.println("DEBUG: dropping duplicate association " + ae);
				break;
			} else {
				AssociationEnd overridden = null;
				for (AssociationEnd superAe : allAssociationList) {
					if (ae.checkOverride(superAe)) {
						overridden = superAe;
					}
				}
				if (overridden != null) {
//					System.out.println("DEBUG: overriding association "
//							+ overridden + " with " + ae);
					allAssociationList.remove(overridden);
				}
				allAssociationList.add(ae);
			}
		}

		return allAssociationList;
	}

	@Override
	protected List<Operation> doGetAllOperationList() {
		List<Operation> allOperationList = new ArrayList<Operation>();
		for (Class c : getSuperClassList()) {
			allOperationList = addUnique(allOperationList, c.getAllOperationList());
		}
		for (Operation op : getOperationList()) {
			if (allOperationList.contains(op)) {
//				System.out.println("DEBUG: dropping duplicate operation " + op);
				break;
			} else {
				Operation overridden = null;
				for (Operation superOp : allOperationList) {
					if (op.checkOverride(superOp)) {
						overridden = superOp;
					}
				}
				if (overridden != null) {
//					System.out.println("DEBUG: overriding operation "
//							+ overridden + " with " + op);
					allOperationList.remove(overridden);
				}
				allOperationList.add(op);
			}
		}

		return allOperationList;
	}

	@Override
	protected List<Dependency> doGetAllDependencyList() {
		List<Dependency> allDependencyList = new ArrayList<Dependency>();

		for (Class c : getSuperClassList()) {
			allDependencyList = addUnique(allDependencyList, c.getDependencyList());
		}
		allDependencyList = addUnique(allDependencyList, getDependencyList());

		return allDependencyList;
	}

	@Override
	protected List<Dependency> doGetAllReverseDependencyList() {
		return null;
	}

	@Override
	protected List<Classifier> doGetAllReferencedTypeList() {
		// TODO check for cycles
		List<Classifier> allReferencedTypeList = new ArrayList<Classifier>();
		for (Class c : getSuperClassList()) {
			allReferencedTypeList = addUnique(allReferencedTypeList, c
					.getAllReferencedTypeList());
			// allReferencedTypeList.addAll(c.getAllReferencedTypes());
		}
		allReferencedTypeList = addUnique(allReferencedTypeList, getReferencedTypeList());

		return allReferencedTypeList;
	}

	@Override
	protected List<Classifier> doGetReferencedTypeList() {
		// TODO check for cycles
		List<Classifier> referencedTypeList = new ArrayList<Classifier>();

		for (Attribute a : getAttributeList()) {
			if (a.getType() != null
					&& !a.getType().getNamespace().startsWith("java.lang")
					&& !referencedTypeList.contains(a.getType())) {
				referencedTypeList.add(a.getType());
			}
		}

		for (AssociationEnd ae : getAssociationList()) {
			if (ae.getNavigable() && ae.getType() != null
					&& !ae.getType().getNamespace().startsWith("java.lang")
					&& !referencedTypeList.contains(ae.getType())) {
				referencedTypeList.add(ae.getType());
				if (ae.getMultiplicity() != null &&
						Integer.parseInt(ae.getMultiplicity().getHigh()) > 1) {
					// TODO: Add java.util.Collection as type?
				}
			}
		}

		for (Operation o : getOperationList()) {
			for (Parameter p : o.getParameterList()) {
				if (p.getType() != null
						&& !p.getType().getNamespace().startsWith("java.lang")
						&& !referencedTypeList.contains(p.getType())) {
					referencedTypeList.add(p.getType());
				}
			}
		}

		for (Class c : getSuperClassList()) {
			if (!referencedTypeList.contains(c)) {
				referencedTypeList.add(c);
			}
		}

		for (Dependency d : getDependencyList()) {
			if (!referencedTypeList.contains(d.getSupplier())) {
				if(d.getSupplier() instanceof Classifier) {
					referencedTypeList.add((Classifier) d.getSupplier());
				}
			}
		}

		return referencedTypeList;
	}

	private <T> List<T> addUnique(List<T> l1, List<T> l2) {
		for (T element : l2) {
			if (!l1.contains(element)) {
				l1.add(element);
			}
		}
		return l1;
	}

}