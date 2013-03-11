/*
 * Inventory.java
 * Copyright (C) 2013 dirk <dirk@Valinor.local>
 *
 * Distributed under terms of the MIT license.
 */
package rogue.creature.util;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> inventorySpaces;
	private int maximumItems;
	private int gold;

	/**
	 * Creates a new Inventory Object.
	 *
	 * @param maximumItems Amount of Items, a character can carry
	 * @param gold Amount of Gold character initially starts with
	 */
	public Inventory(int maximumItems, int gold) {
		inventorySpaces = new ArrayList<Item>(maximumItems);
		this.gold = gold;
		this.maximumItems = maximumItems;
	}
	
	/**
	 * Adds an Item if Inventory is not full.
	 * 
	 * @param item New Item in Inventory
	 * @return Returns true, if adding Item is successfull, otherwise false (e.g. Inventory is full)
	 * @throws NotEnoughSpaceException Players inventory is already full
	 */
	public boolean addItem(Item item) throws NotEnoughSpaceException {
		if (inventorySpaces.size() < maximumItems) {
			inventorySpaces.add(item);
			return true;
		} else {
			throw new NotEnoughSpaceException();
			//return false;
		}
	}

	/**
	 * Deletes an item from Inventory
	 *
	 * @param item Item to be deleted from Inventory
	 */
	public void removeItem(Item item) {
		inventorySpaces.remove(item);
	}

	/**
	 * Deletes an item from Inventory at given index
	 *
	 * @param index Index from which item should be deleted
	 */
	public void removeItem(int index) {
		inventorySpaces.remove(index);
	}

	/**
	 * Sells Item at Index (adding GoldValue to amount of Gold to players inventory)
	 *
	 * @param index Index of Item to be sold
	 */
	public void sellItem(int index) {
		// Riemove Item at index
		Item soldItem = inventorySpaces.remove(index);
		this.increaseGold(soldItem.getValue());
	}

	/**
	 * Adds an Item to inventory, if he has enough gold and at least on free inventory space left
	 *
	 * @param item Item to be bought
	 * @throws NotEnoughSpaceException Inventory has no free Spaces left
	 * @throws NotEnoughGoldException Player has not collected Gold for this Action
	 */
	public void buyItem(Item item) throws NotEnoughSpaceException, NotEnoughGoldException {
		if ((inventorySpaces.size()+1 <= maximumItems) && (item.getValue() <= gold)) {
			decreaseGold(item.getValue());
			this.addItem(item);
		} else if (inventorySpaces.size()+1 > maximumItems) {
			// User cannot carry more Items. Throw an Exception
			throw new NotEnoughSpaceException();
		} else {
			throw new NotEnoughGoldException();
		}
	}

	/**
	 * Adds amount to Players gold stash
	 *
	 * @param amount Amount of Gold to add to players gold stash
	 */
	public void increaseGold(int amount) {
		gold += amount;
	}

	/**
	 * Takes away amount of Players gold
	 *
	 * @param amount Amount of Gold, Player looses
	 * @return true if amount could be decreased, false if an error occured (e.g. not enough gold)
	 * @throws NotEnoughGoldException User has not enough Gold for this Operation
	 */
	public boolean decreaseGold(int amount) throws NotEnoughGoldException {
		if (amount > gold) {
			gold -= amount;
			return true;
		} else {
			throw new NotEnoughGoldException();
		}
	}

	public ArrayList<Item> listInventory() {
		return inventorySpaces;
	}
}
