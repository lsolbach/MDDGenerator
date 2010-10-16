/*
 * Created on Feb 21, 2005
 */
package org.soulspace.modelling.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.oro.text.regex.MatchResult;
import org.apache.tools.ant.BuildException;
import org.soulspace.modelling.repository.elements.Classifier;
import org.soulspace.modelling.repository.elements.ModelElement;
import org.soulspace.template.TemplateEngine;
import org.soulspace.template.datasource.impl.BeanDataSource;
import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.util.RegExHelper;
import org.soulspace.template.util.StringHelper;
import org.soulspace.util.CollectionUtils;

/**
 * @author soulman Base class for ant generators
 * 
 */
public abstract class ClassifierGenerator {

	protected GeneratorContext genContext;

	protected TemplateEngine engine;

	protected BeanDataSource dataSource;

	protected Pattern pattern;

	/**
	 * Constructor
	 */
	public ClassifierGenerator() {
		super();
		genContext = new GeneratorContext();
	}

	public ClassifierGenerator(GeneratorContext genContext) {
		super();
		this.genContext = genContext;
	}
	
	public GeneratorContext getGeneratorContext() {
		return genContext;
	}

	public void getGeneratorContext(GeneratorContext genContext) {
		this.genContext = genContext;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return genContext.getName();
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		genContext.setName(name);
	}

	/**
	 * @return the basename
	 */
	public String getBasename() {
		return genContext.getBasename();
	}

	/**
	 * @param basename
	 *            the basename to set
	 */
	public void setBasename(String basename) {
		genContext.setBasename(basename);
	}

	/**
	 * 
	 * @return
	 */
	public String getImports() {
		return genContext.getImports();
	}

	/**
	 * 
	 * @param imports
	 */
	public void setImports(String imports) {
		genContext.setImports(imports);
	}

	/**
	 * @return Returns the suffix.
	 */
	public String getSuffix() {
		return genContext.getSuffix();
	}

	/**
	 * @param suffix
	 *            The suffix to set.
	 */
	public void setSuffix(String suffix) {
		genContext.setSuffix(suffix);
	}

	public String getSubdir() {
		return genContext.getSubdir();
	}

	public void setSubdir(String subdir) {
		genContext.setSubdir(subdir);
	}

	/**
	 * @return Returns the extension.
	 */
	public String getExtension() {
		return genContext.getExtension();
	}

	/**
	 * @param extension
	 *            The extension to set.
	 */
	public void setExtension(String extension) {
		genContext.setExtension(extension);
	}

	/**
	 * 
	 * @return
	 */
	public String getStereotype() {
		return genContext.getStereotype();
	}

	/**
	 * 
	 * @param stereotype
	 */
	public void setStereotype(String stereotype) {
		genContext.setStereotype(stereotype);
	}

	/**
	 * @return Returns the namespaceReplacement.
	 */
	public String getNamespaceReplacement() {
		return genContext.getNamespaceReplacement();
	}

	/**
	 * @param namespaceReplacement
	 *            The namespaceReplacement to set.
	 */
	public void setNamespaceReplacement(String namespaceReplacement) {
		genContext.setNamespaceReplacement(namespaceReplacement);
	}

	/**
	 * @return Returns the namespacePrefix.
	 */
	public String getNamespacePrefix() {
		return genContext.getNamespacePrefix();
	}

	/**
	 * @param namespacePrefix
	 *            The namespacePrefix to set.
	 */
	public void setNamespacePrefix(String namespacePrefix) {
		genContext.setNamespacePrefix(namespacePrefix);
	}

	/**
	 * @return Returns the namespaceSuffix.
	 */
	public String getNamespaceSuffix() {
		return genContext.getNamespaceSuffix();
	}

	/**
	 * @param namespaceSuffix
	 *            The namespaceSuffix to set.
	 */
	public void setNamespaceSuffix(String namespaceSuffix) {
		genContext.setNamespaceSuffix(namespaceSuffix);
	}

	/**
	 * @return
	 * @see org.soulspace.modelling.generator.GeneratorContext#getUseNameAsNamespace()
	 */
	public boolean getUseNameAsNamespace() {
		return genContext.getUseNameAsNamespace();
	}

	/**
	 * @param useNameAsNamespace
	 * @see org.soulspace.modelling.generator.GeneratorContext#setUseNameAsNamespace(boolean)
	 */
	public void setUseNameAsNamespace(boolean useNameAsNamespace) {
		genContext.setUseNameAsNamespace(useNameAsNamespace);
	}

