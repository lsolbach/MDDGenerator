package org.soulspace.modelling.repository.elements.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.soulspace.modelling.repository.elements.*;
import org.soulspace.modelling.repository.elements.Class;

public class ClassImpl extends AbstractClass {

	public ClassImpl() {
		super();
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
		List<Classifier> allReferencedTypeList = new ArrayList<Classifier>();
		for (Class c : getSuperClassList()) {
			allReferencedTypeList = addUnique(allReferencedTypeList, c
					.getAllReferencedTypeList());
			// allReferencedTypes.addAll(c.getAllReferencedTypes());
		}
		allReferencedTypeList = addUnique(allReferencedTypeList, getReferencedTypeList());

		return allReferencedTypeList;
	}

	@Override
	protected List<Classifier> doGetReferencedTypeList() {
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
				if (Integer.parseInt(ae.getMultiplicity().getHigh()) > 1) {
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
				referencedTypeList.add(d.getSupplier());
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
