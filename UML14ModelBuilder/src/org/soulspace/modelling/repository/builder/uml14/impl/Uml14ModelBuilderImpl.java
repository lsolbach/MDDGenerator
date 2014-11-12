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
	
	public ModelRepository build() {
		for(Model model : umlRepository.getModelList()) {
			factory.createModel(model);
		}
		
		return modelRepository;
	}
	
}
