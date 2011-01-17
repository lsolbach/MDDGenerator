package org.soulspace.modelling.base;

public interface XmiObject {

	String getXmiId();
	void setXmiId(String xmiId);

	String getXmiIdRef();
	void setXmiIdRef(String xmiIdRef);

	String getHRef();
	void setHRef(String hRef);

	String getRefId();
	boolean isReference();
	
	XmiObject getParentElement();
	void setParentElement(XmiObject parent);
	
	String getXmiNamespace();
	void setXmiNamespace(String namespace);

	String getQualifiedName();
	void setQualifiedName(String qualifiedName);
	
	boolean getProfileElement();
	void setProfileElement(boolean profileElement);
}
