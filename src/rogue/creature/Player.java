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


public class Player extends Creature implements Camera
{
    private Terminal term;
    private ViewField fov;

    public Player(Terminal term)
    {
        super(ColoredChar.create('@'));
        this.term = term;
        fov = new RayCaster();
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
                        }
                        move(dir);
                        System.out.println("Spielerx"+x());
                    	System.out.println("Spielery"+y());
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

    public void fight(Monster opponent) {
      
        System.out.println("Du kämpfst gegen " + opponent.name());


    }
}
