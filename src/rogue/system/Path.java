package rogue.system;
/*
 * Path.java
 * Copyright (C) 2013 dirk <dirk@Valinor.local>
 *
 * Distributed under terms of the MIT license.
 */

public class Path {

	/**
	 * Generates an absolute Pathstring from a given relative Path. Automatically adds src folder if not given in working directory.
	 *
	 * @param path Relative Path in Form "[folder]/[path]".
	 * @return Absolute Path in current Workingdirectory
	 */
	public static String generatePath(String path) {
		// What OS is running?
		if (isWin()) {
			System.out.println("Windows Operating System found");
			// We're running Windows, create an absolute Path
			return generateAbsolutePath(path.replaceAll("/","\\"));
		} else {
			// Should work okay with relative Paths
			return generateAbsolutePath(path);
		}
	}
	
	/**
	 * Checks if current OS is Windows
	 *
	 * @return Windows System
	 */
	public static boolean isWin() {
		return (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0);
	}

	public static boolean pathEndsWithSourceFolder(String path) {
		return (path.endsWith("/src") || path.endsWith("\\src"));
	}

	public static String generateAbsolutePath(String path) {
		String currentDirectory = System.getProperty("user.dir");
		if (isWin()) {
			if (pathEndsWithSourceFolder(currentDirectory)) {
				return currentDirectory.concat("\\").concat(path);
			} else {
				return currentDirectory.concat("\\src\\").concat(path);
			}

		} else {
			if (pathEndsWithSourceFolder(currentDirectory)) {
				return currentDirectory.concat("/").concat(path);

			} else {
				return currentDirectory.concat("/src/").concat(path);
			}
		}
	}
}
