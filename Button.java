import java.awt.event.*;
import javax.swing.*;
/**
 * Write a description of class Button here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Button extends JButton implements ActionListener
{
    private GUI ref;
    int row;
    int col;
    public Button(int i, int c, GUI ref){
        this.ref = ref;
        row = i;
        col = c;
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        ChessPiece piece = ref.getBoard().getPieceAt(new ChessLocation(row, col));
        ChessPiece sP = ref.getSelectedPiece();
        if(piece == sP){
            ref.updateStatus("Cancelled selection");
            ref.setSelectedPiece(null);
        }
        if(sP == null){
            if(piece == null){
                ref.updateStatus("Nothing at ("+row+", "+col+")"); 
            }
            else {
                if(piece.getOwner() == ref.getPlayer()){
                    ref.updateStatus("Selected "+piece.toString());
                    ref.setSelectedPiece(piece);
                }else ref.updateStatus("Wrong player, try again!");
            }
        }
        else {
            ChessLocation prevLoc = sP.getLocation();
            if(piece == null){
                if(sP.getOwner() == ref.getPlayer()){
                    if(sP.moveTo(new ChessLocation(row, col))){
                        ref.switchIcon(prevLoc.getRow(), prevLoc.getCol(), row, col);
                        ref.setSelectedPiece(null);
                        ref.changeTurns();
                        ref.updateStatus("Move of "+sP.toString()+" is successful");
                    }else{
                        ref.updateStatus("Invalid move, try again, current selection: "+sP);
                    }
                } else ref.updateStatus("Wrong player, try again!");
            }
            else {
                if(sP.getOwner()!=piece.getOwner()&&sP.moveTo(new ChessLocation(row, col))){
                    ref.switchIcon(prevLoc.getRow(), prevLoc.getCol(), row, col);
                    ref.setSelectedPiece(null);
                    ref.changeTurns();
                    ref.updateStatus("Successful takedown");
                }
            }
        }
    }
}
