package rogue.level;

import jade.core.World;
import jade.gen.Generator;

import jade.gen.map.*;
import jade.ui.TiledTermPanel;
import rogue.creature.*;
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
		switch (level){											//Liste der Maps in Abh�ngikeit vom Level  
		case 0:{
			gen = new World1();
			break;	
		}
		case 1:{
			gen = new World2();
                                addActor(new Troll(term));
                                addActor(new Orc(term));
                                addActor(new InvisibleZombie(term));
			break; 
		}
                    default:{gen = new World1();break;}
		}
		gen.generate(this);
		addActor(player);
	}

	private static Generator getLevelGenerator() {
		return new World1();
	}
}
