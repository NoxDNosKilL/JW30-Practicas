package pe.edu.galaxy.persistencia.utilitario;
import pe.edu.galaxy.bean.exception.PostulanteException;

public class NumeroValidator {

	private static boolean sw = false;
	private static String validarNumero;

	public static boolean fijo(String fijo) throws PostulanteException {
		validarNumero = fijo.replaceAll("[()-]", "");

		if (validarNumero.length() != 7 && validarNumero.length() != 6) {
			throw new PostulanteException("Ingresar telefono valido (6-7 caracteres)");
		}

		matches();
		return sw;
	}

	public static boolean celular(String celular) throws PostulanteException {

		validarNumero = celular.replaceAll("[()-]", "");

		if (validarNumero.length() != 9) {
			throw new PostulanteException("Ingresar 9 numeros ");
		}

		matches();
		return sw;
	}

	private static boolean matches() throws PostulanteException {
		 sw=false;
		if (validarNumero.matches("[0-9]*") ) {
			sw = true;
		} else {
			throw new PostulanteException("Incorrectos caracteres");
		}
		return sw;
	}

}
