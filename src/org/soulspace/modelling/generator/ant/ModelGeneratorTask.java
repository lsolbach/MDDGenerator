/*
 * Created on Feb 20, 2005
 */
package org.soulspace.modelling.generator.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.soulspace.modelling.generator.GenerationContext;
import org.soulspace.modelling.generator.GeneratorGroup;
import org.soulspace.util.CollectionUtils;

/**
 * @author soulman
 * 
 */
public class ModelGeneratorTask extends Task {

	private GenerationContext ctx;
	
	public ModelGeneratorTask() {
		super();
		ctx = new GenerationContext();
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

	public void addClassGenerator(ClassGenerator cg) {
		ctx.getMainGroup().addClassGenerator(cg);
	}

	public void addModelGenerator(ModelGenerator mg) {
		ctx.getMainGroup().addModelGenerator(mg);
	}

	public void addPackageGenerator(PackageGenerator pg) {
		ctx.getMainGroup().addPackageGenerator(pg);
	}

	public void addStateMachineGenerator(StateMachineGenerator sg) {
		ctx.getMainGroup().addStateMachineGenerator(sg);
	}

	public void addActorGenerator(ActorGenerator ag) {
		ctx.getMainGroup().addActorGenerator(ag);
	}

	public void addUseCaseGenerator(UseCaseGenerator ug) {
		ctx.getMainGroup().addUseCaseGenerator(ug);
	}

	public void addGeneratorGroup(GeneratorGroup group) {
		ctx.getMainGroup().addGeneratorGroup(group);
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.tools.ant.Task#execute()
	 */
	public void execute() throws BuildException {
		try {
			ctx.callGenerators(ctx, ctx.getMainGroup());
		} catch (Exception e) {
			throw new BuildException("error while generating!", e);
		}
	}
	
}
