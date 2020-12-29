/**
 * 
 */
package battleship;

/**
 * @author alexandra
 *
 */

public class Ship {

	private int row; //the row (from 0 to 9) of the first cell of a ship
	private int column; //the column (from 0 to 9) of the first cell of a ship
	protected int length; // the number of cells occupied by the ship
	private int orientation; //1 : horizontal, 2 : vertical
	protected int type; //The type of the ship (from 1 to 5)
	boolean contactless = true; //the ship hasn't been hit
	boolean [] hit = new boolean[5]; //the ship has been hit in specific
									// cells that are marked as true,default is false
	protected boolean sunk; //the ship has been sunk
	protected int hitPoints; //the point you get after each hit
	protected int sinkingPoints; //the points you get after sinking
	
	
	public int getRow()	//All the getters
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public int getType()
	{
		return type;
	}
	
	public int getOrientation()

	{
		return orientation;
	}
	
	public int getHitPoints()
	{
		return hitPoints;
	}
	
	public int getSinkingPoints()
	{
		return sinkingPoints;
	}
	
	public boolean isSunk()
	{
		return sunk;
	}
	
	public void setSunk(boolean sunk)
	{
		this.sunk = sunk;
	}
	
	public void setLength(int newLength)
	{
		this.length = newLength;
	}
	
	public void setType(int newType)
	{
		this.type = newType;
	}
	
	public void setRow(int newRow)	//All the setters
	{
		this.row = newRow;
	}
	
	public void setColumn(int newClmn)
	{
		this.column = newClmn;
	}
	
	public void setOrientation(int newOrn)
	{
		this.orientation = newOrn;
	}

	
}

class Carrier extends Ship{
	
	public Carrier()
	{
		type = 1;
		length = 5;
		hitPoints = 350;
		sinkingPoints = 1000;
	}
	
}
	
class Battleship extends Ship{
	
	public Battleship()
	{
		type = 2;
		length = 4;
		hitPoints = 250;
		sinkingPoints = 500;
		
	}
}

class Cruiser extends Ship{
	
	public Cruiser()
	{
		type = 3;
		length = 3;
		hitPoints = 100;
		sinkingPoints = 250;
		
	}
}

class Submarine extends Ship{
	
	public Submarine()
	{
		type = 4;
		length = 3;
		hitPoints = 100;
		sinkingPoints = 0;
		
	}
}

class Destroyer extends Ship{
	
	public Destroyer()
	{
		type = 5;
		length = 2;
		hitPoints = 50;
		sinkingPoints = 0;
		
	}
}

class NoShip extends Ship{  //we use a class to determine empty spot
	
	public NoShip()
	{
		type = 0;
		length = 1;
		hitPoints = 0;
		sinkingPoints = 0;
		
	}
	
}


