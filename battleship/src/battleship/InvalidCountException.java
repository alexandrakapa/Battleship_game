package battleship;
import java.lang.Exception;
@SuppressWarnings("serial")

public class InvalidCountException extends Exception {

	public InvalidCountException(String message)
	{
		System.out.println(message);
	}
	public InvalidCountException()
	{
		super();
	}
}