	/**
	 * @return Returns the prefix.
	 */
	public String getPrefix() {
		return genContext.getPrefix();
	}

	/**
	 * @param prefix
	 *            The prefix to set.
	 */
	public void setPrefix(String prefix) {
		genContext.setPrefix(prefix);
	}

	public String getGenerationFilterPattern() {
		return genContext.getGenerationFilterPattern();
	}

	public void setGenerationFilterPattern(String generationFilterPattern) {
		genContext.setGenerationFilterPattern(generationFilterPattern);
		if (StringHelper.isSet(generationFilterPattern)) {
			pattern = Pattern.compile(generationFilterPattern, Pattern.DOTALL);
		}
	}

	public String getUserSection() {
		return genContext.getUserSection();
	}

	public void setUserSection(String userSection) {
		genContext.setUserSection(userSection);
	}

	public void setNamespaceIncludes(String namespaceIncludes) {
		String[] nsIncludes = namespaceIncludes.split(",");
		genContext
				.setNamespaceIncludes(CollectionUtils.asArrayList(nsIncludes));
	}

	public void setNamespaceExcludes(String namespaceExcludes) {
		String[] nsExcludes = namespaceExcludes.split(",");
		genContext
				.setNamespaceExcludes(CollectionUtils.asArrayList(nsExcludes));
	}

	public void addConfiguredParam(Param param) {
		genContext.addParam(param);
	}

	public void setExcludeStereotypes(String excludeStereotypes) {
		String[] stereotypes = excludeStereotypes.split(",");
		genContext.setExcludeStereotypes(CollectionUtils
				.asArrayList(stereotypes));
	}

	public void setIncludeStereotypes(String includeStereotypes) {
		String[] stereotypes = includeStereotypes.split(",");
		genContext.setIncludeStereotypes(CollectionUtils
				.asArrayList(stereotypes));
	}

	/**
	 * @return Returns the TemplateEngine.
	 */
	public TemplateEngine getEngine(GenerationContext ctx) {
		File[] templateDirs;
		if (ctx.getTemplateDir() == null && ctx.getTemplateDirs() == null) {
			throw new GeneratorException("Template directory not set");
		} else if (ctx.getTemplateDirs() != null) {
			templateDirs = getTemplateDirs(ctx.getTemplateDirs());
		} else {
			templateDirs = new File[] { ctx.getTemplateDir() };
		}

		if (engine != null) {
			return engine;
		}

		engine = new TemplateEngineImpl();
		String[] importTemplateNames = null;
		String[] templates = null;

		try {
			if (isSet(genContext.getImports())) {
				importTemplateNames = genContext.getImports().split(",");
				templates = new String[importTemplateNames.length + 1];
				File[] templateFiles = new File[importTemplateNames.length + 1];
				for (int i = 0; i < importTemplateNames.length; i++) {
					templateFiles[i] = locateFile(templateDirs,
							importTemplateNames[i], ".tinc");
				}
				templateFiles[importTemplateNames.length] = locateFile(
						templateDirs, genContext.getName(), ".tmpl");
				engine.loadTemplates(templateFiles);
			} else {
				engine.loadTemplate(locateFile(templateDirs, genContext
						.getName(), ".tmpl"));
			}
		} catch (Exception e) {
			engine = null;
			System.err.println("Error processing " + genContext.getName());
			throw new RuntimeException(
					"Error creating a template engine for template "
							+ genContext.getName(), e);
		}

		return engine;
	}

	File[] getTemplateDirs(List<String> templateDirNames) {
		File[] templateDirs = new File[templateDirNames.size()];
		for (int i = 0; i < templateDirNames.size(); i++) {
			File file = new File(templateDirNames.get(i));
			if (!file.exists() || !file.isDirectory()) {
				throw new RuntimeException("Error validating directory "
						+ templateDirNames.get(i));
			}
			templateDirs[i] = file;
		}
		return templateDirs;
	}

	File locateFile(File[] templateDirs, String basename, String extension) {
		for (File templateDir : templateDirs) {
			File file = new File(templateDir.getAbsolutePath() + "/" + basename
					+ extension);
			if (file.exists()) {
				return file;
			}
		}
		throw new RuntimeException("Error locating file " + basename
				+ extension);
	}

	/**
	 * Setter for the DataSource
	 * 
	 * @param ds
	 */
	public void setDataSource(BeanDataSource ds) {
		dataSource = ds;
	}

	boolean mustGenerate(Classifier classifier) {
		return generateForNamespace(classifier)
				&& generateForStereotype(classifier)
				&& !checkForProfile(classifier);
	}

