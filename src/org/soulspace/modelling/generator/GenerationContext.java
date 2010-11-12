package org.soulspace.modelling.generator;

import java.io.File;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.ClasspathUtils;
import org.soulspace.modelling.generator.ant.ActorGenerator;
import org.soulspace.modelling.generator.ant.ClassGenerator;
import org.soulspace.modelling.generator.ant.ModelGenerator;
import org.soulspace.modelling.generator.ant.PackageGenerator;
import org.soulspace.modelling.generator.ant.StateMachineGenerator;
import org.soulspace.modelling.generator.ant.UseCaseGenerator;
import org.soulspace.modelling.repository.ModelRepository;
import org.soulspace.modelling.repository.builder.uml14.impl.Uml14ModelBuilderImpl;
import org.soulspace.modelling.repository.builder.uml14.impl.Uml14ModelFactoryImpl;
import org.soulspace.modelling.repository.elements.Actor;
import org.soulspace.modelling.repository.elements.Class;
import org.soulspace.modelling.repository.elements.Model;
import org.soulspace.modelling.repository.elements.Package;
import org.soulspace.modelling.repository.elements.StateMachine;
import org.soulspace.modelling.repository.elements.UseCase;
import org.soulspace.modelling.repository.impl.ModelRepositoryImpl;
import org.soulspace.modelling.uml14.impl.Uml14RepositoryImpl;
import org.soulspace.modelling.uml14.impl.Xmi12ReaderImpl;

public class GenerationContext {

	private File destDir;

	private File backupDir;

	private File templateDir;

	private List<String> templateDirs;

	private File modelFile;

	private String profiles;

	private String modelFactory;

	private GeneratorGroup mainGroup = new GeneratorGroup();

	private ModelRepository repository;

	/**
	 * @return the repository
	 */
	public ModelRepository getRepository() {
		if(repository == null) {
			repository = initRepository();
		}
		return repository;
	}

	/**
	 * @param repository the repository to set
	 */
	public void setRepository(ModelRepository repository) {
		this.repository = repository;
	}

	public GenerationContext() {
		super();
	}

	/**
	 * @return the destDir
	 */
	public File getDestDir() {
		return destDir;
	}

	/**
	 * @param destDir
	 *            the destDir to set
	 */
	public void setDestDir(File destDir) {
		this.destDir = destDir;
	}

	/**
	 * @return the backupDir
	 */
	public File getBackupDir() {
		return backupDir;
	}

	/**
	 * @param backupDir
	 *            the backupDir to set
	 */
	public void setBackupDir(File backupDir) {
		this.backupDir = backupDir;
	}

	/**
	 * @return the templateDir
	 */
	public File getTemplateDir() {
		return templateDir;
	}

	/**
	 * @param templateDir
	 *            the templateDir to set
	 */
	public void setTemplateDir(File templateDir) {
		this.templateDir = templateDir;
	}

	/**
	 * @return the searchDirs
	 */
	public List<String> getTemplateDirs() {
		return templateDirs;
	}

	/**
	 * @param searchDirs
	 *            the searchDirs to set
	 */
	public void setTemplateDirs(List<String> templateDirs) {
		this.templateDirs = templateDirs;
	}

	/**
	 * @return the modelFile
	 */
	public File getModelFile() {
		return modelFile;
	}

	/**
	 * @param modelFile
	 *            the modelFile to set
	 */
	public void setModelFile(File modelFile) {
		this.modelFile = modelFile;
	}

	/**
	 * @return the profiles
	 */
	public String getProfiles() {
		return profiles;
	}

	/**
	 * @param profiles
	 *            the profiles to set
	 */
	public void setProfiles(String profiles) {
		this.profiles = profiles;
	}

	/**
	 * @return the modelFactory
	 */
	public String getModelFactory() {
		return modelFactory;
	}

	/**
	 * @param modelFactory
	 *            the modelFactory to set
	 */
	public void setModelFactory(String modelFactory) {
		this.modelFactory = modelFactory;
	}

	/**
	 * @return the mainGroup
	 */
	public GeneratorGroup getMainGroup() {
		return mainGroup;
	}

	/**
	 * @param mainGroup
	 *            the mainGroup to set
	 */
	public void setMainGroup(GeneratorGroup mainGroup) {
		this.mainGroup = mainGroup;
	}

