package pe.edu.galaxy.persistencia.utilitario;

import pe.edu.galaxy.bean.exception.PostulanteException;

public class DatosValidator {
	public static String validarCaracteres(String dato) throws PostulanteException {
		
		if (dato==null) {
			dato=" ";
			return dato;
		}else	if ((dato.length() >= 3) && (dato.length() <= 60)) {
			return dato;
		} else {
			throw new PostulanteException("Ingresar mas de 3 y menos de 60 caracteres");
		}
	}
}
