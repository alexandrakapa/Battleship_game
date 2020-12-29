/**
 * 
 */
package battleship;

/**
 * @author alexandra
 *
 */

public class BattleshipGame {
	
	
	public static void main(String[] args){
		
		//Ship myship= new Ship();
//		myship.setRow(3);
//		myship.setType(2);
		//System.out.println("I am okay,dont worry :)");
		//System.out.println(myship.sunk);
		//Ship car = new Carrier();
		//System.out.println("type of ship: " + myship.getType());
//		System.out.println("points of carrier: " + car.sinkingPoints);
//		System.out.println("length of carrier: " + car.getLength());
		//Grid mygrid = new Grid();
		//mygrid.ships[3][4]=new Carrier();
		//mygrid.ships[1][2]= new NoShip();
		//System.out.println("The ship in spot [3][4] (Carrier) has length : " + mygrid.ships[3][4].getLength());
		//System.out.println("The ship in spot [1][2] (empty spot) is of type : " + mygrid.ships[1][2].getType());
		
		Grid mygrid2 = new Grid();
//		System.out.println("I have intialiased an array with empty spots of length : " + mygrid2.ships[1][2].getLength());	
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
		//System.out.println(mygrid2.ships[5][4].isSunk());
		
		/*try {
			mygrid2.PlaceShip(4, 3, 7, 2);
			
		}
		catch(OversizeException e)                       !!!!!TRY CATCH BLOCK SAMPLE!!!!
		{
			//System.out.println("Exception thrown  :" + e);
		}*/
	}

}
