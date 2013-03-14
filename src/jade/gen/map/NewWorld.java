package jade.gen.map;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import jade.core.World;
import jade.util.Dice;
import jade.util.datatype.ColoredChar;

public abstract class NewWorld extends MapGenerator {

	// neue generateStep Methode damit man eine Map als txt-Datei einlesen kann
	// - analog zu showfile
	protected void generateStep(World world, Dice dice, String filePath) {
		// TODO Auto-generated method stub
		try {
			// Prepare File for reading (open it)
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			// int lineNumber = MAXHEIGHT; // TODO Delete this line, when it is
			// not needed anymore

			// Read first line
			String line = br.readLine();
			// Count read Lines
			int lineNumber = 0;
			// Go through whole file
			ColoredChar c_color;// einige Zeichen werden durch andere bunte
								// Zeichen ersetzt.
			ArrayList notPassable = new ArrayList();
			notPassable.add('#');
			notPassable.add('~');
			notPassable.add('^');
			notPassable.add(';');
			while (line != null) {
				int i;

				for (i = 0; i < line.length(); i++) {
					// Read Character at Position i in current line
					char c = line.charAt(i);
					c_color = ColoredChar.create(c);
					switch (c) {
					case '@': {
						c_color = ColoredChar.create('#');
						break;
					}
					case ':': {	c_color = ColoredChar.create('.', Color.yellow);	break;}// :wird zu grünen Punkt
					case '~': {	c_color = ColoredChar.create('~', Color.blue);	break;}
					case '^': {	c_color = ColoredChar.create('^', new Color(210,105,30));break;}
					case '$': {	c_color = ColoredChar.create('$', Color.yellow);	break;}
					case 'P': {	c_color = ColoredChar.create('P', Color.green);	break;}
					case ',': {	c_color = ColoredChar.create(',', Color.green);	break;}
					case ';': {	c_color = ColoredChar.create(';', new Color(69,139,0));	break;}
					case '†': {	c_color = ColoredChar.create('†', new Color(199,21,133));break;}
					case '=': {	c_color = ColoredChar.create('=', new Color(210,105,30));break;}
					case '#': {	c_color = ColoredChar.create('#', new Color(205,102,29));break;}
					case '.': {	c_color = ColoredChar.create('.', new Color(51,51,51));break;}
					case '¬': {	c_color = ColoredChar.create('¬', new Color(210,105,30));break;}
					}
					// System.out.println(c); // TODO Delete this line, when it
					// is not needed anymore
					// Put Character on Screen

					world.setTile(c_color, !notPassable.contains(c), i,
							lineNumber);
				}
				// Fill rest of Line with Whitespaces
				while (i < world.width()) {
					world.setTile(ColoredChar.create('#'), false, i, lineNumber);
					i++;
				}
				// Read next line
				line = br.readLine();
				// Increment Linecounter
				lineNumber++;
			}
			// Fill rest of Lines with Whitespaces
			while (lineNumber < world.height()) {
				// Put Whitespaces in Columns
				for (int i = 0; i < world.width(); i++) {
					world.setTile(ColoredChar.create('#'), false, i, lineNumber);
				}
				// Go to next Line
				lineNumber++;
			}

		} catch (IOException e) { // Something went wrong while reading the File
			System.out.println("!IoException");
			e.printStackTrace();
			System.exit(1);
		}
		// catch (FileNotFoundException ex) { TODO Delete this Block if it is
		// not needed anymore
		// System.out.println("file not found");
		// System.exit(1);
		// }
	}

	@Override
	protected abstract void generateStep(World world, Dice dice);
	// Reicht abstrakte Methode weiter, um verschiedene Maps zu laden

}
