package org.soulspace.modelling.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.soulspace.modelling.repository.ModelRepository;
import org.soulspace.modelling.repository.builder.uml14.impl.Uml14ModelBuilderImpl;
import org.soulspace.modelling.repository.impl.ModelRepositoryImpl;
import org.soulspace.modelling.uml14.impl.Uml14RepositoryImpl;
import org.soulspace.modelling.uml14.impl.Xmi12ReaderImpl;

public class GenerationContext extends AbstractGenerationContext {

	private File destDir;

	private File backupDir;

	private File templateDir;

	private List<String> templateDirs = new ArrayList<String>();

	private File modelFile;

	private String profiles;

	private String modelFactory;

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
	 * Adds a generator group.
	 * 
	 * @param group
	 */
	public void addGeneratorGroup(GeneratorGroup group) {
		getMainGroup().addGeneratorGroup(group);
	}

	ModelRepository initRepository() {

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

}
