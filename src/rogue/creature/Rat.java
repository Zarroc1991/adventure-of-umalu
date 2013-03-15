



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rogue.creature;

import jade.fov.RayCaster;
import jade.path.*;
import jade.ui.Terminal;
import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;
import jade.util.datatype.Direction;
import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import rogue.level.Screen;

/**
 *
 * @author alle
=======

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import rogue.level.Screen;

/**
 *
 * An Orc ist a weak Monster
 * he moves randomly like the Dragon and hit the Player if he can
>>>>>>> upstream/master
 */
public class Rat extends Monster {

    PathFinder pathfinder = new AStar();
    RayCaster fov;
    int attackRadius;

    public Rat(Terminal term) {    

        super(ColoredChar.create('R', new Color(110,110,110)), "Ratte", 5, 1, term);
        fov = new RayCaster();
        attackRadius = 3;
        this.typenumber = 0;

    }

    @Override
    public void act() {

        for (Direction dir : Arrays.asList(Direction.values())) {
            Player player = world().getActorAt(Player.class, x() + dir.dx(), y() + dir.dy());
            if (player != null) {
                fight(player);
                return;
            }
        }

        Collection<Coordinate> viewField = fov.getViewField(this.world(), this.pos().x(), this.pos().y(), attackRadius);
        System.out.println(viewField.size());
        for (Coordinate coordinate : viewField) {
            if (this.world().getActorAt(Player.class, coordinate) != null) {
                Direction dir = this.pos().directionTo(pathfinder.getPath(this.world(), this.pos(), coordinate).get(0));
                move(dir);
                return;
            }
        }




        move(Dice.global.choose(Arrays.asList(Direction.values())));
    }



	@Override
	public void fight(Player opponent) {
		// TODO Auto-generated method stub {
	        System.out.println("Die " + name() + " greift dich an");
		// Create Randomizer
	        Random random = new Random();
		// Generate Damage
	        int abzug = random.nextInt(strength)+1;
		// Do Damage to Oppenent
	        opponent.loseHitpoints(abzug);
		// Print Result
            Random generator = new Random();
            int ran = generator.nextInt( 4 );
            switch(ran){
            	case 0:System.out.println("Ihre Z�hne stecken in deinem Hintern.");break;
            	case 1:System.out.println("Sie bei�t in deinen gro�en Zeh");break;
            	case 2:System.out.println("Tollwutalarm");break;
            	case 3:System.out.println("Sie quickt so laut.");break;
	        }
	        System.out.println("Du hast "+ abzug + " HP verloren");
	        System.out.println("verbleibende HP: "+ opponent.hitpoints);
		Screen.redrawEventLine(name()+" macht "+abzug+" Schaden (Rest: "+opponent.hitpoints+")");
		try {
			term.getKey();
		} catch(InterruptedException e) {
			System.out.println("!InterruptedException");
			e.printStackTrace();
		}
	    }

	}



