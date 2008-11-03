package org.soulspace.xmi.util;

import junit.framework.TestCase;

public class XmiHelperTest extends TestCase {

	
	protected void setUp() {
		
	}
	
	protected void tearDown() {
		
	}
	
	public void testAppendNamespace() {
		String result = "";
		result = XmiHelper.appendNamespace("", "");
		assertEquals("Empty strings", "", result);
		
		result = XmiHelper.appendNamespace("", "Class");
		assertEquals("Just class name", "Class", result);

		result = XmiHelper.appendNamespace("package", "");
		assertEquals("Just package name", "package", result);

		result = XmiHelper.appendNamespace("package", "Class");
		assertEquals("Fully qualified class neam", "package.Class", result);
	}
	
}
