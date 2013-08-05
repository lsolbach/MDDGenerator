/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
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
