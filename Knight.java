import java.util.*;
/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends ChessPiece
{
    /**
     * kinght constuctor
     * @param owner (true = white, false = black)
     * @param initial location of piece
     * @param chess game this piece belongs to
     */
    public Knight(boolean owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);
        if(owner) this.id = 'N';
        else this.id = 'n';
    }
    /**
     * update threatening locations
     * @param location of knight
     */
    public void updateThreateningLocation(ChessLocation newLocation){
        ArrayList<ChessLocation> newThreats = new ArrayList<>();
        ChessGame game = this.getGame();
        ChessBoard board = game.getBoard();
        int[] nr = {-2, -2, -1, -1,  1, 1,  2, 2};
        int[] mc = {-1,  1, -2,  2, -2, 2, -1, 1};
        int n = newLocation.getRow();
        int m = newLocation.getCol();
        for(int i = 0; i<8; i++){
            if(n+nr[i] < 8 && n+nr[i] >= 0 && m+mc[i] < 8 && m+mc[i] >=0){
                if(board.isPieceAt(n+nr[i], m+mc[i])){
                    if(board.getPieceAt(new ChessLocation(n+nr[i], m+mc[i])).getOwner()!=this.getOwner()){
                        newThreats.add(new ChessLocation(n+nr[i], m+mc[i]));
                    }
                }else newThreats.add(new ChessLocation(n+nr[i], m+mc[i]));
            }
        }
        this.setList(newThreats);
    }
    /** 
     *  Checks if it is possible to move this knight piece to the given location.
     *  @param New location object
     */
    public boolean moveTo(ChessLocation newLocation)
    {
        int x = this.getLocation().getRow(), y = this.getLocation().getCol(),x1 = newLocation.getRow(),y1 = newLocation.getCol();
          //Knights can only move two horizontally and one vertically
          //or one vertically and two horizontally. Code below checks that.
        if((Math.abs(x-x1)==1 && Math.abs(y-y1) == 2)||(Math.abs(x-x1)==2 && Math.abs(y-y1) == 1))
        {
            super.moveTo(newLocation);
            return true;
        }
        //System.out.println("Invalid move! A knight can't move like that!\n");
        return false;
    }
    public String toString(){
        return "Knight at "+this.getLocation();
    }
    
}
