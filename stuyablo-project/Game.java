import java.util.*;

public class Game {
  
  String kill;
  boolean win;
  boolean dead;
  int day;
  String location;
  String cause;
  boolean acted;//once the player has acted, the day will pass
  
  public int countItems(ArrayList<String> list, String item) {
    int count = 0;
    for (int i = 0; i < list.size(); i++) {
      if (item.equals(list.get(i))) {
        count++;
      }
    }
    return count;
    
  }
  
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
      boolean done = false;
      while (!done) {
        System.out.println("You have been attacked! What do you do: ");
        System.out.println("1. Attack");
        System.out.println("2. Use an item?"); //i created rocks as an inventory item for this
        System.out.println("3. Try to run"); // give this a 50% chance of succeeding
        System.out.println("4. Commit suicide?");
        String sel = sk.nextLine();
        switch (sel) {
          case "1":
            c.attack(e); //have the character attack method print some lines
            done = true;
            break;
          case "2":
            if (c.inventory.contains("rock")) { //make this instantly win the fight
              System.out.println("In panic, you found a rock, threw it,");
              System.out.println("and killed the enemy!");
              e.health = 0;
              done = true;
              break;
            }
            else if (c.inventory.contains("sword")) {
              System.out.println("You swing your sword");
              c.attack *= 2;
              c.attack(e);
              c.attack /= 2;
              done = true;
            }
            else {
              System.out.println("You waste time looking");
              done = true;
            }
          case "3":
            if (rand.nextInt(2) == 0) {
              System.out.println("You ran away. Coward");
              done = true;
              over = true;
              break;
            }
            else {
              System.out.println("The enemy caught up with you");
              done = true;
              break;
            }
          default:
            System.out.println("Invalid input\n\n");
            break;
        }
      }
      
      if (e.health < 1) {
        over = true;
      }
      
      e.attack(c);
      
      if (c.health < 1) {
        g.cause += "You were killed in action!";
        g.dead = true;
        over = true;
      }
    }
    //after the battle
    g.kill = e.name;
    if ((g.kill == "bum") || (g.kill == "survivor")) {
      System.out.println("You obtained iron")
      c.inventory.add("iron");
    }
    else {
      System.out.println("You obtained animal hide");
      c.inventory.add("hide");
    }
    System.out.println("You obtained food!");
    c.inventory.add("food");
  }

  public static boolean build(Character c, Game g) {
    boolean built = false;
    Scanner sn = new Scanner(System.in);
    String choice = "";
    if (c.inventory.contains("tools") && c.inventory.contains("motor") &&  (10 <= g.countItems(c.inventory, "plank")) && (10 <= g.countItems(c.inventory, "cloth"))) {
      System.out.println("Do you want to build a boat?");
      System.out.println("1. for yes");
      System.out.println("Anything else for no");
      choice = sn.nextLine();
      if (choice == "1") {
        c.inventory.add("boat");
        System.out.println("You have a boat. Go use it.");
        for (int i = 0; i < 10; i++) {
          c.inventory.remove("plank");
          c.inventory.remove("cloth");
          built = true;
        }
      }
    }
    if (c.inventory.contains("hide")) {
      System.out.println("Do you want to create cloth from animal hide?");
      System.out.println("1. for yes");
      System.out.println("Anything else for no");
      choice = sn.nextLine();
      if (choice == "1") {
        c.inventory.add("cloth");
        c.inventory.remove("hide");
        built = true;
      }
    }
    boolean finito = false;
    while (!finito) {
      if (c.inventory.contains("wood") || c.inventory.contains("iron")) {
        System.out.println("What do you want to build?");
        System.out.println("1. Tools (build this first)");
        System.out.println("2. Sword");
        System.out.println("3. Planks");
        System.out.println("4. Motor");
        System.out.println("5. Finished?");
        choice = sn.nextLine();
        switch (choice) {
          case "1":
            if (c.inventory.contains("wood") && c.inventory.contains("iron")) {
              c.inventory.add("tools");
              c.inventory.remove("wood");
              c.inventory.remove("iron");
              finito = true;
            }
            else {
              System.out.println("Not enough materials");
            }
            break;
          case "2":
            if (c.inventory.contains("wood") && c.inventory.contains("tools") && (2 <= g.countItems(c.inventory, "iron"))) {
              c.inventory.add("sword");
              c.inventory.remove("wood");
              for (int i = 0; i < 2; i++) {
                c.inventory.remove("iron");
              }
              finito = true;
            }
            else {
              System.out.println("Not enough materials");
            }
            break;
          case "3":
            if (c.inventory.contains("tools") && (3 <= g.countItems(c.inventory, "wood"))) {
              c.inventory.add("plank");
              for (int i = 0; i < 3; i++) {
                c.inventory.remove("wood");
              }
              finito = true;
            }
            else {
              System.out.println("Not enough materials");
            }
            break;
          case "4":
            if (c.inventory.contains("tools") && (10 <= g.countItems(c.inventory, "iron"))) {
              c.inventory.add("motor");
              for (int i = 0; i < 10; i++) {
                c.inventory.remove("iron");
              }
              finito = true;
            }
            else {
              System.out.println("Not enough materials");
            }
            break;
          case "5":
            System.out.println("Exited");
            finito = true;
            break;
          default:
            System.out.println("Invalid input");
            break;
        }
      }
    }
    
    if (built == false) {
      System.out.println("You do not have the materials needed.");
    }
    return built;
  }
  
  public Game() {
    kill = "";
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
    System.out.println("5. Rest for a day");
    System.out.println("6. Just quit: as in just die");
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
    
    boolean chosen = false;
    while (!chosen) {
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
          chosen = true;
          break;
        case "2":
          c = new Engineer(trk);
          chosen = true;
          break;
        case "3":
          c = new Jock(trk);
          chosen = true;
          break;
        default:
          System.out.println("Not a valid choice\n\n");
          break;
      }
    }
    
    System.out.println("You are alone on an island. Or are you?");
    System.out.println("Your mission is to survive, and hopefully, escape this isolated world.");
    System.out.println("You have five days worth of food, and a backpack (inventory).");
    System.out.println("Good luck!");
    System.out.println();
    ///////////////////////////////////////////////////////////GAME START
    
    while (!g.dead && !g.win) {
      g.dayPassed();
      battle(c, g);
      
      Scanner scan = new Scanner(System.in);
      while (!g.acted) {
        if (location == "beach" && c.inventory.contains("boat")) {
          System.out.println("Do you want to sail away?");
          System.out.prinlln("1. to sail away\nAnything else to stay");
          String decision = scan.nextLine();
          if (decision == "1") {
            g.win = true;
            g.cause = "You sailed away into the deep blue\nTo be continued...";
            g.acted = true;
          }
        }
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
            if (event <= 70) {
              System.out.println("You didn't find anything");
            }
            else if (event > 70) {
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
            
          case "5"://rest
            if (c.health < c.maxHealth) {//regain health
              c.health += 3;
            }
            System.out.println("You have rested");
            g.acted = true;
            break;
          
          case "6":
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
      ///////////////////////end of day, after acted
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