	boolean checkForProfile(Classifier classifier) {
		return classifier.getIsProfileElement();
	}

	boolean generateForStereotype(Classifier classifier) {
		if (!genContext.getExcludeStereotypes().isEmpty()) {
			for (String excStereotype : genContext.getExcludeStereotypes()) {
				if (classifier.getStereotypeMap().containsKey(excStereotype)) {
					return false;
				}
			}
		}
		// TODO remove
		if (classifier.getStereotypeMap().get("external") != null) {
			return false;
		}
		if (!isSet(genContext.getStereotype())) {
			return true;
		}
		String st = genContext.getStereotype();
		if (st.equals("NONE")) {
			return classifier.getStereotypeMap().isEmpty();
		}
		if (st.equals("ALL")) {
			return !classifier.getStereotypeMap().isEmpty();
		}

		boolean generate = false;
		String[] incStereotypes = genContext.getStereotype().split(",");
		for (String incStereotype : incStereotypes) {
			if (classifier.getStereotypeMap().containsKey(incStereotype)) {
				generate = true;
			}
		}

		return generate;
	}

	boolean generateForNamespace(Classifier classifier) {
		boolean generate = false;
		if(classifier.getNamespace() == null) {
			return false;
		}
		if (genContext.getNamespaceIncludes().size() == 0
				&& genContext.getNamespaceExcludes().size() == 0
				&& !classifier.getNamespace().startsWith("java")) {
			generate = true;
		} else if (genContext.getNamespaceIncludes().size() > 0) {
			for (String namespace : genContext.getNamespaceIncludes()) {
				if (classifier.getNamespace().startsWith(namespace)) {
					generate = true;
				}
			}
		}
		if (genContext.getNamespaceExcludes().size() > 0) {
			for (String namespace : genContext.getNamespaceExcludes()) {
				if (classifier.getNamespace().startsWith(namespace)) {
					generate = false;
				}
			}
		}
		return generate;
	}

	/**
	 * 
	 * @param gt
	 * @param classifier
	 */
	public void generate(GenerationContext ctx, Classifier classifier) {
		if (!mustGenerate(classifier)) {
			// no generation needed
			return;
		}
		String output;
		Map<String, String> userSections = null;
		//dataSource = ctx.getDataSource();

		engine = getEngine(ctx);

		// TODO fix exception handling?
		try {
			BeanDataSource myDS = new BeanDataSource(classifier);
			myDS.add("GenContext", genContext);
			if (isSet(genContext.getUserSection())) {
				userSections = readUserSections(getPath(ctx, classifier, true));
				myDS.add("USERSECTIONS", userSections);
			}

			output = engine.generate(myDS);

			if (acceptWrite(output)) {
				createPackagePath(ctx, classifier);
				writeFile(getPath(ctx, classifier, false), output);
			}
		} catch (Exception e1) {
			System.err.println("Exception while processing template "
					+ genContext.getName() + " for classifier "
					+ classifier.getName() + "!");
			e1.printStackTrace();
		}
	}

