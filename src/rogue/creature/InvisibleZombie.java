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
 * TODO: th einvisible Zombie should be invisible not just a .
 */
public class InvisibleZombie extends Monster{

    public InvisibleZombie( Terminal term) {
        super(null, "Unsichtbarer Zombie",8 ,2, term);
        this.typenumber =3;
    }

    @Override
    public void act() {
        boolean fight = false;

        for (Direction dir : Arrays.asList(Direction.values())) {
            Player player = world().getActorAt(Player.class, x() + dir.dx(), y() + dir.dy());
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
