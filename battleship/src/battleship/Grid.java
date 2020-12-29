package battleship;

import java.util.Arrays;

public class Grid {
	Ship[][] ships = new Ship [10][10]; //we have a 10*10 grid with ships/empty ships
	public int shots; //shot of the user -> max 40
	int sunkenShips; //the number of sunken ships ->max 5
	boolean [] types = new boolean[6]; //an array that holds what types of ships have been places -> eg if type[1]==true then we have already placed a carrier
	public int points = 0; //the points the player has 
	
	public Grid() {
		for (Ship[] row : ships) 
            Arrays.fill(row, new NoShip());  //initialize an array with empty spots
		shots = 40; //we begin with 40 shots
		sunkenShips = 0; //we begin with 0 sunken ships
		types[0]=true; //We dont care about no ship
	}
	

	
	public void PlaceShip(int type, int row, int column, int orientation) throws OversizeException, OverlapTilesException,AdjacentTilesException,InvalidCountException{
		//if (type==1) {throw new OversizeException();}
		if (types[type]==true) throw new InvalidCountException("Ooops!You have already placed a ship of type " + type + "!");
		if (ships[row][column].getLength()!=1 ) throw new OverlapTilesException("There is another ship here,sorry!"); //not empty spot,throw exception
		//boolean up_down=false;
		int frow = row;  //we keep the variables because they change
		int fcolumn = column;  
		switch(type) {
		case 1:
		 if (orientation == 1)
		{
			 
			if (row > 9 || column + 4 > 9) throw new OversizeException("Wrong placement!The grid is 10*10,sorry!"); //out of grid,throw exception
			 
				/*for (int i = column; i <  column + 5; i++)
				{
					if ((row==0 && ships[row+1][i].getLength()!=1) || (row!=0 && row!=9 && ships[row-1][i].getLength()!=1 && ships[row+1][i].getLength()!=1) || (row==9 && ships[row-1][i].getLength()!=1)) 
					{
						up_down=true;
						break;
						//throw new AdjacentTilesException("You cant place it here,there are adjacent ships!");
					}
				}
				if (up_down==false) throw new AdjacentTilesException("You cant place it here,there are adjacent ships!");
				else {
				for (int i = 0; i< 3; i++)
				{
					if ((column==0 && ships[column+5][row-1+i].getLength()!=1) || (column+4==9 && ships[column-1][row-1+i].getLength()!=1) || (column!=0 && column+4!=9 && ships[column+5][row-1+i].getLength()!=1 && ships[column-1][row-1+i].getLength()!=1))
					{
						throw new AdjacentTilesException("You cant place it here,there are adjacent ships!");
					}
				}
				}*/
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
			if (column > 9 || row + 4 > 9) throw new OversizeException("Wrong placement!The grid is 10*10,sorry!"); //out of grid,throw exception
			
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
				if (row > 9 || column + 3 > 9) throw new OversizeException("Wrong placement!The grid is 10*10,sorry!");
				
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
				if (column > 9 || row + 3 > 9) throw new OversizeException("Wrong placement!The grid is 10*10,sorry!"); //out of grid,throw exception
				
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
				if (row > 9 || column + 2 > 9) throw new OversizeException("Wrong placement!The grid is 10*10,sorry!");
				
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
				if (column > 9 || row + 2 > 9) throw new OversizeException("Wrong placement!The grid is 10*10,sorry!"); //out of grid,throw exception
				
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
				if (row > 9 || column + 2 > 9) throw new OversizeException("Wrong placement!The grid is 10*10,sorry!");
				
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
				if (column > 9 || row + 2 > 9) throw new OversizeException("Wrong placement!The grid is 10*10,sorry!"); //out of grid,throw exception
				
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
				if (row > 9 || column + 1 > 9) throw new OversizeException("Wrong placement!The grid is 10*10,sorry!");
				
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
				if (column > 9 || row + 1 > 9) throw new OversizeException("Wrong placement!The grid is 10*10,sorry!"); //out of grid,throw exception
				
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


	
	void shoot(int shrow, int shcolumn)  //function to shoot at a ship given the position
	{ 
		shots--; //when the user plays he has one less shot
		int i = shcolumn;
		int j = shrow;
		int c = 0; //we initialize a counter
		int p = 0; //the specific spot of the ship that is hit
		
		if (ships[shrow][shcolumn].getType() == 0 ) 		//The player hit an empty spot
			System.out.println("I am sorry,this is an empty spot!");
		if (ships[shrow][shcolumn].getOrientation() == 1)
		{
			while (i != ships[shrow][shcolumn].getColumn()) 	//we try to find the first column of the ship
			{
				i--;
				p++;
				
			}
			if (ships[shrow][shcolumn].hit[p] == true )
				System.out.println("Oh no,it is already hit!");
			else {
			System.out.println("Congrats!You hit a ship!");
			points += ships[shrow][shcolumn].hitPoints; //we add the points of hit 
			ships[shrow][i].hit[p] = true;		//hit array is about the first row/column of the ship,not the middle spots
			}
			
			int frow = shrow;   //the row and the column of the bow of the ship
			int fcolumn = i;
			for (int k = 0;k < ships[shrow][shcolumn].getLength();k++)
			{
				if (ships[shrow][i].hit[k]==false)    //we want to see if the ship is sunk
				{										//we do that by checking the hit array
					break;
				}
				c++;
			}
			if (c==ships[shrow][shcolumn].getLength()) {
				System.out.println("You have sunken the ship!");
				points += ships[frow][fcolumn].sinkingPoints; //the ship has been sunk so the user earns the points
				for (int l=fcolumn;l<fcolumn+ships[shrow][shcolumn].getLength();l++)   //now the ship has been sunk
					ships[frow][l].setSunk(true);

				} 
			
		}
		
		if (ships[shrow][shcolumn].getOrientation() == 2)
		{
			while (j != ships[shrow][shcolumn].getRow()) 	//we try to find the row column of the ship
			{
				j--;
				p++;
				
			}
			if (ships[shrow][shcolumn].hit[p] == true )
				System.out.println("Oh no,it is already hit!");
			else {
			System.out.println("Congrats!You hit a ship!");
			points += ships[shrow][shcolumn].hitPoints; //we add the points of hit 
			ships[j][shcolumn].hit[p] = true;		//hit array is about the first row/column of the ship,not the middle spots
				}
			
			int frow = j;   //the row and the column of the bow of the ship
			int fcolumn = shcolumn;
			for (int k = 0;k < ships[shrow][shcolumn].getLength();k++)
			{
				if (ships[j][shcolumn].hit[k]==false)    //we want to see if the ship is sunk
				{										//we do that by checking the hit array
					break;
				}
				c++;
			}
			if (c==ships[shrow][shcolumn].getLength()) {
				System.out.println("You have sunken the ship!");
				points += ships[frow][fcolumn].sinkingPoints; //the ship has been sunk so the user earns the points
				for (int l=frow;l<frow+ships[shrow][shcolumn].getLength();l++)   //now the ship has been sunk
					ships[l][fcolumn].setSunk(true);

				} 
			
		}
		
		if (shots == 0) System.out.println("The game has ended!");
	}

}


	
