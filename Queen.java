import java.util.*;
/**
 * Queen chesspiece subclass
 * 
 * @author (Mustafa Abdulmajeed 
 * 
 */
public class Queen extends ChessPiece
{

    /**
     * Constructor for objects of class Queen
     * @param owner (true = white, false = black)
     * @param initial location of piece
     * @param chess game this piece belongs to
     */
    public Queen(boolean player, ChessLocation initialLocation, ChessGame game)
    {
        super(player, initialLocation, game);
        if(player) this.id = 'Q';
        else this.id = 'q';
    }

    /**
     * threatening locations: all 8 directions around q
     */
    public void updateThreateningLocation(ChessLocation newLocation){
        ArrayList<ChessLocation> newThreats = new ArrayList<>();
        ChessGame game = this.getGame();
        ChessBoard board = game.getBoard();
        for(int i = -1; i < 2; i++){
            for(int c = -1; c < 2; c++){
                if(c==0 && i ==0) continue;
                else {
                    int n = newLocation.getRow();
                    int m = newLocation.getCol();
                    //if(c*i != 0){
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
     * if in one of 8 directions, move
     */
    public boolean moveTo(ChessLocation destination){
        int dify = destination.getCol() - this.getLocation().getCol();
        int difx = destination.getRow() - this.getLocation().getRow();

        boolean cond1 = dify != 0 && difx == 0;
        boolean cond2 = difx != 0 && dify == 0;
        boolean cond3;
        if(difx!=0&&dify!=0) cond3 =  Math.abs(dify/difx) * Math.abs(difx/dify) == 1;
        else cond3 = false;

        if(checkLineOfSight(this.getLocation(), destination)&&(cond1 || cond2 || cond3)){
            super.moveTo(destination);
            return true;
        } else //System.out.println("Failed move! Please try again...\n");
        return false;
    }

    /**
     * if this was passed as a string
     */
    public String toString(){
        return "Queen at "+this.getLocation();
    }
}
