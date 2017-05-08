
/**
 * chessboard: board for chess game
 * 
 * @author Mustafa Abdulmajeed
 * 
 */
public class ChessBoard
{
    // instance variables - replace the example below with your own
    private ChessPiece[][] board;

    /**
     * Constructor for objects of class ChessBoard
     */
    public ChessBoard()
    {
        // initialise instance variables
        this.board = new ChessPiece[8][8];
    }
    /**
     * Checks if a knight is currently at these coordinates.
     * @param row coords
     * @param column coords
     */
    public boolean isPieceAt(int row, int col){
        if(board[row][col] == null)
            return false;
        return true;
    }
    /**
     * gets piece at
     */
    public ChessPiece getPieceAt(ChessLocation location)
    {
        return board[location.getRow()][location.getCol()];
    }
    /**
     * Places a knight at the given location 
     */
    public void placePieceAt(ChessPiece piece, ChessLocation location){
        board[location.getRow()][location.getCol()] = piece;
        piece.setLocation(location);
        piece.updateThreateningLocation(location);
    }
    /**
     * Replaces any piece at given location with null
     */
    public void removePiece(ChessLocation location){
        board[location.getRow()][location.getCol()] = null;
    }
    //mutators and accessors
    /**
     * gets board
     */
    public ChessPiece[][] getBoard(){
        return this.board;
    }
    /**
     * sets board
     */
    public void setBoard(ChessPiece[][] board){
        this.board = board;
    }
    /**
     * checks if king
     */
    public boolean isKing(int row, int col){
        if(isPieceAt(row, col))
            return board[row][col].getId()=='k' || board[row][col].getId()=='K';
        return false;
    }
    /**
     * Returns the board as an 8x8 string, where null is '-' and a knight is 'k'
     */
    public String toString(){
        String returnString = "01234567\n"; 
        for(int i = 0; i<8; i++){
            for(int c = 0; c<8; c++){
                if(board[i][c] == null) returnString+='-';
                else returnString += board[i][c].id;
            }
            returnString += i+"\n";
        }
        return returnString;
    }
    
}
