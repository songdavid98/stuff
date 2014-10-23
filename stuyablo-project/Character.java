public class Character {

  //variables
  String name;
  int health;
  int attack;
  int defense;
  int intelligence;
   
  String[] inventory;
   
  //Constructor 
  public Character(String n) {
    name = n;
    health = 10;
    defense = 10;
    intelligence = 10;
    attack = 10;
  }
  
  public Character() {
    name = "Default";
    health = 10;
    defense = 10;
    intelligence = 10;
    attack = 10;
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
  
  //Attack Class
 public void attack(Character c) {
    int damage = (attack - (defense / 2));
    if (damage <= 0) {
      damage = 1;
    }
    c.health -= damage;
  }
  
}