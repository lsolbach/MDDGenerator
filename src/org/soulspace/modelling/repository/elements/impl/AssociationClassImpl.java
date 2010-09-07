package org.soulspace.modelling.repository.elements.impl;

import java.util.List;

import org.soulspace.modelling.repository.elements.AbstractAssociationClass;
import org.soulspace.modelling.repository.elements.Association;
import org.soulspace.modelling.repository.elements.AssociationClass;
import org.soulspace.modelling.repository.elements.AssociationEnd;
import org.soulspace.modelling.repository.elements.Attribute;
import org.soulspace.modelling.repository.elements.Class;
import org.soulspace.modelling.repository.elements.Classifier;
import org.soulspace.modelling.repository.elements.Dependency;
import org.soulspace.modelling.repository.elements.Element;
import org.soulspace.modelling.repository.elements.Operation;

public class AssociationClassImpl extends ClassImpl implements AssociationClass {

	@Override
	protected List<AssociationEnd> doGetAllAssociationList() {
		return super.doGetAllAssociationList();
	}

	@Override
	protected List<Attribute> doGetAllAttributeList() {
		return super.doGetAllAttributeList();
	}

	@Override
	protected List<Dependency> doGetAllDependencyList() {
		return super.doGetAllDependencyList();
	}

	@Override
	protected List<Operation> doGetAllOperationList() {
		return super.doGetAllOperationList();
	}

	@Override
	protected List<Classifier> doGetAllReferencedTypeList() {
		return super.getAllReferencedTypeList();
	}

	@Override
	protected List<Classifier> doGetReferencedTypeList() {
		return super.doGetReferencedTypeList();
	}

}
