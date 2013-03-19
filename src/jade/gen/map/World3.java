package jade.gen.map;

import rogue.system.Path;
import jade.core.World;
import jade.util.Dice;


// Beispiel Map World1 
public class World3 extends NewWorld {
	private int level;
	
	public World3(int level){
		super();
		this.level = level;
		
		 
	}
	@Override
	//ruft generateStep aus NewWorld auf
	protected void generateStep(World world, Dice dice) {
		generateStep(world,dice,Path.generateAbsolutePath("maps/Welt3.txt"),level );
		
	}	

}
