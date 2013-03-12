/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rogue.creature;

import jade.ui.Terminal;
import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;
import java.util.Arrays;

/**
 *
 * A Troll is a stupid Monster. He doesnt move at al, but ist very strong, and attcs you, when you are near him
 */
public class Troll extends Monster{

    public Troll( Terminal term) {
        super(ColoredChar.create('T'),"Troll",50, 10, term);
    }


    @Override

    /*
     * A troll just checks, whether he can hit the player or not
     */
    public void act() {
        for (Direction dir : Arrays.asList(Direction.values())) {
            Player player = world().getActorAt(Player.class, x() + dir.dx(), y() + dir.dy());
            if (player != null) {
                fight(player);
                break;

            }

        }


    }
}
