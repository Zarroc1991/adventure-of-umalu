package rogue;

import jade.core.World;
import jade.ui.TiledTermPanel;
import jade.util.datatype.ColoredChar;
import java.awt.Color;
import rogue.creature.Monster;
import rogue.creature.Player;
import rogue.system.SplashScreen;
import rogue.level.Level;

public class Rogue {
	public static void main(String[] args) throws InterruptedException {
		// Get current Operating System
		String path;
		if (System.getProperty("os.name").equalsIgnoreCase("windows xp") ||
			System.getProperty("os.name").equalsIgnoreCase("windows Vista") ||
			System.getProperty("os.name").equalsIgnoreCase("windows 7") ||
			System.getProperty("os.name").equalsIgnoreCase("windows 8")	) {
			System.out.println("Windows Operating System found");
			// We're running Windows, create an absolute Path
			path = System.getProperty("user.dir").concat("\\src\\rogue\\system\\start.txt");
		} else {
			// Should work okay with relative Paths
			path = new String("rogue/system/start.txtBlubb");
		}
        	TiledTermPanel term = TiledTermPanel.getFramedTerminal("Jade Rogue");
        	term.registerTile("dungeon.png", 5, 59, ColoredChar.create('#'));
        	term.registerTile("dungeon.png", 3, 60, ColoredChar.create('.'));
        	term.registerTile("dungeon.png", 5, 20, ColoredChar.create('@'));
        	term.registerTile("dungeon.png", 14, 30, ColoredChar.create('D', Color.red));

        Player player = new Player(term);
		World world = new SplashScreen(path, term);
		term.clearBuffer();
		// Draw Splashscreen
		for (int x = 0; x < world.width(); x++)
			for (int y = 0; y < world.height(); y++)
				term.bufferChar(x + 11, y, world.look(x, y));
		term.bufferCameras();
		term.refreshScreen();
		term.getKey();

		world.tick();
		// Finish World
        //Player player = new Player(term);
        world = new Level(69, 24, player);
        world.addActor(new Monster(ColoredChar.create('D', Color.red)));
        term.registerCamera(player, 5, 5);
        
        
        while(!player.expired())
        {
            term.clearBuffer();
            for(int x = 0; x < world.width(); x++)
                for(int y = 0; y < world.height(); y++)
                    term.bufferChar(x + 11, y, world.look(x, y));
            term.bufferCameras();
            term.refreshScreen();

            world.tick();
        }

	// Quit Application
        System.exit(0);
    }

}
