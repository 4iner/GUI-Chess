import java.util.*;
/**
 * Rook subclass
 * 
 * @author Mustafa
 * 
 */
public class Rook extends ChessPiece
{
    
    /**
     * Constructor for objects of class Rook
    * @param owner (true = white, false = black)
     * @param initial location of piece
     * @param chess game this piece belongs to
     */
    public Rook(boolean player, ChessLocation initialLocation, ChessGame game)
    {
        super(player, initialLocation, game);
        if(player) this.id = 'R';
        else this.id = 'r';
    }
    /**
     * update threatening locations
     * @param location of rook
     */
    public void updateThreateningLocation(ChessLocation newLocation){
        ArrayList<ChessLocation> newThreats = new ArrayList<>();
        ChessGame game = this.getGame();
        ChessBoard board = game.getBoard();
        
        /*for(int i = -7; i < 8; i++){
            if(n+i >= 0 && n+i < 8 && m+i >=0 && m+i<7){
                if(board.isPieceAt(n+i, m)){
                    if(board.getPieceAt(new ChessLocation(n+i, m)).getOwner()!=this.getOwner())
                        newThreats.add(new ChessLocation(n+i, m));
                }
                else newThreats.add(new ChessLocation(n+i, m*c));
                if(board.isPieceAt(n, m+i)){
                    if(board.getPieceAt(new ChessLocation(n*i, m*c)).getOwner()!=this.getOwner())
                        newThreats.add(new ChessLocation(n*i, m*c));
                }
                else newThreats.add(new ChessLocation(n*i, m*c));
            }
        }*/
        for(int i = -1; i < 2; i++){
            for(int c = -1; c < 2; c++){
                if(c*i != 0) continue;
                else {
                    if(c == 0 && i == 0) continue;
                    int n = newLocation.getRow();
                    int m = newLocation.getCol();
                    int s = 1;
                    while(n+s*i < 8 && n+s*i >= 0 && m+s*c < 8 && m+s*c >=0){
                        if(board.isPieceAt(n+s*i, m+s*c)){
                            if(board.getPieceAt(new ChessLocation(n+s*i, m+s*c)).getOwner()!=this.getOwner())
                                newThreats.add(new ChessLocation(n+s*i, m+s*c));
                            break;
                        }
                        else newThreats.add(new ChessLocation(n+s*i, m+s*c));
                        s++;
                    }
                }
            }
        }
        this.setList(newThreats);
    }
    /** 
     *  Checks if it is possible to move this piece piece to the given location.
     *  @param New location object
     */
    public boolean moveTo(ChessLocation destination){
        int dify = destination.getCol() - this.getLocation().getCol();
        int difx = destination.getRow() - this.getLocation().getRow();

        boolean cond1 = dify != 0 && difx == 0;
        boolean cond2 = difx != 0 && dify == 0;
        if(checkLineOfSight(this.getLocation(), destination)&&(cond1 || cond2)){
            super.moveTo(destination);
            return true;
        } else //System.out.println("Failed move! Please try again...\n");
        return false;
    }
    /**
     * to string
     */
    public String toString(){
        return "Rook at "+this.getLocation();
    }
}