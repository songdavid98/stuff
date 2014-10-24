import java.util.*;

public class Game {

  int day;
  String location;
  
  public static void main(String[] args) {
  
    String checkpoint = "beach";
    boolean shelter = false; //true if you need shelter
  
    Scanner s = new Scanner(System.in);
    String t;
  
    System.out.println("*Boom* *Crash*");
    System.out.println("Your plane crashed.");
    System.out.println("You're on a beach.");
    System.out.println("With an ocean as far as the eye can see on one side.");
    System.out.println("And a huge forest on the other.");
    System.out.println("Where is everyone?");
    System.out.println("Press enter to continue:");
    
    System.out.print("");
    t = s.nextLine();
    
    System.out.println("You are alone on an island. Or are you?");
    System.out.println("Your mission is to survive, and hopefully, escape this isolated world.");
    System.out.println("You have five days worth of food, and a backpack (inventory).");
    System.out.println("Good luck!");
    System.out.println();
    System.out.print("What is your name? \n");
    t = s.nextLine();
    Character c = new Character(t);
    System.out.println("");
    System.out.println("These are your stats:");
    System.out.println(c.toString());
    while (checkpoint == "beach") {
    System.out.println("");
      System.out.println("You're on the beach. Where would you like to go?");
      System.out.println("Options: \n (1) To Ocean \n (2) To Forest");
      t = s.nextLine();
      if (t.equals("1")) {
        checkpoint = "ocean";
      }
      else if (t.equals("2")) {
        checkpoint = "forest_entrance";
      }
      else {
        System.out.println("That is not a valid choice");
      }
    }
    while (checkpoint == "ocean") {
      Random r = new Random();
      int x = r.nextInt(100);
      if (x < 10) {
        System.out.println("Storm coming");
        t = s.nextLine();
      }
      else if (x < 30) {
        System.out.println("You've been attacked!");
        //attack function
        t = s.nextLine();
      }
      else {
        System.out.println("Normal ocean, can go fishing if fishing pole is in inventory");
        t = s.nextLine();
      }
    }
    while (checkpoint == "forest_entrance") {
      System.out.println("You are now at the opening of a huge forest.");
      checkpoint = "beach";
    }
  }  

}