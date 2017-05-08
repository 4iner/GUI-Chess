import java.util.*;
/**
 * pawn subclass
 * 
 * @author mustafa abdulmajeed
 */
public class Pawn extends ChessPiece
{
    public boolean firstMove;
    /**
     * @param owner (true = white, false = black)
     * @param initial location of piece
     * @param chess game this piece belongs to
     */
    public Pawn(boolean player, ChessLocation initLocation, ChessGame game)
    {
        super(player, initLocation, game);
        if(player) this.id = 'P';
        else this.id = 'p';
        firstMove = false;
    }
    /**
     * updates threatning locations
     * @param new loc of this piece
     */
    public void updateThreateningLocation(ChessLocation newLocation){
        ArrayList<ChessLocation> newThreats = new ArrayList<>();
        ChessGame game = this.getGame();
        ChessBoard board = game.getBoard();
        int n = newLocation.getRow();
        int m = newLocation.getCol();
        int d = this.getOwner()?-1 : 1;
        if(n+d >= 0 && n+d <8 && m-1 >=0 && m+1 <8){
            if(board.isPieceAt(n+d, m+1)){
                if(board.getPieceAt(new ChessLocation(n+d, m+1)).getOwner()!=this.getOwner()){
                    newThreats.add(new ChessLocation(n+d, m+1));
                }
            }
            else newThreats.add(new ChessLocation(n+d, m+1));
            if(board.isPieceAt(n+d, m-1)){
                if(board.getPieceAt(new ChessLocation(n+d, m-1)).getOwner()!=this.getOwner()){
                    newThreats.add(new ChessLocation(n+d, m-1));
                }
            }
            else newThreats.add(new ChessLocation(n+d, m-1));
        }
        this.setList(newThreats);
    }
    /** 
     *  Checks if it is possible to move this piece piece to the given location.
     *  @param New location object
     */
    public boolean moveTo(ChessLocation destination){
        int m = firstMove ? 1 : 2;
        int dify = destination.getRow() - this.getLocation().getRow();
        if(this.getOwner()) dify *= -1;
        int difx = destination.getCol() - this.getLocation().getCol();
        boolean front = this.getGame().getBoard().isPieceAt(destination.getRow(),destination.getCol());
        if(checkLineOfSight(this.getLocation(),destination) && front){
            if(Math.abs(difx)==1 && Math.abs(dify) == 1){
                if(this.getGame().getBoard().getPieceAt(destination).getOwner()!=this.getOwner())
                    {
                         super.moveTo(destination);
                         if(m == 2) this.firstMove = true;
                         return true;
                    }
            }
        }
        if(checkLineOfSight(this.getLocation(), destination) && dify > 0 && dify <= m && difx == 0&&!front){
                super.moveTo(destination);
                if(m == 2) this.firstMove = true;
                return true;
        } else //System.out.println("Failed move! Please try again...\n");
        return false;
    }
    /**
     * typical to string
     */
    public String toString(){
        return "Pawn at "+this.getLocation();
    }
}
