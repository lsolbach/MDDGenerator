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

  /**
   * @return Returns the namespace.
   */
  public String getNamespace() {
    return namespace;
  }
  /**
   * @param namespace The namespace to set.
   */
  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }
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
}
