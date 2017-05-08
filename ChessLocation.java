
/**
 * location of a piece
 * 
 * @author Mustafa Abdulmajeed
 * 
 */
public class ChessLocation
{
    // instance variables - replace the example below with your own
    private int row, col;

    /**
     * Constructor for objects of class ChessLocation
     */
    public ChessLocation(int row, int col)
    {
        // initialise instance variables
        this.row = row;
        this.col = col;
    }
    //Mutators for ChessLocation
    public int getRow(){return row;}
    public int getCol(){return col;}
    public void setCoords(int row, int col){
        this.row = row;
        this.col = col;
    }
    /**
     * checks if cp is equal to this
     * @param compared cp
     */
    public boolean equals(ChessLocation cp){
        return (cp.getCol() == this.col && cp.getRow() == this.row);
    }
    //typiccal to string
    public String toString(){
        return "("+col+", "+row+")";
    }
}
