package org.soulspace.modelling;

import java.io.File;

import org.soulspace.modelling.generator.ant.ModelGeneratorTask;

public class ModelProfileTest extends AbstractGeneratorTaskTest {

	public static void main(String[] args) {
		ModelProfileTest test = new ModelProfileTest();
		test.testProfiles();
	}
	
	public ModelProfileTest() {
	}
	
	void testProfiles() {
		ModelGeneratorTask task = new ModelGeneratorTask();
		setupTask(task);
		task.setModelFile(new File("../UserModel/model/UserModel.xmi"));
		task.setProfiles("../ModelRepository/data/argouml/profiles/default-uml14.xmi,../ModelRepository/data/argouml/profiles/SoulMDSDProfile.xmi");
		
		task.execute();		

	}

}
