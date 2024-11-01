import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 *  Creates an interactive user interface of the Connect 4 game
 *  including buttons to end game, create a new game, and view the score board.
 *
 *  @author tylernguyen
 *  @version May 27, 2021
 */
public class GUI implements ActionListener
{

    private JFrame frame;
    private JLabel[][] tiles;
    private JButton[] colButtons;
//    private List<String> moveList;
//    private JLabel moves;
    private JPanel cPane;
//    private int curr;
//    private boolean gameNew;
//    private boolean gameEnd;
    private Connect4 boardGame;
    private Color currentColor;


    /**
     * Create a new GUI object.
     * @param c a connect 4 game with all aspects included
     */
    public GUI(Connect4 c) {

        frame = new JFrame("Connect Four!");
        tiles = new JLabel[7][7];
        colButtons = new JButton[7];
        cPane = new JPanel();
        boardGame = c;
        cPane.setLayout(new GridLayout(7, 7 ));

        for (int i = 0; i < 7; i++)
        {
            colButtons[i] = new JButton("Column " + Integer.toString(i+1));
            colButtons[i].setActionCommand(Integer.toString(i));
            colButtons[i].addActionListener(this);
            cPane.add(colButtons[i]);
        }

        for (int j = 0; j < 6; j++)
        {
            for (int i = 0; i < 7; i++)
            {
                tiles[i][j] = new JLabel();
                tiles[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                tiles[i][j].setOpaque(true);
                tiles[i][j].setBackground(Color.blue);
                tiles[i][j].setBorder(new LineBorder(Color.black));
                cPane.add(tiles[i][j]);
            }
        }

        frame.add(cPane);
        frame.setSize(850, 700);
        JPanel movePanel = new JPanel();
        frame.add(movePanel, BorderLayout.SOUTH);
        movePanel.setPreferredSize(new Dimension(frame.getWidth(), 18));
        movePanel.setLayout(new BoxLayout(movePanel, BoxLayout.X_AXIS));
        JMenuBar mb = new JMenuBar();
        frame.setJMenuBar(mb);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a pop up board if a player has won the game.
     */
    public void win()
    {
        String[] options = {"New Game", "End Game", "View Scoreboard"};
        int optionsPane = JOptionPane.showOptionDialog(
                frame,
                "What would you like to do:",
                "Good game!",0,
                JOptionPane.QUESTION_MESSAGE,null, options,0);
        if (optionsPane == 0)
        {
            frame.dispose();
            play();
        }
        else if (optionsPane == 1)
        {
            frame.dispose();
            System.exit(0);
        }
        else if (optionsPane == 2)
        {
            String msg = "Player 1 wins: " + boardGame.redPoints + "\n" + "Player 2 wins: " + boardGame.yelPoints ;
            JOptionPane.showMessageDialog(frame, msg, "Scoreboard:",
                    JOptionPane.INFORMATION_MESSAGE);
            win();
        }
    }

    /**
     * Creates pop up board if the game ends in a tie.
     */
    public void tie()
    {
        String[] options = {"New Game", "End Game", "View Scoreboard"};
        int optionsPane = JOptionPane.showOptionDialog(
                frame,
                "What would you like to do:",
                "Tie Game!",0, JOptionPane.QUESTION_MESSAGE,null,
                options,0);
        if (optionsPane == 0)
        {
            frame.dispose();
            play();
        }
        else if (optionsPane == 1)
        {
            frame.dispose();
            System.exit(0);
        }
        else if (optionsPane == 2)
        {
            String msg = "Player 1 wins: " + boardGame.redPoints + "\n" + "Player 2 wins: " + boardGame.yelPoints ;
            JOptionPane.showMessageDialog(frame, msg, "Scoreboard:",
                    JOptionPane.INFORMATION_MESSAGE);
            tie();
        }
    }


    /**
     * Resets the board if user wants to play a new game.
     */
    public void play()
    {
        Connect4 game = new Connect4(boardGame);
        GUI g = new GUI(game);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
          Pieces[][] thisBoard = boardGame.getBoard();

          int whoseTurn = boardGame.getPlayerTurn();
          int changeTurn;
          if (whoseTurn == 1)
          {
              changeTurn = 2;
          }
          else
          {
              changeTurn = 1;
          }
          if ((whoseTurn + "").equals("1"))
          {
              currentColor = new Color(255, 0, 0);
          }
          else
              currentColor = new Color(255, 255, 0);
          int col = Integer.parseInt(e.getActionCommand());
          int row = boardGame.checkSpace(col);
          if (row != -1)
          {
              tiles[col][row].setBackground(currentColor);
              boardGame.dropTile(col, new Pieces(currentColor, whoseTurn, row, col));
              boardGame.setPlayerTurn(changeTurn);
              if (boardGame.isWin(row, col))
              {
                  win();
              }
              else if (boardGame.isFull())
              {
                  tie();
              }
          }
          else
          {
              JOptionPane.showMessageDialog(null, "Choose a different column.",
                      "Column  full!", JOptionPane.INFORMATION_MESSAGE);
          }// TODO Auto-generated method stub
    }
}





