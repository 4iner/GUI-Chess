import java.util.*;
/**
 * chesspiece: abstract class for chess pieces
 * 
 * @author Mustafa Abdulmajeed 
 *
 */
abstract class ChessPiece implements ChessPieceInterface
{
    private ChessGame game;
    private boolean player = false;//false = black, true = white
    private ChessLocation location;
    private ArrayList<ChessLocation> threateningLocations;
    protected char id;
    /**
     * constructor: builds chesspiece
     * @param owner
     * @param intiial locatio of piece
     * @param gaeme
     */
    ChessPiece(boolean owner, ChessLocation initialLocation, ChessGame game)
    {
        this.player = owner;
        this.location = null;
        this.game = game;
        this.threateningLocations = new ArrayList<>();
        game.getBoard().placePieceAt(this, initialLocation);
    }
    /**
     * checks locations this piece can move to and if an enemy is on one of them
     * @param new location
     */
    protected abstract void updateThreateningLocation(ChessLocation newLocation);
    /**
     * move to location(this only works if subclass invokes it)
     * @param move to this location
     */
    public boolean moveTo(ChessLocation newLocation)
    {
        if(newLocation.getCol() >= 0 && newLocation.getCol() < 8 && newLocation.getRow() >=0 && newLocation.getRow() <8)
        {
            ChessBoard board = game.getBoard();
            if(board.isPieceAt(newLocation.getRow(), newLocation.getCol())){
                if(board.getPieceAt(newLocation).getOwner()!=this.player){
                     board.removePiece(this.location);
                     board.placePieceAt(this, newLocation);
                     return true;
                }
                return false;
            }
            else{
                board.removePiece(this.location);
                board.placePieceAt(this, newLocation);
                return true;
            }
        }
        return false;
    }
    /**
     * checks all directions
     * @param x1 y1
     * @param x2 y2
     */
    public boolean checkLineOfSight(ChessLocation start, ChessLocation end)
    {
        ChessBoard b = this.game.getBoard();
        int dify = end.getRow() - start.getRow();
        int difx = end.getCol() - start.getCol();
        if(difx == 0&&dify!=0){
            int n = dify / Math.abs(dify);  //-1 if going up, +1 if going down
            for(int i = 1;i<Math.abs(dify);i++){
                    if(b.isPieceAt(start.getRow()+i*n, start.getCol()))
                        return false;
                    
                }
            if(b.isPieceAt(end.getRow(),end.getCol())){
                if(b.getPieceAt(end).getOwner()!=this.player)
                    return true;
                else return false;
                }
            else return true;               
        }
        if(dify == 0&&difx!=0){
            
            int n = difx / Math.abs(difx);
            for(int i = 1; i < Math.abs(difx); i++){ 
                 if(b.isPieceAt(start.getRow(), start.getCol()+i*n))
                      return false;
            }
            if(b.isPieceAt(end.getRow(),end.getCol())){
                if(b.getPieceAt(end).getOwner()!=this.player)
                    return true;}
            else return true;
            
            /*int n = difx / Math.abs(difx);
            for(int j = 1; j < difx; j++){
                System.out.println((start.getRow())+" "+ (start.getCol()+j*n));
                if(b.isPieceAt(start.getRow(), start.getCol()+j*n))
                    return false;
            }
            return true;*/
        }
                
        if(dify != 0 && difx != 0)
            if(Math.abs(dify/difx)==1 && Math.abs(difx/dify) == 1){
                for(int i = 1; i < dify; i++){
                    int n = Math.abs(dify) / dify;
                    int m = Math.abs(difx) / difx;
                    if(b.isPieceAt(start.getRow()+i*n, start.getCol()+i*m))
                        return false;
                    }
                if(b.isPieceAt(end.getRow(),end.getCol())){
                    if(b.getPieceAt(end).getOwner()!=this.player)
                    return true;}
                else return true;
            }

        return false;
    }
    /**
     * prints threatens
     */
    public void printList(){
        System.out.println(this+"threatens:");
        StringBuilder sb = new StringBuilder();
        for (ChessLocation s : threateningLocations)
        {
            sb.append(s);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
    /**
     * gets location
     */
    public ChessLocation getLocation(){
        return this.location;
    }
    /**
     * sets location
     */
    public void setLocation(ChessLocation location){
        this.location = location;
    }
    /**
     * gets owner
     */
    public boolean getOwner(){
        return this.player;
    }
    /**
     * sets owner
     */
    public void setOwner(boolean owner){
        this.player = owner;
    }
    /**
     * gets game
     */
    public ChessGame getGame(){
        return this.game;
    }
    /**
     * sets game
     */
    public void setGame(ChessGame game){
        this.game = game;
    }
    /**
     * sets list
     */
    public void setList(ArrayList<ChessLocation> list){
        this.threateningLocations  = list;
    }
    /**
     * gets list
     */
    public ArrayList<ChessLocation> getList(){
        return this.threateningLocations;
    }
    /**
     * gets ID(no need to set because we dont want to change representation on board)
     */
    public char getId(){
        return this.id;
    }
    /**
     * toString (changes as per subclass)
     */
    public abstract String toString();
}
