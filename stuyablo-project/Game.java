import java.util.*;

public class Game {

  boolean win;
  boolean dead;
  int day;
  String location;
  String cause;
  boolean acted;//once the player has acted, the day will pass
  
  public static void battle(Character c, Game g) { 
    boolean over = false;
    Random rand = new Random();
    Scanner sk = new Scanner(System.in);
    Character e = new Character();	//this is our enemy

    if ( g.location == "beach") {
      e = new Shark();
    }
    else if (g.location == "town"){
      if (rand.nextInt(2) == 0) {
        e = new Bum();
      }
      else {
        e = new Survivor();
      }
    }

    else {
      int x = rand.nextInt(3);
      if (x == 0) {
        e = new Bear();
      }
      else if (x == 1) {
        e = new Rat();
      }
      else {
        e = new Snake();
      }

    }

    while (!over) {
      System.out.println("You have been attacked! What do you do: ");
      System.out.println("1. Attack");
      System.out.println("2. Use an item?"); //i created rocks as an inventory item for this
      System.out.println("3. Try to run"); // give this a 50% chance of succeeding
      String sel = sk.nextLine();
      switch (sel) {
        case "1":
          c.attack(e); //have the character attack method print some lines
          break;
        case "2":
          if (c.inventory.contains("rock")) { //make this instantly win the fight
            System.out.println("In panic, you found a rock, threw it,");
            System.out.println("and killed the enemy!");
            e.health = 0;
            break;
          }
          else if (c.inventory.contains("sword")) {
            System.out.println("You swing your sword");
            c.attack *= 2;
            c.attack(e);
            c.attack /= 2;
          }
          else {
            System.out.println("You waste time looking");
          }
        case "3":
          if (rand.nextInt(2) == 0) {
            System.out.println("You ran away. Coward");
            over = true;
            break;
          }
          else {
            System.out.println("The enemy caught up with you");
            break;
          }
        default:
          System.out.println("");
          break;
        }
      
      e.attack(c);

      if (e.health < 1) {
        over = true;
      }
    }


  }

  public static boolean build(Character c) {
    boolean built = false;
    Scanner sn = new Scanner(System.in);
    String choice = "";
    if (c.inventory.contains("tools") && c.inventory.contains("planks") && c.inventory.contains("cloth")) {
      System.out.println("Do you want to build a boat?");
      System.out.println("1. for yes");
      System.out.println("2. for no");
      choice = sn.nextLine();
      if (choice == "1") {
        c.inventory.add("boat");
        c.inventory.remove("planks");
        c.inventory.remove("cloth");
      }
    }
    else if (c.inventory.contains("hide")) {
      System.out.println("Do you want to create cloth from animal hide?");
      System.out.println("1. for yes");
      System.out.println("2. for no");
      choice = sn.nextLine();
      if (choice == "1") {
        c.inventory.add("cloth");
        c.inventory.remove("hide");
      }
    }
    else if (c.inventory.contains("wood") && c.inventory.contains("iron")) {
      System.out.println("What do you want to build?");
      System.out.println("1. Tools");
      System.out.println("2. Sword");
      System.out.println("3. Planks");
      System.out.println("4. Finished?");
      c.inventory.remove("wood");
      c.inventory.remove("iron");
    }
    else {
      System.out.println("You do not have the materials needed.");
      built = false;
    }
    return built;
  }
  
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
  
  public void menu() {
    System.out.println("You are currently at: " + location);
    System.out.println("You can choose the following: ");
    System.out.println("1. Look at inventory and stats");
    System.out.println("2. You can look for food and supplies");
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
        System.out.println("Not a valid choice");//check for input validation
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
      battle(c, g);
      System.out.println("You obtained animal hide!");
      c.inventory.add("hide");
      
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
            if (build(c)) {
              g.acted = true;
            }
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