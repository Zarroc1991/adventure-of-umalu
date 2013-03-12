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

	/**
	 * Describes Item is as Helmet
	 */
	public static final int ITEMTYPE_HEAD = 0;
	/**
	 * Describes Item as Chest Armor
	 */
	public static final int ITEMTYPE_BODY = 2;
	/**
	 * Describes Item as a Weapon (Sword)
	 */
	public static final int ITEMTYPE_SWORD = 1;

	/**
	 * Creates a new Item Object wth given name and Goldvalue
	 *
	 * @param name Name of Item
	 * @param goldValue Goldvalue of Object
	 */
	public Item(String name, int goldValue, int type, int bonusDamage, int bonusHealth) {
		this.name = name;
		this.goldValue = goldValue;
		this.type = type;
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

	public int getDamageBonus() {
		return modificators[0];
	}

	public int getHealthBonus() {
		return modificators[1];
	}
}


