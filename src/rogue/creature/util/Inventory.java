/*
 * Inventory.java
 * Copyright (C) 2013 dirk <dirk@Valinor.local>
 *
 * Distributed under terms of the MIT license.
 */
import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> inventorySpaces;
	private int maximumItems;
	private int gold;

	/**
	 * 
	 */
	public Inventory(int maximumItems, int gold) {
		inventorySpaces = new ArrayList<Item>(spaces);
		this.gold = gold;
		this.maximumItems = maximumItems;
	}
	
	/**
	 *
	 */
	public boolean addItem(Item item) {
		if (inventorySpaces.size() < maximumItems) {
			inventorySpaces.addItem(item);
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 */
	public void removeItem(Item item) {
		inventorySpaces.removeItem(item);
	}

	/**
	 *
	 */
	public void removeItem(int index) {
		inventorySpaces.remove(index);
	}
}


