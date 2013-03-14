/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rogue.creature;

import java.util.Arrays;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;
import jade.ui.Terminal;
import jade.util.Dice;
import jade.util.datatype.Direction;
import java.util.Arrays;

/**
 * TODO Delete this Class, when it is not used anymore, as instances of Montster do the same thing right now
 * @author alle
 */
public class Dragon extends Monster {
   /*
    * every Dragon has 100 Hitpoints and Strength 5(until now)
    */
    public Dragon(ColoredChar face, String name, Terminal term) {
        super(face,name, 100, 5, term);
        this.typenumber=0;
    }

    @Override
    public void act() {
		boolean fight = false;

		for (Direction dir : Arrays.asList(Direction.values())) {
			Player player = world().getActorAt(Player.class, x() + dir.dx(),
					y() + dir.dy());
			if (player != null) {
				fight(player);

				fight = true;
				break;
			}
		}

		if (!fight) {
			move(Dice.global.choose(Arrays.asList(Direction.values())));
		}
	}

}

