package org.soulspace.xmi.uml14.test;

import java.io.File;
import java.io.IOException;

import org.soulspace.xmi.uml14.repository.XmiParserImpl;
import org.soulspace.xmi.uml14.repository.XmiRepositoryImpl;
import org.soulspace.xmi.uml14.repository.XmiWriterImpl;
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
		
		XmiRepositoryImpl xmiRepository = new XmiRepositoryImpl();
		XmiParserImpl parser = new XmiParserImpl(xmiRepository);
		XmiWriterImpl writer = new XmiWriterImpl(out);
		try {
			parser.parseXmi(new File(in), false);
			writer.write(xmiRepository);
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

//		System.out.println("Boolean Value of 'true': " + Boolean.parseBoolean("true"));
//		System.out.println("Boolean Value of 'true': " + Boolean.valueOf("true"));
//		System.out.println("Boolean Value of 'true': " + Boolean.getBoolean("true"));
	}

}
