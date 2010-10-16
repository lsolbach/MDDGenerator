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

public class Uml14ModelFactoryImpl extends AbstractModelFactory implements ModelFactory {

	protected Uml14RepositoryImpl umlRepository;
	
	public Uml14ModelFactoryImpl(Uml14RepositoryImpl umlRepository, ModelRepository repository) {
		this.repository = repository;
		this.umlRepository = umlRepository;
	}

	//
	// create abstract elements
	//
	protected Action createAction(org.soulspace.modelling.uml14.elements.Action xmiSource) {
		if(xmiSource instanceof org.soulspace.modelling.uml14.elements.ActionSequence) {
			return createActionSequence(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.CallAction) {
			return createCallAction(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.CreateAction) {
			return createCreateAction(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.DestroyAction) {
			return createDestroyAction(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.ReturnAction) {
			return createReturnAction(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.SendAction) {
			return createSendAction(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.TerminateAction) {
			return createTerminateAction(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.UninterpretedAction) {
			return createUninterpretedAction(xmiSource);
		} else {
			return null;
		}
	}
	
	protected Event createEvent(org.soulspace.modelling.uml14.elements.Event xmiSource) {
		if(xmiSource instanceof org.soulspace.modelling.uml14.elements.CallEvent) {
			return createCallEvent(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.ChangeEvent) {
			return createChangeEvent(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.SignalEvent) {
			return createSignalEvent(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.TimeEvent) {
			return createTimeEvent(xmiSource);
		} else {
			return null;
		}
	}
	
	protected Classifier createClassifier(org.soulspace.modelling.uml14.elements.Classifier xmiSource) {

		if(xmiSource instanceof org.soulspace.modelling.uml14.elements.AssociationClass) {
			return createAssociationClass(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.Class) {
			return createClass(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.Interface) {
			return createInterface(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.DataType) {
			return createDataType(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.Node) {
			return createNode(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.Component) {
			return createComponent(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.Artifact) {
			// FIXME
			return null;
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.Signal) {
			return createSignal(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.Actor) {
			return createActor(xmiSource);
		} else if(xmiSource instanceof org.soulspace.modelling.uml14.elements.UseCase) {
			return createUseCase(xmiSource);
		} else {
			return null;
		}
		
	}
	
	//
	// initialize abstract elements
	//
	protected <T extends Element> T initElement(T element, org.soulspace.modelling.base.XmiObject xmiSource) {
		element.setId(xmiSource.getXmiId());
		element.setIsProfileElement(xmiSource.getProfileElement());
		return element;
	}	
	
	protected <T extends ModelElement> T initModelElement(T element, org.soulspace.modelling.uml14.elements.ModelElement xmiSource) {

		element = initElement(element, xmiSource);

		element.setName(xmiSource.getName());
		if(xmiSource.getNamespace() != null) {
			element.setNamespace(buildNamespace(xmiSource.getNamespace()));			
		} else {
			element.setNamespace("");
		}
		
		for(org.soulspace.modelling.uml14.elements.Stereotype xmiStereotype : xmiSource.getStereotypeSet()) {
			Stereotype st = createStereotype(xmiStereotype);
			element.addStereotype(st.getName(), st);
		}

		for(org.soulspace.modelling.uml14.elements.TaggedValue xmiTaggedValue : xmiSource.getTaggedValueSet()) {
			TaggedValue tv = createTaggedValue(xmiTaggedValue);
			element.addTaggedValue(tv.getName(), tv);
		}
		
		for(org.soulspace.modelling.uml14.elements.Comment xmiComment : xmiSource.getCommentSet()) {
			element.addComment(createComment(xmiComment));
		}

		for(org.soulspace.modelling.uml14.elements.Constraint xmiConstraint : xmiSource.getConstraintSet()) {
			element.addConstraint(createConstraint(xmiConstraint));
		}
		
		for(org.soulspace.modelling.uml14.elements.Component xmiComponent : xmiSource.getContainerSet()) {
			
		}

		for(org.soulspace.modelling.uml14.elements.StateMachine xmiStateMachine : xmiSource.getBehaviorSet()) {
			
		}		
		
		return element;
	}
	
	protected <T extends Package> T initNamespace(T namespace, org.soulspace.modelling.uml14.elements.Namespace xmiSource) {
		namespace = initModelElement(namespace, xmiSource);
		for(org.soulspace.modelling.uml14.elements.ModelElement xmiElement :  xmiSource.getOwnedElementSet()) {
			// FIXME Namespace instead of package, add elements
			if(xmiElement instanceof org.soulspace.modelling.uml14.elements.Package) {
				namespace.addPackage(createPackage(xmiElement));
			} else if(xmiElement instanceof org.soulspace.modelling.uml14.elements.AssociationClass) {
				namespace.addClass(createAssociationClass(xmiElement));
			} else if(xmiElement instanceof org.soulspace.modelling.uml14.elements.Class) {
				namespace.addClass(createClass(xmiElement));
			} else if(xmiElement instanceof org.soulspace.modelling.uml14.elements.Interface) {
				namespace.addInterface(createInterface(xmiElement));
			} else if(xmiElement instanceof org.soulspace.modelling.uml14.elements.DataType) {
				namespace.addDataType(createDataType(xmiElement));
			} else if(xmiElement instanceof org.soulspace.modelling.uml14.elements.Generalization) {
				namespace.addGeneralization(createGeneralization(xmiElement));
			} else if(xmiElement instanceof org.soulspace.modelling.uml14.elements.Association) {
				namespace.addAssociation(createAssociation(xmiElement));
			} else if(xmiElement instanceof org.soulspace.modelling.uml14.elements.Dependency) {
				namespace.addDependency(createDependency(xmiElement));
			} else {
				// System.out.println("Unhandled ownedElement " + xmiElement.getClass().getSimpleName());
			}
		}
		return namespace;
	}
	
	protected <T extends Feature> T initFeature(T feature, org.soulspace.modelling.uml14.elements.Feature xmiSource) {
		feature = initModelElement(feature, xmiSource);
		feature.setVisibility(xmiSource.getVisibility().name());
		feature.setOwnerScope(xmiSource.getOwnerScope().name());
		// TODO feature.setIsDerived();
		// feature.setIsDerived(arg0);
		return feature;
	}

	protected <T extends StructuralFeature> T initStructuralFeature(T stf, org.soulspace.modelling.uml14.elements.StructuralFeature xmiSource) {
		stf = initFeature(stf, xmiSource);
		if(xmiSource.getChangeability() != null) {
			stf.setChangeability(xmiSource.getChangeability().name());
		}
		if(xmiSource.getTargetScope() != null) {
			stf.setTargetScope(xmiSource.getTargetScope().name());
		}
		if(xmiSource.getOrdering() != null) {
			stf.setOrdering(xmiSource.getOrdering().name());			
		}
		stf.setMultiplicity(createMultiplicity(xmiSource.getMultiplicity()));
		return stf;
	}

	protected <T extends BehaviouralFeature> T initBehaviouralFeature(T bef, org.soulspace.modelling.uml14.elements.BehaviouralFeature xmiSource) {
		bef = initFeature(bef, xmiSource);
		bef.setIsQuery(xmiSource.getIsQuery());
		return bef;
	}

	protected <T extends Relationship> T initRelationship(T rel, org.soulspace.modelling.uml14.elements.Relationship xmiSource) {
		rel = initModelElement(rel, xmiSource);
		return rel;
	}

	protected <T extends Classifier> T initClassifier(T c, org.soulspace.modelling.uml14.elements.Classifier xmiSource) {
		c = initModelElement(c, xmiSource);
//		for(org.soulspace.modelling.uml14.elements.StructuralFeature feature : xmiSource.getTypedFeatureSet()) {
//			
//		}
		
		return c;
	}

	protected <T extends Action> T initAction(T action, org.soulspace.modelling.uml14.elements.Action xmiSource) {
		action = initModelElement(action, xmiSource);
		// TODO
		xmiSource.getActualArgumentList();
		return action;
	}

	protected <T extends Event> T initEvent(T event, org.soulspace.modelling.uml14.elements.Event xmiSource) {
		event = initModelElement(event, xmiSource);
		for(org.soulspace.modelling.uml14.elements.Parameter xmiParam : xmiSource.getParameterList()) {
			event.addParameter(createParameter(xmiParam));
		}
		return event;
	}

	protected <T extends State> T initState(T state, org.soulspace.modelling.uml14.elements.State xmiSource) {
		state = initModelElement(state, xmiSource);
		for(org.soulspace.modelling.uml14.elements.Transition xmiTrans : xmiSource.getIncomingSet()) {
			state.addIncomingTransition(createTransition(xmiTrans));
		}
		for(org.soulspace.modelling.uml14.elements.Transition xmiTrans : xmiSource.getOutgoingSet()) {
			state.addOutgoingTransition(createTransition(xmiTrans));
		}
		
		state.addDeferredEvent(createEvent(xmiSource.getDeferrableEvent()));
		state.setDoActivity(createAction(xmiSource.getDoActivity()));
		state.setEntryAction(createAction(xmiSource.getEntry()));
		state.setExitAction(createAction(xmiSource.getExit()));
		return state;
	}
	
	
	@Override
	protected ActorImpl initActor(ActorImpl actor, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Actor xmiSource = (org.soulspace.modelling.uml14.elements.Actor) xmiObject;

		actor = initClassifier(actor, xmiSource);
		
		return actor;
	}

	@Override
	protected AssociationImpl initAssociation(AssociationImpl association,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Association xmiSource = (org.soulspace.modelling.uml14.elements.Association) xmiObject;

		association = initModelElement(association, xmiSource);
		for(org.soulspace.modelling.uml14.elements.AssociationEnd xmiAEnd : xmiSource.getConnectionList()) {
			association.addConnection(createAssociationEnd(xmiAEnd));
		}
		return association;
	}

	@Override
	protected AssociationClassImpl initAssociationClass(
			AssociationClassImpl associationClass, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.AssociationClass xmiSource = (org.soulspace.modelling.uml14.elements.AssociationClass) xmiObject;

		associationClass = initClassifier(associationClass, xmiSource);

		return associationClass;
	}

	@Override
	protected AssociationEndImpl initAssociationEnd(AssociationEndImpl associationEnd,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.AssociationEnd xmiSource = (org.soulspace.modelling.uml14.elements.AssociationEnd) xmiObject;

		associationEnd = initModelElement(associationEnd, xmiSource);
		if(xmiSource.getAggregation() != null) {
			associationEnd.setAggregation(xmiSource.getAggregation().name());
		}
		if(xmiSource.getChangeability() != null) {
			associationEnd.setChangeability(xmiSource.getChangeability().name());
		}
		if(/* associationEnd.getTaggedValueMap().get("derived") != null ||*/
				(xmiSource.getName() != null && xmiSource.getName().startsWith("/"))) {
			associationEnd.setDerived(true);
		}
		associationEnd.setMultiplicity(createMultiplicity(xmiSource.getMultiplicity()));
		associationEnd.setNavigable(xmiSource.getIsNavigable());
		if(xmiSource.getOrdering() != null) {
			associationEnd.setOrdering(xmiSource.getOrdering().name());
		}
		for(org.soulspace.modelling.uml14.elements.Attribute xmiQualifier : xmiSource.getQualifierList()) {
			associationEnd.addQualifier(createAttribute(xmiQualifier));
		}
		// FIXME
		// associationEnd.setSourceEnd(xmiSource.get)
		if(xmiSource.getTargetScope() != null) {
			associationEnd.setTargetScope(xmiSource.getTargetScope().name());
		}
		associationEnd.setType(createClassifier(xmiSource.getParticipant()));
		if(xmiSource.getVisibility() != null) {
			associationEnd.setVisibility(xmiSource.getVisibility().name());
		}
		return associationEnd;
	}

	@Override
	protected AttributeImpl initAttribute(AttributeImpl attribute, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Attribute xmiSource = (org.soulspace.modelling.uml14.elements.Attribute) xmiObject;

		attribute = initStructuralFeature(attribute, xmiSource);
		// TODO more?
		return attribute;
	}

	@Override
	protected CallEventImpl initCallEvent(CallEventImpl callEvent, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.CallEvent xmiSource = (org.soulspace.modelling.uml14.elements.CallEvent) xmiObject;

		callEvent = initEvent(callEvent, xmiSource);

		callEvent.setOperation(createOperation(xmiSource.getOperation()));
		
		return callEvent;
	}

	@Override
	protected ClassImpl initClass(ClassImpl aClass, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Class xmiSource = (org.soulspace.modelling.uml14.elements.Class) xmiObject;

		aClass = initClassifier(aClass, xmiSource);

		for(org.soulspace.modelling.uml14.elements.Feature xmiFeature : xmiSource.getFeatureList()) {
			if(xmiFeature instanceof org.soulspace.modelling.uml14.elements.Attribute) {
				Attribute attr = createAttribute(xmiFeature);
				aClass.addAttribute(attr);
			}
			if(xmiFeature instanceof org.soulspace.modelling.uml14.elements.Operation) {
				Operation op = createOperation(xmiFeature);
				aClass.addOperation(op);
			}
		}
		aClass.setVisibility(xmiSource.getVisibility().name());
		return aClass;
	}

	@Override
	protected ComponentImpl initComponent(ComponentImpl component, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Component xmiSource = (org.soulspace.modelling.uml14.elements.Component) xmiObject;

		component = initClassifier(component, xmiSource);
		
		return component;
	}

	@Override
	protected DataTypeImpl initDataType(DataTypeImpl dataType, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.DataType xmiSource = (org.soulspace.modelling.uml14.elements.DataType) xmiObject;

		dataType = initClassifier(dataType, xmiSource);
		
		return dataType;
	}

	@Override
	protected DependencyImpl initDependency(DependencyImpl dependency,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Dependency xmiSource = (org.soulspace.modelling.uml14.elements.Dependency) xmiObject;

		dependency = initModelElement(dependency, xmiSource);
		
		return dependency;
	}

	@Override
	protected ExtendImpl initExtend(ExtendImpl extend, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Extend xmiSource = (org.soulspace.modelling.uml14.elements.Extend) xmiObject;

		extend = initElement(extend, xmiSource);
		
		return extend;
	}

	@Override
	protected ExtensionPointImpl initExtensionPoint(ExtensionPointImpl extensionPoint,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.ExtensionPoint xmiSource = (org.soulspace.modelling.uml14.elements.ExtensionPoint) xmiObject;

		extensionPoint = initModelElement(extensionPoint, xmiSource);
		
		return extensionPoint;
	}

	@Override
	protected FinalStateImpl initFinalState(FinalStateImpl finalState,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.FinalState xmiSource = (org.soulspace.modelling.uml14.elements.FinalState) xmiObject;

		finalState = initModelElement(finalState, xmiSource);
		
		return finalState;
	}

	@Override
	protected GeneralizationImpl initGeneralization(GeneralizationImpl generalization,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Generalization xmiSource = (org.soulspace.modelling.uml14.elements.Generalization) xmiObject;

		generalization = initElement(generalization, xmiSource);
		
		return generalization;
	}

	@Override
	protected IncludeImpl initInclude(IncludeImpl include, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Include xmiSource = (org.soulspace.modelling.uml14.elements.Include) xmiObject;

		include = initElement(include, xmiSource);
		
		return include;
	}

	@Override
	protected InterfaceImpl initInterface(InterfaceImpl aInterface, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Interface xmiSource = (org.soulspace.modelling.uml14.elements.Interface) xmiObject;

		aInterface = initClassifier(aInterface, xmiSource);
		
		return aInterface;
	}

	@Override
	protected ModelImpl initModel(ModelImpl model, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Model xmiSource = (org.soulspace.modelling.uml14.elements.Model) xmiObject;

		model = initNamespace(model, xmiSource);
		return model;
	}

	@Override
	protected MultiplicityImpl initMultiplicity(MultiplicityImpl multiplicity,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Multiplicity xmiSource = (org.soulspace.modelling.uml14.elements.Multiplicity) xmiObject;

		multiplicity = initElement(multiplicity, xmiSource);

		for(MultiplicityRange xmiRange : xmiSource.getRangeSet()) {
			multiplicity.setLow(Integer.toString(xmiRange.getLower()));
			multiplicity.setHigh(Integer.toString(xmiRange.getUpper()));
		}
		return multiplicity;
	}

	@Override
	protected NodeImpl initNode(NodeImpl node, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Node xmiSource = (org.soulspace.modelling.uml14.elements.Node) xmiObject;

		node = initClassifier(node, xmiSource);
		
		return node;
	}

	@Override
	protected OperationImpl initOperation(OperationImpl operation, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Operation xmiSource = (org.soulspace.modelling.uml14.elements.Operation) xmiObject;

		operation = initBehaviouralFeature(operation, xmiSource);
		operation.setConcurrency(xmiSource.getConcurrency().name());
		operation.setIsAbstract(xmiSource.getIsAbstract());
		
		return operation;
	}

	@Override
	protected PackageImpl initPackage(PackageImpl aPackage, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Package xmiSource = (org.soulspace.modelling.uml14.elements.Package) xmiObject;

		aPackage = initNamespace(aPackage, xmiSource);
		
		return aPackage;
	}

	@Override
	protected ParameterImpl initParameter(ParameterImpl parameter, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Parameter xmiSource = (org.soulspace.modelling.uml14.elements.Parameter) xmiObject;

		parameter = initModelElement(parameter, xmiSource);
		
		return parameter;
	}

	@Override
	protected PseudostateImpl initPseudostate(PseudostateImpl pseudostate,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Pseudostate xmiSource = (org.soulspace.modelling.uml14.elements.Pseudostate) xmiObject;

		pseudostate = initModelElement(pseudostate, xmiSource);
		
		return pseudostate;
	}

	@Override
	protected StateImpl initState(StateImpl state, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.State xmiSource = (org.soulspace.modelling.uml14.elements.State) xmiObject;

		state = initModelElement(state, xmiSource);
		
		return state;
	}

	@Override
	protected StateMachineImpl initStateMachine(StateMachineImpl stateMachine,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.StateMachine xmiSource = (org.soulspace.modelling.uml14.elements.StateMachine) xmiObject;

		stateMachine = initModelElement(stateMachine, xmiSource);
		for(org.soulspace.modelling.uml14.elements.Transition xmiTrans : xmiSource.getTransitionsSet()) {
			stateMachine.addTransition(createTransition(xmiTrans));
		}
		
		stateMachine.addState(createCompositeState(xmiSource.getTop()));

		return stateMachine;
	}

	@Override
	protected StereotypeImpl initStereotype(StereotypeImpl stereotype,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Stereotype xmiSource = (org.soulspace.modelling.uml14.elements.Stereotype) xmiObject;

		stereotype = initModelElement(stereotype, xmiSource);

		return stereotype;
	}

	@Override
	protected SubmachineStateImpl initSubmachineState(
			SubmachineStateImpl submachineState, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.SubmachineState xmiSource = (org.soulspace.modelling.uml14.elements.SubmachineState) xmiObject;

		submachineState = initModelElement(submachineState, xmiSource);
		submachineState.setStateMachine(createStateMachine(xmiSource.getSubmachine()));
		
		return submachineState;
	}

	@Override
	protected TaggedValueImpl initTaggedValue(TaggedValueImpl taggedValue,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.TaggedValue xmiSource = (org.soulspace.modelling.uml14.elements.TaggedValue) xmiObject;

		taggedValue = initElement(taggedValue, xmiSource);
		org.soulspace.modelling.uml14.elements.TagDefinition xmiTagDef
			= (org.soulspace.modelling.uml14.elements.TagDefinition) umlRepository.findByXmiId(xmiSource.getType().getRefId());
		if(xmiTagDef != null) {
			taggedValue.setName(xmiTagDef.getName());
		}
			
		return taggedValue;
	}

	@Override
	protected TransitionImpl initTransition(TransitionImpl transition,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Transition xmiSource = (org.soulspace.modelling.uml14.elements.Transition) xmiObject;

		transition = initModelElement(transition, xmiSource);
		transition.setSource(createState(xmiSource.getSource()));
		transition.setTarget(createState(xmiSource.getTarget()));
		transition.setTrigger(createEvent(xmiSource.getTrigger()));			

		return transition;
	}

	@Override
	protected UseCaseImpl initUseCase(UseCaseImpl useCase, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.UseCase xmiSource = (org.soulspace.modelling.uml14.elements.UseCase) xmiObject;

		useCase = initClassifier(useCase, xmiSource);
		
		for(org.soulspace.modelling.uml14.elements.ExtensionPoint xmiExtPoint : xmiSource.getExtensionPointSet()) {
			useCase.addExtensionPoint(createExtensionPoint(xmiExtPoint));
		}
		for(org.soulspace.modelling.uml14.elements.Include xmiInclude : xmiSource.getIncludeSet()) {
			createInclude(xmiInclude);
			// useCase.addInclude();
			// include is a dependency so supplier and client denominate the participating use cases
		}
		
		return useCase;
	}
	
	@Override
	protected CallActionImpl initCallAction(CallActionImpl callAction,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.CallAction xmiSource = (org.soulspace.modelling.uml14.elements.CallAction) xmiObject;

		callAction = initAction(callAction, xmiSource);
		callAction.setOperation(createOperation(xmiSource.getOperation()));
		
		return callAction;
	}

	@Override
	protected ChangeEventImpl initChangeEvent(ChangeEventImpl changeEvent,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.ChangeEvent xmiSource = (org.soulspace.modelling.uml14.elements.ChangeEvent) xmiObject;

		changeEvent = initEvent(changeEvent, xmiSource);

		return changeEvent;
	}

	@Override
	protected CompositeStateImpl initCompositeState(
			CompositeStateImpl compositeState, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.CompositeState xmiSource = (org.soulspace.modelling.uml14.elements.CompositeState) xmiObject;

		compositeState = initModelElement(compositeState, xmiSource);

		return compositeState;
	}

	@Override
	protected CreateActionImpl initCreateAction(CreateActionImpl createAction,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.CreateAction xmiSource = (org.soulspace.modelling.uml14.elements.CreateAction) xmiObject;

		createAction = initAction(createAction, xmiSource);

		return createAction;
	}

	@Override
	protected DestroyActionImpl initDestroyAction(
			DestroyActionImpl destroyAction, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.DestroyAction xmiSource = (org.soulspace.modelling.uml14.elements.DestroyAction) xmiObject;

		destroyAction = initAction(destroyAction, xmiSource);
		
		return destroyAction;
	}

	@Override
	protected SignalImpl initSignal(SignalImpl signal, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Signal xmiSource = (org.soulspace.modelling.uml14.elements.Signal) xmiObject;

		signal = initModelElement(signal, xmiSource);
		
		return signal;
	}

	@Override
	protected SignalEventImpl initSignalEvent(SignalEventImpl signalEvent,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.SignalEvent xmiSource = (org.soulspace.modelling.uml14.elements.SignalEvent) xmiObject;

		signalEvent = initEvent(signalEvent, xmiSource);
		
		return signalEvent;
	}

	@Override
	protected TimeEventImpl initTimeEvent(TimeEventImpl timeEvent,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.TimeEvent xmiSource = (org.soulspace.modelling.uml14.elements.TimeEvent) xmiObject;

		timeEvent = initEvent(timeEvent, xmiSource);
		
		return timeEvent;
	}

	@Override
	protected FlowImpl initFlow(FlowImpl flow, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Flow xmiSource = (org.soulspace.modelling.uml14.elements.Flow) xmiObject;

		flow = initRelationship(flow, xmiSource);
		
		return flow;
	}

	@Override
	protected ReturnActionImpl initReturnAction(ReturnActionImpl returnAction,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.ReturnAction xmiSource = (org.soulspace.modelling.uml14.elements.ReturnAction) xmiObject;

		returnAction = initAction(returnAction, xmiSource);
		
		return returnAction;
	}

	@Override
	protected SendActionImpl initSendAction(SendActionImpl sendAction,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.SendAction xmiSource = (org.soulspace.modelling.uml14.elements.SendAction) xmiObject;

		sendAction = initAction(sendAction, xmiSource);
		sendAction.setSignal(createSignal(xmiSource.getSignal()));
		return sendAction;
	}

	@Override
	protected TerminateActionImpl initTerminateAction(
			TerminateActionImpl terminateAction, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.TerminateAction xmiSource = (org.soulspace.modelling.uml14.elements.TerminateAction) xmiObject;

		terminateAction = initAction(terminateAction, xmiSource);
		
		return terminateAction;
	}

	@Override
	protected UninterpretedActionImpl initUninterpretedAction(
			UninterpretedActionImpl uninterpretedAction, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.UninterpretedAction xmiSource = (org.soulspace.modelling.uml14.elements.UninterpretedAction) xmiObject;

		uninterpretedAction = initAction(uninterpretedAction, xmiSource);
		
		return uninterpretedAction;
	}

	@Override
	protected ActionSequenceImpl initActionSequence(
			ActionSequenceImpl actionSequence, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.ActionSequence xmiSource = (org.soulspace.modelling.uml14.elements.ActionSequence) xmiObject;

		actionSequence = initAction(actionSequence, xmiSource);
		for(org.soulspace.modelling.uml14.elements.Action xmiAction : xmiSource.getActionList()) {
			actionSequence.addAction(createAction(xmiAction));
		}

		return actionSequence;
	}

	@Override
	protected ExpressionImpl initExpression(ExpressionImpl expression,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Expression xmiSource = (org.soulspace.modelling.uml14.elements.Expression) xmiObject;

		expression = initElement(expression, xmiSource);
		expression.setBody(xmiSource.getBody());
		expression.setLanguage(xmiSource.getLanguage());
		
		return expression;
	}

	@Override
	protected CommentImpl initComment(CommentImpl comment, XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Comment xmiSource = (org.soulspace.modelling.uml14.elements.Comment) xmiObject;

		comment = initModelElement(comment, xmiSource);
		comment.setBody(xmiSource.getBody());
		
		return comment;
	}

	@Override
	protected ConstraintImpl initConstraint(ConstraintImpl constraint,
			XmiObject xmiObject) {
		org.soulspace.modelling.uml14.elements.Constraint xmiSource = (org.soulspace.modelling.uml14.elements.Constraint) xmiObject;

		constraint = initModelElement(constraint, xmiSource);
		constraint.setExpression(createExpression(xmiSource.getBody()));
		
		return constraint;
	}

	String buildNamespace(org.soulspace.modelling.uml14.elements.ModelElement xmiSource) {
		StringBuilder sb = new StringBuilder("");
		if(xmiSource != null && !(xmiSource instanceof org.soulspace.modelling.uml14.elements.Model)) {
			buildNamespace(xmiSource, sb);			
		}
		return sb.toString();
	}
	
	void buildNamespace(org.soulspace.modelling.uml14.elements.ModelElement xmiSource, StringBuilder sb) {
		if(xmiSource.getNamespace() != null
				&& !(xmiSource.getNamespace() instanceof org.soulspace.modelling.uml14.elements.Model)) {
			buildNamespace(xmiSource.getNamespace(), sb);
		}
		if(sb.length() > 0) {
			sb.append(".");
		}
		sb.append(xmiSource.getName());
	}

}
