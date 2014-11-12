package org.soulspace.modelling;

import java.io.File;

import org.soulspace.modelling.generator.ClassGenerator;
import org.soulspace.modelling.generator.ModelGenerator;
import org.soulspace.modelling.generator.ant.ModelGeneratorTask;

public class ModelGeneratorTaskTest extends AbstractGeneratorTaskTest {

	public static void main(String[] args) {

		ModelGeneratorTaskTest mgtTest = new ModelGeneratorTaskTest();
		mgtTest.testPersonApp();
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

	void testAddressModel() {
		ModelGeneratorTask mdaGeneratorTask = new ModelGeneratorTask();
		setupTask(mdaGeneratorTask);
		
		mdaGeneratorTask.setModelFile(new File("data/test/model/AddressComponent.xmi"));
		mdaGeneratorTask.setTemplateDirs("../MdaTemplates/std-templates2,../MdaTemplates/templates2");
		mdaGeneratorTask.setProfiles("../MdaTemplates/profiles/argouml/default-uml14.xmi,../MdaTemplates/profiles/MDSDProfile.xmi");
		mdaGeneratorTask.setBackupDir(new File("data/test/backup"));
		
		ClassGenerator cg = new ClassGenerator();
		System.out.println("Template: hibernate-mapping");
		cg.setName("domain/persistence/hibernate-mapping");
		cg.setExtension("hbn.xml");
		cg.setImports("lib,model/lib,common/lib,common/xml/lib,java/lib,persistence/db/lib,domain/model/lib,domain/persistence/lib");
		cg.setStereotype("entity");
		mdaGeneratorTask.addClassGenerator(cg);
		mdaGeneratorTask.execute();		
	}

	void testStateMachine() {
		ModelGeneratorTask mdaGeneratorTask = new ModelGeneratorTask();
		setupTask(mdaGeneratorTask);
		
		mdaGeneratorTask.setModelFile(new File("../../apps/PersonWeb/model/PersonWeb.xmi"));
		mdaGeneratorTask.setTemplateDirs("../MdaTemplates/std-templates2,../MdaTemplates/templates2");
		mdaGeneratorTask.setProfiles("../MdaTemplates/profiles/argouml/default-uml14.xmi,../MdaTemplates/profiles/MDSDProfile.xmi");
		mdaGeneratorTask.setBackupDir(new File("data/test/backup"));
		
		ClassGenerator cg = new ClassGenerator();
		System.out.println("Template: webflow2");
		cg.setName("presentation/spring/webflow2");
		cg.setExtension("xml");
		cg.setImports("lib,model/lib,common/lib,common/xml/lib");
		cg.setStereotype("flow");
		mdaGeneratorTask.addClassGenerator(cg);
		mdaGeneratorTask.execute();		
	}

	void testPersonApp() {
		ModelGeneratorTask mdaGeneratorTask = new ModelGeneratorTask();
		setupTask(mdaGeneratorTask);
		
		mdaGeneratorTask.setModelFile(new File("../../apps/PersonAppComponent/model/PersonAppComponent.xmi"));
		mdaGeneratorTask.setTemplateDirs("../MdaTemplates/std-templates2,../MdaTemplates/templates2");
		mdaGeneratorTask.setProfiles("../MdaTemplates/profiles/argouml/default-uml14.xmi,../MdaTemplates/profiles/MDSDProfile.xmi,../../apps/PersonComponent/model/PersonComponent.xmi");
		mdaGeneratorTask.setBackupDir(new File("data/test/backup"));
		
		ClassGenerator cg = new ClassGenerator();
		System.out.println("Template: webflow2");
		cg.setName("presentation/spring/webflow2");
		cg.setExtension("xml");
		cg.setImports("lib,model/lib,common/lib,common/xml/lib");
		cg.setStereotype("flow");
		mdaGeneratorTask.addClassGenerator(cg);
		mdaGeneratorTask.execute();		
	}
}
