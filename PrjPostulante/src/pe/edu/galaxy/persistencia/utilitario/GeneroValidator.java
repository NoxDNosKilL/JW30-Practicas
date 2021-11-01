package pe.edu.galaxy.persistencia.utilitario;

import pe.edu.galaxy.bean.exception.PostulanteException;

public class GeneroValidator {
	public static boolean validar(String genero) throws PostulanteException{
		boolean sw = false;
		
		
		if(genero.toUpperCase().charAt(0)!='F' && genero.toUpperCase().charAt(0) !='M') {
			throw new PostulanteException("Ingresar 'F' o 'M'");
		} else {
			System.out.println("Genero guardado");
			sw= true;
		}

		return sw;
	}
}
