import java.util.Scanner;
import java.util.ArrayList;
public class Driver {
    public static void main(String[] args) {
        
    
        //creating scanner
        Scanner reader = new Scanner(System.in); 
        
        //creating a game and filling it with possible pokemon battles
        Game game1 = new Game();
        game1.fillFighters();
        
        //prompting the user
        System.out.println("Welcome to Natalie & Sherlene's Pokemon game!\nWhat is your trainer name? ");
        String name = reader.nextLine();
        
        System.out.println();
        System.out.println("First, choose a starter Pokemon. This will be the Pokemon you use for your first fight(s) until you catch another!");
        System.out.println(" 1 - "+Colors.GREEN+"Bulbasaur   "+Colors.RESET+"(Grass Type)");
        System.out.println(" 2 - "+Colors.RED+"Charmander  "+Colors.RESET+"(Fire Type)");
        System.out.println(" 3 - "+Colors.BLUE+"Squirtle    "+Colors.RESET+"(Water Type)");
        System.out.println("Enter 1, 2, or 3: ");
        
        Player player1 = new Player(name);
        game1.setStarter(reader.nextInt(),player1);
        
        // Player player1 = new Player(name, starter);
        System.out.println();
        System.out.println("Great "+player1.getMyName()+"! Your first pokemon is "+(player1.getMyPokemon()).get(0)+" and your starting rank is 0.");
        
        System.out.println();
        System.out.println("Ready?! This is your field where you can enter coordinates to search for Pokemon! Battle them to gain rank and CATCH THEM ALL!");
        
        //generating the field
        Field field1 = new Field();
        
        System.out.println();
        String enter = "";
        reader.nextLine();
        
        //entering coord logic
        int j = 0;
        while(j>=0){
            Field.showBoard();
            System.out.println("Enter a coordinate to find Pokemon! (ie. A1)(enter \"quit\" to leave): ");
            String coord = reader.nextLine();
            coord = coord.substring(0,1).toUpperCase()+coord.substring(1);
            if(coord.equals("Quit")){
                System.out.println();
                System.out.println("Great job playing Pokemon!"+player1.getMyName()+" Hope to see you again!");
                System.out.println("These are your Pokemon: "+ player1.getMyPokemon());
                j=-1;
            }
            else if(game1.validEntry(coord)){
                System.out.println();
                field1.searchCoord(coord, game1, player1);
                j++;
            }
            else if(game1.validEntry(coord) == false){
                System.out.println();
                System.out.println(Colors.RED+"Invalid Entry"+Colors.RESET);
                System.out.println();
                j++;
            }
        }
    
    }
    
}