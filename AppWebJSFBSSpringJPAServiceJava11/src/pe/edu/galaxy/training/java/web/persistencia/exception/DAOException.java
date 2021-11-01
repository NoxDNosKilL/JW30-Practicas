package pe.edu.galaxy.training.java.web.persistencia.exception;

public class DAOException extends Exception{

	private static final long serialVersionUID = -2499625942923691978L;

	public DAOException() {
		super();
		
	}

	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public DAOException(String message) {
		super(message);
		
	}

	public DAOException(Throwable cause) {
		super(cause);
		
	}

	

}
