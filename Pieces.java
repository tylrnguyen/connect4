import java.awt.Color;
/**
 * Create the pieces of the connect 4 game, either red or yellow.
 *
 *  @author tylernguyen
 *  @version May 23, 2021
 */
public class Pieces
{

    /**
     * Represents color of the tile
     */
    public Color color;
    /**
     * Represents the 2 players
     */
    public int player;
    /**
     * Represents row on the board
     */
    public int row;
    /**
     * Represents the column on the board
     */
    public int col;


    // initialize colors
    private static final Color pRed = new Color(255, 0, 0);
    private static final Color pYellow = new Color(255, 255, 0);

    //~ Constructors ..........................................................
    /**
     * Create a new Pieces object.
     * @param col integer for column
     * @param p integer representing the 2 players
     * @param r integer for row
     * @param c color of pieces
     */
    public Pieces(Color col, int p, int r, int c)
    {
        this.color = col;
        this.player = p;
        this.row = r;
        this.col = c;
    }

    //~Public  Methods ........................................................
    /**
     * Returns the player, either 1 or 2
     * @return an integer representing a player
     */
    public int getPlayer()
    {
        return player;
    }
}




