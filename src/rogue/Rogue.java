package rogue;

import jade.core.Actor;
import jade.core.World;
import jade.gen.map.Cellular;
import jade.gen.map.World1;
import jade.path.AStar;
import jade.ui.TiledTermPanel;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Calendar;
import java.util.logging.Logger;
import javax.swing.JApplet;

import rogue.creature.Dragon;

import rogue.creature.InvisibleZombie;
import rogue.creature.Dummy;

import rogue.creature.Monster;
import rogue.creature.Orc;
import rogue.creature.Player;
import rogue.creature.Shadow;
import rogue.creature.Troll;
import rogue.level.Level;
import rogue.level.Screen;
import rogue.system.Path;
import rogue.system.CharacterCreation;
import rogue.system.SystemHelper;

public class Rogue extends JApplet implements KeyEventDispatcher {
	int level ;
	int levelanzahl;
	// How many rounds for next healing+
	final int hpCycle = 10;
	ArrayList<Integer> levelorder;
	int roundsToHpUp;
	
	public static void main(String[] args) throws InterruptedException {

   		int level = 0; 
		int stepSum = 0;
		int stepLevel = 0;
		// Set System options
   		int levelanzahl = 5;
   		// How many rounds for next healing+
   		final int hpCycle=10; 
   		int roundsToHpUp = hpCycle;
   		// Set System options
		Screen.initialiseScreen();
		SystemHelper.getArgs(args);
		TiledTermPanel term = TiledTermPanel.getFramedTerminal("Jade Rogue");

		// Nobody knows right now, what happens here
		/*
		 * term.registerTile("dungeon.png", 5, 59, ColoredChar.create('#'));
		 * term.registerTile("dungeon.png", 3, 60, ColoredChar.create('.'));
		 * term.registerTile("dungeon.png", 5, 20, ColoredChar.create('@'));
		 * term.registerTile("dungeon.png", 14, 30, ColoredChar.create('D',
		 * Color.red));
		 */

		// Create a new Player
		Player player = new Player(term);
		// erstellt zufällige Levelreihenfolge
		ArrayList<Integer> levelorder = term.levelorder(levelanzahl);
		levelorder.add(5);
		
		// Generate a new World
		World world = new Level(80, 32, player, levelorder.get(level), level,true,
				term);
		// Show Splashscreen for Start
		Screen.showFile(Path.generateAbsolutePath("maps/start.txt"), term,
				world);
		term.getKey();
		player.setName(CharacterCreation.getCharacterName(term, world));
		if (SystemHelper.debug) {
			Screen.printLine(player.getName(),term,world);
		}
		term.getKey();
		
		//Screen.showFile(Path.generateAbsolutePath("maps/start.txt"),term,world);
		//Zeigt Intro 
		if (!SystemHelper.debug) {
			Screen.intro(player.getName(), Path.generateAbsolutePath("txt Dateien/Intro.txt"),term,world);
		}
		// Press any Key to continue
		term.getKey();

		Calendar cal = Calendar.getInstance();
		long startTime = cal.getTimeInMillis();
		// Who deleted this, and why?
		// world.addActor(new Monster(ColoredChar.create('D',
		// Color.red),"roter Drache"));

		// Add Minimap to left part in Window (Size given as Parameter), focus
		// on Player
		// Add Minimap to left part in Window (Size given as Parameter), focus
		// on Player

		// term.registerCamera(player, 5, 5);

		// Add Minimap to left part in Window (Size given as Parameter), focus
		// on Player

		// Play Game
		// world.tick();

		while (!player.expired()) { // Player is still living?
			if (player.worldchangeup) { // Überprüft, ob einen Levelup erfolgt
										// ist
				world.removeActor(player); // entfernt Spieler aus der alten
											// Welt
				world = new Level(80, 32, player, levelorder.get(++level),
						level,true, term); // lädt das nächste Level
				player.setWorld(world); // Spieler erkennt seine Welt
				player.worldchangeup = false;
			} else if (player.worldchangedown) {
				world.removeActor(player); // entfernt Spieler aus der alten
											// Welt
				world = new Level(80, 32, player, levelorder.get(--level),
						level,false, term); // lädt das nächste Level
				player.setWorld(world); // Spieler erkennt seine Welt
				player.worldchangedown = false;
			}

			// ? TODO Delete this Block if it is not needed anymore
			/*
			 * Collection<Monster> monsters = world.getActorsAt(Monster.class,
			 * player.pos()); if(!monsters.isEmpty()){ player.expire();
			 * continue; }
			 */
			term.registerCamera(player, player.x(), player.y() + 1); // Kamera
																		// verfolgt
																		// den
																		// Spieler
			// TODO HPup Codeblock should move to Player.act(), since it is only
			// his stuff
			// Finished hpCycle?
			if (roundsToHpUp == 0) { // Yes
				// Give Player a hitpoint
				player.regainHitpoint();
				// Reset Counter
				roundsToHpUp = hpCycle;
			}
			// Decrement hpCycle Counter
			roundsToHpUp--;

			// Generate a List of Monsters still on Map

			Collection<Monster> monsters = world.getActorsAt(Monster.class,
					player.pos());
			// Has every Monster been killed?
			if (!monsters.isEmpty()) { // Yes
				// Stop Game
				player.expire();
				continue;
			}

			Screen.lastWorld = world;
			Screen.lastTerminal = term;
			if (!SystemHelper.speedrun) {
				Screen.redrawMap("HP: "+player.getHitpoints()+"/"+player.getMaxHitpoints());
			} else {
				Calendar calen = Calendar.getInstance();
				long timeInSeconds = (calen.getTimeInMillis()-startTime)/1000;
				if (timeInSeconds < 60) {
				Screen.redrawMap("Steps: "+stepSum+" in "+timeInSeconds+"s");
				} else {
					Screen.redrawMap("Steps: "+stepSum+" in "+timeInSeconds/60+"min "+timeInSeconds%60+"s");
				}
			}

			// TODO Delete this Block if noone needs it anymore.
			// Redraw Windowcontents now
			/*
			 * term.clearBuffer(); for(int x = 0; x < world.width(); x++)
			 * for(int y = 0; y < world.height(); y++) term.bufferChar(x + 11,
			 * y, world.look(x, y)); term.bufferCameras(); term.refreshScreen();
			 */

			// Screen
			// Give everyone else the chance to make his move
			// world.tick();
			world.tick();
			stepSum++;
		}
		term.clearBuffer();
		// Screen.showFile(normalizePath("src\\rogue\\system\\end.txt","rogue/system/end.txt"),
		// term, world);
		Screen.showFile(Path.generatePath("maps/end.txt"), term, world);

		term.getKey();
		System.exit(0);

	}

