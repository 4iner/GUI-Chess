import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.GridLayout;
import java.awt.Image;

public class GUI
{
    private ChessGame game;
    private ChessBoard board;
    private JLabel status;
    private JLabel turn;
    private JFrame frame;
    private JPanel panel;
    private JMenu menu;
    private JMenuBar menuBar;
    private JMenuItem newGameItem;
    private Button squares[][] = new Button[9][9];
    private boolean player; //false for white, true for black
    private ChessPiece selectedPiece;
    public GUI(){
        //set up frame
        frame = new JFrame("Chess");
        frame.setSize(600, 600);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        //set up panel & add it to frame
        newGame();
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        newGameItem = new JMenuItem("New game");
        newGameItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    resetGame();
                }
            });
        menu.add(newGameItem);
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                   frame.setVisible(false);
                   frame.dispose();
                }
            });
        menu.add(quitItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        frame.add(panel);
        //add status & turn text boxes to the frame
        JPanel panel1 = new JPanel();
        panel1.add(status);
        frame.add(panel1);
        JPanel panel2 = new JPanel();
        panel2.add(turn);
        frame.add(panel2);
        //minimize frame to the smallest window size for all contents
        frame.pack();
        //make frame visible
        frame.show();
    }
    /**
     * return the chess board at memory
     */
    public ChessBoard getBoard(){
        return board;
    }
    /**
     * return player variable
     */
    public boolean getPlayer(){
        return player;
    }

    /**
     * change turns of players from white -> black or black -> white
     */
    public void changeTurns(){
        player = !player;
        turn.setText((player?"White":"Black")+"'s turn");
    }

    /**
     * return current piece selected
     */
    public ChessPiece getSelectedPiece(){
        return selectedPiece;
    }

    /**
     * set current selected piece
     */
    public void setSelectedPiece(ChessPiece piece){
        this.selectedPiece = piece;
    }

    /**
     * start new game with fresh variables
     */
    private void newGame(){
        panel = createChessBoard();
        player = true;
        selectedPiece = null;
        game = new ChessGame("White", "Black");
        board = game.getBoard();
        status = new JLabel("New game!");
        turn = new JLabel("White's turn");
    }
    //update Status bar at the bottom
    public void updateStatus(String msg){
        status.setText(msg);
    }
    //switch icons of buttons, pl = previous location, nl = new location (with r and c meaning row and col)
    public void switchIcon(int plc, int plr, int nlc, int nlr){ 
        Icon temp = squares[plc][plr].getIcon();
        squares[nlc][nlr].setIcon((ImageIcon)temp);
        squares[plc][plr].setIcon(null);
    }
    //create a JPanel with a new chess board
    private JPanel createChessBoard(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8, 1, 1));
        boolean b = true;
        for(int i = 0; i <8; i++){
            b = (i%2==0) ? true : false;
            for(int c = 0; c < 8; c++){
                squares[i][c] = new Button(i, c, this);
                if(b) squares[i][c].setBackground(Color.WHITE);
                else squares[i][c].setBackground(Color.GRAY);
                squares[i][c].setPreferredSize(new Dimension(45,45));
                b = !b;
                panel.add(squares[i][c]);
            }
        }
        loadIcons();
        return panel;
    }
    public void resetGame(){
        for(int i = 2; i < 6; i++){
            for(int c = 0; c < 8; c++){
                squares[i][c].setIcon(null);
            }
        }
        loadIcons();
        player = true;
        selectedPiece = null;
        game = new ChessGame("White", "Black");
        board = game.getBoard();
        status.setText("Reset board");
        turn.setText("White's turn");
    }
    /**
     * load fresh icons onto panel
     */
    public void loadIcons(){
        //credit for icons: https://commons.wikimedia.org/wiki/Category:SVG_chess_pieces
        try{
            Image img = ImageIO.read(getClass().getResource("icons/WhitePawn.png"));
            for(int i = 0; i < 8; i++)
                squares[6][i].setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("icons/BlackPawn.png"));
            for(int i = 0; i < 8; i++)
                squares[1][i].setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("icons/BlackRook.png"));
            squares[0][0].setIcon(new ImageIcon(img));
            squares[0][7].setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("icons/BlackKnight.png"));
            squares[0][1].setIcon(new ImageIcon(img));
            squares[0][6].setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("icons/BlackBishop.png"));
            squares[0][2].setIcon(new ImageIcon(img));
            squares[0][5].setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("icons/BlackKing.png"));
            squares[0][3].setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("icons/BlackQueen.png"));
            squares[0][4].setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("icons/WhiteRook.png"));
            squares[7][0].setIcon(new ImageIcon(img));
            squares[7][7].setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("icons/WhiteKnight.png"));
            squares[7][1].setIcon(new ImageIcon(img));
            squares[7][6].setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("icons/WhiteBishop.png"));
            squares[7][2].setIcon(new ImageIcon(img));
            squares[7][5].setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("icons/WhiteKing.png"));
            squares[7][3].setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("icons/WhiteQueen.png"));
            squares[7][4].setIcon(new ImageIcon(img));
        }catch (Exception e){
        }
    }
}
