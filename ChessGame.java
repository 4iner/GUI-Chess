
/**
 * A game of chess
 * @author mustafa abdulmajeed
 */
public class ChessGame
{
    // instance variables - replace the example below with your own
    private ChessBoard board;
    private String player1;
    private String player2;

    /**
     * Constructor for objects of class ChessGame
     * @param player 1 name
     * @param player 2 name
     */
    public ChessGame(String player1, String player2)
    {
        // initialise instance variables
        this.board = new ChessBoard();
        this.player1 = player1;
        this.player2 = player2;
            for(int i = 0; i < 8; i++){
                board.placePieceAt(new Pawn(false, new ChessLocation(1,i), this), new ChessLocation(1,i));
            }
            board.placePieceAt(new Rook(false, new ChessLocation(0,0), this), new ChessLocation(0,0));
            board.placePieceAt(new Rook(false, new ChessLocation(0,7), this), new ChessLocation(0,7));
            board.placePieceAt(new Knight(false, new ChessLocation(0,1), this), new ChessLocation(0,1));
            board.placePieceAt(new Knight(false, new ChessLocation(0,6), this), new ChessLocation(0,6));
            board.placePieceAt(new Bishop(false, new ChessLocation(0,2), this), new ChessLocation(0,2));
            board.placePieceAt(new Bishop(false, new ChessLocation(0,5), this), new ChessLocation(0,5));
            board.placePieceAt(new King(false, new ChessLocation(0,3), this), new ChessLocation(0,3));
            board.placePieceAt(new Queen(false, new ChessLocation(0,4), this), new ChessLocation(0,4));
            for(int i = 0; i < 8; i++){
                board.placePieceAt(new Pawn(true, new ChessLocation(6,i), this), new ChessLocation(6,i));
            }
            board.placePieceAt(new Rook(true, new ChessLocation(7,0), this), new ChessLocation(7,0));
            board.placePieceAt(new Rook(true, new ChessLocation(7,7), this), new ChessLocation(7,7));
            board.placePieceAt(new Knight(true, new ChessLocation(7,1), this), new ChessLocation(7,1));
            board.placePieceAt(new Knight(true, new ChessLocation(7,6), this), new ChessLocation(7,6));
            board.placePieceAt(new Bishop(true, new ChessLocation(7,2), this), new ChessLocation(7,2));
            board.placePieceAt(new Bishop(true, new ChessLocation(7,5), this), new ChessLocation(7,5));
            board.placePieceAt(new King(true, new ChessLocation(7,3), this), new ChessLocation(7,3));
            board.placePieceAt(new Queen(true, new ChessLocation(7,4), this), new ChessLocation(7,4));
            
    }
    /**
     * Mutators for the board.
     */
    public ChessBoard getBoard(){
        return this.board;
    }
    public void setBoard(ChessBoard board){
        this.board = board;
    }
   
}
