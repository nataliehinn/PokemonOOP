import java.util.ArrayList;

public class Player {
    
    //instance variables
    private String myName;
    private int rank;
    private ArrayList<Pokemon> myPokemon = new ArrayList<Pokemon>();
    
    
    //value constructor
    public Player(String myName){
        this.myName = myName;
        this.rank = 0;
    }
    
    //getters
    public String getMyName(){
        return Colors.PURPLE+myName+Colors.RESET;
    }
    public int getRank(){
        return rank;
    }
    public ArrayList <Pokemon> getMyPokemon(){
        return myPokemon;
    }
    
    //setter
    public void setRank(){
        rank++;
    }
    
    //adding pokemon to the players collection
    public void addPokemon(Pokemon poke){
        myPokemon.add(poke);
    }
}