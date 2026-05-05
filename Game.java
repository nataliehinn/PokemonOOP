import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    
    public ArrayList <String> coordsEntered = new ArrayList<String> ();
    private ArrayList <Pokemon> possPoke = new ArrayList<Pokemon>(9);
    
    //default constructor
    public Game(){
        
    }
    
    //scanner
    Scanner reader = new Scanner(System.in);
    
    //making the starter pokemon
    Pokemon Bulbasaur = new Grass("Bulbasaur");
    Pokemon Charmander = new Fire("Charmander");
    Pokemon Squirtle = new Water("Squirtle");
    
    //fire
    Pokemon Flareon = new Fire("Flareon");
    Pokemon Ponyta = new Fire("Ponyta");
    Pokemon Litleo = new Fire("Litleo");
    
    //water
    Pokemon Slowpoke = new Water("Slowpoke");
    Pokemon Psyduck = new Water("Psyduck");
    Pokemon Horsea = new Water("Horsea");
    
    //grass
    Pokemon Bellsprout = new Grass("Bellsprout");
    Pokemon Oddish = new Grass("Oddish");
    Pokemon Chikorita = new Grass("Chikorita");
    
    
    //filling the possible pokemon battle array with the pokemon you could fight
    public void fillFighters(){
        possPoke.add(Flareon);
        possPoke.add(Ponyta);
        possPoke.add(Litleo);
        possPoke.add(Slowpoke);
        possPoke.add(Psyduck);
        possPoke.add(Horsea);
        possPoke.add(Bellsprout);
        possPoke.add(Oddish);
        possPoke.add(Chikorita);
    }
    
    //gettter
    public ArrayList<Pokemon> getPossPoke(){
        return possPoke;
    }
    
    //making sure the starter selected is a valid input and adding the pokemon to their collection   
    public void setStarter(int input, Player player1){
        int i=0;
        while(i>=0){
            if(input == 1){
                player1.addPokemon(Bulbasaur);
                break;
            }
            else if(input == 2){
                player1.addPokemon(Charmander);
                break;
            }
            else if(input == 3){
                player1.addPokemon(Squirtle);
                break;
            }
            //prompting user when their choice does not match
            else{
                System.out.println("Invalid. Try again ");
                System.out.println("Enter 1, 2, or 3: ");
                input = reader.nextInt();
                i++;
            }
        }
    }
    
    //checking to see if the coords entered are valid to play the game
    public boolean validEntry(String entered){
        //making sure the coord they entered is even correct by length
        if(entered.length() == 2){
            //seeing if the character is from a to j
            if(((int)(entered.charAt(0))) >=65 && ((int)(entered.charAt(0)))<=74){
                //making sure the character is from 1 to 5
                if(((int)(entered.charAt(1))>= 49 && ((int)(entered.charAt(1))<=53))){
                    //making sure the user did not previously enter the same coordinate
                    if(sequentialSearch(coordsEntered, entered)){
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }
    
    //making a sequential search method
    public static boolean sequentialSearch (ArrayList<String> coordsEntered, String target){
        for(int i = coordsEntered.size()-1; i>=0; i--){
            if(coordsEntered.get(i).equals(target)){
                return false;
            }
        }
        
        //adding the coordinates enetered from the player into an arraylist to keep track of spaces
        coordsEntered.add(target);
        return true;
    }
    
    //game logic for starting a battle with choosing abilities
    public void startBattle(Pokemon randPoke, Player player1){
        //printing out the players options in pokemon
        reader.nextLine();
        System.out.println("\nSelect which pokemon to battle with: ");
        for(int i = 0; i<(player1.getMyPokemon()).size(); i++){
            System.out.println("    "+i+ " - " +(player1.getMyPokemon()).get(i));
        }
        
        System.out.println();
        String pokeIntChose = reader.nextLine();
        
        int r = 1;
        while(r>-1){
            if(pokeIntChose.length() == 1){
                if((Integer.parseInt(pokeIntChose)) >= 0 && (Integer.parseInt(pokeIntChose))<player1.getMyPokemon().size()){
                    r=-1;
                }
                System.out.println("That is not an option, enter which Pokemon you want to use to battle with according to it's integer: ");
                pokeIntChose = reader.nextLine();
                System.out.println();
                r++;
            }
            System.out.println("That is not an option, enter which Pokemon you want to use to battle with according to it's integer: ");
            pokeIntChose = reader.nextLine();
            System.out.println();
            r++;
    
        }
        //variables for method
        Pokemon pokemonChose = (player1.getMyPokemon()).get((Integer.parseInt(pokeIntChose)));
        
        reader.nextLine();
        int myHp = pokemonChose.getHp();
        int oppHp = randPoke.getHp();
        
        //talking to player and outputting the pokemon's inputs
        System.out.println();
        System.out.println("Great! You chose "+ pokemonChose+"! Here is your starting HP and your abilities: ");
        System.out.println(pokemonChose.getInfo());
        System.out.println();
        System.out.println("Attack subtracts your opponent's HP.\nDefend lessens the damage taken to your pokemon's HP.\nCounter subtracts your opponent's HP while lessening the damage taken to your pokemon's HP\n"); 
        
        //looping options for choosing ability while the pokemons still have health
        while(myHp > 0 && oppHp > 0){
            System.out.println("Select an ability (D, A, C): ");
            String myAbChose = reader.nextLine().toUpperCase();
            String oppAbChose = oppAbility();
            System.out.println();
            if(myAbChose.equals("A")){
                if(oppAbChose.equals("D")){
                    if((pokemonChose.getAttack() - randPoke.getDefend()) > 0){
                        oppHp -= (pokemonChose.getAttack() - randPoke.getDefend());
                    }
                    System.out.println(pokemonChose+" uses ATTACK: "+Colors.RED+"-"+pokemonChose.getAttack()+Colors.RESET);
                    System.out.println(randPoke+" uses DEFEND: "+Colors.GREEN+"+"+randPoke.getDefend()+Colors.RESET);
                }
                else if(oppAbChose.equals("C")){
                    oppHp -= (pokemonChose.getAttack() - randPoke.getCounter()/2);
                    myHp -= randPoke.getCounter()/2;
                    System.out.println(pokemonChose+" uses ATTACK: "+Colors.RED+"-"+pokemonChose.getAttack()+Colors.RESET);
                    System.out.println(randPoke+" uses COUNTER: "+Colors.RED+"-"+randPoke.getCounter()/2+Colors.GREEN+" +"+randPoke.getCounter()/2+Colors.RESET);
                }
                else if(oppAbChose.equals("A")){
                    oppHp -= pokemonChose.getAttack();
                    myHp -= randPoke.getAttack();
                    System.out.println(pokemonChose+" uses ATTACK: "+Colors.RED+"-"+pokemonChose.getAttack()+Colors.RESET);
                    System.out.println(randPoke+" uses ATTACK: "+Colors.RED+"-"+randPoke.getAttack()+Colors.RESET);
                }
            }
            else if(myAbChose.equals("D")){
                if(oppAbChose.equals("A")){
                    if((randPoke.getAttack() - pokemonChose.getDefend()) > 0){
                        myHp -= (randPoke.getAttack() - pokemonChose.getDefend());
                    }
                    System.out.println(pokemonChose+" uses DEFEND: "+Colors.GREEN+"+"+pokemonChose.getDefend()+Colors.RESET);
                    System.out.println(randPoke+" uses ATTACK: "+Colors.RED+"-"+randPoke.getAttack()+Colors.RESET);
                }
                else if(oppAbChose.equals("C")){
                    if((randPoke.getCounter()/2 - pokemonChose.getDefend()) > 0){
                        myHp -= (randPoke.getCounter()/2 - pokemonChose.getDefend());
                    }
                    System.out.println(pokemonChose+" uses DEFEND: "+Colors.GREEN+"+"+pokemonChose.getDefend()+Colors.RESET);
                    System.out.println(randPoke+" uses COUNTER: "+Colors.RED+"-"+randPoke.getCounter()/2+Colors.GREEN+" +"+randPoke.getCounter()/2+Colors.RESET);
                }
                else if(oppAbChose.equals("D")){
                    System.out.println(pokemonChose+" uses DEFEND: "+Colors.GREEN+"+"+pokemonChose.getDefend()+Colors.RESET);
                    System.out.println(randPoke+" uses DEFEND: "+Colors.GREEN+"+"+randPoke.getDefend()+Colors.RESET);
                }
            }
            else if(myAbChose.equals("C")){
                if(oppAbChose.equals("A")){
                    if((randPoke.getAttack() - pokemonChose.getCounter()/2) > 0){
                        myHp -= (randPoke.getAttack() - pokemonChose.getCounter()/2);
                    }
                    oppHp -= (pokemonChose.getCounter()/2);
                    System.out.println(pokemonChose+" uses COUNTER: "+Colors.RED+"-"+pokemonChose.getCounter()/2+Colors.GREEN+" +"+pokemonChose.getCounter()/2+Colors.RESET);
                    System.out.println(randPoke+" uses ATTACK: "+Colors.RED+"-"+randPoke.getAttack()+Colors.RESET);
                }
                else if(oppAbChose.equals("C")){
                    if((randPoke.getCounter()/2 - pokemonChose.getCounter()/2) > 0){
                        myHp -= (randPoke.getCounter()/2 - pokemonChose.getCounter()/2);
                    }
                    if((pokemonChose.getCounter()/2 - randPoke.getCounter()/2) > 0){
                        oppHp -= (pokemonChose.getCounter()/2 - randPoke.getCounter()/2);
                    }
                    System.out.println(pokemonChose+" uses COUNTER: "+Colors.RED+"-"+pokemonChose.getCounter()/2+Colors.GREEN+" +"+pokemonChose.getCounter()/2+Colors.RESET);
                    System.out.println(randPoke+" uses COUNTER: "+Colors.RED+"-"+randPoke.getCounter()/2+Colors.GREEN+" +"+randPoke.getCounter()/2+Colors.RESET);
                }
                else if(oppAbChose.equals("D")){
                    if((pokemonChose.getCounter()/2 - randPoke.getDefend()) > 0){
                        oppHp -= (pokemonChose.getCounter()/2 - randPoke.getDefend());
                    }
                    System.out.println(pokemonChose+" uses COUNTER: "+Colors.RED+"-"+pokemonChose.getAttack()+Colors.RESET);
                    System.out.println(randPoke+" uses DEFEND: "+Colors.GREEN+"+"+randPoke.getDefend()+Colors.RESET);
                }
            }
            else{
                System.out.println("Invalid Entry. Try again.");
            }
            
            //updating the user of the stats
            System.out.println();
            System.out.println("Your Pokemon's updated HP: " + Colors.CYAN +myHp+ Colors.RESET);
            System.out.println("Your Opponent's updated HP: "+ Colors.CYAN +oppHp+ Colors.RESET+"\n");
        }
        if(myHp <= 0 && oppHp > 0){
            System.out.println("You lost! Nice try "+player1.getMyName()+". Your rank will stay the same :(\nRank: "+player1.getRank());
            System.out.println();
            
        }
        else if(oppHp <= 0 && myHp > 0){
            player1.setRank();
            System.out.println("You win! Good job "+player1.getMyName()+". Your rank will increase by 1 :)\nRank: "+player1.getRank());
            System.out.println("Would you like to catch "+randPoke+"? ");
            String input = reader.nextLine().toUpperCase();
            
            int m = 0;
            while(m!=-1){
                if(input.equals("YES")){
                    player1.addPokemon(randPoke);
                    System.out.println("Your pokemon: "+player1.getMyPokemon());
                    m=-1;
                }
                else if(!(input.equals("NO"))){
                    System.out.println("Invalid Entry.");
                    System.out.println("Would you like to catch "+randPoke+"? \nEnter a \"yes\" or \"no\" ");
                    input = reader.nextLine().toUpperCase();
                    m++;
                }
            }
            System.out.println();
        }
        else{
            System.out.print("You tied! Your rank stay the same :(");
            System.out.println();
        }
    }
    
    //random generating an option for ability
    public String oppAbility(){
        int num = (int)((Math.random()*3)+1);
        if(num == 1){
            return "A";
        }
        else if(num == 2){
            return "D";
        }
        
        return "C";
    }
   
}