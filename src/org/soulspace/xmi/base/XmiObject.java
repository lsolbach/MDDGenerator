/*
 * Created on Dec 28, 2004
 */
package org.soulspace.xmi.base;

/**
 * Base class for XMI objects
 * @author soulman
 *
 */
public class XmiObject {

  private String namespace = "";
  private String qualifiedName = "";
  private XmiObject parent = null;
  private String xmiId;
  private String xmiIdRef;
  private String hRef;
  
  /**
   * Constructor
   */
  public XmiObject() {
    super();
  }

  /**
   * Constructor
   * @param namespace Namespace for the XMI object
   * @param parent
   */
  public XmiObject(String namespace, XmiObject parent) {
    super();
    this.namespace = namespace;
    this.parent = parent;
  }

//  /**
//   * @return Returns the namespace.
//   */
//  public String getNamespace() {
//    return namespace;
//  }
//  /**
//   * @param namespace The namespace to set.
//   */
//  public void setNamespace(String namespace) {
//    this.namespace = namespace;
//  }
  /**
   * @return Returns the parent.
   */
  public XmiObject getParent() {
    return parent;
  }
  /**
   * @param parent The parent to set.
   */
  public void setParent(XmiObject parent) {
    this.parent = parent;
  }
  /**
   * @return Returns the qualifiedName.
   */
  public String getQualifiedName() {
    return qualifiedName;
  }
  /**
   * @param qualifiedName The qualifiedName to set.
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
   * @param xmiId the xmiId to set
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
   * @param xmiIdRef the xmiIdRef to set
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
   * @param xmiIdRef the xmiIdRef to set
   */
  public void setHRef(String hRef) {
    this.hRef = hRef;
  }
  
  public String getRefId() {
	  if(getXmiIdRef() != null) {
		  return getXmiIdRef();
	  } else if(getHRef() != null) {
		  String[] parts = getHRef().split("#");
		  if(parts.length == 2) {
			  String xmiIdRef = parts[1];
			  return xmiIdRef;	  
		  }
	  }
	  return null;
  }

  
}
