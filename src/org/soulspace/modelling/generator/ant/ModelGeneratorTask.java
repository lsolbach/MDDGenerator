/*
 * Created on Feb 20, 2005
 */
package org.soulspace.modelling.generator.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.soulspace.modelling.generator.GeneratorGroup;
import org.soulspace.util.CollectionUtils;

/**
 * @author soulman
 * 
 */
public class ModelGeneratorTask extends AbstractGeneratorTask {
	
	public ModelGeneratorTask() {
		super();
	}

	public void setDestDir(File destDir) {
		ctx.setDestDir(destDir);
	}

	public void setBackupDir(File backupDir) {
		ctx.setBackupDir(backupDir);
	}

	public void setTemplateDir(File templateDir) {
		ctx.setTemplateDir(templateDir);
	}

	public void setTemplateDirs(String templateDirs) {
		String[] dirs = templateDirs.split(",");
		ctx.setTemplateDirs(CollectionUtils
				.asArrayList(dirs));
	}
	
	public void setModelFile(File modelFile) {
		ctx.setModelFile(modelFile);
	}

	public void setModelFactory(String modelFactory) {
		ctx.setModelFactory(modelFactory);
	}

	public void setProfiles(String profiles) {
		ctx.setProfiles(profiles);
	}

	public void addGeneratorGroup(GeneratorGroup group) {
		ctx.getMainGroup().addGeneratorGroup(group);
	}	

	public void execute() throws BuildException {
		try {
			ctx.callGenerators(ctx, ctx.getMainGroup(), null);
		} catch (Exception e) {
			throw new BuildException("error while generating!", e);
		}
	}
	
}
