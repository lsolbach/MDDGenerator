/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
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
