package pe.edu.galaxy.persistencia.utilitario;

import java.sql.Connection;
import java.sql.DriverManager;

public class BDUtil {

	private static String driver = "oracle.jdbc.OracleDriver"; // Motor de BD y Version
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Servidor(IP), puerto, servicio(BD)
	private static String usu = "JW30", psw = "1234567";
	
	//jdbc:mysql://localhost:3306/bd_taller?serverTimezone=GMT-6
	//com.mysql.jdbc.Driver
	
	// com.microsoft.sqlserver.jdbc.SQLServerDriver
	// jdbc:sqlserver://localhost\\sqlexpress;user=sa;password=secret

	private static boolean cargarDriver() {
		try {
			Class.forName(driver);
			//System.out.println("Exito al cargar el driver");
			return true;
		} catch (Exception e) {
			System.err.println("Error al cargar el driver " + e.getMessage());
			return false;
		}
	}
	

	public  static Connection getConnection() {
		if (!cargarDriver()) {
			return null;
		}
		try {
			Connection cn = DriverManager.getConnection(url, usu, psw);
			//System.out.println("Exito de conexión");
			return cn;
		} catch (Exception e) {
			System.err.println("Error de conexión " + e.getMessage());
			return null;
		}
		
	}
}
