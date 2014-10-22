import java.util.*;

public class Game {

  int day;
  
  public static void main(String[] args) {
  
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
  
  }  

}