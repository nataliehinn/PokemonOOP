public class Pokemon {
    
    //instance variables
    private String name;
    private int hp;
    private int counter;
    private int attack;
    private int defend;
    
    //value constructure
    public Pokemon(String name, int counter, int attack, int defend, int hp){
        this.name = name;
        this.counter = counter;
        this.attack = attack;
        this.defend = defend;
        this.hp = hp;
    }
    
    //to string method
    public String toString(){
        return name;
    }
    
    //getters
    public int getCounter(){
        return counter; 
    }
    public int getAttack(){
        return attack;
    }
    public int getDefend(){
        return defend;
    }
    public int getHp(){
        return hp;
    }
    public String getInfo(){
        return Colors.CYAN+"  HP: "+getHp()+ "\n  D - Defend: "+getDefend()+ "\n  A - Attack: "+getAttack()+"\n  C - Counter: "+getCounter()+Colors.RESET;
    }
    
    
}