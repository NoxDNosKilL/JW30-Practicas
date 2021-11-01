package pe.edu.galaxy.training.java.web.entity.exception;

public class UsuarioException extends Exception{

	private static final long serialVersionUID = 7761094474228364109L;
			
	public UsuarioException() {
		
	}

	public UsuarioException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UsuarioException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

	public UsuarioException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public UsuarioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}


}
