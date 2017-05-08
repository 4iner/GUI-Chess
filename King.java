import java.util.*;
/**
 * subclass king for chesspiece
 * 
 * @author Mustafa Abdulmajeed
 */
public class King extends ChessPiece
{
    /**
     * king constructor
     * @param owner (true = white, false = black)
     * @param initial location of piece
     * @param chess game this piece belongs to
     */
    public King(boolean player, ChessLocation initLocation, ChessGame game)
    {
        super(player, initLocation, game);
        if(player) this.id = 'K';
        else this.id = 'k';
    }
    /**
     * update threatening locations
     * @param location of king
     */
    public void updateThreateningLocation(ChessLocation newLocation){
        ArrayList<ChessLocation> newThreats = new ArrayList<>();
        ChessGame game = this.getGame();
        ChessBoard board = game.getBoard();
        int n = newLocation.getRow();
        int m = newLocation.getCol();
        for(int i = -1; i < 2; i++){
            for(int c = -1; c < 2; c++){
                if(i == 0 && c == 0) continue;
                else {
                    if(n+i >= 0 && n+i < 8 && m+c >= 0 && m+c <8){
                        if(board.isPieceAt(n+i, m+c)){
                            if(board.getPieceAt(new ChessLocation(n+i, m+c)).getOwner()!=this.getOwner()){
                                newThreats.add(new ChessLocation(n+i, m+c));
                            }
                        }else newThreats.add(new ChessLocation(n+i, m+c));
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
        ChessLocation l = this.getLocation();
        int dify = destination.getCol() - l.getCol();
        int difx = destination.getRow() - l.getRow();
        if(checkLineOfSight(this.getLocation(), destination)&& Math.abs(dify)<=1 && Math.abs(difx)<=1&&locationInDanger(destination)==null){
            super.moveTo(destination);
            return true;
        }
        else //System.out.println("Failed move! Please try again...\n");
        return false;
    } 
    /**
     * checks if this location is currently in danger
     * @param location checked
     */
    public ChessPiece locationInDanger(ChessLocation destinationLocation){
        ChessBoard board = this.getGame().getBoard();
        for(int i = 0; i < 8; i++){
            for(int c = 0; c < 8; c++){
                if(board.isPieceAt(i, c)){
                    ChessPiece possiblePiece = board.getPieceAt(new ChessLocation(i, c));
                    if(possiblePiece == this) continue;
                    if(possiblePiece.getOwner()==this.getOwner()) continue;
                    else {
                        ArrayList<ChessLocation> threats = possiblePiece.getList();
                        for(ChessLocation loc : threats){
                            if(destinationLocation.equals(loc)){
                                return possiblePiece;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    /**
     * returns if king can move or nah
     */
    public boolean anyMovesLeft(){
        ChessGame game = this.getGame();
        ChessBoard board = game.getBoard();
        int n = this.getLocation().getRow();
        int m = this.getLocation().getCol();
        for(int i = -1; i < 2; i++){
            for(int c = -1; c < 2; c++){
                if(i == 0 && c == 0) continue;
                else {
                    if(n+i >= 0 && n+i < 8 && m+c >= 0 && m+c <8){
                        if(!board.isPieceAt(n+i, m+c)){
                            if(locationInDanger(new ChessLocation(n+i, m+c))!=null)
                                return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    /**
     * returns piece that threatens king
     */
    public ChessPiece check(){
        return locationInDanger(this.getLocation());
    }
    /**
     * typical to string
     */
    public String toString(){
        return "King at "+this.getLocation();
    }
}
