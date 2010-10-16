package org.soulspace.modelling;

import java.io.File;

import org.soulspace.modelling.generator.ant.ClassGenerator;
import org.soulspace.modelling.generator.ant.ModelGeneratorTask;
import org.soulspace.modelling.generator.ant.ModelGenerator;

public class ModelGeneratorTaskTest extends AbstractGeneratorTaskTest {

	public static void main(String[] args) {

		ModelGeneratorTaskTest mgtTest = new ModelGeneratorTaskTest();
		//mgtTest.testRepositoryInterface();
		//mgtTest.testZebraHtmlDoc();
		//mgtTest.testZebraDesignHtmlDoc();
		//mgtTest.testUml14Schema();
		//mgtTest.testMultipleTemplateDirs();		
		//mgtTest.testStdTemplates();		
		mgtTest.testUserSections();		
	}
	
	void testRepositoryInterface() {
		ModelGeneratorTask mdaGeneratorTask = new ModelGeneratorTask();
		setupTask(mdaGeneratorTask);
		mdaGeneratorTask.setModelFile(new File("data/test/model/Content.xmi"));
		mdaGeneratorTask.setTemplateDir(new File("data/test/templates"));
		
		ClassGenerator cg = new ClassGenerator();
		System.out.println("Template: java/domain/repository-interface");
		cg.setName("java/domain/repository-interface");
		cg.setImports("java/base,java/domain/base");
		cg.setStereotype("repository");
		cg.setExtension("java");
		mdaGeneratorTask.addClassGenerator(cg);
		mdaGeneratorTask.execute();
	}
	
	void testZebraHtmlDoc() {
		ModelGeneratorTask mdaGeneratorTask = new ModelGeneratorTask();
		setupTask(mdaGeneratorTask);
		mdaGeneratorTask.setModelFile(new File("data/test/model/ZebraAnalysis.xmi"));
		mdaGeneratorTask.setTemplateDir(new File("data/test/templates"));
		
		ModelGenerator mg = new ModelGenerator();
		System.out.println("Template: doc/htmldoc");
		mg.setName("doc/htmldoc");
		mg.setImports("doc/css");
		mg.setExtension("html");
		mdaGeneratorTask.addModelGenerator(mg);
		mdaGeneratorTask.execute();		
	}

	void testZebraDesignHtmlDoc() {
		ModelGeneratorTask mdaGeneratorTask = new ModelGeneratorTask();
		setupTask(mdaGeneratorTask);
		mdaGeneratorTask.setModelFile(new File("data/test/model/ZebraDesign.xmi"));
		mdaGeneratorTask.setTemplateDir(new File("data/test/templates"));
		mdaGeneratorTask.setModelFactory("org.soulspace.mdlrepo.ddd.metamodel.impl.ModelFactory");
		
		ModelGenerator mg = new ModelGenerator();
		System.out.println("Template: doc/htmldoc");
		mg.setName("doc/htmldoc");
		mg.setImports("doc/css");
		mg.setExtension("html");
		mg.setGenerationFilterPattern("^(?s)(?:\\w)*([A-Za-z]+)(?:.*)$");
		mdaGeneratorTask.addModelGenerator(mg);
		mdaGeneratorTask.execute();		
	}
	
	void testUml14Schema() {
		ModelGeneratorTask mdaGeneratorTask = new ModelGeneratorTask();
		setupTask(mdaGeneratorTask);
		mdaGeneratorTask.setModelFile(new File("data/test/model/uml1.4.xmi"));
		mdaGeneratorTask.setTemplateDir(new File("data/test/templates"));
		
		ModelGenerator mg = new ModelGenerator();
		System.out.println("Template: xmischema");
		mg.setName("xmischema");
		mg.setExtension("xsd");
		mdaGeneratorTask.addModelGenerator(mg);
		mdaGeneratorTask.execute();		
	}

	void testMultipleTemplateDirs() {
		ModelGeneratorTask mdaGeneratorTask = new ModelGeneratorTask();
		setupTask(mdaGeneratorTask);
		mdaGeneratorTask.setModelFile(new File("data/test/model/uml1.4.xmi"));
		mdaGeneratorTask.setTemplateDirs("../MdaTemplates/std-templates,data/test/templates");
		
		ModelGenerator mg = new ModelGenerator();
		System.out.println("Template: xmischema");
		mg.setName("xmischema");
		mg.setExtension("xsd");
		mg.setImports("lib,model/lib");
		mdaGeneratorTask.addModelGenerator(mg);
		mdaGeneratorTask.execute();	
	}

	void testStdTemplates() {
		ModelGeneratorTask mdaGeneratorTask = new ModelGeneratorTask();
		setupTask(mdaGeneratorTask);
		mdaGeneratorTask.setModelFile(new File("data/test/model/Content.xmi"));
		mdaGeneratorTask.setTemplateDirs("../MdaTemplates/std-templates");
		
		ClassGenerator cg = new ClassGenerator();
		System.out.println("Template: bean-interface");
		cg.setName("java/bean-interface");
		cg.setExtension("java");
		cg.setImports("lib,model/lib,java/lib,java/interface");
		mdaGeneratorTask.addClassGenerator(cg);
		mdaGeneratorTask.execute();		
	}
	
	void testUserSections() {
		ModelGeneratorTask mdaGeneratorTask = new ModelGeneratorTask();
		setupTask(mdaGeneratorTask);
		
		mdaGeneratorTask.setModelFile(new File("data/test/model/TestClass.xmi"));
		mdaGeneratorTask.setTemplateDirs("data/test/templates");
		mdaGeneratorTask.setProfiles("../ModelRepository/data/argouml/profiles/default-uml14.xmi");
		mdaGeneratorTask.setBackupDir(new File("data/test/backup"));
		
		ClassGenerator cg = new ClassGenerator();
		System.out.println("Template: bean-interface");
		cg.setName("usersection");
		cg.setExtension("java");
		//cg.setImports("");
		cg.setUserSection("PA");
		mdaGeneratorTask.addClassGenerator(cg);
		mdaGeneratorTask.execute();		
	}
}
