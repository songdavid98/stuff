public class Civilian extends Character {

    public Civilian() {
	super.name = "Default";
	super.health = 10;
	super.defense = 10;
	super.intelligence = 10;
	super.attack = 10;
    }

    public Civilian(String n) {
	super.name = n;
	super.health = 10;
	super.defense = 10;
	super.intelligence = 10;
	super.attack = 10;
    }


    public void attack(Character c) {
	super.attack(c);
    }

}
