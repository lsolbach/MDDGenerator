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
import org.soulspace.modelling.repository.elements.Association;
import org.soulspace.modelling.repository.elements.Package;
import org.soulspace.modelling.repository.elements.Class;
import org.soulspace.modelling.repository.elements.DataType;
import org.soulspace.modelling.repository.elements.Generalization;
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

public  class PackageImpl extends AbstractPackage implements Package {

	private static final long serialVersionUID = 1L;

	public PackageImpl() {
		super();
	}
	
	public PackageImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName,  Element parentElement) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName, parentElement);
	}
	
	public PackageImpl( String id,  boolean isProfileElement,  boolean initialized,  String name,  String namespace,  String qualifiedName) {
		super(id, isProfileElement, initialized, name, namespace, qualifiedName);
	}

	@Override
	protected String doGetElementType() {		
		return "Package";
	}

	@Override
	protected Element doGetThis() {
		return this;
	}

	@Override
	protected Set<TaggedValue> doGetTaggedValueSet() {
		Set<TaggedValue> taggedValueSet = new TreeSet<TaggedValue>();
		for(String key : getTaggedValueMap().keySet()) {
			taggedValueSet.add(getTaggedValue(key));
		}
		return Collections.unmodifiableSet(taggedValueSet);
	}

	@Override
	protected Set<Stereotype> doGetStereotypeSet() {
		Set<Stereotype> stereotypeSet = new TreeSet<Stereotype>();
		for(String key : getStereotypeMap().keySet()) {
			stereotypeSet.add(getStereotype(key));
		}
		return Collections.unmodifiableSet(stereotypeSet);
	}

	@Override
	protected Set<Class> doGetClassSet() {
		Set<Class> aClassSet = new TreeSet<Class>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof Class) {
				aClassSet.add((Class) element);
			}
		}
		return Collections.unmodifiableSet(aClassSet);
	}

	@Override
	protected Set<Interface> doGetInterfaceSet() {
		Set<Interface> aInterfaceSet = new TreeSet<Interface>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof Interface) {
				aInterfaceSet.add((Interface) element);
			}
		}
		return Collections.unmodifiableSet(aInterfaceSet);
	}

	@Override
	protected Set<Package> doGetPackageSet() {
		Set<Package> aPackageSet = new TreeSet<Package>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof Package) {
				aPackageSet.add((Package) element);
			}
		}
		return Collections.unmodifiableSet(aPackageSet);
	}

	@Override
	protected Set<DataType> doGetDataTypeSet() {
		Set<DataType> dataTypeSet = new TreeSet<DataType>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof DataType) {
				dataTypeSet.add((DataType) element);
			}
		}
		return Collections.unmodifiableSet(dataTypeSet);
	}

	@Override
	protected Set<Association> doGetAssociationSet() {
		Set<Association> associationSet = new TreeSet<Association>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof Association) {
				associationSet.add((Association) element);
			}
		}
		return Collections.unmodifiableSet(associationSet);
	}

	@Override
	protected Set<Dependency> doGetDependencySet() {
		Set<Dependency> dependencySet = new TreeSet<Dependency>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof Dependency) {
				dependencySet.add((Dependency) element);
			}
		}
		return Collections.unmodifiableSet(dependencySet);
	}

	@Override
	protected Set<Generalization> doGetGeneralizationSet() {
		Set<Generalization> generalizationSet = new TreeSet<Generalization>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof Generalization) {
				generalizationSet.add((Generalization) element);
			}
		}
		return Collections.unmodifiableSet(generalizationSet);
	}

	@Override
	protected Set<StateMachine> doGetStateMachineSet() {
		Set<StateMachine> stateMachineSet = new TreeSet<StateMachine>();
		for(ModelElement element : getOwnedElementSet()) {
			if(element instanceof StateMachine) {
				stateMachineSet.add((StateMachine) element);
			}
		}
		return Collections.unmodifiableSet(stateMachineSet);
	}

}