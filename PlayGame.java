import java.util.Scanner;
/**
 *  Plays the game.
 * 
 * @author Mustafa Abdulmajeed
 * @stud
 * @assignment Assignment #1
 * @description A simple chess game. game ends when king is conquered by enemy. Instructions are presented when running.
 * 
 */
public class PlayGame
{

    public static void main(String[] args){
        ChessGame newGame = new ChessGame("white", "black");    //current game
        ChessBoard board = newGame.getBoard();  //Chessboard
        boolean turn = true;//true = white chess pieces(player1), false = black chesspieces (player2)
        Scanner in = new Scanner(System.in);    //Scanner for user inputs
        String input;
        boolean quit = false;
        int row = 0, col = 0;
        King king1 = (King) board.getPieceAt(new ChessLocation(7,4));
        King king2 = (King) board.getPieceAt(new ChessLocation(0,4));
        ChessPiece selectedPiece = null;
        System.out.println("Hello! Welcome to 1 player chess! \n"+board.toString()+
            "r = rook, n = knight, b = bishop, q = queen, k = king, p = pawn\n"+
            "Start by inputting the coordinates of the piece you would like to\n"+
            "select, by typing in the row, then the column after. The rows and columns\n"+
            "are numbered for your convenience.\n");

        do{
            System.out.println("Type in the corresponding number for what you would like to do:\n1) Move\n2) Restart\n3) Quit");
            input = in.nextLine();
            switch(input){
                case "1":
                do{
                    System.out.println("Player "+(turn?1:2)+"'s turn. Please enter the column of the piece you would want to select, or type 'quit' to exit.");
                    input = in.nextLine();
                    if(input.equals("quit")) break;
                    col = Integer.parseInt(input);
                    while(col < 0 || col > 7)
                    {
                        System.out.println("Out of bounds! Enter a number between from 0 to 7:");
                        input = in.nextLine();
                        col = Integer.parseInt(input);
                    }
                    System.out.println("Now enter the row of the piece:");
                    input = in.nextLine();
                    row = Integer.parseInt(input);
                    while(row < 0 || row > 7)
                    {
                        System.out.println("Out of bounds! Enter a number between from 0 to 7:");
                        input = in.nextLine();
                        row = Integer.parseInt(input);
                    }
                    selectedPiece = board.getBoard()[row][col];
                    if(selectedPiece==null){
                        System.out.println("Nothing there. Try again.");
                        continue;
                    }
                    if(selectedPiece.getOwner()!=turn) {
                        System.out.println("You selected your opponent's chess piece. Try again.");
                        continue;
                    }
                }while(selectedPiece==null || selectedPiece.getOwner()!=turn);
                System.out.println("Selected piece: "+selectedPiece.id+" at location ("+selectedPiece.getLocation().getCol()+","+selectedPiece.getLocation().getRow()+") Now choose which column to move to:");
                input = in.nextLine();
                col = Integer.parseInt(input);
                while(col < 0 || col > 7)
                {
                    System.out.println("Out of bounds! Enter a number between from 0 to 7:");
                    input = in.nextLine();
                    col = Integer.parseInt(input);
                }
                System.out.println("Now enter the row where you want to move:");
                input = in.nextLine();
                row = Integer.parseInt(input);
                while(row < 0 || row > 7)
                {
                    System.out.println("Out of bounds! Enter a number between from 0 to 7:");
                    input = in.nextLine();
                    row = Integer.parseInt(input);
                }
                System.out.println();
                if(board.isKing(row, col)&&selectedPiece.getOwner()!=board.getPieceAt(new ChessLocation(row, col)).getOwner()){
                    System.out.println("Check mate, mate. "+(turn?"White":"Black")+" wins! Quit? (Y/N)");
                    String inp = in.nextLine();
                    switch(inp){
                        case "Y":
                        quit = true;
                        break;
                    }
                    if(quit) break;
                }
                if(selectedPiece.moveTo(new ChessLocation(row, col))){
                    if(selectedPiece.getId()=='k'){
                            king2.setLocation(new ChessLocation(row, col));
                    }
                    if(selectedPiece.getId()=='K'){
                        king1.setLocation(new ChessLocation(row, col));
                    }
                    
                    if(king1.check()!=null){
                            System.out.println("Player 1's king is under check by "+king1.check());
                        }
                    if(king2.check()!=null){
                        System.out.println("Player 2's king is under check by "+king2.check());
                    }
                    turn = !turn;
                    System.out.println(board.toString());
                }
                
                break;
                case "2":
                System.out.println("Starting a new game! The board is reset, and it is player 1's turn.\n");
                newGame = new ChessGame("white", "black");    //current game
                board = newGame.getBoard();  //Chessboard
                turn = true;
                System.out.println(board);
                break;
                case "3":
                quit = true;
                break;
            }


        }while(!quit);
        System.out.println("Thanks for playing!");
    }

}
