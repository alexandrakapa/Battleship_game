package battleship;

import java.util.Arrays;




public class Grid {
	Ship[][] ships = new Ship [11][11]; //we have a 10*10 grid with ships/empty ships
	public int shots; //shot of the user -> max 40
	int sunkenShips; //the number of sunken ships ->max 5
	int aliveShips;
	boolean [] types = new boolean[6]; //an array that holds what types of ships have been places -> eg if type[1]==true then we have already placed a carrier
	public int points = 0; //the points the player has 
	boolean firstsuccessfulhit=true; //we want to see the first successful hit of the computer
	int [] start = new int[3]; //in this array we have keep the coordinates [x,y,z] of the first successful hit
								//in order to use it form right to left and down to up
	int [] immediateprev = new int[3]; //in this array we keep the coordinates [x,y,z] of the immediate previous hit
	boolean prevright=false; //if prevright == true then we have a successful hit but we are now on the end of the ship 
						//so we have to go back to the start
	boolean goleft = false; //to know that we go left
	boolean goup = false; //to know that we go up 
	int [][] computerLastFive = new int [41][4]; //here we keep the shots of computer [i][0]: x coordinate,[i][1]:y coordinate,[i][2]:hit(1) or miss(0),[i][3]:type of ship
	int m = 0; //counter for the above array
	int [][] playerLastFive = new int [41][4]; //here we keep the shots of player [i][0]: x coordinate,[i][1]:y coordinate,[i][2]:hit(1) or miss(0),[i][3]:type of ship
	int n = 0; //counter for the above array
	boolean [] shipsSunk = new boolean[5]; //an array to show the condition of computer's ships, shipsSunk[0]:carrier,shipsSunk[1]:battleship,shipsSunk[2]:cruiser
										 //shipsSunk[3]:submarine,shipsSunk[4]:destroyer, when we have true then that ships has been sunk  
	boolean isAlreadyHit = false; //we want to see if the ship is already hit
	boolean [] countedBeforeAsSunken = new boolean[6]; //this array holds information about whether a type of ship has been counted as sunken before
														//i.e. if countedBeforeAsSunken[1] == true then we have already counted carrier as sunken
	int [][] randomPlacement = new int[5][4]; //we want an array to keep the random placement values
	boolean [][] hasComputerShoot = new boolean [11][11]; //an array to keep if the computer has shoot there,if true then yes
	
	
	/**
	 * The constructor of the Grid
	 */
	public Grid() {
		for (Ship[] row : ships) 
            Arrays.fill(row, new NoShip());  //initialize an array with empty spots
		shots = 40; //we begin with 40 shots
		sunkenShips = 0; //we begin with 0 sunken ships
		aliveShips = 5; //we begin with 5 alive ships
		types[0]=true; //We don't care about no ship
		immediateprev[0] = 30; //we put a very big number to help us out
	}
	
	
	/*
	 * Initializes an empty grid
	 */
	public void emptyGrid() { //used to empty the grid
		for (Ship[] row : ships) 
            Arrays.fill(row, new NoShip());  //initialize an array with empty spots
		shots = 40; //we begin with 40 shots
		sunkenShips = 0; //we begin with 0 sunken ships
		aliveShips = 5; //we begin with 5 alive ships
		points = 0;
		firstsuccessfulhit=true;
		types[0]=true; //We don't care about no ship
		immediateprev[0] = 30; //we put a very big number to help us out
		prevright = false;
		m = 0;
		n = 0;
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 41; j++)
			{
				computerLastFive[j][i] = 0;
				playerLastFive[j][i] = 0;
			}
		}
		for (int i = 0; i < 6; i++)
		{
			types[i]=false;
		}
		for (int i = 0; i < 5; i++)
		{
			shipsSunk[i]=false;
		}
		for (int i = 0; i < 3; i++)
			{
			start[i] = 0;
			immediateprev[i]= 0; 
			}
	}
	
	/*
	 * A function to place the ships
	 * @param type 	 		the type of the ship we want to place
	 * @param row 	 		the row of the ship we want to place
	 * @param column 		the column of the ship we want to place
	 * @param orientation	the orientation of the ship we want to place
	 */
	public void PlaceShip(int type, int row, int column, int orientation) throws OversizeException, OverlapTilesException,AdjacentTilesException,InvalidCountException{
		if (types[type]==true) throw new InvalidCountException();//"Ooops!You have already placed a ship of type " + type + "!");
		int frow = row;  //we keep the variables because they change
		int fcolumn = column;  
		switch(type) {
		case 1:
		 if (orientation == 1)
		{
			for (int i = 0; i < 5; i++) //we have to check if it overlaps another ship and if yes throw error
			{
				if (ships[row][column + i].getLength() != 1) throw new OverlapTilesException();//not empty spot,throw exception
			}
			
			for (int i = 0; i < 5; i++) //we have to check if it is adjacent to another ship(above and down) and if yes throw error
			{
				if (row != 0 && ships[row-1][column+i].getLength() != 1) throw new AdjacentTilesException();//we check the above tiles
				else if (row != 9 && ships[row+1][column+i].getLength() != 1) throw new AdjacentTilesException();//we check the down tiles
			}
			
			for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(left and right) and if yes throw error
			{
				if (column != 0 && ships[row+i-1][column-1].getLength() != 1) throw new AdjacentTilesException(); //we check the left tiles)
				else if (column != 9 && ships[row+i-1][column+5].getLength() != 1) throw new AdjacentTilesException();//we check the right tiles)
			}
			
			 
			if (row > 9 || column + 4 > 9) throw new OversizeException(); //out of grid,throw exception
			 
			for (int i = column ;i < column + new Carrier().getLength(); i++)
					{
				ships[row][i]=new Carrier();
				ships[row][i].setRow(frow);          //we set the start row and the start column of each ship
				ships[row][i].setColumn(fcolumn);    //so that we know the start of every ship and the spots
				ships[row][i].setType(type);			//associated with that
				ships[row][i].setOrientation(orientation);
				}
		}
		else if (orientation == 2) 
		{
			
			for (int i = 0; i < 5; i++) //we have to check if it overlaps another ship and if yes throw error
			{
				if (ships[row + i][column].getLength() != 1) throw new OverlapTilesException();//not empty spot,throw exception
			}
			

			for (int i = 0; i < 5; i++) //we have to check if it is adjacent to another ship(left and right) and if yes throw error
			{
				if (column != 0 && ships[row+i][column-1].getLength() != 1) throw new AdjacentTilesException(); //we check the left tiles
				else if (column != 9 && ships[row+i][column+1].getLength() != 1) throw new AdjacentTilesException();//we check the right tiles
			}
			
			for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(above and down) and if yes throw error
			{
				if (row != 0 && ships[row-1][column+i-1].getLength() != 1) throw new AdjacentTilesException(); //we check the above tiles)
				else if (row != 9 && ships[row+5][column+i-1].getLength() != 1) throw new AdjacentTilesException(); //we check the down tiles)
			}
			
			
			if (column > 9 || row + 4 > 9) throw new OversizeException(); //out of grid,throw exception
			
			for (int i = row; i < row + new Carrier().getLength(); i++)
				{
				ships[i][column]=new Carrier();
				ships[i][column].setRow(frow);          //we set the start row and the start column of each ship
				ships[i][column].setColumn(fcolumn);    //so that we know the start of every ship and the spots
				ships[i][column].setType(type);			//associated with that
				ships[i][column].setOrientation(orientation);
				}
		}
		types[1]=true; //we have placed a carrier
		break;
		case 2:
			if (orientation == 1)
			{
				
				for (int i = 0; i < 4; i++) //we have to check if it overlaps another ship and if yes throw error
				{
					if (ships[row][column + i].getLength() != 1) throw new OverlapTilesException(); //not empty spot,throw exception
				}
		
				for (int i = 0; i < 4; i++) //we have to check if it is adjacent to another ship(above and down) and if yes throw error
				{
					if (row != 0 && ships[row-1][column+i].getLength() != 1) throw new AdjacentTilesException(); //we check the above tiles
					else if (row != 9 && ships[row+1][column+i].getLength() != 1) throw new AdjacentTilesException(); //we check the down tiles
				}
				
				for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(left and right) and if yes throw error
				{
					if (column != 0 && ships[row+i-1][column-1].getLength() != 1) throw new AdjacentTilesException(); //we check the left tiles)
					else if (column != 9 && ships[row+i-1][column+4].getLength() != 1) throw new AdjacentTilesException(); //we check the right tiles)
				}
				
				
				if (row > 9 || column + 3 > 9) throw new OversizeException();//"Wrong placement!The grid is 10*10,sorry!");
				
				for (int i = column ;i < column + new Battleship().getLength(); i++)
						{
					ships[row][i]=new Battleship();
					ships[row][i].setRow(frow);          //we set the start row and the start column of each ship
					ships[row][i].setColumn(fcolumn);    //so that we know the start of every ship and the spots
					ships[row][i].setType(type);			//associated with that
					ships[row][i].setOrientation(orientation);
						}
			}
			else if (orientation == 2) 
			{
				for (int i = 0; i < 4; i++) //we have to check if it overlaps another ship and if yes throw error
				{
					if (ships[row + i][column].getLength() != 1) throw new OverlapTilesException(); //not empty spot,throw exception
				}
				
				for (int i = 0; i < 4; i++) //we have to check if it is adjacent to another ship(left and right) and if yes throw error
				{
					if (column != 0 && ships[row+i][column-1].getLength() != 1) throw new AdjacentTilesException(); //we check the left tiles
					else if (column != 9 && ships[row+i][column+1].getLength() != 1) throw new AdjacentTilesException();//we check the right tiles
				}
				
				for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(above and down) and if yes throw error
				{
					if (row != 0 && ships[row-1][column+i-1].getLength() != 1) throw new AdjacentTilesException(); //we check the above tiles)
					else if (row != 9 && ships[row+4][column+i-1].getLength() != 1) throw new AdjacentTilesException(); //we check the down tiles)
				}
				
				
				if (column > 9 || row + 3 > 9) throw new OversizeException(); //out of grid,throw exception
				
				for (int i = row; i < row + new Battleship().getLength(); i++)
					{
					ships[i][column]=new Battleship();
					ships[i][column].setRow(frow);          //we set the start row and the start column of each ship
					ships[i][column].setColumn(fcolumn);    //so that we know the start of every ship and the spots
					ships[i][column].setType(type);			//associated with that
					ships[i][column].setOrientation(orientation);
					}
			}
		types[2]=true; //we have placed a battleship
		break;
		case 3:
			if (orientation == 1)
			{
				
				for (int i = 0; i < 3; i++) //we have to check if it overlaps another ship and if yes throw error
				{
					if (ships[row][column + i].getLength() != 1) throw new OverlapTilesException();//not empty spot,throw exception
				}


				for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(above and down) and if yes throw error
				{
					if (row != 0 && ships[row-1][column+i].getLength() != 1) throw new AdjacentTilesException();//we check the above tiles
					else if (row != 9 && ships[row+1][column+i].getLength() != 1) throw new AdjacentTilesException();//we check the down tiles
				}
				
				for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(left and right) and if yes throw error
				{
					if (column != 0 && ships[row+i-1][column-1].getLength() != 1) throw new AdjacentTilesException(); //we check the left tiles)
					else if (column != 9 && ships[row+i-1][column+3].getLength() != 1) throw new AdjacentTilesException();//we check the right tiles)
				}
				
				
				if (row > 9 || column + 2 > 9) throw new OversizeException();
				
				for (int i = column ;i < column + new Cruiser().getLength(); i++)
						{
					ships[row][i]=new Cruiser();
					ships[row][i].setRow(frow);          //we set the start row and the start column of each ship
					ships[row][i].setColumn(fcolumn);    //so that we know the start of every ship and the spots
					ships[row][i].setType(type);			//associated with that
					ships[row][i].setOrientation(orientation);
						}
			}
			else if (orientation == 2) 
			{
				for (int i = 0; i < 3; i++) //we have to check if it overlaps another ship and if yes throw error
				{
					if (ships[row + i][column].getLength() != 1) throw new OverlapTilesException(); //not empty spot,throw exception
				}
				
				for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(left and right) and if yes throw error
				{
					if (column != 0 && ships[row+i][column-1].getLength() != 1) throw new AdjacentTilesException(); //we check the left tiles
					else if (column != 9 && ships[row+i][column+1].getLength() != 1) throw new AdjacentTilesException(); //we check the right tiles
				}
				
				for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(above and down) and if yes throw error
				{
					if (row != 0 && ships[row-1][column+i-1].getLength() != 1) throw new AdjacentTilesException(); //we check the above tiles)
					else if (row != 9 && ships[row+3][column+i-1].getLength() != 1) throw new AdjacentTilesException(); //we check the down tiles)
				}
				
				
				if (column > 9 || row + 2 > 9) throw new OversizeException(); //out of grid,throw exception
				
				for (int i = row; i < row + new Cruiser().getLength(); i++)
					{
					ships[i][column]=new Cruiser();
					ships[i][column].setRow(frow);          //we set the start row and the start column of each ship
					ships[i][column].setColumn(fcolumn);    //so that we know the start of every ship and the spots
					ships[i][column].setType(type);			//associated with that
					ships[i][column].setOrientation(orientation);
					}
			}
		types[3]=true; //we have placed a cruiser	
		break;
		case 4:
			if (orientation == 1)
			{
				for (int i = 0; i < 3; i++) //we have to check if it overlaps another ship and if yes throw error
				{
					if (ships[row][column + i].getLength() != 1) throw new OverlapTilesException(); //not empty spot,throw exception
				}

				for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(above and down) and if yes throw error
				{
					if (row != 0 && ships[row-1][column+i].getLength() != 1) throw new AdjacentTilesException();//we check the above tiles
					else if (row != 9 && ships[row+1][column+i].getLength() != 1) throw new AdjacentTilesException();//we check the down tiles
				}
				
				for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(left and right) and if yes throw error
				{
					if (column != 0 && ships[row+i-1][column-1].getLength() != 1) throw new AdjacentTilesException();//we check the left tiles)
					else if (column != 9 && ships[row+i-1][column+3].getLength() != 1) throw new AdjacentTilesException(); //we check the right tiles)
				}
				
				
				if (row > 9 || column + 2 > 9) throw new OversizeException();
				
				for (int i = column ;i < column + new Submarine().getLength() ; i++)
						{
					ships[row][i]=new Submarine();
					ships[row][i].setRow(frow);          //we set the start row and the start column of each ship
					ships[row][i].setColumn(fcolumn);    //so that we know the start of every ship and the spots
					ships[row][i].setType(type);			//associated with that
					ships[row][i].setOrientation(orientation);
						}
			}
			else if (orientation == 2) 
			{
				for (int i = 0; i < 3; i++) //we have to check if it overlaps another ship and if yes throw error
				{
					if (ships[row + i][column].getLength() != 1) throw new OverlapTilesException();//not empty spot,throw exception
				}
				
				for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(left and right) and if yes throw error
				{
					if (column != 0 && ships[row+i][column-1].getLength() != 1) throw new AdjacentTilesException();//we check the left tiles
					else if (column != 9 && ships[row+i][column+1].getLength() != 1) throw new AdjacentTilesException(); //we check the right tiles
				}
				
				for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(above and down) and if yes throw error
				{
					if (row != 0 && ships[row-1][column+i-1].getLength() != 1) throw new AdjacentTilesException();//we check the above tiles)
					else if (row != 9 && ships[row+3][column+i-1].getLength() != 1) throw new AdjacentTilesException();//we check the down tiles)
				}
				
				
				if (column > 9 || row + 2 > 9) throw new OversizeException(); //out of grid,throw exception
				
				for (int i = row; i < row + new Submarine().getLength() ; i++)
					{
					ships[i][column]=new Submarine();
					ships[i][column].setRow(frow);          //we set the start row and the start column of each ship
					ships[i][column].setColumn(fcolumn);    //so that we know the start of every ship and the spots
					ships[i][column].setType(type);			//associated with that
					ships[i][column].setOrientation(orientation);
					}
			}
		types[4]=true; //we have placed a submarine
		break;
		case 5:
			if (orientation == 1)
			{
				for (int i = 0; i < 2; i++) //we have to check if it overlaps another ship and if yes throw error
				{
					if (ships[row][column + i].getLength() != 1) throw new OverlapTilesException(); //not empty spot,throw exception
				}
				

				for (int i = 0; i < 2; i++) //we have to check if it is adjacent to another ship(above and down) and if yes throw error
				{
					if (row != 0 && ships[row-1][column+i].getLength() != 1) throw new AdjacentTilesException(); //we check the above tiles
					else if (row != 9 && ships[row+1][column+i].getLength() != 1) throw new AdjacentTilesException();//we check the down tiles
				}
				
				for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(left and right) and if yes throw error
				{
					if (column != 0 && ships[row+i-1][column-1].getLength() != 1) throw new AdjacentTilesException();//we check the left tiles)
					else if (column != 9 && ships[row+i-1][column+2].getLength() != 1) throw new AdjacentTilesException();//we check the right tiles)
				}
				
				
				if (row > 9 || column + 1 > 9) throw new OversizeException();
				
				for (int i = column ;i < column + new Destroyer().getLength() ; i++)
						{
					ships[row][i]=new Destroyer();
					ships[row][i].setRow(frow);          //we set the start row and the start column of each ship
					ships[row][i].setColumn(fcolumn);    //so that we know the start of every ship and the spots
					ships[row][i].setType(type);			//associated with that
					ships[row][i].setOrientation(orientation);
						}
			}
			else if (orientation == 2) 
			{
				for (int i = 0; i < 2; i++) //we have to check if it overlaps another ship and if yes throw error
				{
					if (ships[row + i][column].getLength() != 1) throw new OverlapTilesException(); //not empty spot,throw exception
				}
				
				for (int i = 0; i < 2; i++) //we have to check if it is adjacent to another ship(left and right) and if yes throw error
				{
					if (column != 0 && ships[row+i][column-1].getLength() != 1) throw new AdjacentTilesException(); //we check the left tiles
					else if (column != 9 && ships[row+i][column+1].getLength() != 1) throw new AdjacentTilesException(); //we check the right tiles
				}
				
				for (int i = 0; i < 3; i++) //we have to check if it is adjacent to another ship(above and down) and if yes throw error
				{
					if (row != 0 && ships[row-1][column+i-1].getLength() != 1) throw new AdjacentTilesException(); //we check the above tiles)
					else if (row != 9 && ships[row+2][column+i-1].getLength() != 1) throw new AdjacentTilesException(); //we check the down tiles)
				}
				
				
				if (column > 9 || row + 1 > 9) throw new OversizeException();//out of grid,throw exception
				
				for (int i = row; i < row + new Destroyer().getLength() ; i++)
					{
					ships[i][column]=new Destroyer();
					ships[i][column].setRow(frow);          //we set the start row and the start column of each ship
					ships[i][column].setColumn(fcolumn);    //so that we know the start of every ship and the spots
					ships[i][column].setType(type);			//associated with that
					ships[i][column].setOrientation(orientation);
					}
			}
		types[5]=true; //we have placed a destroyer
		break;
		}
	}


	/*
	 * A function for computer to place the ships randomly
	 * 
	 */
	public void computerPlaceShip() {
		int i = 5;
		int c = 1; //we want a counter for the type of the ship
		while ( i > 0) {
		try    //place the players ships 		
		{
				
				int row = (int) Math.floor(Math.random() * 10); //will return a number between 0 and 9
				int column = (int) Math.floor(Math.random() * 10); //will return a number between 0 and 9
				int orientation = (int) ( Math.random() * 2 + 1); // will return either 1 or 2
				randomPlacement[5-i][0] = c;
				randomPlacement[5-i][1] = row;
				randomPlacement[5-i][2] = column;
				randomPlacement[5-i][3] = orientation;
				i--;
				PlaceShip(c, row, column, orientation);
				c++;
			}

			catch(OversizeException e)                       
			{
				i=i+1; //in order to place again
			}
			catch(OverlapTilesException o)
			{
				i=i+1; //in order to place again
			}		
			catch(AdjacentTilesException a)
			{
				i=i+1; //in order to place again
			}
			catch(InvalidCountException k)
			{
				i=i+1; //in order to place again
			}
			catch(ArrayIndexOutOfBoundsException a)
			{
				i=i+1; //in order to place again
			//	System.out.println("Wrong placement!The grid is 10*10,sorry!");
			}
		}
		
	}
	
	
	/*
	 * A function for shooting at specific cell of the grid
	 * @param shrow		the row of the cell
	 * @param shcolumn	the column of the cell
	 */
	void shoot(int shrow, int shcolumn) throws OutOfBoundsException//function to shoot at a ship given the position
	{ 
		
		if (shrow > 9 || shcolumn > 9) throw new OutOfBoundsException();
		playerLastFive[n][0]=shrow;
		playerLastFive[n][1]=shcolumn;
		shots--; //when the user plays he has one less shot
		int i = shcolumn;
		int j = shrow;
		int c = 0; //we initialize a counter
		int p = 0; //the specific spot of the ship that is hit
		int z = ships[shrow][shcolumn].getType(); //we want to keep the type of the ship
		
		if (ships[shrow][shcolumn].getType() == 0 ) 		//The player hit an empty spot
		{
			playerLastFive[n][2]=0; //we have a miss
			playerLastFive[n][3]=0;
		}
		if (ships[shrow][shcolumn].getOrientation() == 1)
		{
			while (i != ships[shrow][shcolumn].getColumn()) 	//we try to find the first column of the ship
			{
				i--;
				p++;
				
			}
			if (ships[shrow][i].hit[p] == true )
				{
				isAlreadyHit = true;
				playerLastFive[n][2]=0; //we have a miss
				playerLastFive[n][3]=0;
				}
			
			else {
			playerLastFive[n][2]=1; //we have a hit
			playerLastFive[n][3]=ships[shrow][shcolumn].getType(); //to get the type of the ship
			points += ships[shrow][shcolumn].hitPoints; //we add the points of hit 
			ships[shrow][i].hit[p] = true;		//hit array is about the first row/column of the ship,not the middle spots
			}
			
			int frow = shrow;   //the row and the column of the bow of the ship
			int fcolumn = i;
			for (int k = 0;k < ships[shrow][shcolumn].getLength();k++)
			{
				if (!countedBeforeAsSunken[z]) {	//that means we haven't counted that as sunken before
				if (ships[shrow][i].hit[k]==false)    //we want to see if the ship is sunk
				{										//we do that by checking the hit array
					break;
				}
				c++;
				}
			}
			if (c==ships[shrow][shcolumn].getLength()) {
				countedBeforeAsSunken[z] = true;
				points += ships[frow][fcolumn].sinkingPoints; //the ship has been sunk so the user earns the points
				sunkenShips++; //the number of sunken ships
				aliveShips--; //the number of alive ships
				shipsSunk[ships[frow][fcolumn].getType()-1] = true; //set that this ship has been sunk 
				for (int l=fcolumn;l<fcolumn+ships[shrow][shcolumn].getLength();l++)   //now the ship has been sunk
					ships[frow][l].setSunk(true);

				} 
			
		}
		
		if (ships[shrow][shcolumn].getOrientation() == 2)  //vertical
		{
			while (j != ships[shrow][shcolumn].getRow()) 	//we try to find the row column of the ship
			{
				j--;
				p++;
				
			}
			if (ships[j][shcolumn].hit[p] == true )
				{
				isAlreadyHit = true;
				playerLastFive[n][2]=0; //we have a miss
				playerLastFive[n][3]=0;
				}
			else {
			playerLastFive[n][2]=1; //we have a hit
			playerLastFive[n][3]=ships[shrow][shcolumn].getType(); //to get the type of the ship
			points += ships[shrow][shcolumn].hitPoints; //we add the points of hit 
			ships[j][shcolumn].hit[p] = true;		//hit array is about the first row/column of the ship,not the middle spots
				}
			
			int frow = j;   //the row and the column of the bow of the ship
			int fcolumn = shcolumn;
			for (int k = 0;k < ships[shrow][shcolumn].getLength();k++)
			{
				if (!countedBeforeAsSunken[z]) {	//that means we haven't counted that as sunken before
				if (ships[j][shcolumn].hit[k]==false)    //we want to see if the ship is sunk
				{										//we do that by checking the hit array
					break;
				}
				c++;
				}
			}
			if (c==ships[shrow][shcolumn].getLength()) {
				countedBeforeAsSunken[z] = true;
				points += ships[frow][fcolumn].sinkingPoints; //the ship has been sunk so the user earns the points
				sunkenShips++; //the number of sunken ships
				aliveShips--; //the number of alive ships
				shipsSunk[ships[frow][fcolumn].getType()-1] = true;
				for (int l=frow;l<frow+ships[shrow][shcolumn].getLength();l++)   //now the ship has been sunk
					ships[l][fcolumn].setSunk(true);

				} 
			
		}
		n++;
	}


	/*
	 * A function for the computer to shoot at a random cell 
	 */
	void computerShoot()  //function when computer shoots
{ 
		
    int shrow;
    int shcolumn;
	
    if (immediateprev[0] < 10 && immediateprev[2] == 1 && goleft == false) //so it has been initialized and the orientation of the ship is 1
    {
    	shrow = immediateprev[0];
    	shcolumn = immediateprev[1] + 1; //we move to the right
    }
    else if (immediateprev[0] < 10 && immediateprev[2] == 1 && goleft == true) //so it has been initialized and the orientation of the ship is 1
    {
    	shrow = immediateprev[0];
    	shcolumn = immediateprev[1] - 1; //we move to the left
    }
    else if (immediateprev[0] < 10 && immediateprev[2] == 2 && goup == false) //so it has been initialized and the orientation of the ship is 2
    {
    	shrow = immediateprev[0] + 1; //we move down
    	shcolumn = immediateprev[1]; 
    }
    else if (immediateprev[0] < 10 && immediateprev[2] == 2 && goup == true) //so it has been initialized and the orientation of the ship is 2
    {
    	shrow = immediateprev[0] - 1; //we move up
    	shcolumn = immediateprev[1]; 
    }
    else {	//it hasn't been initialized yet -> i.e we haven't got a successful hit
    	shrow = (int)(Math.random() * 10);
        shcolumn = (int)(Math.random() * 10);
    }
    
    computerLastFive[m][0]=shrow;
	computerLastFive[m][1]=shcolumn;
	if (m > 0 && computerLastFive[m-1][0] == shrow && computerLastFive[m-1][1] == shcolumn || hasComputerShoot[shrow][shcolumn] == true)
	{
		shrow = (int)(Math.random() * 10);
        shcolumn = (int)(Math.random() * 10);
        computerLastFive[m][0]=shrow;
    	computerLastFive[m][1]=shcolumn;
	}
    hasComputerShoot[shrow][shcolumn] = true;
	shots--; //when the user plays he has one less shot
	int i = shcolumn;
	int j = shrow;
	int c = 0; //we initialize a counter
	int p = 0; //the specific spot of the ship that is hit
	int z = ships[shrow][shcolumn].getType(); //we want to keep the type of the ship
	
	if (ships[shrow][shcolumn].getType() == 0 ) 		//The player hit an empty spot
	{
		computerLastFive[m][2]=0; //we have a miss
		computerLastFive[m][3]=0;
		if (prevright == true) //that means that our previous hit was a success so now we are at the end of the ship
		{
			if (immediateprev[2] == 1) //the ship is horizontal so we move to start and go left
			{
				goleft = true; //now we go left
				immediateprev[0] = start[0];
				immediateprev[1] = start[1];
				immediateprev[2] = start[2];
			}
			if (immediateprev[2] == 2) //the ship is vertical so we move to start and go up
			{
				goup = true; //now we go up
				immediateprev[0] = start[0];
				immediateprev[1] = start[1];
				immediateprev[2] = start[2];
			}
		}
	}
	if (ships[shrow][shcolumn].getOrientation() == 1) //horizontal
	{
		while (i != ships[shrow][shcolumn].getColumn()) 	//we try to find the first column of the ship
		{
			i--;
			p++;
			
		}
		if (ships[shrow][i].hit[p] == true )
		{
			isAlreadyHit = true;
			computerLastFive[m][2]=0; //we have a miss
			computerLastFive[m][3]=0; 
		}
		
		else {
		computerLastFive[m][2]=1; //we have a hit
		computerLastFive[m][3]=ships[shrow][shcolumn].getType(); //to get the type of the ship
		if (firstsuccessfulhit == true ) //if it is the first successful we want to keep the coordinates as start [x,y,z
		{
			start[0]=shrow; 
			start[1]=shcolumn;
			start[2]=ships[shrow][shcolumn].getOrientation(); //here it is the orientation
			firstsuccessfulhit = false; //we found the first succes.we don't want to change the variables
			immediateprev[0]=start[0];
			immediateprev[1]=start[1];  //for now the immediate previous is the start point
			immediateprev[2]=start[2];
			prevright = true; //we had a successful hit so on the next hit the previous was a success
		}
		if (firstsuccessfulhit == false) //if it is not the first successful hit we still have to put the immediateprev values
		{
			immediateprev[0]=shrow;
			immediateprev[1]=shcolumn;  //for now the immediate previous is the start point
			immediateprev[2]=ships[shrow][shcolumn].getOrientation();
			prevright = true; //we had a successful hit so on the next hit the previous was a success
		}
		points += ships[shrow][shcolumn].hitPoints; //we add the points of hit 
		ships[shrow][i].hit[p] = true;		//hit array is about the first row/column of the ship,not the middle spots
		}
		
		int frow = shrow;   //the row and the column of the bow of the ship
		int fcolumn = i;
		for (int k = 0;k < ships[shrow][shcolumn].getLength();k++)
		{
			if (!countedBeforeAsSunken[z]) {
			if (ships[shrow][i].hit[k]==false)    //we want to see if the ship is sunk
			{										//we do that by checking the hit array
				break;
			}
			c++;
			}
		}
		if (c==ships[shrow][shcolumn].getLength()) {
			countedBeforeAsSunken[z] = true;
			firstsuccessfulhit = true; //now we try to find the next successful hit
			immediateprev[0] = 30; //we want to make random shoots until we find a successful
			prevright = false;
			goleft = false;	//we want to go by default right and down
			goup = false;
			sunkenShips++; //the number of sunken ships
			aliveShips--; //the number of alive ships
			points += ships[frow][fcolumn].sinkingPoints; //the ship has been sunk so the user earns the points
			for (int l=fcolumn;l<fcolumn+ships[shrow][shcolumn].getLength();l++)   //now the ship has been sunk
				ships[frow][l].setSunk(true);

			} 
		
	}
	
	if (ships[shrow][shcolumn].getOrientation() == 2)  //vertical
	{
		while (j != ships[shrow][shcolumn].getRow()) 	//we try to find the row column of the ship
		{
			j--;
			p++;
			
		}
		if (ships[j][shcolumn].hit[p] == true )
		{
			isAlreadyHit = true;
			computerLastFive[m][2]=0; //we have a miss
			computerLastFive[m][3]=0; 	
		}
		
		else {
		computerLastFive[m][2]=1; //we have a hit
		computerLastFive[m][3]=ships[shrow][shcolumn].getType();  
		if (firstsuccessfulhit == true ) //if it is the first successful we want to keep the coordinates as start [x,y,z
		{
			start[0]=shrow; 
			start[1]=shcolumn;
			start[2]=ships[shrow][shcolumn].getOrientation(); //here it is the orientation
			firstsuccessfulhit = false; //we found the first succes.we don't want to change the variables
			immediateprev[0]=start[0];
			immediateprev[1]=start[1];  //for now the immediate previous is the start point
			immediateprev[2]=start[2];
			prevright = true; //we had a successful hit so on the next hit the previous was a success
		}
		if (firstsuccessfulhit == false) //if it is not the first successful hit we still have to put the immediateprev values
		{
			immediateprev[0]=shrow;
			immediateprev[1]=shcolumn;  //for now the immediate previous is the start point
			immediateprev[2]=ships[shrow][shcolumn].getOrientation();
			prevright = true; //we had a successful hit so on the next hit the previous was a success
		}
		points += ships[shrow][shcolumn].hitPoints; //we add the points of hit 
		ships[j][shcolumn].hit[p] = true;		//hit array is about the first row/column of the ship,not the middle spots
			}
		
		int frow = j;   //the row and the column of the bow of the ship
		int fcolumn = shcolumn;
		for (int k = 0;k < ships[shrow][shcolumn].getLength();k++)
		{
			if (!countedBeforeAsSunken[z]) {
			if (ships[j][shcolumn].hit[k]==false)    //we want to see if the ship is sunk
			{										//we do that by checking the hit array
				break;
			}
			c++;
			}
		}
		if (c==ships[shrow][shcolumn].getLength()) {
			countedBeforeAsSunken[z] = true;
			sunkenShips++; //the number of sunken ships
			aliveShips--; //the number of alive ships
			points += ships[frow][fcolumn].sinkingPoints; //the ship has been sunk so the user earns the points
			for (int l=frow;l<frow+ships[shrow][shcolumn].getLength();l++)   //now the ship has been sunk
				ships[l][fcolumn].setSunk(true);

			} 
		
	}
	m++;
}



	
}

	
