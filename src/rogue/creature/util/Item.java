/*
 * Item.java
 * Copyright (C) 2013 dirk <dirk@Valinor.local>
 *
 * Distributed under terms of the MIT license.
 */
package rogue.creature.util;

import jade.ui.TiledTermPanel;
import jade.ui.Terminal;
import java.util.ArrayList;
import rogue.level.Screen;
import rogue.system.HelpScreen;

/**
 * Represents a single Item for players Inventory
 */
public class Item {
	private String name;
	private int goldValue;
	private int[] modificators;
	private int itemType;
	private int type;
	private int maxDurability;
	private int durability;
	private boolean equipped;
	protected int stability;
	private ArrayList<String> description;

	/**
	 * Describes Item is as Helmet
	 */
	public static final int ITEMTYPE_HEAD = 0;

	/**
	 * Describes Item as Chest Armor
	 */
	public static final int ITEMTYPE_BODY = 1;

	/**
	 * Describes Item as a Weapon (Sword)
	 */
	public static final int ITEMTYPE_SWORD = 2;

	/**
	 * Creates a new Item Object with given name and Goldvalue
	 *
	 * @param name Name of Item
	 * @param goldValue Goldvalue of Item
	 * @param type Sword or Helmet Item
	 * @param bonusDamage Additional Damage by Item
	 * @param bonusHealth Additional Health by Item
	 */
	public Item(String name, int goldValue, int itemType, int bonusDamage, int bonusHealth, int stability) {
		// Name einfuegen
		this.name = name;
		// Platzhalter fuer den Fall der Faelle
		this.goldValue = goldValue;
		// Was fuer eine Art Item ist das?
		this.itemType = itemType;
		// Setze die Modifikatoren
		this.modificators = new int[2];
		this.modificators[0] = bonusDamage;
		this.modificators[1] = bonusHealth;
		equipped = false;
		this.stability= stability ; 
	}
	
	/**
	 * Creates a new Item Object with given Parameters. Works like Item(name, goldValue, itemType, bonusDamag, bonusHealth),
	 * but also sets a descriptive text.
	 *
	 * @param name Name of Item
	 * @param goldValue Goldvalue of Item
	 * @param type Weapon or Armor
	 * @param bonusDamage Additional Damage by Item
	 * @param bonusHealth Additional Health by Item
	 * @param description Descriptive Text, given by Lore
	 */
	public Item(String name, int goldValue, int itemType, int bonusDamage, int bonusHealth, String description, int stability) {
		this(name, goldValue, itemType, bonusDamage, bonusHealth,stability);
		this.description = new ArrayList<String>();
		this.description.add(description);
	}

	/**
	 * Creates a new Item Object with given Parameters. Works like Item(name, goldValue, itemType, bonusDamag, bonusHealth),
	 * but also sets a descriptive text.
	 *
	 * @param name Name of Item
	 * @param goldValue Goldvalue of Item
	 * @param type Weapon or Armor
	 * @param bonusDamage Additional Damage by Item
	 * @param bonusHealth Additional Health by Item
	 * @param description Descriptive Text, given by Lore. Allows to add multiple Lines of Text.
	 */
	public Item(String name, int goldValue, int itemType, int bonusDamage, int bonusHealth, ArrayList<String> description, int stability) {
		this(name, goldValue, itemType, bonusDamage, bonusHealth, stability);
		this.description = description;
	}

	/**
	 * Creates a new Item Object with given name and Goldvalue. Additionally sets equipped Value.
	 * This method should only get called for initial Items in inventory, all other Items should use
	 * Item(name, goldValue, type, bonusDamage, bonusHealth,[description])!
	 *
	 * @param name Name of Item
	 * @param goldValue Goldvalue of Item
	 * @param type Sword or Helmet Item
	 * @param bonusDamage Additional Damage by Item
	 * @param bonusHealth Addiotional Health by Item
	 * @param equipped Equipped Value
	 */

	public Item(String name, int goldValue, int type, int bonusDamage, int bonusHealth, boolean equipped, int stability) {
		// Call other Constructor
		this(name, goldValue, type, bonusDamage, bonusHealth,stability);
		// Set equipped
		this.equipped = equipped;
	}

	/**
	 * Returns Goldvalue of this item.
	 *
	 * @return Goldvalue of this Item
	 */
	public int getGoldValue() {
		return goldValue;
	}

	/**
	 * Returns Name of Item
	 *
	 * @return Name of Item
	 */
	public String getName() {
		return name;
	}


	public int getItemType() {
		return itemType;
	}

	/**
	 * Returns Damagebonus of this Item
	 * 
	 * @return Damagebonus of this Item
	 */
	public int getDamageBonus() {
		return modificators[0];
	}

	/**
	 * Returns Healthbonus of this Item
	 *
	 * @return Healthbonus of this Item
	 */
	public int getHealthBonus() {
		return modificators[1];
	}

	/**
	 * Prints Information about given Item on Screen.
	 * 
	 * @param term currently used terminal
	 * @param inventory currently used inventory
	 */
	
	public void showItem(Terminal term, Inventory inventory) {
		Item item = this;
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(item.getName());
		lines.add("Bonus Schaden: "+item.getDamageBonus());
		lines.add("Bonus Gesundheitspunkte: "+item.getHealthBonus()); // TODO Bessere Uebersetzung findel
		// Add some descriptive Text for this Item
		if (item.description != null) {
			lines.add("");
			lines.addAll(description);
			lines.add("");
		}
		String result = new String();
		if (equipped) {
			lines.add("Derzeit angelegt.");
		} else {
			lines.add("Derzeit nicht angelegt");
		}
		char key = ' ';
		Screen.printBlock(lines, Screen.lastTerminal, Screen.lastWorld);
		try{
			key = term.getKey();
		} catch (InterruptedException e) {
			System.out.println("!Error: ");
			e.printStackTrace();
		}
		switch (key) {
			case 'q':
				// Leave this screen (go back to inventory) and the inventory untouched.
				break;
			case 's':
				if (!equipped) {
					// Put this Item on.
					inventory.equip(this);
				} else {
					// Cannot put this item down without substitute right now.
				}
				break;
			case 'd':
				// Destroy item, when it is not worn
				if (!equipped) {
					ArrayList<String> warningScreen = new ArrayList<String>();
					warningScreen.add("============================");
					warningScreen.add("=WARNUNG!=WARNUNG!=WARNUNG!=");
					warningScreen.add("============================");
					warningScreen.add("Sicher das "+this.getName()+" zerstoert werden soll?");
					warningScreen.add("<J>a");
					warningScreen.add("<N>ein");
					Screen.printBlock(warningScreen, Screen.lastTerminal, Screen.lastWorld);
					try {
						char selection = term.getKey();
						if (selection == 'j') {
							inventory.removeItem(this);
						}
					} catch (InterruptedException e) {
						System.out.println("!Error: InterruptedException");
						e.printStackTrace();
					}
				}
				break;
			case 'o':
				HelpScreen.printItemHelpScreen();
				break;
		}
	}

	public void setEquipped(boolean equipped) {
		this.equipped = equipped;
	}
	public void decreaseStability(){
		stability--;
		
	}
}
