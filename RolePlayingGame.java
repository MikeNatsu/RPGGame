// Date:11/18/2020

import java.util.Scanner;
import java.lang.*;

public class RolePlayingGame 
{
	private int choice;
	private String player, weapon;
   public int health, swordDurability, shieldDurability, enemyHealth, bossHealth, potions, turn; 
   boolean sword, shield, shieldActive;
   
   boolean homeBed = true; 
   boolean wellRested = true;
   boolean foodSearch = true;
   boolean vampireTrap = false;
   
   
   Scanner input = new Scanner(System.in);
	
   public RolePlayingGame(String name) 
   {
  		/**  Implement the class constructor to assign instance variables
    	**/
	 		player = name;
         this.health = 100;  
         this.swordDurability = 10; 
         this.shieldDurability = 5; 
         this.sword = true; 
         this.shield = true;
         this.shieldActive = false; 
         potions = 1; 
   }

	public void beginning(){
         separateEvents();
			System.out.println("Your adventure is about to begin, our city has been invaded, grab your sword and shield.");
			System.out.println("Defeat all the monsters in front of you!!\n");
			System.out.print("Press 1 to continue :: ");
			input.nextInt();
         start();      
         
	}
   
   public void start(){
      separateEvents();
      System.out.println("You heard an explosion coming from the village, you decide to go and see what's happening...\n\n");
      System.out.print("Press 1 to continue :: ");
      input.nextInt();
	   crossRoad();
         
   }
	
	public void crossRoad()
   {
      
   
      separateEvents();
      currentHealth();
		System.out.println("You are at a crossroad.\n");
      
		System.out.println("1: Go north\n2: Go east\n3: Go south\n4: Go west\n");
      System.out.print("I want to :: ");
      
      choice = input.nextInt();
				
		if(choice==1)
			north();
		else if(choice==2)
			east();
		else if(choice==3)
			south();
		else if(choice==4)
			west();
		else
      {
			System.out.println(player + ", that's not a valid choice. \nTry again.\n");
			System.out.print("Press 1 to continue :: ");
         input.nextInt();
			crossRoad();
		}
	}
	
   public void separateEvents(){
      System.out.println("\n-----------------------------------------------------------------------------------\n");
   }
   
   //updates the Health points  
   public void currentHealth(){
      
      System.out.println("// HP: " + health + " Potions: "+potions + "//");
   }
   
   //consume potions
   public void usePotion(){
      if(potions > 0){
          health = health + 5;
          potions--;
          System.out.println("\n+5 HP"); 
      }else{
          System.out.println("You're out of potions");
      }
   }
   
   //check sword and shield
   public void checkItems(){
      
      if(swordDurability <= 0){
         sword = false;
         System.out.println("Your sword is broken, get another one ASAP!!");
      }
         
      if(shieldDurability <= 0){
         shield = false;
         System.out.println("Your shield has been broken");
      }
   }
   //check if you're still alive xd lol
   public void checkHealth(){
      if(health <= 0){
         separateEvents();
         currentHealth();
         System.out.println("\nGame Over\n");
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         health = 20; 
         beginning();
         
      }
   }
   
   //set enemy hp and random turn
   public void setBattle(){
      enemyHealth = ((int)(Math.random() * 5) + 10); // set maxhp to 15 to 10
      turn = ((int) (Math.random() * 2));  
   } 
   
   //set boss hp and random turn
   public void setBossBattle(){
      bossHealth = 100;
      turn = ((int) (Math.random() * 2));
      
   }
   
