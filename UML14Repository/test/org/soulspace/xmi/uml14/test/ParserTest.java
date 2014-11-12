/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.xmi.uml14.test;

import java.io.File;
import java.io.IOException;

import org.soulspace.modelling.uml14.impl.Uml14RepositoryImpl;
import org.soulspace.modelling.uml14.impl.Xmi12ReaderImpl;
import org.soulspace.modelling.uml14.impl.Xmi12WriterImpl;
import org.xml.sax.SAXException;

public class ParserTest {

	static String in = "model/uml1.4.xmi";
	static String out = "build/data/uml1.4.xmi";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length == 1) {
			in = args[0];
		} else if(args.length == 2) {
			in = args[0];
			out = args[1];			
		}
		
		Uml14RepositoryImpl xmiRepository = new Uml14RepositoryImpl();
		Xmi12ReaderImpl parser = new Xmi12ReaderImpl(xmiRepository);
//		Xmi12WriterImpl writer = new Xmi12WriterImpl(out);
		try {
			parser.parseXmi(new File(in), false);
//			writer.write(xmiRepository);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("#Models         : " + xmiRepository.getModelList().size());
		System.out.println("#Packages       : " + xmiRepository.getPackageList().size());
		System.out.println("#Classes        : " + xmiRepository.getClassList().size());
		System.out.println("#Interfaces     : " + xmiRepository.getInterfaceList().size());
		System.out.println("#Datatypes      : " + xmiRepository.getDataTypeList().size());
		System.out.println("#Associations   : " + xmiRepository.getAssociationList().size());
		System.out.println("#Generalizations: " + xmiRepository.getGeneralizationList().size());
		System.out.println("#Dependencies   : " + xmiRepository.getDependencyList().size());

	}

}