	Player player;
	World world;
	TiledTermPanel term;
	Thread t;

	@Override
	public void init() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
		level=0;
		levelanzahl = 5;
		
		Screen.initialiseScreen();
		term = new TiledTermPanel();
		getContentPane().add(term.panel());
		player = new Player(term);
		levelorder = term.levelorder(levelanzahl);
		world = new Level(80, 32, player, levelorder.get(level),level,true,term);
		System.out.println("applet initialized");
	}

	@Override
	public void start() { 
	    roundsToHpUp = hpCycle;
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				Screen.showFile(Path.generateAbsolutePath("maps/start.txt"), term,world);
				try {
					term.getKey();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				player.setName(CharacterCreation.getCharacterName(term, world));
				Screen.printLine(player.getName(), term, world);
				try {
					term.getKey();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Screen.intro(player.getName(),Path.generateAbsolutePath("txt Dateien/Intro.txt"), term, world);
				try {
					term.getKey();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				while (!player.expired()) { // Player is still living?
					if (player.worldchangeup) { // Überprüft, ob einen Levelup erfolgt
												// ist
						world.removeActor(player); // entfernt Spieler aus der alten
													// Welt
						world = new Level(80, 32, player, levelorder.get(++level),
								level,true, term); // lädt das nächste Level
						player.setWorld(world); // Spieler erkennt seine Welt
						player.worldchangeup = false;
					} else if (player.worldchangedown) {
						world.removeActor(player); // entfernt Spieler aus der alten
													// Welt
						world = new Level(80, 32, player, levelorder.get(--level),
								level,false, term); // lädt das nächste Level
						player.setWorld(world); // Spieler erkennt seine Welt
						player.worldchangedown = false;
					}

					term.registerCamera(player, player.x(), player.y() + 1); // Kamera
																				// verfolgt
																				// den
																				// Spieler
					// TODO HPup Codeblock should move to Player.act(), since it is only
					// his stuff
					// Finished hpCycle?
					if (roundsToHpUp == 0) { // Yes
						// Give Player a hitpoint
						player.regainHitpoint();
						// Reset Counter
						roundsToHpUp = hpCycle;
					}
					// Decrement hpCycle Counter
					roundsToHpUp--;

					// Generate a List of Monsters still on Map

					Collection<Monster> monsters = world.getActorsAt(Monster.class,
							player.pos());
					// Has every Monster been killed?
					if (!monsters.isEmpty()) { // Yes
						// Stop Game
						player.expire();
						continue;
					}

					Screen.lastWorld = world;
					Screen.lastTerminal = term;
					Screen.redrawMap("HP: " + player.getHitpoints() + "/"
							+ player.getMaxHitpoints());

					

					// Screen
					// Give everyone else the chance to make his move
					// world.tick();
					world.tick();
			}
				term.clearBuffer();
				// Screen.showFile(normalizePath("src\\rogue\\system\\end.txt","rogue/system/end.txt"),
				// term, world);
				Screen.showFile(Path.generatePath("maps/end.txt"), term, world);

				try {
					term.getKey();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
		}});
		t.start();
       		System.out.println("applet started");
	}
	@Override
	public void stop() {
		System.out.println("applet stopped");
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_TYPED)
			term.keyPressed(e);
		return true;
	}
}