   public void zombieCombat(){
      checkHealth();
      checkItems();
      separateEvents();
      if(enemyHealth <= 0){
         int randomPotion = ((int)(Math.random() * 2) + 1); //earn one or two potions
         System.out.println("You killed the zombie, and obtained " + randomPotion+ " health potion");
         potions = potions + randomPotion; 
         
      }      
      if(turn == 1 && enemyHealth > 0){
         currentHealth();
         System.out.println("Your enemy HP: " + enemyHealth);
         System.out.println("You go first!! What are you going to do??!! ");
         if(swordDurability != 0){
            System.out.println("1: Attact it");
         }
         if(shieldDurability != 0 && shield == true){
            System.out.println("2: Protect me with the shield");
         }
         System.out.println("3: Do nothing!");
         if(potions > 0){
            System.out.println("4: Use a potion");
         }
         
         System.out.println();
         System.out.print("I want to:  ");
         choice = input.nextInt();
         
         if(choice == 1){
            enemyHealth = enemyHealth - 3; 
            swordDurability = swordDurability - 1;
            turn = 0;
            zombieCombat();
         }else if(choice == 2 && shield == true){
            shieldActive = true;
            turn = 0; 
            zombieCombat(); 
         }else if(choice == 3){
            turn = 0; 
            zombieCombat();
         }else if(choice == 4){
            usePotion();
            turn = 0; 
            zombieCombat();
            
         }else{
            System.out.println(player + ", that's not a valid choice. \nTry again.\n");
			   System.out.print("Press 1 to continue :: ");
            input.nextInt();
			   zombieCombat();
         }
         
      }else if(turn == 0 && enemyHealth > 0){
      
         if(shieldActive == false){
         
            currentHealth();
            System.out.println("Your enemy HP: " + enemyHealth); 
            System.out.println("The zombie bit you!!\n ");
            health = health - 2;
            turn = 1;
            System.out.print("Press 1 to continue :: ");
            input.nextInt();
            zombieCombat();
         }else{ 
            currentHealth();
            System.out.println("Your enemy HP: " + enemyHealth);
            System.out.println("Your shield protected you");
            shieldActive = false;
            turn = 1;
            System.out.print("Press 1 to continue :: ");
            input.nextInt(); 
            zombieCombat();
         }
         
      }
      
       
   }
      
   public void vampireCombat(){
      checkHealth();
      checkItems();
      separateEvents();
      if(enemyHealth <= 0){
         int randomPotion = ((int)(Math.random() * 2) + 1); //earn one or two potions
         System.out.println("You killed the zombie, and obtained " + randomPotion+ " health potion");
         potions = potions + randomPotion; 
         
      }      
      if(turn == 1 && enemyHealth > 0){
         currentHealth();
         System.out.println("Your enemy HP: " + enemyHealth);
         System.out.println("You go first!! What are you going to do??!! ");
         if(swordDurability != 0){
            System.out.println("1: Attact it");
         }
         if(shieldDurability != 0 && shield == true){
            System.out.println("2: Protect me with the shield");
         }
         System.out.println("3: Do nothing!");
         if(potions > 0){
            System.out.println("4: Use a potion");
         }
         
         System.out.println();
         System.out.print("I want to:  ");
         choice = input.nextInt();
         
         if(choice == 1){
            enemyHealth = enemyHealth - 3; 
            swordDurability = swordDurability - 1;
            turn = 0;
            vampireCombat();
         }else if(choice == 2 && shield == true){
            shieldActive = true;
            turn = 0; 
            vampireCombat(); 
         }else if(choice == 3){
            turn = 0; 
            vampireCombat();
         }else if(choice == 4){
            usePotion();
            turn = 0; 
            vampireCombat();
            
         }else{
            System.out.println(player + ", that's not a valid choice. \nTry again.\n");
			   System.out.print("Press 1 to continue :: ");
            input.nextInt();
			   vampireCombat();
         }
         
      }else if(turn == 0 && enemyHealth > 0){
      
         if(shieldActive == false){
         
            currentHealth();
            System.out.println("Your enemy HP: " + enemyHealth); 
            System.out.println("The zombie bit you!!\n ");
            health = health - 2;
            turn = 1;
            System.out.print("Press 1 to continue :: ");
            input.nextInt();
            vampireCombat();
         }else{ 
            currentHealth();
            System.out.println("Your enemy HP: " + enemyHealth);
            System.out.println("Your shield protected you");
            shieldActive = false;
            turn = 1;
            System.out.print("Press 1 to continue :: ");
            input.nextInt(); 
            vampireCombat();
         }
         
      }
      
   }
   
