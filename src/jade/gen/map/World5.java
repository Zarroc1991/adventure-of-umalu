package jade.gen.map;

import rogue.system.Path;
import jade.core.World;
import jade.util.Dice;


// Beispiel Map World1 
public class World5 extends NewWorld {
	private int level; 
	public World5(int level){
		super();
		this.level = level;
	}
	@Override
	//ruft generateStep aus NewWorld auf
	protected void generateStep(World world, Dice dice) {
		//generateStep(world,dice,Path.generateAbsolutePath("maps/Welt5.txt"));
		generateStep(world,dice,Path.generateAbsolutePath("maps/Welt4.txt"),level );
	}

	
	

}
