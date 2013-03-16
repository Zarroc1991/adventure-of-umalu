/*
 * HelpScreen.java
 * Copyright (C) 2013 dirk <dirk@Valinor.local>
 *
 * Distributed under terms of the MIT license.
 */
package rogue.system;

import rogue.level.Screen;
import java.util.ArrayList;
public class HelpScreen {
	public static void printMainHelpScreen() {
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("Adventures in Umalu - Help");
		lines.add("");
		lines.add("-- Steuerung --");
		lines.add("w,k - Nach Norden laufen");
		lines.add("a,h - Nach Westen laufen");
		lines.add("s,j - Nach Sueden laufen");
		lines.add("d,l - Nach Osten  laufen");
		lines.add("Es gibt aber noch andere Moeglichkeiten sich der Welt von Umalu zu bewegen.");
		lines.add("Doch diese sind schon lange Vergessen. Kannst du sie wiederentdecken?");
		lines.add("");
		lines.add("-- Weiteres Interface --");
		lines.add("i - Inventar anzeigen");
		lines.add("g - Diese Hilfe anezeigen");
		lines.add("x - Eventlog anzeigen");
		lines.add("q - Adventures of Umalu beenden");
		lines.add("");
		lines.add("+++ Beliebige Taste zum Fortfahren druecken +++");
		Screen.putText(lines);
		Screen.anyKey();
	}
}
