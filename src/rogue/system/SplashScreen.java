package rogue.system;

import jade.core.World;
import jade.util.datatype.ColoredChar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SplashScreen extends World {
	final static int MAXWIDTH = 100;
	final static int MAXHEIGHT = 75;
	/**
	 *  Loads a Splash Screen from a given file. To be used as Start or Ending screen (ASCII Graphics)
	 *
	 * @param file Path to file.
	 */
	public SplashScreen(String filePath) { 
		super(MAXWIDTH, MAXHEIGHT);// TODO Make this code dynamic to filesize
		// Prepare to open File
		try {
			BufferedReader br;
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			// Read first line
			int lineNumber = MAXHEIGHT;
			String line = br.readLine();
			while (line != null) { // End of File reached?
				int i;
				for (i=0; i < line.length(); i++) {
					// Put character on Screen
					grid[i][MAXHEIGHT-lineNumber].setFace(ColoredChar.create(line.charAt(i)));
				}
				// Fill Rest of the Screen with Whitespaces
				while (i<MAXWIDTH) {
					grid[i][MAXHEIGHT-lineNumber].setFace(ColoredChar.create(' '));
					i++;
				}
				line = br.readLine();
				lineNumber--;
			}
			while (lineNumber != 0) {
				for (int i=0;i<MAXWIDTH;i++) {
					grid[i][MAXHEIGHT-lineNumber].setFace(ColoredChar.create(' '));
				}
				lineNumber--;
			}
		} catch (FileNotFoundException e) {
			System.out.println("!Could not find file: "+filePath+"\nShutting down");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("!IO Error:");
			e.printStackTrace();
			System.exit(1);
		}
	}
}
