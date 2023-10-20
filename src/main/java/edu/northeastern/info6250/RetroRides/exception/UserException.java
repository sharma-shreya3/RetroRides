package edu.northeastern.info6250.RetroRides.exception;

public class UserException extends Exception {
	
	public UserException(String message)
	{
		super("CategoryException-"+message);
	}
	
	public UserException(String message, Throwable cause)
	{
		super("CategoryException-"+message,cause);
	}
	
}
