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

	/**
	 * Creates a new Item Object wth given name and Goldvalue
	 *
	 * @param name Name of Item
	 * @param goldValue Goldvalue of Object
	 */
	public Item(String name, int goldValue) {
		this.name = name;
		this.goldValue = goldValue;
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
}