   //BossCombat
   public void bossCombat(){
      
      boolean alert = true;
      checkHealth();
      checkItems();
      separateEvents();
      
      if(bossHealth <= 50 && alert == true){
         separateEvents();
         alert = false;
         System.out.println("DRACULA IS MORE FURIOUS THAN EVER BEFORE!!!! HOLD YOUR POTIONS!!... ");
         System.out.println();
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
      }
      if(bossHealth <= 0){
         winner();         
      }      
      if(turn == 1 && bossHealth > 0){
         currentHealth();
         System.out.println("Your enemy HP: " + bossHealth);
         System.out.println("You go first!! What are you going to do??!! ");
         if(swordDurability != 0){
            System.out.println("1: Attact it");
         }
         if(shieldDurability != 0 && shield == true){
            System.out.println("2: Protect me with the shield");
         }
         System.out.println("3: Do nothing!");
         if(potions > 0){
            System.out.println("4: Use a potion");
         }
         
         System.out.println();
         System.out.print("I want to:  ");
         choice = input.nextInt();
         
         if(choice == 1){
            bossHealth = bossHealth - 3; 
            swordDurability = swordDurability - 1;
            turn = 0;
            bossCombat();
         }else if(choice == 2 && shield == true){
            shieldActive = true;
            turn = 0; 
            bossCombat(); 
         }else if(choice == 3){
            turn = 0; 
            bossCombat();
         }else if(choice == 4){
            usePotion();
            turn = 0; 
            bossCombat();
            
         }else{
            System.out.println(player + ", that's not a valid choice. \nTry again.\n");
			   System.out.print("Press 1 to continue :: ");
            input.nextInt();
			   bossCombat();
         }
         
      }else if(turn == 0 && bossHealth > 0){
      
         if(bossHealth <= 50 && shieldActive == false){
         
            currentHealth();
            System.out.println("Your enemy HP: " + bossHealth); 
            System.out.println("Dracula hit you!!\n ");
            health = health - 4;
            turn = 1;
            System.out.print("Press 1 to continue :: ");
            input.nextInt();
            bossCombat();
         }else if(shieldActive == false){
            currentHealth();
            System.out.println("Your enemy HP: " + bossHealth); 
            System.out.println("Dracula bit you!!\n ");
            health = health - 2;
            turn = 1;
            System.out.print("Press 1 to continue :: ");
            input.nextInt();
            bossCombat();

         }else{ 
            currentHealth();
            System.out.println("Your enemy HP: " + bossHealth);
            System.out.println("Your shield protected you");
            shieldActive = false;
            turn = 1;
            System.out.print("Press 1 to continue :: ");
            input.nextInt(); 
            bossCombat();
         }
         
      }

   } 
  
   
   //First Routes
	public void north()
   {     
         checkHealth();
         separateEvents();
         currentHealth();
			System.out.println("You're at the entrace of the city.");
         System.out.println("\n 1: Keep going Forward. \n 2: Go back \n 3: Sit and rest \n 4: Look for food \n");
         System.out.print("I want to :: ");
         
         choice = input.nextInt();
         
         if(choice == 1){
            cityCenter();    
         }else if(choice == 2){
            crossRoad();
         }else if(choice == 3){
            if(wellRested == true){
               wellRested();
            }else{
               separateEvents();
               System.out.println("You already took a break!! :)\n");
               System.out.print("Press 1 to continue :: ");
               input.nextInt();
               north();
               
            }
         }else if(choice == 4){
            health = health - ((int)(Math.random() * 10)); 
            searchFood();         
         }else{
            System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
            System.out.print("Press 1 to continue :: ");
            input.nextInt();
            crossRoad();       
         }
          
	}	
	
