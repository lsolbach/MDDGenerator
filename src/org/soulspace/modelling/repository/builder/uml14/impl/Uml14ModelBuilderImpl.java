package org.soulspace.modelling.repository.builder.uml14.impl;

import org.soulspace.modelling.repository.ModelRepository;
import org.soulspace.modelling.uml14.elements.Model;
import org.soulspace.modelling.uml14.impl.Uml14RepositoryImpl;

public class Uml14ModelBuilderImpl {

	Uml14RepositoryImpl umlRepository;
	ModelRepository modelRepository;
	Uml14ModelFactoryImpl factory;
	
	public Uml14ModelBuilderImpl(Uml14RepositoryImpl umlRepository,
			ModelRepository modelRepository) {
		super();
		this.umlRepository = umlRepository;
		this.modelRepository = modelRepository;
		this.factory = new Uml14ModelFactoryImpl(this.umlRepository, modelRepository);
	}
	
	ModelRepository build() {
		for(Model model : umlRepository.getModelList()) {
			factory.createModel(model);
		}
		
		return modelRepository;
	}
	
}
