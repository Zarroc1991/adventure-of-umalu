/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rogue.creature;

import jade.util.datatype.ColoredChar;
import jade.ui.Terminal;

/**
 *
 * @author alle
 */
public class Dragon extends Monster {
   /*
    * every Dragon has 100 Hitpoints and Strength 5(until now)
    */
    public Dragon(ColoredChar face, String name, Terminal term) {
        super(face,name, 100, 5, term);
    }

}