	protected void createPackagePath(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	// FIXME refactor to use JavaUtils instead of TemplateEngine StringHelper
	protected void createPackagePath(GenerationContext ctx,
			Classifier classifier) {
		StringBuilder sb = new StringBuilder();
		if (ctx.getDestDir() != null) {
			sb.append(ctx.getDestDir().getAbsolutePath() + File.separator);
		}
		if (StringHelper.isSet(genContext.getSubdir())) {
			sb.append(genContext.getSubdir() + File.separatorChar);
		}

		if (StringHelper.isSet(genContext.getNamespaceReplacement())) {
			sb.append(genContext.getNamespaceReplacement().replace('.',
					File.separatorChar)
					+ File.separatorChar);
		} else {
			if (StringHelper.isSet(genContext.getNamespacePrefix())) {
				sb.append(genContext.getNamespacePrefix().replace('.',
						File.separatorChar)
						+ File.separatorChar);
			}
			sb.append(classifier.getNamespace()
					.replace('.', File.separatorChar)
					+ File.separatorChar);
			if (classifier instanceof Package
					&& genContext.getUseNameAsNamespace()) {
				sb.append(classifier.getName().replace('.', File.separatorChar)
						+ File.separatorChar);
			}
			if (StringHelper.isSet(genContext.getNamespaceSuffix())) {
				sb.append(genContext.getNamespaceSuffix().replace('.',
						File.separatorChar)
						+ File.separatorChar);
			}
		}

		File file = new File(sb.toString());
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	protected String getPath(String prefix, String filename, String suffix,
			String extension) {
		StringBuilder sb = new StringBuilder();
		if (filename == null) {
			throw new BuildException("no filename set!");
		}

		if (prefix != null) {
			sb.append(prefix + File.separator);
		}
		sb.append(filename.replace('.', File.separatorChar));

		if (suffix != null) {
			sb.append(suffix);
		}

		if (extension != null) {
			sb.append(extension);
		}
		return sb.toString();
	}

	protected String getPath(File prefix, String filename, String suffix,
			String extension) {
		StringBuilder sb = new StringBuilder();
		if (filename == null) {
			throw new BuildException("no filename set!");
		}

		if (prefix != null) {
			sb.append(prefix.getAbsolutePath() + File.separator);
		}
		sb.append(filename.replace('.', File.separatorChar));

		if (suffix != null) {
			sb.append(suffix);
		}

		if (extension != null) {
			sb.append(extension);
		}
		return sb.toString();
	}

	protected String getPath(GenerationContext ctx, Classifier cf,
			boolean backup) {
		StringBuilder sb = new StringBuilder();

		if (!backup) {
			if (ctx.getDestDir() != null) {
				sb.append(ctx.getDestDir().getAbsolutePath() + File.separator);
			}
		} else {
			if (ctx.getBackupDir() != null) {
				sb
						.append(ctx.getBackupDir().getAbsolutePath()
								+ File.separator);
			}
		}
		if (StringHelper.isSet(genContext.getSubdir())) {
			sb.append(genContext.getSubdir() + File.separatorChar);
		}

		if (StringHelper.isSet(genContext.getNamespaceReplacement())) {
			sb.append(genContext.getNamespaceReplacement().replace('.',
					File.separatorChar)
					+ File.separatorChar);
		} else {
			if (StringHelper.isSet(genContext.getNamespacePrefix())) {
				sb.append(genContext.getNamespacePrefix().replace('.',
						File.separatorChar)
						+ File.separatorChar);
			}
			sb.append(cf.getNamespace().replace('.', File.separatorChar)
					+ File.separatorChar);
			if (cf instanceof Package && genContext.getUseNameAsNamespace()) {
				sb.append(cf.getName().replace('.', File.separatorChar)
						+ File.separatorChar);
			}
			if (StringHelper.isSet(genContext.getNamespaceSuffix())) {
				sb.append(genContext.getNamespaceSuffix().replace('.',
						File.separatorChar)
						+ File.separatorChar);
			}
		}

		if (StringHelper.isSet(genContext.getPrefix())) {
			sb.append(genContext.getPrefix());
		}
		if (!StringHelper.isSet(genContext.getBasename())) {
			sb.append(cf.getName());
		} else {
			sb.append(genContext.getBasename());
		}
		if (StringHelper.isSet(genContext.getSuffix())) {
			sb.append(genContext.getSuffix());
		}

		if (StringHelper.isSet(genContext.getExtension())) {
			sb.append(".");
			sb.append(genContext.getExtension());
		}

		return sb.toString();
	}

	protected boolean writeFile(String filename, String content) {
		File file = new File(filename);
		PrintWriter pw;
		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
					file), genContext.getEncoding()));
			pw.print(content);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return true;
	}

	protected Map<String, String> readUserSections(String filename) {
		Map<String, String> userSections = new HashMap<String, String>();
		String line = null;
		String name = "";
		MatchResult result = null;

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.out.println("File " + filename + " not found.");
			return userSections;
		}

		try {
			StringBuffer sb = null;
			while ((line = in.readLine()) != null) {
				// match user section
				if ((result = RegExHelper.match(line, "^.*"
						+ genContext.getUserSection() + "-BEGIN\\((.*)\\).*$")) != null) {
					name = result.group(1);
					sb = new StringBuffer(64);
				} else if ((result = RegExHelper.match(line, "^.*"
						+ genContext.getUserSection() + "-END\\(" + name
						+ "\\).*$")) != null) {
					userSections.put(name, sb.toString());
					sb = null;
					name = "";
				} else if (sb != null) { // ) {
					sb.append(line + "\n");
				}
			}
		} catch (IOException e1) {
			System.err.println("Error parsing user sections on file " + filename);
			e1.printStackTrace();
		}

		return userSections;
	}

	boolean isSet(String s) {
		if (s == null || s.equals("")) {
			return false;
		}
		return true;
	}

	protected boolean acceptWrite(String generated) {
		if (pattern == null) {
			return true;
		}
		Matcher matcher = pattern.matcher(generated);
		return !matcher.matches();
	}

}