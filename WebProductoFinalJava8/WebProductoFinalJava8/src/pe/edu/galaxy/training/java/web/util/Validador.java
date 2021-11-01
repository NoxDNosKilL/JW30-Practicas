package pe.edu.galaxy.training.java.web.util;

public class Validador {

	public static String  dni(String dni){
		if (dni==null) {
			return "El dni es requerido";
		}
		if (dni.trim().length()!=8) {
			return "El dni debe tener 8 d�gitos";
		}
		if (!dni.matches("[0-9]+")) {
			return "El dni debe contener solo d�gitos";
		}
		
		return null;
	}
	
	public static String  ruc(String dni){
		if (dni==null) {
			return "El ruc es requerido";
		}
		if (dni.trim().length()!=11) {
			return "El ruc debe tener 11 d�gitos";
		}
		if (!dni.matches("[0-9]+")) {
			return "El ruc debe contener solo d�gitos";
		}
		
		return null;
	}
	
	public static String correo(String correo){
		if (correo==null) {
			return "El correo es requerido";
		}
		if (correo.indexOf('@')<=0) {
			return "El correo no es v�lido, debe contener la '@'";
		}
		
		return null;
	}
	
	public static boolean requerido(String str) {
		return str == null || str.trim().length() ==0;
	}
	
}
