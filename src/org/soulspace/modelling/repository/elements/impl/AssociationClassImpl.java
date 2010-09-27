package org.soulspace.modelling.repository.elements.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.modelling.repository.elements.AssociationClass;
import org.soulspace.modelling.repository.elements.AssociationEnd;
import org.soulspace.modelling.repository.elements.Attribute;
import org.soulspace.modelling.repository.elements.Classifier;
import org.soulspace.modelling.repository.elements.Dependency;
import org.soulspace.modelling.repository.elements.Operation;

public class AssociationClassImpl extends ClassImpl implements AssociationClass {

	private List<AssociationEnd> connectionList = new ArrayList<AssociationEnd>();
	
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

}
