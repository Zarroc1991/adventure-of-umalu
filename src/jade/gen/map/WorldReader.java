/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jade.gen.map;

import jade.core.World;
import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import rogue.level.Screen;
import rogue.system.Path;

/**
 *
 * @author alle
 */
public class WorldReader extends MapGenerator{


    @Override
    protected void generateStep(World world, Dice dice) {

		try {
			// Prepare File for reading (open it)
			FileReader fr = new FileReader(Path.generateAbsolutePath("rogue/system/world.txt"));
			BufferedReader br = new BufferedReader(fr);
			//int lineNumber = MAXHEIGHT; // TODO Delete this line, when it is not needed anymore

			// Read first line
			String line = br.readLine();
			// Count read Lines
			int lineNumber = 0;
			// Go through whole file
			while(line!=null){
				int i;
				for (i=0; i < line.length(); i++) {
					// Read Character at Position i in current line
					char c = line.charAt(i);
					//System.out.println(c); // TODO Delete this line, when it is not needed anymore
					//Put Character on Screen
					world.setTile(ColoredChar.create(c),c!='#', i, lineNumber);
				}
				// Fill rest of Line with Whitespaces
				while(i<world.width()){
					world.setTile(ColoredChar.create('#'), false, i, lineNumber);
					i++;
				}
				// Read next line
				line= br.readLine();
				// Increment Linecounter
				lineNumber++;
			}
			// Fill rest of Lines with Whitespaces
			while(lineNumber<world.height()){
				// Put Whitespaces in Columns
				for (int i = 0; i < world.width(); i++) {
					 world.setTile(ColoredChar.create('#'), false, i, lineNumber);
				}
				// Go to next Line
				lineNumber++;
			}
			// Redraw Window


		} catch (IOException e) { // Something went wrong while reading the File
			System.out.println("!IoException");
			e.printStackTrace();
			System.exit(1);
		}
		// catch (FileNotFoundException ex) { TODO Delete this Block if it is not needed anymore
		  //  System.out.println("file not found");
		  //  System.exit(1);
		//}
	}

    }


