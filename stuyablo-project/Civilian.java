public class Civilian extends Character {

    public Civilian() {
	name = "Default";
    }

    public Civilian(String n) {
	name = "Default";
	health = 10;
	defense = 10;
	intelligence = 10;
	attack = 10;
    }


    public void attack(Character c) {
	attack(c);
    }

}
