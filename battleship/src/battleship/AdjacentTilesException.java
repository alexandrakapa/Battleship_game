package battleship;
import java.lang.Exception;
@SuppressWarnings("serial")


public class AdjacentTilesException extends Exception{

	public AdjacentTilesException(String message)
	{
		System.out.println(message);
	}
	
}
