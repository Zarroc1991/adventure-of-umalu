package jade.util.datatype;

/**
 * A cardinal direction as a 2-dimensional integer vector.
 */
public enum Direction
{
    /**
     * Up on the screen
     */
    NORTH(0, -1),
    /**
     * Up-right on the screen
     */
    NORTHEAST(1, -1),
    /**
     * Right on the screen
     */
    EAST(1, 0),
    /**
     * Down-right on the screen
     */
    SOUTHEAST(1, 1),
    /**
     * Down on the screen
     */
    SOUTH(0, 1),
    /**
     * Down-left on the screen
     */
    SOUTHWEST(-1, 1),
    /**
     * Left on the screen
     */
    WEST(-1, 0),
    /**
     * Up-left on the screen
     */
    NORTHWEST(-1, -1),
    /**
     * No change on the screen
     */
    ORIGIN(0, 0);

    private int dx;
    private int dy;

    private Direction(int dx, int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the x component of the directional vector.
     * @return the x component of the directional vector
     */
    public int dx()
    {
        return dx;
    }

    /**
     * Returns the y component of the directional vector.
     * @return the y component of the directional vector
     */
    public int dy()
    {
        return dy;
    }

    /**
     * Returns the {@code Direction} corresponding to the given key press, or null if there is none.
     * The key can be either vi-keys (with '.' as {@code ORIGIN}), or num-pad keys.
     * @param key the key direction being queried
     * @return the {@code Direction} corresponding to key
     */
    public static Direction keyToDir(char key)
    {
        switch(key)
        {
		case '6':
		case 'd':
                	return EAST;
		case '4':
		case 'a':
           		 return WEST;
           	case '8':
			case 'w':
           		return NORTH;
           	case '2':
		case 's':
		case 'x':
         		return SOUTH;
           	case '3':
           	case 'n':
           	case 'c':
          		return SOUTHEAST;
           	case '1':
           	case 'b':
           	case 'y':
           		return SOUTHWEST;
           	case '9':
           	case 'u':
           	case 'e':
           		return NORTHEAST;
           	case '7':
           	case 'q':
           		return NORTHWEST;
           	case '5':
           	case '.':
           	   	return ORIGIN;
           	default:
           	   	return null;
        }
    }
}
