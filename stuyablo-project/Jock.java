public class Jock extends Character {
	
  public Jock() {
		name = "Default";
    intelligence = 1;
    attack = 15;
    health = 20;
  }

	public Jock(String n) {
    name = "n";
    intelligence = 1;
    attack = 15;
    health = 20;
	}
   
  public void attack(Character c) {
    super.attack(c);
  }
}