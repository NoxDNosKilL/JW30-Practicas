package pe.edu.galaxy.persistencia.utilitario;

import pe.edu.galaxy.bean.exception.PostulanteException;

public class DNIValidator {
	
	public static boolean validar(String dni) throws PostulanteException {
		boolean sw = false;
		if(dni == null) {
			throw new PostulanteException("DNI no puede ser nulo");
			
		}
		
		if (dni.length()!= 8) {
			throw new PostulanteException("El DNI debe de tener 8 caracteres ");
		}
		
		if(dni.matches("[0-9]*")) {
			System.out.println("Dni guardado");
			sw = true;
		}else {
			sw = false;
			throw new PostulanteException("El dni debe tener solo digitos");
		}
		return sw;
	}
	
}