	/**
	 * Adds a class generator.
	 * 
	 * @param cg
	 */
	public void addClassGenerator(ClassGenerator cg) {
		mainGroup.addClassGenerator(cg);
	}

	/**
	 * Adds a model generator.
	 * 
	 * @param mg
	 */
	public void addModelGenerator(ModelGenerator mg) {
		mainGroup.addModelGenerator(mg);
	}

	/**
	 * Adds a package generator.
	 * 
	 * @param pg
	 */
	public void addPackageGenerator(PackageGenerator pg) {
		mainGroup.addPackageGenerator(pg);
	}

	/**
	 * Adds a state machine generator.
	 * 
	 * @param sg
	 */
	public void addStateMachineGenerator(StateMachineGenerator sg) {
		mainGroup.addStateMachineGenerator(sg);
	}

	/**
	 * Adds a state machine generator.
	 * 
	 * @param sg
	 */
	public void addActorGenerator(ActorGenerator ag) {
		mainGroup.addActorGenerator(ag);
	}

	/**
	 * Adds a state machine generator.
	 * 
	 * @param sg
	 */
	public void addUseCaseGenerator(UseCaseGenerator ug) {
		mainGroup.addUseCaseGenerator(ug);
	}

	/**
	 * Adds a generator group.
	 * 
	 * @param group
	 */
	public void addGeneratorGroup(GeneratorGroup group) {
		mainGroup.addGeneratorGroup(group);
	}

	ModelRepository initRepository() {
		// TODO changes for uml version/tool dependend xmi repositories
		// TODO instanciate (specific) XmiRepository

		if (destDir == null || !destDir.isDirectory()) {
			throw new RuntimeException(destDir + " is not a valid directory");
		}

		if (modelFile == null) {
			throw new RuntimeException("no modelFile set");
		}

		ModelRepository repository = new ModelRepositoryImpl();
		Uml14RepositoryImpl umlRepository = new Uml14RepositoryImpl();
		Xmi12ReaderImpl xmiReader = new Xmi12ReaderImpl(umlRepository);
		
		Uml14ModelBuilderImpl builder = new Uml14ModelBuilderImpl(umlRepository, repository);

//		if (modelFactory != null) {
//			// instanciate model factory
//			mFactory = (IModelFactory) ClasspathUtils.newInstance(modelFactory,
//					this.getClass().getClassLoader());
//		} else {
//			mFactory = new ModelFactory();
//		}

		try {
			if (getProfiles() != null) {
				// initialize repository with the provided xmi files
				String[] profileNames = getProfiles().split(",");
				for (String profileName : profileNames) {
					// log("loading profile " + profileName);
					xmiReader.loadProfile(new File(profileName.trim()));
				}
			}
			// log("loading model " + modelFile);
			xmiReader.loadModel(modelFile);
		} catch(Exception e) {
			throw new BuildException(e);
		}
			
		return builder.build();
	}

	public void callGenerators(GenerationContext ctx, GeneratorGroup gg) {
		// model
		for(Model model : getRepository().getModelList()) {
			for (ClassifierGenerator mg : gg.getModelGenerators()) {
				mg.generate(ctx, model);
			}
		}

		// packages
		for (Package p : getRepository().getPackageList()) {
			for (ClassifierGenerator pg : gg.getPackageGenerators()) {
				pg.generate(ctx, p);
			}
		}

		// classes
		for (Class c : getRepository().getClassList()) {
			for (ClassifierGenerator cg : gg.getClassGenerators()) {
				cg.generate(ctx, c);
			}
		}

		// state machines
		for (StateMachine s : getRepository().getStateMachineList()) {
			for (ClassifierGenerator sg : gg.getStateMachineGenerators()) {
				sg.generate(ctx, s);
			}
		}
		
		for(Actor a : getRepository().getActorList()) {
			for(ClassifierGenerator ag : gg.getActorGenerators()) {
				ag.generate(ctx, a);
			}
		}
		
		for(UseCase uc : getRepository().getUseCaseList()) {
			for(ClassifierGenerator ug : gg.getUseCaseGenerators()) {
				ug.generate(ctx, uc);
			}
		}
		
		for(GeneratorGroup genGroup : gg.getGeneratorGroups()) {
			callGenerators(ctx, genGroup);
		}
	}

}
