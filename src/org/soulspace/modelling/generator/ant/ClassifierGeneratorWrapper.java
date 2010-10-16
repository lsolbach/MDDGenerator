package org.soulspace.modelling.generator.ant;

import java.util.regex.Pattern;

import org.soulspace.modelling.generator.ClassifierGenerator;
import org.soulspace.modelling.generator.GeneratorContext;
import org.soulspace.modelling.generator.Param;
import org.soulspace.template.util.StringHelper;
import org.soulspace.util.CollectionUtils;

public abstract class ClassifierGeneratorWrapper extends ClassifierGenerator {

	
	public ClassifierGeneratorWrapper() {
		super();
		genContext = new GeneratorContext();
	}

	public ClassifierGeneratorWrapper(GeneratorContext genContext) {
		super(genContext);
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


}
