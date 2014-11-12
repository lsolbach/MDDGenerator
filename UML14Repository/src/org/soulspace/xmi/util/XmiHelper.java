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

/**
 * @author soulman
 * 
 */
public class XmiHelper {

	/**
	 * Appends a new path element to a namespace
	 * 
	 * @param base
	 * @param ext
	 * @return
	 */
	public static String appendNamespace(String base, String ext) {
		String name = getName(ext);
		if (!isSet(base)) {
			if (!isSet(name)) {
				return "";
			} else {
				return name;
			}
		} else {
			StringBuffer sb = new StringBuffer(32);
			sb.append(base);
			if (isSet(name)) {
				sb.append(".");
				sb.append(name);
			}
			return sb.toString();
		}
	}

	static String getName(String s) {
		if (isSet(s) && s.startsWith("/")) {
			return s.substring(1);
		}
		return s;
	}

	/**
	 * Test if a String is set, that is not null and not empty.
	 * 
	 * @param string
	 * @return true, if not null and not an empty string, false otherwise.
	 */
	public static boolean isSet(String string) {
		if (string == null || string.equals("")) {
			return false;
		} else {
			return true;
		}
	}
}
