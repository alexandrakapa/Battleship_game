/**
 * 
 */
package battleship;

import java.util.Scanner;

/**
 * @author alexandra
 *
 */

public class BattleshipGame {
	
	
	public static void main(String[] args){
		//System.out.println("Battleship game has started with an empty grid!");
	/*
		Grid mygrid2 = new Grid();
		try {
		mygrid2.PlaceShip(1,7,0,1); //blue ship
		mygrid2.PlaceShip(2,5,1,1); //purple
		mygrid2.PlaceShip(3,6,6,2); //red
		mygrid2.PlaceShip(4,1,1,1); //yellow  //we have initialised our board
		mygrid2.PlaceShip(5,3,8,2); //green
		}
		catch(OversizeException e)                       
		{
			//System.out.println("Exception thrown  :" + e);
		}
		catch(OverlapTilesException o)
		{
			//System.out.println("Exception thrown  :" + o);
		}
		catch(AdjacentTilesException a)
		{}
		catch(InvalidCountException i)
		{}
		catch(ArrayIndexOutOfBoundsException a)
		{
			System.out.println("Wrong placement!The grid is 10*10,sorry!");
		}
		mygrid2.shoot(5, 2);
		*/
		Grid playerGrid = new Grid(); //Initialize the two grids
		Grid computerGrid = new Grid();
		try {   //place the players ships 
			playerGrid.PlaceShip(1,7,0,1); //blue ship
			playerGrid.PlaceShip(2,5,1,1); //purple
			playerGrid.PlaceShip(3,6,6,2); //red
			playerGrid.PlaceShip(4,1,1,1); //yellow  //we have initialised our board
			playerGrid.PlaceShip(5,3,8,2); //green
		}
			catch(OversizeException e)                       
			{}
			catch(OverlapTilesException o)
			{}
			catch(AdjacentTilesException a)
			{}
			catch(InvalidCountException i)
			{}
			catch(ArrayIndexOutOfBoundsException a)
			{
				System.out.println("Wrong placement!The grid is 10*10,sorry!");
			}
		//System.out.println("Player has placed her ships!");
		try {
			computerGrid.PlaceShip(1,8,4,1); //blue ship
			computerGrid.PlaceShip(2,4,2,2); //purple
			computerGrid.PlaceShip(3,3,8,2); //red
			computerGrid.PlaceShip(4,1,6,2); //yellow
			computerGrid.PlaceShip(5,4,4,2); //green
			
		}
			catch(OversizeException e)                       
			{}
			catch(OverlapTilesException o)
			{}
			catch(AdjacentTilesException a)
			{}
			catch(InvalidCountException i)
			{}
			catch(ArrayIndexOutOfBoundsException a)
			{
				System.out.println("Wrong placement!The grid is 10*10,sorry!");
			}
		//System.out.println("Computer has placed his ships!");
		long r =  Math.round( Math.random() ); //we produce a random number (either 0 or 1) to see who plays first
		if (r == 0) { //computer plays first
			System.out.println("Computer plays first");
			while (computerGrid.shots != 0 && playerGrid.shots != 0) //each player has 40 moves
			{
				 playerGrid.computerShoot(); //the computer shoots
				 Scanner in = new Scanner(System.in);
				 System.out.print("Enter the coordinations (x,y) of the place you want to shoot at : ");
				 int row = in.nextInt();
				 int column = in.nextInt();
				computerGrid.shoot(row, column); //the player shoots
				System.out.println("Computer has : " + playerGrid.points + " points.Number of shots left : " + playerGrid.shots + ". Number of sunken ships: " + playerGrid.sunkenShips);
				System.out.println("Player has : " + computerGrid.points + " points.Number of shots left : " + computerGrid.shots + ". Number of sunken ships: " + computerGrid.sunkenShips);
				if (playerGrid.sunkenShips == 2 || computerGrid.sunkenShips == 2)
					break;
			}
		}
		else if (r == 1) { //player plays first
			System.out.println("Player plays first");
			while (computerGrid.shots != 0 && playerGrid.shots != 0) //each player has 40 moves
			{
				 Scanner in = new Scanner(System.in);
				 System.out.print("Enter the coordinations (x,y) of the place you want to shoot at : ");
				 int row = in.nextInt();
				 int column = in.nextInt();
				computerGrid.shoot(row, column); //the player shoots
				playerGrid.computerShoot(); //the computer shoots
				System.out.println("Player has : " + computerGrid.points + " points.Number of shots left : " + computerGrid.shots + ". Number of sunken ships: " + computerGrid.sunkenShips);
				System.out.println("Computer has : " + playerGrid.points + " points.Number of shots left : " + playerGrid.shots + ". Number of sunken ships: " + playerGrid.sunkenShips);
				if (playerGrid.sunkenShips == 2 || computerGrid.sunkenShips == 2)
					break;
			}
		}
		if (computerGrid.shots == 0 && playerGrid.shots == 0)
		{
			if (computerGrid.points > playerGrid.points) //the player has achieved more points on computers grid
				System.out.println("The player has won with " + computerGrid.points + " points!");
			else System.out.println("The computer has won with " + playerGrid.points + " points!");
		}
		else 
		{
			if (playerGrid.sunkenShips == 2) //the computer has sunk players ships in players grid
				System.out.println("The computer has won because he has sunken all players ships!");
			else System.out.println("The player has won because she has sunken all computers ships!");
		}
		
	}
}