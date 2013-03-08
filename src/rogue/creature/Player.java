package rogue.creature;

import jade.core.Actor;
import java.util.Collection;
import jade.fov.RayCaster;
import jade.fov.ViewField;
import jade.ui.Camera;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;
import jade.util.datatype.Direction;
import java.util.Random;


public class Player extends Creature implements Camera
{
    private Terminal term;
    private ViewField fov;
    private static final int maxHitpoints =15;
    private int strength;

    public Player(Terminal term)
    {
        super(ColoredChar.create('@'));
        this.term = term;
        fov = new RayCaster();
        hitpoints = maxHitpoints;
        strength = 5;
    }

    @Override
    public void act()
    {
        try
        {
            char key;
            key = term.getKey();
            switch(key)
            {
                case 'q':
                    expire();
                    break;
                default:
                    Direction dir = Direction.keyToDir(key);
                    if(dir != null){
                        Collection<Monster> actorlist = world().getActorsAt(Monster.class, x()+dir.dx(), y()+dir.dy());
                        if(!actorlist.isEmpty()){
                            fight((Monster) actorlist.toArray()[0]);
                            break;//I dont want to move, if I fight
                        }
                        move(dir);
                    }break;
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Coordinate> getViewField()
    {
        return fov.getViewField(world(), pos(), 5);
    }

    /*
     * Player fights the opponent. Causes random damage between 1 and strength
     * @param opponent The opponent Monster
     */
    private void fight(Monster opponent) {
        System.out.println("Du kämpfst gegen " + opponent.name());
        Random random = new Random();
        int damage = random.nextInt(strength)+1;
        opponent.loseHitpoints(damage);
        System.out.println("Du hast "+ damage + "Schaden verursacht");
        System.out.println(opponent.name()+" hat noch " + opponent.hitpoints +" HP");
    }

    /*
     * player regains 1 Hitpoint. Method should be used every x moves in rogue
     */
    public void regainHitpoint(){
        if(hitpoints<maxHitpoints){
            hitpoints++;
            System.out.println("Du hast einen HP regeneriert, jetzt " + hitpoints+" HP");
        }
    }
}
