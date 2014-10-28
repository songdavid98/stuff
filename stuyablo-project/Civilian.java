public class Civilian extends Character {

    public Civilian() {

    }

    public Civilian(String n) {
      super(n);
    }


    public void attack(Character c) {
      super.attack(c);
    }
    
    public String toString() {
      System.out.println("Civilian");
      return super.toString();
    }
}