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
 * Used to print Stuff in Terminal.
 * @author 
 */
public class Screen {
	public static void showFile(String filePath, TiledTermPanel term, World world){
		try {
			// Prepare File for reading (open it)
			FileReader fr = new FileReader(filePath);
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
					term.bufferChar(i,lineNumber,ColoredChar.create(c));
				}
				// Fill rest of Line with Whitespaces
				while(i<world.width()){
					term.bufferChar(i, lineNumber,ColoredChar.create(' '));
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
					 term.bufferChar(i, lineNumber,ColoredChar.create(' '));
				}
				// Go to next Line
				lineNumber++;
			}
			// Redraw Window
			term.refreshScreen();
		
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
