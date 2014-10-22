public class Character {

  //variables
  String name;
  int health;
  int attack;
  int defense;
  int intelligence;
   
  String[] inventory;
  
  public Character() {
    name = "Default";
    health = 10;
    attack = 10;
    defense = 10;
    intelligence = 10;
  }
   
  public Character(String n) {
    name = n;
  }
  
  public String toString() {
    String s = "";
    s += "Name: " + name + "\n";
    s += "Health: " + health + "\n";
    s += "Intelligence: " + intelligence + "\n";
    s += "Attack: " + attack + "\n";
    s += "Defencse: " + defense;
    return s;
  }
  
  public static void main(String[] args) {
    Character c = new Character("James");
    
    System.out.println(c.toString());
  }
  
}
