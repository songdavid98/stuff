public class Engineer extends Character {
    public Engineer (String n){
   	 super(n);
   	 health = 10;
   	 defense = 10;
   	 intelligence = 15;
   	 attack = 10;
    }
    public void attack(Character c) {
    super.attack(c);
	}
}