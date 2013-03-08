/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rogue.level;

import jade.core.World;
import jade.ui.TiledTermPanel;
import jade.util.datatype.ColoredChar;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alle
 */
public class Screen {
   public static void ShowFile(String filePath, TiledTermPanel term, World world){
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            //int lineNumber = MAXHEIGHT;
            String line = br.readLine();
            int lineNumber = 0;
            while(line!=null){
                int i;
                for (i=0; i < line.length(); i++) {
                    // Put character on Screen
                    
                    char c = line.charAt(i);
                    //System.out.println(c);
                    term.bufferChar(i,lineNumber,ColoredChar.create(c));
                }
                while(i<world.width()){
                    term.bufferChar(i, lineNumber,ColoredChar.create(' '));
                    i++;
                }
                line= br.readLine();
                lineNumber++;
            }
            
            while(lineNumber<world.height()){
                for (int i = 0; i < world.width(); i++) {
                    term.bufferChar(i, lineNumber,ColoredChar.create(' '));

                }
                lineNumber++;
            }
            term.refreshScreen();
            
        } catch (IOException ex) {
            System.out.println("IoEx");
            System.exit(1);
        }// catch (FileNotFoundException ex) {
          //  System.out.println("file not found");
          //  System.exit(1);
        //}
   }

}
