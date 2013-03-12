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
 * An Orc ist a weak Monster
 * he moves randomly like the Dragon and hit the Player if he can
 */
public class Orc extends Monster {

    public Orc(Terminal term) {
        super(ColoredChar.create('O'), "Orc", 10, 3, term);
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


