/**
 *  Create all the necessary pieces needed for a functional Connect 4 game
 *
 *  @author tylernguyen
 *  @version May 27, 2021
 */
public class Connect4
{

    /**
     * points for red player
     */
    public static int redPoints;
    /**
     * points for yellow player
     */
    public static int yelPoints;

    /**
     * 2D Array of the Connect 4 Board
     */
    Pieces[][] board = new Pieces[6][7];
    /**
     * integer value of which player is going first
     */
    int playerTurn = 1;


    /**
     * initializes points for score board to 0
     */
    public Connect4()
    {
        redPoints = 0;
        yelPoints = 0;
    }

    /**
     * initializes the red and yellow points to local variables
     * @param test Connect 4
     */
    public Connect4(Connect4 test)
    {
        redPoints = test.redPoints;
        yelPoints = test.yelPoints;
    }

    /**
     * Checks to see if the board is full of tiles
     * @return false if the board isn't full, true otherwise
     */
    public boolean isFull()
    {
    for (int x = 0; x < board.length; x++)
    {
        for (int y = 0; y < board[0].length; y++)
        {
            if (board[x][y] == null)
            {
                return false;
            }
        }
    }
    return true;
    }


    /**
     * Check if there are any vertical 4 in a row for either player
     * @param r integer for row
     * @param c integer for column
     * @return true if there's a vertical 4 in a row, false otherwise
     */
    public boolean isVertical(int r, int c)
    {
        int current = board[r][c].getPlayer();
        int counter = 0;
        for (int x = 0; x < board.length; x++)
        {
            if (board[x][c] != null && board[x][c].getPlayer() == current)
            {
                counter++;
                if (counter == 4)
                {
                    if (current == 1)
                    {
                        redPoints++;
                    }
                    else
                    {
                        yelPoints++;
                    }
                    return true;
                }
            }
            else
            {
                counter = 0;

            }
        }
        return false;
    }

    /**
     * Check if there are any horizontal 4 in a row for either player
     * @param r integer for row
     * @param c integer for column
     * @return true if there's a horizontal 4 in a row, false otherwise
     */
    public boolean isHorizontal(int r, int c)
    {
        int current = board[r][c].getPlayer();
        int counter = 0;
        for (int x = 0; x < board[0].length; x++)
        {
            if (board[r][x] != null && board[r][x].getPlayer() == current)
            {
                counter++;
                if (counter == 4)
                {
                    if (current == 1)
                    {
                        redPoints++;
                    }
                    else
                    {
                        yelPoints++;
                    }
                    return true;
                }
            }
            else
            {
                counter = 0;
            }
        }
        return false;
    }

    /**
     * Check if there are any horizontal 4 in a row for either player
     * @param r integer for row
     * @param c integer for column
     * @return true if there's a diagonal 4 in a row, false otherwise
     */
    public boolean isDiagonal(int r, int c)
    {
        int current = board[r][c].getPlayer();
        int counter = 0;

        // check (\)
        for(int i = -4; i <= 4; i++) {
            if(isInBounds(r + i,c + i)) {
                if(board[r + i][c + i] != null && board[r+i][c+i].getPlayer() == current) {
                    counter++;
                    if(counter == 4) {
                        if (current == 1)
                        {
                            redPoints++;
                        }
                        else
                        {
                            yelPoints++;
                        }
                         return true;
                    }
                } else {
                    counter = 0;
                }
            }
        }

        counter = 0;
        // checks (/)
        for(int i = -4; i <= 4; i++) {
            if(isInBounds(r + i, c - i)) {
                if(board[r + i][c - i] != null && board[r + i][c - i].getPlayer() == current) {
                    counter++;
                    if(counter == 4) {
                        if (current == 1)
                        {
                            redPoints++;
                        }
                        else
                        {
                            yelPoints++;
                        }
                         return true;
                    }
                } else {
                    counter = 0;
                }
            }
        }
        return false;
    }

    /**
     * Combines all possible 4 in a row checks to see if players won
     * @param r integer for row
     * @param c integer for column
     * @return true if there are any 4 in a row on the board (either color)
     */
    public boolean isWin(int r, int c)
    {
        return (isVertical(r, c) || isHorizontal(r, c) || isDiagonal(r, c));
    }

    /**
     * Returns the 2D array of pieces
     * @return 2D array of pieces
     */
    public Pieces[][] getBoard()
    {
        return board;
    }

    /**
     * Determines whose turn it is to place a tile
     * @return an integer 1 or 2 representing whose turn it is
     */
    public int getPlayerTurn()
    {
        return playerTurn;
    }

    /**
     * Alternates who places a tile
     * @param x sets the value according to whose turn it is
     */
    public void setPlayerTurn(int x)
    {
        playerTurn = x;
    }

    /**
     * Check if there are vacant spaces in columns
     * @param colNum specific column that a tile can be dropped in
     * @return return the open column, if not return -1
     */
    public int checkSpace(int colNum)
    {
        int val = -1;
        for (int i = board.length - 1; i >= 0; i--)
        {
            if (board[i][colNum] == null)
            {
                val = i;
                break;
            }
        }
        return val;
    }

    /**
     *
     * @param row integer for row
     * @param col integer for column
     * @param p represents a token/tile
     */
    public void setTile(int row, int col, Pieces p)
    {
        board[row][col] = p;
    }

    /**
     * Drops the tile onto the board.
     * @param col integer for column
     * @param p token/tile on the board
     */
    public void dropTile(int col, Pieces p)
    {
        int row = checkSpace(col);
        this.setTile(row, col, p);
    }

    /**
     * Checks to see if the tile is in bounds
     * @param row integer for row
     * @param col integer for column
     * @return true if piece is on the board, false otherwise
     */
    private boolean isInBounds(int row, int col)
    {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    /**
     * Main method.
     * @param args arguments
     */
    public static void main(String[] args)
    {
        Connect4 game = new Connect4();
        GUI g = new GUI(game);
    }
}



