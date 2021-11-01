package pe.edu.galaxy.persistencia.utilitario;

import pe.edu.galaxy.bean.exception.PostulanteException;

public class EmailValidator {
	
	public static boolean validar(String email) throws PostulanteException{
		boolean sw = false;
	
		if(email.contains((CharSequence)"@") && email.contains((CharSequence) ".")) {
			System.out.println("Correo guardado");
			sw = true;
		}else {
			throw new PostulanteException("Email invalido missing: '@' O '.'");
		}
		return sw;
	}
}
