package pe.edu.galaxy.training.java.web.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class BD {

    private static String driver 	= "oracle.jdbc.OracleDriver";
    private static String url 		= "jdbc:oracle:thin:@localhost:1521:XE";
    private static String usuario 	= "JW27";
    private static String clave 	= "123456";

    private static boolean cargarDriver() {
        try {
            Class.forName(driver);
            return true;
        } catch (Exception e) {
            System.err.println("Error al cargar el driver  " + e.getMessage());
            return false;
        }
    }

    public static Connection conectar() {

        if (!cargarDriver()) {
            return null;
        }
        try {
            Connection cn = DriverManager.getConnection(url, usuario, clave);
            return cn;
        } catch (Exception e) {
            System.err.println("Error al conectar  " + e.getMessage());
            return null;
        }
    }

}
