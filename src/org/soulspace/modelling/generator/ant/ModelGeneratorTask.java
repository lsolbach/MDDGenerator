/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
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
		generationContext.setDestDir(destDir);
	}

	public void setBackupDir(File backupDir) {
		generationContext.setBackupDir(backupDir);
	}

	public void setTemplateDir(File templateDir) {
		generationContext.setTemplateDir(templateDir);
	}

	public void setTemplateDirs(String templateDirs) {
		String[] dirs = templateDirs.split(",");
		generationContext.setTemplateDirs(CollectionUtils
				.asArrayList(dirs));
	}
	
	public void setModelFile(File modelFile) {
		generationContext.setModelFile(modelFile);
	}

	public void setModelFactory(String modelFactory) {
		generationContext.setModelFactory(modelFactory);
	}

	public void setProfiles(String profiles) {
		generationContext.setProfiles(profiles);
	}

	public void addGeneratorGroup(GeneratorGroup group) {
		generationContext.getMainGroup().addGeneratorGroup(group);
	}	

	public void execute() throws BuildException {
		try {
			generationContext.callGenerators(generationContext, generationContext.getMainGroup(), null);
		} catch (Exception e) {
			throw new BuildException("error while generating!", e);
		}
	}
	
}
