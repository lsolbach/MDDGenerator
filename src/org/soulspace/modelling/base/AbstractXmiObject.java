/*
 * Created on Dec 28, 2004
 */
package org.soulspace.modelling.base;

/**
 * Base class for XMI objects
 * 
 * @author soulman
 * 
 */
public class AbstractXmiObject implements XmiObject {

	private String xmiNamespace = "";
	private String qualifiedName = "";
	private XmiObject parentElement = null;
	private String xmiId;
	private String xmiIdRef;
	private String hRef;
	boolean profileElement;

	/**
	 * Constructor
	 */
	public AbstractXmiObject() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param namespace
	 *            Namespace for the XMI object
	 * @param parent
	 */
	public AbstractXmiObject(String namespace, AbstractXmiObject parentElement) {
		super();
		this.xmiNamespace = namespace;
		this.parentElement = parentElement;
	}

	/**
	 * @return the profileElement
	 */
	public boolean getProfileElement() {
		return profileElement;
	}

	/**
	 * @param profileElement
	 *            the profileElement to set
	 */
	public void setProfileElement(boolean profileElement) {
		this.profileElement = profileElement;
	}

	/**
	 * @return Returns the namespace.
	 */
	public String getXmiNamespace() {
		return xmiNamespace;
	}
	
	/**
	 * @param namespace The namespace to set.
	 */
	public void setXmiNamespace(String namespace) {
		this.xmiNamespace = namespace;
	}

	/**
	 * @return Returns the parent.
	 */
	public XmiObject getParentElement() {
		return parentElement;
	}

	/**
	 * @param parent
	 *            The parent to set.
	 */
	public void setParentElement(XmiObject parentElement) {
		this.parentElement = parentElement;
	}

	/**
	 * @return Returns the qualifiedName.
	 */
	public String getQualifiedName() {
		return qualifiedName;
	}

	/**
	 * @param qualifiedName
	 *            The qualifiedName to set.
	 */
	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}

	/**
	 * @return the xmiId
	 */
	public String getXmiId() {
		return xmiId;
	}

	/**
	 * @param xmiId
	 *            the xmiId to set
	 */
	public void setXmiId(String xmiId) {
		this.xmiId = xmiId;
	}

	/**
	 * @return the xmiIdRef
	 */
	public String getXmiIdRef() {
		return xmiIdRef;
	}

	/**
	 * @param xmiIdRef
	 *            the xmiIdRef to set
	 */
	public void setXmiIdRef(String xmiIdRef) {
		this.xmiIdRef = xmiIdRef;
	}

	/**
	 * @return the xmiIdRef
	 */
	public String getHRef() {
		return hRef;
	}

	/**
	 * @param xmiIdRef
	 *            the xmiIdRef to set
	 */
	public void setHRef(String hRef) {
		this.hRef = hRef;
	}

	public String getRefId() {
		if (getXmiIdRef() != null) {
			return getXmiIdRef();
		} else if (getHRef() != null) {
			String[] parts = getHRef().split("#");
			if (parts.length == 2) {
				String xmiIdRef = parts[1];
				return xmiIdRef;
			}
		}
		return null;
	}

	public boolean isReference() {
		return getXmiIdRef() != null || getHRef() != null;
	}
}
