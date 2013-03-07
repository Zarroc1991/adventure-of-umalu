package rogue.creature;

import java.util.Arrays;
import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

public class Monster extends Creature
{
    private String name;
    public Monster(ColoredChar face)
    {
        super(face);
    }
    public Monster(ColoredChar face, String name)
    {
        super(face);
        this.name = name;
    }
    public String name(){
        return name;
    }

    @Override
    public void act()
    {
        move(Dice.global.choose(Arrays.asList(Direction.values())));
    }
}
