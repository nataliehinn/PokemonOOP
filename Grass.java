public class Grass extends Pokemon{

    public Grass(String name){
        super(Colors.GREEN+name+Colors.RESET, 50, 50, 50 ,300);
        // this.counter = 50; //subtracts 25 damage and attacks 25
        // this.attack = 50; //takes away hp
        // this.defense = 50; //subtracts damage taken
        // this.hp = 300
    }
}