	public void west()
   { 
   
     
      separateEvents();
      if(vampireTrap == true){
         vampireTrap = false; 
         vampire();
      }
      currentHealth();
      System.out.println(player +", you arrived to your house.");
      System.out.println("1: Go inside  \n2: Go back \n");
      
      System.out.print("I want to :: ");
      
      choice = input.nextInt();
      if(choice == 1 ){
         home();
      }else if(choice == 2){
         crossRoad();
      }else{
         System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         west();       
      }
      
   }

	
	public void south()
   { 
      separateEvents();
      currentHealth();
      System.out.println("\nMmm... Looks like there's nothing here.\n");
      System.out.println("1: Go back\n");
      System.out.print("Press 1 to continue :: ");
      choice = input.nextInt();
      
      if(choice == 1 ){
          crossRoad();
      }else{
         System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         south();  
      }
   } 
   
   public void east()
   {
      separateEvents();
      currentHealth();
      System.out.println("There's a villager calling you, what you do you want to?\n");
      System.out.println("1: Talk to him \n2: Ignore him and go back\n");

      System.out.print("I want to :: ");
      choice = input.nextInt();
      if(choice == 1){
         villager();
        
      }else if(choice ==2){
         crossRoad();
      }else{
         System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         east();
      }
      
   } 
   
   
   //Going to north
   public void cityCenter(){
      zombie();
      System.out.print("Press 1 to continue :: ");
      input.nextInt();
      separateEvents();
      //next scene
      castle();
   }
   
   //castle
   public void castle(){
      System.out.println("The castle is in front of you, but there's also a cliff.");
      
      System.out.println("\n1: Jump \n2: Find another way \n3: Go back\n");
      System.out.print("I want to :: ");
      choice = input.nextInt();
      if(choice == 1){
         health -= 100;
         checkHealth();
      }else if(choice == 2){
         System.out.println("You found a special potion, the label says that it makes you jump higher than any human being.\n");
         System.out.println("1: Drink it and jump the cliff \n2: Leave it\n");
         System.out.print("I want to :: ");
         choice = input.nextInt();
         
         if(choice == 1){
            System.out.println("You're in the castle now, Hold on... OH NO!!");
            finalBoss();
         }else if(choice == 2){
            castle();
         }else{
            System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
            System.out.print("Press 1 to continue :: ");
            input.nextInt();
            castle();

         }
   
      }else if(choice == 3){
         crossRoad();
      }else{
         System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         castle();
      }

   }
   
   public void wellRested(){
      separateEvents();
      currentHealth();
      
      System.out.println("You found 2 potions while you were resting, Awesome!! \n");
      potions += 2;
      wellRested = false;
      System.out.println();
      System.out.print("Press 1 to continue :: ");
      input.nextInt();
      north();
   }
   
   public void searchFood(){
      separateEvents();
      currentHealth();
      System.out.println("You grabbed elderberries which made you sick. \n -7 HP\n");
      health -= 3;
      System.out.print("Press 1 to continue :: ");
      input.nextInt();
      north(); 
      
      
   }
   
   //going to west
   public void home(){
      separateEvents();
      currentHealth();
      System.out.println("sweet home, but I probably need to go back to the village\n");
      System.out.println("1: Lay in your bed \n2: Go back");
      System.out.print("I want to :: ");
      choice = input.nextInt();
      
      if(choice == 1 && homeBed == true ){
         separateEvents();
         currentHealth();
         potions += 1;
         homeBed = false;
         vampireTrap = true;
         System.out.println(player +": Hey!!, there's a potion here, I forgot I left one here before.");
         System.out.println("\n +1 potion\n");
         
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         west();  
         
         
      }else if(choice == 2){
         crossRoad();  
      }else if (choice == 1 && homeBed == false){
         separateEvents();
         currentHealth();
         System.out.println("I was already here, better get back to the village!!\n");
         
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         home();
      }else{
         System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         home();

      }   
     
   }  
  
