/*
 * Item.java
 * Copyright (C) 2013 dirk <dirk@Valinor.local>
 *
 * Distributed under terms of the MIT license.
 */
package rogue.creature.util;

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
	 * Creates a new Item Object wth given name and Goldvalue
	 *
	 * @param name Name of Item
	 * @param goldValue Goldvalue of Object
	 */
	public Item(String name, int goldValue, int type, int bonusDamage, int bonusHealth) {
		// Name einfuegen
		this.name = name;
		// Platzhalter fuer den Fall der Faelle
		this.goldValue = goldValue;
		// Was fuer eine Art Item ist das?
		this.type = type;
		// Setze die Modifikatoren
		this.modificators = new int[2];
		this.modificators[0] = bonusDamage;
		this.modificators[1] = bonusHealth;
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
	 * @param index Index of Item in Backpack
	 */
	public void showItem(int index, Terminal term) {
		Item item = backpackSpaces[index]
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(item.getName());
		lines.add("Bonus Damage: "+item.getDamageBonus());
		lines.add("Bonus Health: "+item.getHealthBonus());
		String result = new String();
		if () {
			
		}
		char key;
		try{
			key = term.getKey();
		} catch (InterruptedException e) {
			System.out.println("!Error: ");
			e.printStackTrace();
		}
		switch (key) {
			case 'q':
		}
	}
}


