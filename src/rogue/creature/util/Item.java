/*
 * Item.java
 * Copyright (C) 2013 dirk <dirk@Valinor.local>
 *
 * Distributed under terms of the MIT license.
 */
package rogue.creature.util;


public class Item {
	String name;
	int value;

	/**
	 *
	 */
	public Item(String name, int value) {
		this.name = name;
		this.value = value;
	}

	/**
	 *
	 */
	public int getValue() {
		return value;
	}

	/**
	 *
	 */
	public String getName() {
		return name;
	}
}