  //villager
  public void villager(){
    separateEvents();
    currentHealth();
    System.out.println("Villager: Hi! Seems like you're going to the village,\n I saw a lot of monsters there. Fortunally, I'm giving free swords to those who have the courage to go to that village");
    System.out.println("Would you like one?");
    
    System.out.println("\n1: Yes \n2: No, thank you.\n");
    System.out.print("Answer :: ");
    choice = input.nextInt();
    if(choice == 1 && sword == false){
      separateEvents();
      sword = true;
      swordDurability = 10;
      System.out.println("You just obtained a new sword. Bravo :v");
      
      System.out.print("Press 1 to continue :: ");
      input.nextInt();
      crossRoad(); 
      
    }else if(choice == 2){
      separateEvents();
      System.out.println("Villager: Well, I didn't want to give it to you anyways :/ \n");
      System.out.print("Press 1 to continue :: ");
      input.nextInt();
      crossRoad();
    }else if(choice == 1 && sword ==true){
      separateEvents();
      System.out.println("Villager: Bruhh, you already have a sword :V Get out of here!!\n");
      System.out.print("Press 1 to continue :: ");
      input.nextInt();
      crossRoad();
    }else{
         System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         villager();

      }   

      
  }
  
   
   //Enemies
   public void vampire(){
      checkItems();
      separateEvents();
      currentHealth();
      System.out.println("A vampire appeared right in front of you.");
      System.out.println("\n1: Attack  \n2: Escape \n");
      System.out.print("I want to :: ");
      choice = input.nextInt();
      if(choice == 1){
         setBattle();
         vampireCombat();
      }else if(choice == 2){
         int randomNum = ((int) (Math.random() * 2));
         if(randomNum == 1){
            System.out.println("The vampire didn't let you go.");
            System.out.print("Press 1 to continue :: ");
            input.nextInt();
          
            setBattle();
            vampireCombat();
         }else if(randomNum == 0){
            separateEvents();
            System.out.println(player + ", you successfully escaped!");
            
         }else{
         System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         vampire();

      }   

      }else{
         System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         vampire();

      }   
    

   }
  
   
   public void zombie(){
      checkItems();
      separateEvents();
      currentHealth();
      System.out.println("A zombie appeared right in front of you.");
      System.out.println("\n1: Attack  \n2: Escape \n");
      System.out.print("I want to :: ");
      choice = input.nextInt();
      if(choice == 1){
         setBattle();
         zombieCombat();
      }else if(choice == 2){
         int randomNum = ((int) (Math.random() * 2));
         if(randomNum == 1){
            System.out.println("The zombie didn't let you go.");
            System.out.print("Press 1 to continue :: ");
            input.nextInt();
          
            setBattle();
            zombieCombat();
         }else if(randomNum == 0){
            separateEvents();
            System.out.println(player + ", you successfully escaped!");
           
         }else{
            System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
            System.out.print("Press 1 to continue :: ");
            input.nextInt();
            zombie();

         }   

      }else{
         System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         zombie();

      }   
   
    } 
    
    
    //FinalBoss LMAO
    public void finalBoss(){
      checkItems();
      separateEvents();
      currentHealth();
      System.out.println("DRACULA APPEARED!! AND HE'S FURIOUS!!!");
      System.out.println("\n1: Attack \n");
      System.out.print("I want to :: ");
      choice = input.nextInt();
      if(choice == 1){
         setBossBattle();
         bossCombat();
      }else{
         System.out.println(player + ", that's not a valid choice. \nTry Again.\n ");
         System.out.print("Press 1 to continue :: ");
         input.nextInt();
         finalBoss();

      }   
      
    }
    
    
    public void winner(){
      separateEvents();
      System.out.println("You killed dracula and saved the city congratulations :3 (THANKS FOR PLAYING :3)");
      System.out.println();
      System.out.print("Press 1 to continue :: ");
      input.nextInt();
      
      
      separateEvents();
      System.out.println("Alucard: "+ player + ", what have you done to him???... I'LL KILL YOUU!! ");
      separateEvents();
      //:3 Sorry xd I love ;-; this type of endings
      
    }
} 
      
      
      
            

