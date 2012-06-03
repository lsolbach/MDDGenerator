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
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.oro.text.regex.MatchResult;
import org.apache.tools.ant.BuildException;
import org.soulspace.modelling.repository.elements.Model;
import org.soulspace.modelling.repository.elements.ModelElement;
import org.soulspace.template.TemplateEngine;
import org.soulspace.template.datasource.impl.BeanDataSourceImpl;
import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.util.RegExHelper;
import org.soulspace.template.util.StringHelper;
import org.soulspace.util.CollectionUtils;

/**
 * @author soulman Base class for ant generators
 * 
 */
public abstract class ModelElementGenerator {

	protected GeneratorContext genContext;
	protected TemplateEngine engine;
	protected BeanDataSourceImpl dataSource;
	protected Pattern pattern;

	/**
	 * Constructor
	 */
	protected ModelElementGenerator() {
		super();
		genContext = new GeneratorContext();
	}

	protected ModelElementGenerator(GeneratorContext genContext) {
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
	public String getBaseName() {
		return genContext.getBaseName();
	}

	/**
	 * @param baseName
	 *            the basename to set
	 */
	public void setBaseName(String baseName) {
		genContext.setBaseName(baseName);
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
	public String getBaseNamespace() {
		return genContext.getBaseNamespace();
	}

	/**
	 * @param baseNamespace
	 *            The namespaceReplacement to set.
	 */
	public void setBaseNamespace(String baseNamespace) {
		genContext.setBaseNamespace(baseNamespace);
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

		try {
			if(isSet(genContext.getImports())) {
				importTemplateNames = genContext.getImports().split(",");
				File[] templateFiles = new File[importTemplateNames.length + 1];
				for (int i = 0; i < importTemplateNames.length; i++) {
					templateFiles[i] = locateFile(templateDirs,
							importTemplateNames[i].trim(), ".tinc");
				}
				templateFiles[importTemplateNames.length] = locateFile(
						templateDirs, genContext.getName(), ".tmpl");
				engine.loadTemplates(templateFiles);
			} else {
				engine.loadTemplate(locateFile(templateDirs, genContext
						.getName(), ".tmpl"));
			}
		} catch(Exception e) {
			engine = null;
			System.err.println("Error creating a template engine for template "
					+ genContext.getName());
			throw new RuntimeException(
					"Error creating a template engine for template "
							+ genContext.getName(), e);
		}

		return engine;
	}

	File[] getTemplateDirs(List<String> templateDirNames) {
		File[] templateDirs = new File[templateDirNames.size()];
		for (int i = 0; i < templateDirNames.size(); i++) {
			File file = new File(templateDirNames.get(i).trim());
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
	public void setDataSource(BeanDataSourceImpl ds) {
		dataSource = ds;
	}

	boolean mustGenerate(ModelElement element) {
		return generateForNamespace(element)
				&& generateForStereotype(element)
				&& !checkForProfile(element);
	}

	boolean checkForProfile(ModelElement element) {
		return element.getIsProfileElement();
	}

	boolean generateForStereotype(ModelElement element) {
		if (!genContext.getExcludeStereotypes().isEmpty()) {
			for (String excStereotype : genContext.getExcludeStereotypes()) {
				if (element.getStereotypeMap().containsKey(
						excStereotype.trim())) {
					return false;
				}
			}
		}
		if (!isSet(genContext.getStereotype())) {
			return true;
		}
		String st = genContext.getStereotype();
		if (st.equals("NONE")) {
			return element.getStereotypeMap().isEmpty();
		}
		if (st.equals("ALL")) {
			return !element.getStereotypeMap().isEmpty();
		}

		boolean generate = false;
		String[] incStereotypes = genContext.getStereotype().split(",");
		for (String incStereotype : incStereotypes) {
			if (element.getStereotypeMap().containsKey(incStereotype.trim())) {
				generate = true;
			}
		}

		return generate;
	}

	boolean generateForNamespace(ModelElement element) {
		boolean generate = false;
		String namespace = "";
		if (getUseNameAsNamespace()) {
			// TODO check if set
			namespace = element.getQualifiedName();
		} else {
			namespace = element.getNamespace();
		}
		if ((genContext.getNamespaceIncludes() == null
					|| genContext.getNamespaceIncludes().size() == 0)
					&& (genContext.getNamespaceExcludes() == null
					||genContext.getNamespaceExcludes().size() == 0)
				) {
			generate = true;
		} else if (genContext.getNamespaceIncludes() != null
				&& genContext.getNamespaceIncludes().size() > 0) {
			for (String ns : genContext.getNamespaceIncludes()) {
				if (namespace.startsWith(ns.trim())) {
					generate = true;
				}
			}
		}
		if (genContext.getNamespaceExcludes() != null
				&& genContext.getNamespaceExcludes().size() > 0) {
			for (String ns : genContext.getNamespaceExcludes()) {
				if (namespace.startsWith(ns.trim())) {
					generate = false;
				}
			}
		}
		return generate;
	}

	public void generate(GenerationContext ctx, ModelElement element) {
		generate(ctx, element, null);
	}

	/**
	 * 
	 * @param gt
	 * @param element
	 */
	public void generate(GenerationContext ctx, ModelElement element,
			BeanDataSourceImpl ds) {
		if (!mustGenerate(element)) {
			// no generation needed
			return;
		}
		String output;
		Map<String, String> userSections = null;
		// dataSource = ctx.getDataSource();

		engine = getEngine(ctx);
		BeanDataSourceImpl myDS;
		// TODO fix exception handling?
		if (ds != null) {
			myDS = new BeanDataSourceImpl(element, ds);
		} else {
			myDS = new BeanDataSourceImpl(element);
		}
		if (isSet(genContext.getUserSection())) {
			userSections = readUserSections(getPath(ctx, element, true));
			myDS.add("USERSECTIONS", userSections);
		}
		try {
			myDS.add("GenContext", genContext);

			output = engine.generate(myDS);

			if (acceptWrite(output)) {
				createPackagePath(ctx, element);
				writeFile(getPath(ctx, element, false), output);
			}
		} catch (Exception e) {
			System.err.println("Exception while processing template "
					+ genContext.getName() + " for classifier "
					+ element.getName() + "!");
			throw new RuntimeException("Exception while processing template "
					+ genContext.getName() + " for classifier "
					+ element.getName() + "!", e);
		}
	}

	protected void createPackagePath(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	// TODO refactor to use JavaUtils instead of TemplateEngine StringHelper
	protected void createPackagePath(GenerationContext ctx,
			ModelElement element) {
		StringBuilder sb = new StringBuilder();
		if (ctx.getDestDir() != null) {
			sb.append(ctx.getDestDir().getAbsolutePath() + File.separator);
		}
		if (StringHelper.isSet(genContext.getSubdir())) {
			sb.append(genContext.getSubdir() + File.separatorChar);
		}
		appendNamespace(sb, element);

		createPackagePath(sb.toString());
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

	protected String getPath(GenerationContext ctx, ModelElement element,
			boolean backup) {
		StringBuilder sb = new StringBuilder();

		if (!backup) {
			if (ctx.getDestDir() != null) {
				sb.append(ctx.getDestDir().getAbsolutePath() + File.separator);
			}
		} else {
			if (ctx.getBackupDir() != null) {
				sb.append(ctx.getBackupDir().getAbsolutePath()
						+ File.separator);
			}
		}
		if (StringHelper.isSet(genContext.getSubdir())) {
			sb.append(genContext.getSubdir() + File.separatorChar);
		}

		appendNamespace(sb, element);
		appendName(sb, element);

		return sb.toString();
	}

	void appendNamespace(StringBuilder sb, ModelElement element) {
		if (StringHelper.isSet(genContext.getNamespacePrefix())) {
			sb.append(genContext.getNamespacePrefix().replace('.',
					File.separatorChar)
					+ File.separatorChar);
		}
		if (StringHelper.isSet(genContext.getBaseNamespace())) {
			if(genContext.getBaseNamespace().startsWith("[")) {
				sb.append(getNameFromModel(element, genContext.getBaseNamespace()));
			} else {
				sb.append(genContext.getBaseNamespace().replace('.',
						File.separatorChar)
						+ File.separatorChar);				
			}
		} else {
			if(element.getNamespace() != null) {
				sb.append(element.getNamespace()
						.replace('.', File.separatorChar)
						+ File.separatorChar);
			}
			if (genContext.getUseNameAsNamespace()) {
				sb.append(element.getName().replace('.', File.separatorChar)
						+ File.separatorChar);
			}
		}
		if (StringHelper.isSet(genContext.getNamespaceSuffix())) {
			sb.append(genContext.getNamespaceSuffix().replace('.',
					File.separatorChar)
					+ File.separatorChar);
		}		
	}
	
	void appendName(StringBuilder sb, ModelElement element) {
		if (StringHelper.isSet(genContext.getPrefix())) {
			sb.append(genContext.getPrefix());
		}
		if (!StringHelper.isSet(genContext.getBaseName())) {
			sb.append(element.getName());
		} else {
			if(genContext.getBaseName().startsWith("[")) {
				sb.append(getNameFromModel(element, genContext.getBaseName()));
			} else {
				sb.append(genContext.getBaseName());
			}
		}
		if (StringHelper.isSet(genContext.getSuffix())) {
			sb.append(genContext.getSuffix());
		}

		if (StringHelper.isSet(genContext.getExtension())) {
			sb.append(".");
			sb.append(genContext.getExtension());
		}
	}
	
	private String getNameFromModel(ModelElement element,
			String name) {
		if(name.equals("[MODEL_NAME]")) {
			ModelElement modelCandidate = element;
			while(modelCandidate != null && !(modelCandidate instanceof Model)) {
				modelCandidate = (ModelElement) modelCandidate.getParentElement();
			}
			if(modelCandidate != null) {
				return modelCandidate.getName();
			}
		} else if(name.equals("[ELEMENT_NAME]")) {
			if(element != null && element.getName() != null) {
				return element.getName();
			}
		} else if(name.equals("[ELEMENT_NAMESPACE]")) {
			if(element != null && element.getNamespace() != null) {
				return element.getNamespace();
			}
		} else if(name.equals("[ELEMENT_QNAME]")) {
			if(element != null && element.getQualifiedName() != null) {
				return element.getQualifiedName();
			}
		} else if(name.equals("[ELEMENT_ID]")) {
			if(element != null && element.getId() != null) {
				return element.getId();
			}	
		}
		return name;
	}

	protected boolean writeFile(String filename, String content) {
		PrintWriter pw;
		File file = new File(filename);
		File path = file.getParentFile();
		try {
			if(!path.exists()) {
				path.mkdirs();
			}
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
		} catch (IOException e) {
			System.err.println("Error parsing user sections on file "
					+ filename);
			throw new RuntimeException("Error parsing user sections on file "
					+ filename, e);
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