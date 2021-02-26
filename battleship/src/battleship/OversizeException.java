package battleship;
import java.lang.Exception;
@SuppressWarnings("serial")

public class OversizeException extends Exception {

	public OversizeException(String message)
	{
		System.out.println(message);
	}
	
	public OversizeException()
	{
		super();
	}
	
}
