package rogue.creature;

import java.util.Arrays;
import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;
import java.util.Random;
import rogue.level.Screen;
import jade.ui.Terminal;
import java.lang.InterruptedException;

public abstract class Monster extends Creature {

    protected String name;
    private int maxHitpoints;
    protected Terminal term;
    // Typenumber is used to recognize, wich Sort of Monster we have
    protected  int typenumber;

    public Monster(ColoredChar face) {
        super(face);
        strength = 5;
        maxHitpoints = 10;
        hitpoints = maxHitpoints;
    }

    public Monster(ColoredChar face, String name,int maxHitpoints, int strength, Terminal term) {
        super(face);
        this.strength = strength;
        this.maxHitpoints= maxHitpoints;
        hitpoints = maxHitpoints;
        this.name = name;
	this.term = term;
    }

    public String name() {
        return name;
    }


     /* fight of the Moster aganst the Player
     * causes random damage between 1 and 5
     */
    // TODO Clean up Messages in Console, to use just a single line
    abstract public void fight(Player opponent); /*{
        System.out.println("der " + name + "greift dich an");
	// Create Randomizer
        Random random = new Random();
	// Generate Damage
        int abzug = random.nextInt(strength)+1;
	// Do Damage to Oppenent
        opponent.loseHitpoints(abzug);
	// Print Result
        System.out.println("Du hast "+ abzug + " HP verloren");
        System.out.println("verbleibende HP: "+ opponent.hitpoints);
	Screen.redrawEventLine(name+" macht "+abzug+" Schaden (Rest: "+opponent.hitpoints+")");
	try {
		term.getKey();
	} catch(InterruptedException e) {
		System.out.println("!InterruptedException");
		e.printStackTrace();
	}
    }*/
} 
