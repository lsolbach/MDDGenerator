package org.soulspace.modelling.repository.elements.impl;

import java.util.HashSet;
import java.util.Set;

import org.soulspace.modelling.repository.elements.*;
import org.soulspace.modelling.repository.elements.Class;
import org.soulspace.modelling.repository.elements.Interface;
import org.soulspace.modelling.repository.elements.Package;

public class PackageImpl extends AbstractPackage {

	@Override
	protected Set<Class> doGetClassSet() {
		Set<Class> classSet = new HashSet<Class>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof Class) {
				classSet.add((Class) element);
			}
		}
		return classSet;
	}

	@Override
	protected Set<Interface> doGetInterfaceSet() {
		Set<Interface> interfaceSet = new HashSet<Interface>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof Interface) {
				interfaceSet.add((Interface) element);
			}
		}
		return interfaceSet;
	}

	@Override
	protected Set<Package> doGetPackageSet() {
		Set<Package> packageSet = new HashSet<Package>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof Package) {
				packageSet.add((Package) element);
			}
		}
		return packageSet;
	}

	@Override
	protected Set<DataType> doGetDataTypeSet() {
		Set<DataType> dataTypeSet = new HashSet<DataType>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof DataType) {
				dataTypeSet.add((DataType) element);
			}
		}
		return dataTypeSet;
	}

	@Override
	protected Set<Association> doGetAssociationSet() {
		Set<Association> associationSet = new HashSet<Association>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof Association) {
				associationSet.add((Association) element);
			}
		}
		return associationSet;
	}

	@Override
	protected Set<Dependency> doGetDependencySet() {
		Set<Dependency> dependencySet = new HashSet<Dependency>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof Dependency) {
				dependencySet.add((Dependency) element);
			}
		}
		return dependencySet;
	}

	@Override
	protected Set<Generalization> doGetGeneralizationSet() {
		Set<Generalization> generalizationSet = new HashSet<Generalization>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof Generalization) {
				generalizationSet.add((Generalization) element);
			}
		}
		return generalizationSet;
	}

	@Override
	protected Set<StateMachine> doGetStateMachineSet() {
		Set<StateMachine> stateMachineSet = new HashSet<StateMachine>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof StateMachine) {
				stateMachineSet.add((StateMachine) element);
			}
		}
		return stateMachineSet;
	}

}
