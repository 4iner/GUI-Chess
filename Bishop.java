import java.util.*;
/**
 * bishop sublclass of chesspiece
 * @author Mustafa Abdulmajeed
 */
public class Bishop extends ChessPiece
{
    /**
     * Constructor for objects of class Bishop
     * @param owner (true = white, false = black)
     * @param initial location of piece
     * @param chess game this piece belongs to
     */
    public Bishop(boolean player, ChessLocation initialLocation, ChessGame game)
    {
        super(player, initialLocation, game);
        if(player) this.id = 'B';
        else this.id = 'b';
    }
    protected void updateThreateningLocation(ChessLocation newLocation){
        ArrayList<ChessLocation> newThreats = new ArrayList<>();
        ChessGame game = this.getGame();
        ChessBoard board = game.getBoard();
        for(int i = -1; i < 2; i++){
            for(int c = -1; c < 2; c++){
                if(c*i == 0) continue;
                else {
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
    public boolean moveTo(ChessLocation destination){
        ChessLocation CLocation = this.getLocation();
        int dify = destination.getCol() - CLocation.getCol();
        int difx = destination.getRow() - CLocation.getRow();
        if(difx*dify != 0)
            if(Math.abs(dify/difx) == 1 && Math.abs(difx/dify) == 1){
                if(checkLineOfSight(CLocation, destination)){
                    super.moveTo(destination);
                    return true;
                }
                
            }
       // System.out.println("Failed move! Please try again...\n");
        return false;
    }
    public String toString(){
        return "Bishop at "+this.getLocation();
    }
}
