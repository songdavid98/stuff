public class Policeman extends Character {
	
  public Policeman() {
		name = "Default";
    intelligence = 5;
    attack = 15;
  }

	public Policeman(String n) {
    name = "n";
    intelligence = 5;
    attack = 15;
  }
  
  public void attack(Character c) {
    super.attack(c);
  }
}