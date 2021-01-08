package battleship;
import java.lang.Exception;
@SuppressWarnings("serial")

public class OverlapTilesException extends Exception{

	public OverlapTilesException(String message)
	{
		System.out.println(message);
	}
	public OverlapTilesException()
	{
		super();
	}
}
