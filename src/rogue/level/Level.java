package rogue.level;

import java.awt.Color;

import jade.core.World;
import jade.gen.Generator;
import jade.gen.map.*;
import jade.ui.TiledTermPanel;
import jade.util.datatype.ColoredChar;
import rogue.creature.Dragon;
import rogue.creature.Dummy;
import rogue.creature.Player;
public class Level extends World
{
	private static Generator gen = getLevelGenerator();

	public Level(int width, int height, Player player) {
		// Create a new Map, but make it 2 Rows less higher than window is, so we have some Space for
		// Statusmessages and stuff.
		super(width, height-2);
		gen.generate(this);
		addActor(player);
	}
	// Zweiter Konstruktor, um ein neues Level aufzurufen 
	public Level(int width, int height, Player player, int level, TiledTermPanel term) {
		super(width, height-2);
		switch (level){											//Ordnet jedem Level eine Map zu  
		case 0:{
			gen = new World1();
			break;	
		}
		case 1:{
			gen = new World2();
			addActor(new Dummy(ColoredChar.create('X',Color.blue),"Dummy",term)); //Ordnet jedem Level Monster hinzu 
			break; 
		}
		}
		gen.generate(this);
		addActor(player);
	}

	private static Generator getLevelGenerator() {
		return new World1();
	}
}
