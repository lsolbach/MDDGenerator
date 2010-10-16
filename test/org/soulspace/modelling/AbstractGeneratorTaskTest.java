package org.soulspace.modelling;

import java.io.File;

import org.apache.tools.ant.Project;
import org.soulspace.modelling.generator.ant.ModelGeneratorTask;

public abstract class AbstractGeneratorTaskTest {
	
	public final void setupTask(ModelGeneratorTask task) {
		File testDir = new File("build/test/generated");
		File backupDir = new File("build/test/backup");
		if(!testDir.exists()) {
			testDir.mkdirs();
		}
		if(!backupDir.exists()) {
			backupDir.mkdirs();
		}
		task.setDestDir(new File("build/test/generated"));
		task.setBackupDir(new File("build/test/backup"));
		Project project = new Project();
		project.setName("Test");
		task.setProject(project);
	}
	

	
}
