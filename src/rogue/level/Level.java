package rogue.level;

import jade.core.Actor;
import java.awt.Color;

import jade.core.Actor;
import jade.core.World;
import jade.gen.Generator;

import jade.gen.map.*;
import jade.ui.Terminal;
import jade.ui.TiledTermPanel;
import java.util.logging.Logger;

import rogue.creature.*;

import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;
import java.lang.reflect.InvocationTargetException;
import rogue.creature.util.Item;

import rogue.system.SystemHelper;
public class Level extends World {
    private static Generator gen; /* = getLevelGenerator();*/
    public Level(int width, int height, Player player) {
        // Create a new Map, but make it 2 Rows less higher than window is, so we have some Space for
        // Statusmessages and stuff.
        super(width, height - 2);
        gen.generate(this);
        addActor(player);
    }
    // Zweiter Konstruktor, um ein neues Level aufzurufen

    public Level(int width, int height, Player player,int levelorder, int level,boolean aufwaerts, TiledTermPanel term) {
        super(width, height - 2);
        switch (levelorder) {											//Liste der Maps in Abh�ngikeit vom Level
            case 0: {
                gen = new World1(level,aufwaerts);
                break;
            }
            case 1: {
                gen = new World2(level,aufwaerts);
                break;
            }
	    case 2: {
		gen = new World3(level,aufwaerts);
		break;
	    }
	    case 3: {
		gen = new World4(level,aufwaerts);
		break;
	    }
	    case 4: {
		gen = new World5(level,aufwaerts);
		break;
	    }
	    case 5: {
		gen = new Arena();
		break;
	    }
            default: {
                gen = new World1(level,aufwaerts);
                break;
            }
        }
        gen.generate(this);
        //player starts at Playerstart
        this.addActor(player, playerstart);
        //insert Monster. We need new case-separation, because the Monsters should be added
        //after the tile, so they do not land on unpassable tiles
	if (!SystemHelper.speedrun) {
		switch (level) {											//Liste der Maps in Abhaengikeit vom Level  
			case 0: {
					//addActor(new Troll(term,level));
					addActors(Rat.class, term, 15);
					addActors(Slug_fat.class, term, 4);
					addActor(new Frog_poisonous(term));
					addActor(new ItemGenerator(ColoredChar.create('I', Color.yellow), new Item("Testhelm", 0, Item.ITEMTYPE_HEAD, 0, 15), term));
					break;
			}
			case 1: {
					//addActor(new Troll(term,level));
					addActor(new Rat(term));
					addActor(new Slug_fat(term));
					addActor(new Frog_poisonous(term));
					addActor(new Zombie(term));
					break;
			}
			case 2: {
					//addActor(new Troll(term,level));
					addActor(new Rat(term));//kat1
					addActor(new Slug_fat(term));//kat2
					addActor(new Frog_poisonous(term));//kat3
					addActor(new Zombie(term));//kat4
					addActor(new Unbeliever(term));//kat5
					addActor(new Orc(term));//kat6
					addActor(new Shadow(term));//kat7
					break;
			}
			case 3:{
				       break;        
			}
			case 4:{
				       break;        
			}
			case 5:{

				       addActor(new Dragon(term));
				       break;
			}
			default: {
					 break;
			}
		}
	}
    }

    /*private static Generator getLevelGenerator() {
        return new World1();
    }*/
    // Delete following function?
    /*
     * adds {@ anzahl} actors of the class  {@cls}
     */
    public void addActors(Class<? extends Actor> cls,Terminal term, int anzahl){
        try {
            for (int i = 0; i < anzahl; i++) {
                //create a new Actor of the class with the constructor that requires term
                addActor(cls.getConstructor(Terminal.class).newInstance(term));
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex){
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex){
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
