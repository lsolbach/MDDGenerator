package org.soulspace.xmi.base;

public interface XmiObject {

	String getXmiId();
	void setXmiId(String xmiId);

	String getXmiIdRef();
	void setXmiIdRef(String xmiIdRef);

	String getHRef();
	void setHRef(String hRef);
	String getHRefId();

	XmiObject getParent();
	void setParent(XmiObject parent);
	
//	String getNamespace();
//	void setNamespace(String namespace);

	String getQualifiedName();
	void setQualifiedName(String qualifiedName);
	
	boolean getProfileElement();
	void setProfileElement(boolean profileElement);
}
