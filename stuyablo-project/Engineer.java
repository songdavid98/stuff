public class Engineer extends Character {

  public Engineer() {
    intelligence = 15;
    inventory.add("tools");
  }

  public Engineer (String n){
    super(n);
    intelligence = 15;
    inventory.add("tools");
    inventory.add("iron");
  }
    
  public void attack(Character c) {
    super.attack(c);
	}
  
  public String toString() {
    System.out.println("Engineer");
    return super.toString();
  }
}