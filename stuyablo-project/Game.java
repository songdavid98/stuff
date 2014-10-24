import java.util.*;

public class Game {

  boolean win;
  boolean dead;
  int day;
  String location;
  String cause;
  boolean acted;//once the player has acted, the day will pass
  
  public Game() {
    day = 0;
    win = false;
    dead = false;
    location = "beach";
    cause = "";
    acted = false;
  }
  
  public void dayPassed() {
      System.out.println("A new day has started");
      day += 1;
  }
  
  public static void creation() {
    
  }
  
  public void menu() {
    System.out.println("You are currently at: " + location);
    System.out.println("You can choose the following: ");
    System.out.println("1. Look at inventory and stats");
    System.out.println("2. You can look for food and supples");
    System.out.println("3. Travel");
    System.out.println("4. Build something");
    System.out.println("5. Just quit: as in just die");
  }
  
  public static void main(String[] args) {
    Game g = new Game();
    ////////////////////////////Character creation
    Scanner scanner = new Scanner(System.in);
    String trk;
    String selection;
    
    System.out.println("*Boom* *Crash*");
    System.out.println("Your plane crashed.");
    System.out.println("You're on a beach.");
    System.out.println("With an ocean as far as the eye can see on one side.");
    System.out.println("And a huge forest on the other.");
    System.out.println("");
    
    System.out.print("What is your name? \n");
    trk = scanner.nextLine();
    System.out.println("Occupation?");
    System.out.println("1. Civilian");
    System.out.println("2. Engineer");
    System.out.println("3. Jock");
    selection = scanner.nextLine();
    Character c = new Character();
    switch(selection) {
      case "1":
        c =  new Civilian(trk);
        break;
      case "2":
        c = new Engineer(trk);
        break;
      case "3":
        c = new Jock(trk);
        break;
      default:
        System.out.println("Not a valid choice");
        creation();
        break;
    }
    
    
    System.out.println("You are alone on an island. Or are you?");
    System.out.println("Your mission is to survive, and hopefully, escape this isolated world.");
    System.out.println("You have five days worth of food, and a backpack (inventory).");
    System.out.println("Good luck!");
    System.out.println();
    ///////////////////////////////////////////////////////////
    
    while (!g.dead && !g.win) {
      g.dayPassed();
      
      Scanner scan = new Scanner(System.in);
      
      while (!g.acted) {
        g.menu();
        selection = scan.nextLine();
        
        switch (selection) {
          case "1":
            System.out.println("\n\n");
            System.out.println(c);
            System.out.println("\n\n");
            break;
            
          case "2"://randomly add food and supplies
            Random r = new Random();
            System.out.println("You take a quick look around");
            if (r.nextInt(3) == 0) {
              c.inventory.add("rock");
              System.out.println("You have found a rock");
            }
            System.out.println("You have thoroughly searched the area");
            System.out.println("Added to inventory: ");
            int event = r.nextInt(100);
            if (event > 50) {
              for (int i = -1; i < r.nextInt(2); i++) {
                  c.inventory.add("food");
                  System.out.println("food");
              }
            }
            else {
              c.inventory.add("food");
              System.out.println("food");
              if (r.nextInt(2) == 0) {
                c.inventory.add("wood");
                System.out.println("wood");
              }
              else {
                c.inventory.add("iron");
                System.out.println("iron");
              }  
              g.acted = true;
            }
            break;
            
          case "3"://travel
            System.out.println("Where do you want to travel:");
            System.out.println("1. Forest");
            System.out.println("2. Cave");
            System.out.println("3. Abandoned Town");
            System.out.println("4. Go back");
            selection = scan.nextLine();
            if (selection == "4") {}//do nothing, it will go back
            else {
              switch (selection) {
                case "1":
                  g.location = "forest";
                  break;
                case "2":
                  g.location = "cave";
                  break;
                case "3":
                  g.location = "town";
                  break;
                default:
                  System.out.println("You have failed to choose.");
                  g.acted = true;
                  break;
              }
              g.acted = !g.acted;
            }
            break;
            
          case "4"://build
            System.out.println("What do you want to build?");
            g.acted = true;
            break;
            
          case "5":
            g.cause += "You have quit. Loser";
            g.dead = true;
            g.acted = true;
            break;
          case "innerforcezlc"://basically a cheat code
            g.cause += "You have used David's cheat code and beat the game";
            g.win = true;
            g.acted = true;
            break;
            
          default:
            System.out.println("Not a valid choice");
            break;
        }
      }
      if (!g.dead) {
        System.out.println("The day is ending");
        System.out.println("You are starting to get hungry");
        if (!c.inventory.contains("food")) {
        if (c.health == 1) {
          g.cause += "You have died of hunger.";
          g.dead = true;
        }
        c.health -= 1;
        }
        
        else {
          System.out.println("You eat some food");
          System.out.println("You go to sleep\n\n\n\n");
          c.inventory.remove("food");
          if (c.health < c.maxHealth) {//regain health
            c.health += 1;
          }
        }
      }
      
      g.acted = false;//very important
    }    
    System.out.println(g.cause);
  }
}