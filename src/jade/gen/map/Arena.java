package jade.gen.map;

import rogue.system.Path;
import jade.core.World;
import jade.util.Dice;


// Beispiel Map World1 
public class Arena extends NewWorld {

	@Override
	//ruft generateStep aus NewWorld auf
	protected void generateStep(World world, Dice dice) {
		generateStep(world,dice,Path.generateAbsolutePath("maps/Arena.txt"),5, true);
	}
}
