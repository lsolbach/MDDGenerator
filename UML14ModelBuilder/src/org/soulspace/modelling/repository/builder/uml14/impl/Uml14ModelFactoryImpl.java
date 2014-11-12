/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.modelling.repository.builder.uml14.impl;

import org.soulspace.modelling.base.XmiObject;
import org.soulspace.modelling.repository.ModelRepository;
import org.soulspace.modelling.repository.builder.ModelFactory;
import org.soulspace.modelling.repository.builder.impl.AbstractModelFactory;
import org.soulspace.modelling.repository.elements.*;
import org.soulspace.modelling.repository.elements.Class;
import org.soulspace.modelling.repository.elements.Package;
import org.soulspace.modelling.repository.elements.impl.*;
import org.soulspace.modelling.uml14.elements.MultiplicityRange;
import org.soulspace.modelling.uml14.impl.Uml14RepositoryImpl;

public class Uml14ModelFactoryImpl extends AbstractModelFactory implements
		ModelFactory {

	public Uml14ModelFactoryImpl(Uml14RepositoryImpl umlRepository,
			ModelRepository repository) {
		this.repository = repository;
		this.umlRepository = umlRepository;
	}

	//
	// initialize abstract elements
	//
	protected <T extends Element> T initElement(T element,
			org.soulspace.modelling.base.XmiObject xmiSource) {
		if(element == null || xmiSource == null) {
			if(xmiSource != null) {
				System.out.println("ERROR: model element is null " + xmiSource);
			} else if(element != null) {
				System.out.println("ERROR: xmi element is null " + element);
			} else {
				System.out.println("ERROR: element and xmi element are null!");				
			}
		}
		element.setId(xmiSource.getXmiId());			
		element.setIsProfileElement(xmiSource.getProfileElement());
		if(xmiSource.getParentElement() != null) {
			element.setParentElement(createElement(xmiSource.getParentElement()));
		}
		return element;
	}

	protected <T extends ModelElement> T initModelElement(T element,
			org.soulspace.modelling.uml14.elements.ModelElement xmiSource) {

		element = initElement(element, xmiSource);

		element.setName(xmiSource.getName());
		for (org.soulspace.modelling.uml14.elements.Stereotype xmiStereotype : xmiSource
				.getStereotypeList()) {
			Stereotype st = createStereotype(xmiStereotype);
			element.addStereotype(st.getName(), st);
		}

		for (org.soulspace.modelling.uml14.elements.TaggedValue xmiTaggedValue : xmiSource
				.getTaggedValueList()) {
			TaggedValue tv = createTaggedValue(xmiTaggedValue);
			element.addTaggedValue(tv.getName(), tv);
		}

		for (org.soulspace.modelling.uml14.elements.Comment xmiComment : xmiSource
				.getCommentList()) {
			element.addComment(createComment(xmiComment));
		}

		for (org.soulspace.modelling.uml14.elements.Constraint xmiConstraint : xmiSource
				.getConstraintList()) {
			element.addConstraint(createConstraint(xmiConstraint));
		}

		for (org.soulspace.modelling.uml14.elements.Component xmiComponent : xmiSource
				.getContainerList()) {

		}

		for (org.soulspace.modelling.uml14.elements.StateMachine xmiStateMachine : xmiSource
				.getBehaviorList()) {
			// element.addStateMachine();
		}

		for (org.soulspace.modelling.uml14.elements.Dependency xmiDependency : xmiSource
				.getSupplierDependencyList()) {
			element.addSupplierDependency(createDependency(xmiDependency));
		}

		for (org.soulspace.modelling.uml14.elements.Dependency xmiDependency : xmiSource
				.getClientDependencyList()) {
			element.addClientDependency(createDependency(xmiDependency));
		}

		return element;
	}

	protected <T extends Namespace> T initNamespace(T namespace,
			org.soulspace.modelling.uml14.elements.Namespace xmiSource) {
		namespace = initModelElement(namespace, xmiSource);
		for (org.soulspace.modelling.uml14.elements.ModelElement xmiElement : xmiSource
				.getOwnedElementList()) {
			ModelElement element = createModelElement(xmiElement);
			if(element != null) {
				element.setNamespace(buildNamespace(xmiSource));
//				if(element.getName() == null) {
//					System.out.println("Name is null for element of type " + element.getElementType() + " with id " + element.getId());
//				} else if(element.getNamespace() == null) {
//					System.out.println("Namespace is null for element " + element.getName());
//				}
				if(element.getNamespace() != null && !element.getNamespace().equals("")) {
					element.setQualifiedName(element.getNamespace() + "." + element.getName());
				} else {
					element.setQualifiedName(element.getName());
				}
				namespace.addOwnedElement(element);
				// FIXME trace addition of owned elements
			}
		}
		return namespace;
	}

	protected <T extends Feature> T initFeature(T feature,
			org.soulspace.modelling.uml14.elements.Feature xmiSource) {
		feature = initModelElement(feature, xmiSource);
		feature.setVisibility(xmiSource.getVisibility().getName());
		feature.setOwnerScope(xmiSource.getOwnerScope().getName());
		// FIXME feature.setIsDerived();
		// feature.setIsDerived(arg0);
		return feature;
	}

	protected <T extends StructuralFeature> T initStructuralFeature(T stf,
			org.soulspace.modelling.uml14.elements.StructuralFeature xmiSource) {
		stf = initFeature(stf, xmiSource);
		if (xmiSource.getType() != null) {
			stf.setType(createClassifier(xmiSource.getType()));
		}
		if (xmiSource.getChangeability() != null) {
			stf.setChangeability(xmiSource.getChangeability().getName());
		}
		if (xmiSource.getTargetScope() != null) {
			stf.setTargetScope(xmiSource.getTargetScope().getName());
		}
		if (xmiSource.getOrdering() != null) {
			stf.setOrdering(xmiSource.getOrdering().getName());
		}
		stf.setMultiplicity(createMultiplicity(xmiSource.getMultiplicity()));
		return stf;
	}

	protected <T extends BehavioralFeature> T initBehavioralFeature(T bef,
			org.soulspace.modelling.uml14.elements.BehavioralFeature xmiSource) {
		bef = initFeature(bef, xmiSource);
		bef.setIsQuery(xmiSource.getIsQuery());
		for (org.soulspace.modelling.uml14.elements.Parameter xmiParam : xmiSource
				.getParameterList()) {
			bef.addParameter(createParameter(xmiParam));
		}
		return bef;
	}

	protected <T extends Relationship> T initRelationship(T rel,
			org.soulspace.modelling.uml14.elements.Relationship xmiSource) {
		rel = initModelElement(rel, xmiSource);
		return rel;
	}

	protected <T extends Classifier> T initClassifier(T c,
			org.soulspace.modelling.uml14.elements.Classifier xmiSource) {
		c = initNamespace(c, xmiSource);

		// for(org.soulspace.modelling.uml14.elements.StructuralFeature feature
		// : xmiSource.getTypedFeatureSet()) {
		//
		// }

		return c;
	}

	protected <T extends Action> T initAction(T action,
			org.soulspace.modelling.uml14.elements.Action xmiSource) {
		action = initModelElement(action, xmiSource);
		// TODO implement
		xmiSource.getActualArgumentList();
		return action;
	}

	protected <T extends Event> T initEvent(T event,
			org.soulspace.modelling.uml14.elements.Event xmiSource) {
		event = initModelElement(event, xmiSource);
		for (org.soulspace.modelling.uml14.elements.Parameter xmiParam : xmiSource
				.getParameterList()) {
			event.addParameter(createParameter(xmiParam));
		}
		return event;
	}

	protected <T extends StateVertex> T initStateVertex(T stateVertex,
			org.soulspace.modelling.uml14.elements.StateVertex xmiSource) {
		
		stateVertex = initModelElement(stateVertex, xmiSource);

		for (org.soulspace.modelling.uml14.elements.Transition xmiTrans : xmiSource
				.getIncomingList()) {
			stateVertex.addIncomingTransition(createTransition(xmiTrans));
		}
		for (org.soulspace.modelling.uml14.elements.Transition xmiTrans : xmiSource
				.getOutgoingList()) {
			stateVertex.addOutgoingTransition(createTransition(xmiTrans));
		}

		return stateVertex;
	}
	
	@Override
	protected State initState(State state, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.State xmiSource = (org.soulspace.modelling.uml14.elements.State) xmiObject;

		state = initStateVertex(state, xmiSource);
		
		if(xmiSource.getDeferrableEvent() != null) {
			state.addDeferrableEvent(createEvent(xmiSource.getDeferrableEvent()));
		}
		if(xmiSource.getDoActivity() != null) {
			state.setDoActivity(createAction(xmiSource.getDoActivity()));
		}
		if(xmiSource.getEntry() != null) {
			state.setEntryAction(createAction(xmiSource.getEntry()));
		}
		if(xmiSource.getExit() != null) {
			state.setExitAction(createAction(xmiSource.getExit()));
		}

		for(org.soulspace.modelling.uml14.elements.Transition xmiTrans : xmiSource.getInternalTransitionList()) {
			state.addInternalTransition(createTransition(xmiTrans));
		}

		return state;
	}
	
	@Override
	protected Actor initActor(Actor actor, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Actor xmiSource = (org.soulspace.modelling.uml14.elements.Actor) xmiObject;

		actor = initClassifier(actor, xmiSource);

		return actor;
	}

	@Override
	protected Association initAssociation(Association association,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Association xmiSource = (org.soulspace.modelling.uml14.elements.Association) xmiObject;
		association = initModelElement(association, xmiSource);
		for (org.soulspace.modelling.uml14.elements.AssociationEnd xmiAEnd : xmiSource
				.getConnectionList()) {
			association.addConnection(createAssociationEnd(xmiAEnd));
		}

		// initialize source ends of the association ends
		if (association.getConnectionList().size() == 1) {
			System.err.println("WARN: only one association end on association "
					+ association.getId());
			AssociationEnd ae = association.getConnectionList().get(0);
			ae.setSourceEnd(ae);
		} else if (association.getConnectionList().size() > 2) {
			System.err.println("WARN: more than  association end on association "
					+ association.getId());
		} else if (association.getConnectionList().size() == 2) {
			AssociationEnd ae1 = association.getConnectionList().get(0);
			AssociationEnd ae2 = association.getConnectionList().get(1);
			ae1.setSourceEnd(ae2);
			ae2.setSourceEnd(ae1);

			ae1.setAssociation(association);
			ae2.setAssociation(association);
			
			// FIXME generalize
			if (ae1.getType() instanceof Classifier) {
//				Classifier cI = createClassifier(xmiObject)
				Classifier cI = (Classifier) ae1.getType();
				cI.addAssociation(ae2);
			} else {
				System.err.println("WARN: unhandled association end type " + ae1.getType().getClass().getSimpleName());
			}
			if (ae2.getType() instanceof Classifier) {
				Classifier cI = (Classifier) ae2.getType();
				cI.addAssociation(ae1);
			} else {
				System.err.println("WARN: unhandled association end type " + ae2.getType().getClass().getSimpleName());
			}
		}
		return association;
	}

	@Override
	protected AssociationClass initAssociationClass(
			AssociationClass associationClass, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.AssociationClass xmiSource = (org.soulspace.modelling.uml14.elements.AssociationClass) xmiObject;

		associationClass = (AssociationClass) initAssociation(associationClass, xmiSource);
		associationClass = (AssociationClass) initClass(associationClass, xmiSource);
		
		return associationClass;
	}

	@Override
	protected AssociationEnd initAssociationEnd(
			AssociationEnd associationEnd, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.AssociationEnd xmiSource = (org.soulspace.modelling.uml14.elements.AssociationEnd) xmiObject;

		associationEnd = initModelElement(associationEnd, xmiSource);
		if (xmiSource.getAggregation() != null) {
			associationEnd.setAggregation(xmiSource.getAggregation().getName());
		}
		if (xmiSource.getChangeability() != null) {
			associationEnd.setChangeability(xmiSource.getChangeability()
					.getName());
		}
		if ((xmiSource.getName() != null && xmiSource.getName().startsWith("/"))) {
			associationEnd.setDerived(true);
			associationEnd.setName(associationEnd.getName().substring(1));
		}
		if(associationEnd.getTaggedValueMap().get("derived") != null) {
			if(associationEnd.getTaggedValueMap().get("derived") != null) {
				TaggedValue taggedValue = associationEnd.getTaggedValueMap().get("derived");
				if(taggedValue.getValue().equals("true")) {
					associationEnd.setDerived(true);	
				}
			}
		}
		if (xmiSource.getMultiplicity() != null) {
			associationEnd.setMultiplicity(createMultiplicity(xmiSource
					.getMultiplicity()));
		}
		associationEnd.setNavigable(xmiSource.getIsNavigable());
		if (xmiSource.getOrdering() != null) {
			associationEnd.setOrdering(xmiSource.getOrdering().getName());
		}
		for (org.soulspace.modelling.uml14.elements.Attribute xmiQualifier : xmiSource
				.getQualifierList()) {
			associationEnd.addQualifier(createAttribute(xmiQualifier));
		}
		if (xmiSource.getTargetScope() != null) {
			associationEnd.setTargetScope(xmiSource.getTargetScope().getName());
		}
		associationEnd.setType(createClassifier(xmiSource.getParticipant()));
		if (xmiSource.getVisibility() != null) {
			associationEnd.setVisibility(xmiSource.getVisibility().getName());
		}
		return associationEnd;
	}

	@Override
	protected Attribute initAttribute(Attribute attribute, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Attribute xmiSource = (org.soulspace.modelling.uml14.elements.Attribute) xmiObject;

		attribute = initStructuralFeature(attribute, xmiSource);

		// FIXME handle expressions correctly in xmi and generator model
		// FIXME expressions are no model elements
		// attribute.setInitialValue(createExpression(xmiSource.getInitialValue()));

		if ((xmiSource.getName() != null && xmiSource.getName().startsWith("/"))) {
			attribute.setDerived(true);
			attribute.setName(attribute.getName().substring(1));
		}
		if(attribute.getTaggedValueMap().get("derived") != null) {
			TaggedValue taggedValue = attribute.getTaggedValueMap().get("derived");
			if(taggedValue.getValue().equals("true")) {
				attribute.setDerived(true);				
			}
		}
		return attribute;
	}

	@Override
	protected CallEvent initCallEvent(CallEvent callEvent, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.CallEvent xmiSource = (org.soulspace.modelling.uml14.elements.CallEvent) xmiObject;

		callEvent = initEvent(callEvent, xmiSource);
		if(xmiSource.getOperation() != null) {
			callEvent.setOperation(createOperation(xmiSource.getOperation()));
		}
		
		return callEvent;
	}

	@Override
	protected Class initClass(Class aClass, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Class xmiSource = (org.soulspace.modelling.uml14.elements.Class) xmiObject;

		aClass = initClassifier(aClass, xmiSource);
		
		for (org.soulspace.modelling.uml14.elements.Feature xmiFeature : xmiSource
				.getFeatureList()) {
			if (xmiFeature instanceof org.soulspace.modelling.uml14.elements.Attribute) {
				Attribute attr = createAttribute(xmiFeature);
				aClass.addAttribute(attr);
			}
			if (xmiFeature instanceof org.soulspace.modelling.uml14.elements.Operation) {
				Operation op = createOperation(xmiFeature);
				aClass.addOperation(op);
			}
		}
		aClass.setIsAbstract(xmiSource.getIsAbstract());
		if(xmiSource.getVisibility() != null) {
			aClass.setVisibility(xmiSource.getVisibility().getName());
		} else {
			System.err.println("WARN: visibility is null for class " +  xmiSource.getName() + " - " +  xmiSource.getXmiId());
			System.err.println("WARN:   maybe a required profile is missing.");
			aClass.setVisibility("public"); // default
		}
		return aClass;
	}

	@Override
	protected Component initComponent(Component component, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Component xmiSource = (org.soulspace.modelling.uml14.elements.Component) xmiObject;

		component = initClassifier(component, xmiSource);

		return component;
	}

	@Override
	protected DataType initDataType(DataType dataType,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.DataType xmiSource = (org.soulspace.modelling.uml14.elements.DataType) xmiObject;

		dataType = initClassifier(dataType, xmiSource);

		return dataType;
	}


	@Override
	protected Dependency initDependency(Dependency dependency, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Dependency xmiSource = (org.soulspace.modelling.uml14.elements.Dependency) xmiObject;

		dependency = initModelElement(dependency, xmiSource);

		for (org.soulspace.modelling.uml14.elements.ModelElement xmiClient : xmiSource
				.getClientList()) {
			dependency.setClient(createModelElement(xmiClient));
		}

		for (org.soulspace.modelling.uml14.elements.ModelElement xmiSupplier : xmiSource
				.getSupplierList()) {
			dependency
					.setSupplier(createModelElement(xmiSupplier));
		}

		return dependency;
	}

	@Override
	protected Extend initExtend(Extend extend, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Extend xmiSource = (org.soulspace.modelling.uml14.elements.Extend) xmiObject;

		extend = initElement(extend, xmiSource);
		// TODO implement for real
		xmiSource.getExtension();
		xmiSource.getBase();
		xmiSource.getExtensionPointList();

		return extend;
	}

	@Override
	protected ExtensionPoint initExtensionPoint(
			ExtensionPoint extensionPoint, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.ExtensionPoint xmiSource = (org.soulspace.modelling.uml14.elements.ExtensionPoint) xmiObject;

		extensionPoint = initModelElement(extensionPoint, xmiSource);

		return extensionPoint;
	}

	@Override
	protected FinalState initFinalState(FinalState finalState,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.FinalState xmiSource = (org.soulspace.modelling.uml14.elements.FinalState) xmiObject;

		finalState = initModelElement(finalState, xmiSource);

		return finalState;
	}

	@Override
	protected Generalization initGeneralization(
			Generalization generalization, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Generalization xmiSource = (org.soulspace.modelling.uml14.elements.Generalization) xmiObject;

		generalization = initElement(generalization, xmiSource);

		ModelElement parent = createModelElement(xmiSource.getParent());
		ModelElement child = createModelElement(xmiSource.getChild());

		if(parent instanceof Class && child instanceof Class) {
			((Class) parent).addSubClass((Class) child);
			((Class) child).addSuperClass((Class) parent);
		} else if(parent instanceof Actor && child instanceof Actor) {
			((Actor) parent).setSubActor((Actor) child);
			((Actor) child).setSuperActor((Actor) parent);
		} else if(parent instanceof Interface && child instanceof Interface) {
			((Interface) parent).addSubInterface((Interface) child);
			((Interface) child).addSuperInterface((Interface) parent);
		}
		// TODO check if other relevant generalizations have to be added
		
		return generalization;
	}

	@Override
	protected Include initInclude(Include include, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Include xmiSource = (org.soulspace.modelling.uml14.elements.Include) xmiObject;

		include = initElement(include, xmiSource);
		UseCase base = (UseCase) createModelElement(xmiSource.getBase());
		UseCase addition = (UseCase) createModelElement(xmiSource.getAddition());
		base.addInclude(addition);
		
		return include;
	}

	@Override
	protected Interface initInterface(Interface aInterface,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Interface xmiSource = (org.soulspace.modelling.uml14.elements.Interface) xmiObject;

		aInterface = initClassifier(aInterface, xmiSource);

		return aInterface;
	}

	@Override
	protected Model initModel(Model model, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Model xmiSource = (org.soulspace.modelling.uml14.elements.Model) xmiObject;

		model = initNamespace(model, xmiSource);
		return model;
	}

	@Override
	protected Multiplicity initMultiplicity(Multiplicity multiplicity,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Multiplicity xmiSource = (org.soulspace.modelling.uml14.elements.Multiplicity) xmiObject;

		multiplicity = initElement(multiplicity, xmiSource);

		for (MultiplicityRange xmiRange : xmiSource.getRangeList()) {
			multiplicity.setLow(Integer.toString(xmiRange.getLower()));
			multiplicity.setHigh(Integer.toString(xmiRange.getUpper()));
		}
		return multiplicity;
	}

	@Override
	protected Node initNode(Node node, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Node xmiSource = (org.soulspace.modelling.uml14.elements.Node) xmiObject;

		node = initClassifier(node, xmiSource);

		return node;
	}

	@Override
	protected Operation initOperation(Operation operation,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Operation xmiSource = (org.soulspace.modelling.uml14.elements.Operation) xmiObject;

		operation = initBehavioralFeature(operation, xmiSource);
		operation.setConcurrency(xmiSource.getConcurrency().getName());
		operation.setIsAbstract(xmiSource.getIsAbstract());

		return operation;
	}

	@Override
	protected Package initPackage(Package aPackage, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Package xmiSource = (org.soulspace.modelling.uml14.elements.Package) xmiObject;

		aPackage = initNamespace(aPackage, xmiSource);
		
		return aPackage;
	}

	@Override
	protected Parameter initParameter(Parameter parameter,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Parameter xmiSource = (org.soulspace.modelling.uml14.elements.Parameter) xmiObject;

		parameter = initModelElement(parameter, xmiSource);
		if(xmiSource.getKind() != null) {
			parameter.setKind(xmiSource.getKind().getName());
		}
		if(xmiSource.getType() != null) {
			parameter.setType(createClassifier(xmiSource.getType()));
		}
		return parameter;
	}

	@Override
	protected Pseudostate initPseudostate(Pseudostate pseudostate,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Pseudostate xmiSource = (org.soulspace.modelling.uml14.elements.Pseudostate) xmiObject;

		pseudostate = initStateVertex(pseudostate, xmiSource);

		if(xmiSource.getKind() != null) {
			pseudostate.setKind(xmiSource.getKind().getName());
		}
			
		return pseudostate;
	}

	@Override
	protected StateMachine initStateMachine(StateMachine stateMachine,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.StateMachine xmiSource = (org.soulspace.modelling.uml14.elements.StateMachine) xmiObject;

		stateMachine = initModelElement(stateMachine, xmiSource);
		for (org.soulspace.modelling.uml14.elements.Transition xmiTrans : xmiSource
				.getTransitionsList()) {
			stateMachine.addTransition(createTransition(xmiTrans));
		}
		if(xmiSource.getTop() != null) {
			stateMachine.setTop(createState(xmiSource.getTop()));
		}
		if(xmiSource.getContext() != null) {
			stateMachine.setContext(createModelElement(xmiSource.getContext()));
		}
		return stateMachine;
	}

	@Override
	protected Stereotype initStereotype(Stereotype stereotype,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Stereotype xmiSource = (org.soulspace.modelling.uml14.elements.Stereotype) xmiObject;
		stereotype = initModelElement(stereotype, xmiSource);
		for(org.soulspace.modelling.uml14.elements.TagDefinition xmiTagDefinition : xmiSource.getDefinedTagList()) {
			stereotype.addDefinedTag(createTagDefinition(xmiTagDefinition));
		}
		for(String baseClass : xmiSource.getBaseClassList()) {
			stereotype.addBaseClass(baseClass);
		}
		return stereotype;
	}

	@Override
	protected SubmachineState initSubmachineState(SubmachineState submachineState, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.SubmachineState xmiSource = (org.soulspace.modelling.uml14.elements.SubmachineState) xmiObject;

		submachineState = initModelElement(submachineState, xmiSource);
		
		if(xmiSource.getSubmachine() != null) {
			submachineState.setSubmachine(createStateMachine(xmiSource
					.getSubmachine()));
		}

		return submachineState;
	}

	@Override
	protected CompositeState initCompositeState(
			CompositeState compositeState, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.CompositeState xmiSource = (org.soulspace.modelling.uml14.elements.CompositeState) xmiObject;

		compositeState = initModelElement(compositeState, xmiSource);

		for(org.soulspace.modelling.uml14.elements.StateVertex xmiStateVertex : xmiSource.getSubvertexList()) {
			compositeState.addSubvertex(createStateVertex(xmiStateVertex));
		}
		
		return compositeState;
	}

	@Override
	protected SimpleState initSimpleState(SimpleState simpleState, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.SimpleState xmiSource = (org.soulspace.modelling.uml14.elements.SimpleState) xmiObject;

		initState(simpleState, xmiSource);
		
		return simpleState;
	}

	@Override
	protected SynchState initSynchState(SynchState synchState, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.SynchState xmiSource = (org.soulspace.modelling.uml14.elements.SynchState) xmiObject;

		synchState = initStateVertex(synchState, xmiSource);
		
		return synchState;
	}

	@Override
	protected SubState initSubState(SubState subState, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.SubState xmiSource = (org.soulspace.modelling.uml14.elements.SubState) xmiObject;

		subState = initStateVertex(subState, xmiSource);

		return subState;
	}

	@Override
	protected TagDefinition initTagDefinition(TagDefinition tagDefinition,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.TagDefinition xmiSource = (org.soulspace.modelling.uml14.elements.TagDefinition) xmiObject;

		tagDefinition = initModelElement(tagDefinition, xmiSource);
		if (xmiSource.getMultiplicity() != null) {
			tagDefinition.setMultiplicity(createMultiplicity(xmiSource
					.getMultiplicity()));
		}

		return tagDefinition;
	}

	@Override
	protected TaggedValue initTaggedValue(TaggedValue taggedValue,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.TaggedValue xmiSource = (org.soulspace.modelling.uml14.elements.TaggedValue) xmiObject;

		taggedValue = initElement(taggedValue, xmiSource);
		org.soulspace.modelling.uml14.elements.TagDefinition xmiTagDef = null;
		if(xmiSource.getType() != null) {
			xmiTagDef = 
				(org.soulspace.modelling.uml14.elements.TagDefinition) umlRepository
				.findByXmiId(xmiSource.getType().getRefId());
		}
		taggedValue.setType(createTagDefinition(xmiSource.getType()));
		if (xmiTagDef != null) {
			taggedValue.setName(xmiTagDef.getName());
		} else {
			System.err.println("WARN: unknown TagDefinition on TaggedValue " + xmiSource.getRefId());			
		}
		if (xmiSource.getDataValueList() != null
				&& !xmiSource.getDataValueList().isEmpty()) {
			taggedValue.setValue(xmiSource.getDataValueList().get(0));
		}

		return taggedValue;
	}

	@Override
	protected Transition initTransition(Transition transition,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Transition xmiSource = (org.soulspace.modelling.uml14.elements.Transition) xmiObject;

		transition = initModelElement(transition, xmiSource);

		if(xmiSource.getSource() != null) {
			transition.setSource(createStateVertex(xmiSource.getSource()));
		}
		
		if(xmiSource.getTarget() != null) {
			transition.setTarget(createStateVertex(xmiSource.getTarget()));
		}
		
		if(xmiSource.getTrigger() != null) {
			transition.setTrigger(createEvent(xmiSource.getTrigger()));
		}

		if(xmiSource.getEffect() != null) {
			transition.setEffect(createAction(xmiSource.getEffect()));
		}
		
		if(xmiSource.getGuard() != null) {
			transition.setGuard(createGuard(xmiSource.getGuard()));
		}
		
		return transition;
	}
	
	@Override
	protected Guard initGuard(Guard guard, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Guard xmiSource = (org.soulspace.modelling.uml14.elements.Guard) xmiObject;

		guard = initModelElement(guard, xmiSource);

		if(xmiSource.getExpression() != null) {
			guard.setExpression(createExpression(xmiSource.getExpression()));
		}
		
		return guard;
	}

	@Override
	protected UseCase initUseCase(UseCase useCase, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.UseCase xmiSource = (org.soulspace.modelling.uml14.elements.UseCase) xmiObject;

		useCase = initClassifier(useCase, xmiSource);

		for (org.soulspace.modelling.uml14.elements.ExtensionPoint xmiExtPoint : xmiSource
				.getExtensionPointList()) {
			useCase.addExtensionPoint(createExtensionPoint(xmiExtPoint));
		}
		for (org.soulspace.modelling.uml14.elements.Include xmiInclude : xmiSource
				.getIncludeList()) {
			// TODO handle extends in initExtend()? (see initInclude())
			Include include = createInclude(xmiInclude);
		}
		for(org.soulspace.modelling.uml14.elements.Extend xmiExtend : xmiSource.getExtendList()) {
			// TODO handle extends in initExtend()? (see initInclude())
			createExtend(xmiExtend);
		}
			
		return useCase;
	}

	@Override
	protected CallAction initCallAction(CallAction callAction, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.CallAction xmiSource = (org.soulspace.modelling.uml14.elements.CallAction) xmiObject;

		callAction = initAction(callAction, xmiSource);

		callAction.setOperation(createOperation(xmiSource.getOperation()));

		return callAction;
	}

	@Override
	protected ChangeEvent initChangeEvent(ChangeEvent changeEvent, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.ChangeEvent xmiSource = (org.soulspace.modelling.uml14.elements.ChangeEvent) xmiObject;

		changeEvent = initEvent(changeEvent, xmiSource);

		return changeEvent;
	}

	@Override
	protected CreateAction initCreateAction(CreateAction createAction, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.CreateAction xmiSource = (org.soulspace.modelling.uml14.elements.CreateAction) xmiObject;

		createAction = initAction(createAction, xmiSource);

		return createAction;
	}

	@Override
	protected DestroyAction initDestroyAction(DestroyAction destroyAction, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.DestroyAction xmiSource = (org.soulspace.modelling.uml14.elements.DestroyAction) xmiObject;

		destroyAction = initAction(destroyAction, xmiSource);

		return destroyAction;
	}

	@Override
	protected Signal initSignal(Signal signal, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Signal xmiSource = (org.soulspace.modelling.uml14.elements.Signal) xmiObject;

		signal = initModelElement(signal, xmiSource);

		return signal;
	}

	@Override
	protected SignalEvent initSignalEvent(SignalEvent signalEvent, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.SignalEvent xmiSource = (org.soulspace.modelling.uml14.elements.SignalEvent) xmiObject;

		signalEvent = initEvent(signalEvent, xmiSource);

		return signalEvent;
	}

	@Override
	protected TimeEvent initTimeEvent(TimeEvent timeEvent, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.TimeEvent xmiSource = (org.soulspace.modelling.uml14.elements.TimeEvent) xmiObject;

		timeEvent = initEvent(timeEvent, xmiSource);

		return timeEvent;
	}

	@Override
	protected Flow initFlow(Flow flow, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Flow xmiSource = (org.soulspace.modelling.uml14.elements.Flow) xmiObject;

		flow = initRelationship(flow, xmiSource);

		return flow;
	}

	@Override
	protected ReturnAction initReturnAction(ReturnAction returnAction, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.ReturnAction xmiSource = (org.soulspace.modelling.uml14.elements.ReturnAction) xmiObject;

		returnAction = initAction(returnAction, xmiSource);

		return returnAction;
	}

	@Override
	protected SendAction initSendAction(SendAction sendAction, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.SendAction xmiSource = (org.soulspace.modelling.uml14.elements.SendAction) xmiObject;

		sendAction = initAction(sendAction, xmiSource);
		sendAction.setSignal(createSignal(xmiSource.getSignal()));
		return sendAction;
	}

	@Override
	protected TerminateAction initTerminateAction(TerminateAction terminateAction, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.TerminateAction xmiSource = (org.soulspace.modelling.uml14.elements.TerminateAction) xmiObject;

		terminateAction = initAction(terminateAction, xmiSource);

		return terminateAction;
	}

	@Override
	protected UninterpretedAction initUninterpretedAction(
			UninterpretedAction uninterpretedAction, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.UninterpretedAction xmiSource = (org.soulspace.modelling.uml14.elements.UninterpretedAction) xmiObject;

		uninterpretedAction = initAction(uninterpretedAction, xmiSource);

		return uninterpretedAction;
	}

	@Override
	protected ActionSequence initActionSequence(
			ActionSequence actionSequence, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.ActionSequence xmiSource = (org.soulspace.modelling.uml14.elements.ActionSequence) xmiObject;

		actionSequence = initAction(actionSequence, xmiSource);

		for (org.soulspace.modelling.uml14.elements.Action xmiAction : xmiSource
				.getActionList()) {
			actionSequence.addAction(createAction(xmiAction));
		}

		return actionSequence;
	}

	@Override
	protected Expression initExpression(Expression expression, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Expression xmiSource = (org.soulspace.modelling.uml14.elements.Expression) xmiObject;

		expression = initElement(expression, xmiSource);
		
		expression.setBody(xmiSource.getBody());
		expression.setLanguage(xmiSource.getLanguage());

		return expression;
	}

	@Override
	protected Comment initComment(Comment comment, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Comment xmiSource = (org.soulspace.modelling.uml14.elements.Comment) xmiObject;

		comment = initModelElement(comment, xmiSource);
		
		comment.setBody(xmiSource.getBody());

		return comment;
	}

	@Override
	protected Constraint initConstraint(Constraint constraint, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Constraint xmiSource = (org.soulspace.modelling.uml14.elements.Constraint) xmiObject;

		constraint = initModelElement(constraint, xmiSource);
		
		constraint.setExpression(createExpression(xmiSource.getBody()));

		return constraint;
	}

	@Override
	protected Subsystem initSubsystem(Subsystem subsystem, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Subsystem xmiSource = (org.soulspace.modelling.uml14.elements.Subsystem) xmiObject;

		subsystem = initNamespace(subsystem, xmiSource);
		
		return subsystem;
	}
	
	@Override
	protected ActivityGraph initActivityGraph(ActivityGraph activityGraph, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.ActivityGraph xmiSource = (org.soulspace.modelling.uml14.elements.ActivityGraph) xmiObject;

		initStateMachine(activityGraph, xmiSource);
		for(org.soulspace.modelling.uml14.elements.Partition xmiPartition : xmiSource.getPartitionList()) {
			activityGraph.addPartition(createPartition(xmiPartition));
		}
		return activityGraph;
	}

	@Override
	protected Partition initPartition(Partition partition, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Partition xmiSource = (org.soulspace.modelling.uml14.elements.Partition) xmiObject;

		initModelElement(partition, xmiSource);
		for(org.soulspace.modelling.uml14.elements.ModelElement xmiModelElement : xmiSource.getContentsList()) {
			partition.addContents(createModelElement(xmiModelElement));
		}
		return partition;
	}

	@Override
	protected SubactivityState initSubactivityState(SubactivityState subactivityState, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.SubactivityState xmiSource = (org.soulspace.modelling.uml14.elements.SubactivityState) xmiObject;

		initState(subactivityState, xmiSource);

		subactivityState.setIsDynamic(xmiSource.getIsDynamic());
		if(xmiSource.getDynamicArguments() != null) {
			// TODO implement
		}
		if(xmiSource.getDynamicMultiplicity() != null) {
			subactivityState.setDynamicMultiplicity(createMultiplicity(xmiSource.getDynamicMultiplicity()));
		}
		return subactivityState;
	}

	@Override
	protected ActionState initActionState(ActionState actionState, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.ActionState xmiSource = (org.soulspace.modelling.uml14.elements.ActionState) xmiObject;
		
		initSimpleState(actionState, xmiSource);

		actionState.setIsDynamic(xmiSource.getIsDynamic());
		if(xmiSource.getDynamicArguments() != null) {
			// TODO implement
		}
		if(xmiSource.getDynamicMultiplicity() != null) {
			actionState.setDynamicMultiplicity(createMultiplicity(xmiSource.getDynamicMultiplicity()));
		}
		
		return actionState;
	}

	@Override
	protected ObjectFlowState initObjectFlowState(ObjectFlowState objectFlowState, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.ObjectFlowState xmiSource = (org.soulspace.modelling.uml14.elements.ObjectFlowState) xmiObject;

		initSimpleState(objectFlowState, xmiSource);
		objectFlowState.setIsSynch(xmiSource.getIsSynch());
		for(org.soulspace.modelling.uml14.elements.Parameter xmiParameter : xmiSource.getParameterList()) {
			Parameter p = createParameter(xmiParameter);
			p.addState(objectFlowState);
			objectFlowState.addParameter(p);
		}
		
		return objectFlowState;
	}

	@Override
	protected CallState initCallState(CallState callState, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.CallState xmiSource = (org.soulspace.modelling.uml14.elements.CallState) xmiObject;

		initActionState(callState, xmiSource);

		return callState;
	}

	@Override
	protected ClassifierInState initClassifierInState(ClassifierInState classifierInState, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.ClassifierInState xmiSource = (org.soulspace.modelling.uml14.elements.ClassifierInState) xmiObject;

		if(xmiSource.getType() != null) {
			classifierInState.setType(createClassifier(xmiSource.getType()));
		}
		for(org.soulspace.modelling.uml14.elements.State xmiState : xmiSource.getInStateList()) {
			classifierInState.addInState(createState(xmiState));
		}

		return classifierInState;
	}

	protected Enumeration initEnumeration(Enumeration enumeration, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Enumeration xmiSource = (org.soulspace.modelling.uml14.elements.Enumeration) xmiObject;
		initDataType(enumeration, xmiSource);

		for(org.soulspace.modelling.uml14.elements.EnumerationLiteral xmiEnumLiteral : xmiSource.getLiteralList()) {
			enumeration.addLiteral(createEnumerationLiteral(xmiEnumLiteral));
		}
		return enumeration;
	}
	
	protected EnumerationLiteral initEnumerationLiteral(EnumerationLiteral enumerationLiteral, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.EnumerationLiteral xmiSource = (org.soulspace.modelling.uml14.elements.EnumerationLiteral) xmiObject;
		initModelElement(enumerationLiteral, xmiSource);
		
		return enumerationLiteral;
	}
	
	String buildNamespace(
			org.soulspace.modelling.uml14.elements.ModelElement xmiSource) {
		StringBuilder sb = new StringBuilder("");
		if (xmiSource != null
				&& !(xmiSource instanceof org.soulspace.modelling.uml14.elements.Model)) {
			buildNamespace(xmiSource, sb);
		}
		return sb.toString();
	}

	void buildNamespace(
			org.soulspace.modelling.uml14.elements.ModelElement xmiSource,
			StringBuilder sb) {
		if (xmiSource.getNamespace() != null
				&& !(xmiSource.getNamespace() instanceof org.soulspace.modelling.uml14.elements.Model)) {
			buildNamespace(xmiSource.getNamespace(), sb);
		}
		if(xmiSource.getName() != null) {
			if (sb.length() > 0) {
				sb.append(".");
			}
			sb.append(xmiSource.getName());
		}
	}

	XmiObject getXmiSource(XmiObject xmiObject) {
		if (xmiObject.isReference()) {
			return umlRepository.findByXmiId(xmiObject.getRefId());
		} else {
			return xmiObject;
		}
	}

}
