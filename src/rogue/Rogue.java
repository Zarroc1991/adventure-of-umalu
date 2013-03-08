package rogue;

import jade.core.Actor;
import jade.core.World;
import jade.core.World.Tile;
import jade.ui.TiledTermPanel;
import jade.util.datatype.ColoredChar;
import java.awt.Color;
import java.util.Collection;
import rogue.creature.Dragon;
import rogue.creature.Monster;
import rogue.creature.Player;
import rogue.level.Level;
import rogue.level.Screen;

public class Rogue
{

    public static void main(String[] args) throws InterruptedException
    {
        final int hpCycle=10;
        int roundsToHpUp =hpCycle;
        TiledTermPanel term = TiledTermPanel.getFramedTerminal("Jade Rogue");
        term.registerTile("dungeon.png", 5, 59, ColoredChar.create('#'));
        term.registerTile("dungeon.png", 3, 60, ColoredChar.create('.'));
        term.registerTile("dungeon.png", 5, 20, ColoredChar.create('@'));
        term.registerTile("dungeon.png", 14, 30, ColoredChar.create('D', Color.red));

        Player player = new Player(term);
        World world = new Level(69, 24, player);
        String StartscreenPath = "C:/Users/alle/Desktop/Text1.txt";
        String EndscreenPath = "C:/Users/alle/Desktop/Endscreen2.txt";
        Screen.ShowFile(StartscreenPath, term, world);
        term.getKey();
        world.addActor(new Dragon(ColoredChar.create('D', Color.red),"roter Drache"));
        term.registerCamera(player, 5, 5);

        // hallo

        while(!player.expired())
        {
            /*Collection<Monster> monsters = world.getActorsAt(Monster.class, player.pos());
            if(!monsters.isEmpty()){
                player.expire();
                continue;
            }*/
            if(roundsToHpUp==0){
                player.regainHitpoint();//the player gains 1Hp after (HpCycle) rounds
                roundsToHpUp = hpCycle; 
            }
            roundsToHpUp--;
            term.clearBuffer();
            for(int x = 0; x < world.width(); x++)
                for(int y = 0; y < world.height(); y++)
                    term.bufferChar(x + 11, y, world.look(x, y));
            term.bufferCameras();
            term.refreshScreen();

            world.tick();
            
            System.out.println();
        }
        term.clearBuffer();
        Screen.ShowFile(EndscreenPath, term, world);
        term.getKey();
        System.exit(0);

    }
}
