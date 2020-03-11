package exception;

@SuppressWarnings("serial")
public class NotRootException extends Exception
{
	public NotRootException(String message)
	{
		super(message);
	}
}
