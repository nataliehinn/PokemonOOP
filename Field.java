import java.util.Scanner;
import java.util.Arrays;
public class Field {
    
    private static String[][] board = new String[5][10];
    
    //default makes the board
    public Field(){
        initializeBoard(board);
    }
    
    //printing out the board with its labels
    public static void showBoard(){
         System.out.println("   A  B  C  D  E  F  G  H  I  J");
        for(int i=0; i<board.length; i++){
            for(int k=0; k<board[i].length; k++){
                if(k==0){
                    System.out.print((i+1)+" ");
                }
                System.out.print(Colors.GREEN_BACKGROUND+Colors.MED_GREEN+" "+board[i][k]+" "+Colors.RESET);
            }
            System.out.println();
            
        }
    }
    
    //getting board
    public String[][] getBoard(){
        return board;
    }
    
    //intiializing the board
    public static void initializeBoard(String[][] board){
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                board[i][j] = "?";
            }
        }
    }
    
    //method to update board with ↟ and !
    public static void updateBoard(String [][] board, String cord, String marker){
        board[Integer.parseInt(cord.substring(1))-1][cord.charAt(0)-'A'] = marker;
        //"↟" no pokemon, just grass
        //"!" pokemon encouter
    }
    
    public void searchCoord(String coord, Game game1, Player player1){
        Scanner reader = new Scanner(System.in);
        int random = (int)(Math.random()*2);
        if(random == 0){
            System.out.println("No Pokemon here... Keep searching "+player1.getMyName()+"!");
            System.out.println();
            updateBoard(board, coord, Colors.DARK_GREEN+"↟"+Colors.RESET+Colors.MED_GREEN+Colors.GREEN_BACKGROUND);
        }
        else{
            Pokemon randPoke = game1.getPossPoke().get((int)(Math.random()*game1.getPossPoke().size()));
            System.out.println("You've encountered a wild "+randPoke);
            System.out.println(randPoke.getInfo());
            updateBoard(board, coord, Colors.RED_BOLD+"!"+Colors.RESET+Colors.GREEN_BACKGROUND+Colors.MED_GREEN);
            System.out.println("Do you want to BATTLE or RUN? ");
            String answer = reader.nextLine().toUpperCase();
            int j = 0;
            while(j!=-1){
                if(answer.equals("BATTLE")){
                    game1.startBattle(randPoke, player1);
                    j = -1;
                }
                else if(answer.equals("RUN")){
                    System.out.println("You're a coward. ");
                    j = -1;
                }
                else{
                    System.out.println("Your entry is invalid :(\nDo you want to BATTLE or RUN? ");
                    answer = reader.nextLine().toUpperCase();
                }
            }
        
        }
        
    }
    
}