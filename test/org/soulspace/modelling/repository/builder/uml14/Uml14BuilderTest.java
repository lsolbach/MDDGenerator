/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.modelling.repository.builder.uml14;

import java.io.File;
import java.io.IOException;

import org.soulspace.modelling.repository.ModelRepository;
import org.soulspace.modelling.repository.builder.uml14.impl.Uml14ModelBuilderImpl;
import org.soulspace.modelling.repository.impl.ModelRepositoryImpl;
import org.soulspace.modelling.uml14.impl.Uml14RepositoryImpl;
import org.soulspace.modelling.uml14.impl.Xmi12ReaderImpl;
import org.xml.sax.SAXException;

import com.sun.xml.internal.bind.v2.model.impl.ModelBuilder;

public class Uml14BuilderTest {

	Uml14RepositoryImpl umlRepository;
	Xmi12ReaderImpl xmiReader;
	Uml14ModelBuilderImpl modelBuilder;
	ModelRepository modelRepository;
	
	public static void main(String[] args) {
	
		Uml14BuilderTest test = new Uml14BuilderTest();
		try {
			for(int i = 0; i < args.length; i++) {
				if(i < args.length -1) {
					test.xmiReader.loadProfile(new File(args[i]));
				} else {
					test.xmiReader.loadModel(new File(args[i]));
				}
			}
			test.modelRepository = test.modelBuilder.build();
			System.out.println("#Models: " + test.modelRepository.getModelList().size());
			System.out.println("#Model Packages: " + test.modelRepository.getModelList().get(0).getPackageSet().size());
			System.out.println("#Packages: " + test.modelRepository.getPackageList().size());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public Uml14BuilderTest() {
		umlRepository = new Uml14RepositoryImpl();
		xmiReader = new Xmi12ReaderImpl(umlRepository);
		modelRepository = new ModelRepositoryImpl();
		modelBuilder = new Uml14ModelBuilderImpl(umlRepository, modelRepository);
	}
}
