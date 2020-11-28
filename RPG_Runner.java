// Name:Michael Gonzalez Santamaria
// Date:11/19/2020

import java.util.Scanner;

public class RPG_Runner 
{
	public static void main(String[] args) 
   {
		Scanner keyboard = new Scanner(System.in);
      System.out.print("Enter your name :: ");
      String name = keyboard.nextLine();
      
      //Instantiate a new game object
      RolePlayingGame game = new RolePlayingGame(name);  
      //Call a method on the game object to start the player at that location
      game.beginning();
	}
}
