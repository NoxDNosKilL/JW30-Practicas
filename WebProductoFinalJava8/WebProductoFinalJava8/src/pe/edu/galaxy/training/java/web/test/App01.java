package pe.edu.galaxy.training.java.web.test;

public class App01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filtro = "";
		String ap = "";
		if (ap.length() > 0) {
			filtro = "Apellido Paterno = " + ap + ", ";
		}

		String am = "M";
		if (am.length() > 0) {
			filtro = filtro + "Apellido Materno = " + am + ", ";
		}

		if (filtro.length() > 1) {
			filtro = filtro.substring(0, filtro.length() - 2);
		}

		System.out.println(filtro);

	}

}
