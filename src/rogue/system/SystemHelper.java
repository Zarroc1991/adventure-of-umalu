/*
 * System.java
 * Copyright (C) 2013 dirk <dirk@Valinor.local>
 *
 * Distributed under terms of the MIT license.
 */
package rogue.system;

public class SystemHelper {
	public static boolean debug = false;

	public static void debugMessage(String message) {
		if (debug) {
			System.out.println("> Debug: "+message);
		}
	}
}
