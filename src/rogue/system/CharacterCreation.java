/*
 * CharacterCreation.java
 * Copyright (C) 2013 dirk <dirk@Valinor.local>
 *
 * Distributed under terms of the MIT license.
 */
package rogue.system;

import jade.ui.TiledTermPanel;
import jade.core.World;
import rogue.level.Screen;
import java.lang.InterruptedException;

public class CharacterCreation {
	/**
	 * Asks user for a Name for his or her character. Name be is limited to 8 Characters.
	 *
	 * @param term Terminal used to print Messages
	 * @param world World used
	 * @return Chosen Name of Character
	 */
	public static String getCharacterName(TiledTermPanel term, World world) {
		char c = ' ';
		String printName = new String("Name: _");
		String name = new String();
		while (c != '\n') {
			Screen.printLine(printName, term, world);
			try {
				c = term.getKey();
				switch (c) {
					case '\b':
						if (name.length() != 0) {
							name = name.substring(0,name.length()-1);
							printName = printName.substring(0,printName.length()-2).concat("_");
						}
						break;
					case '\n':
						break;
					default:
						if (name.length() < 8) {
							name = name.concat(new Character(c).toString());
							printName = printName.substring(0,printName.length()-1).concat(new Character(c).toString()).concat("_");
						}
						break;

				}
			} catch(InterruptedException e) {
				System.out.println("!Interrupted Exception");
				e.printStackTrace();
			}
		}
		return name;
	}
}
