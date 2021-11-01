package pe.edu.galaxy.aplicacion;

import pe.edu.galaxy.bean.exception.PostulanteException;

import java.util.List;

import pe.edu.galaxy.bean.Postulante;
import pe.edu.galaxy.persistencia.exception.DAOException;
import pe.edu.galaxy.persistencia.implementacion.PostulanteDAOImpl;
import pe.edu.galaxy.persistencia.interfaces.PostulanteDAO;
import pe.edu.galaxy.persistencia.utilitario.DNIValidator;
import pe.edu.galaxy.persistencia.utilitario.DatosValidator;
import pe.edu.galaxy.persistencia.utilitario.EmailValidator;
import pe.edu.galaxy.persistencia.utilitario.GeneroValidator;




public class AppPostulante{

	public static void main(String[] args) throws PostulanteException  {
		
		//eliminar();
		listar();
		//listar("Nat");
		//agregar();
		//modificar();

		}
	
	
	
	
	
	private static void listar() {
		try {
			PostulanteDAO postulanteDAO= new PostulanteDAOImpl();
			
			List<Postulante> postulantes= postulanteDAO.listar();
			postulantes.forEach(p->{
				
				System.out.println(p);
			
			});
			//System.out.println(postulantes.size());
			
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		}

	}
	
	
	private static void listar(String nombre) {
		
		try {
			PostulanteDAO postulanteDAO= new PostulanteDAOImpl();
			
			List<Postulante> postulantes= postulanteDAO.listar(nombre);

			postulantes.forEach(p->{
				System.out.println(p);;
			});
			
			
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		}
	
	}
	
	private static void agregar() {

		try {
			PostulanteDAO postulanteDAO = new PostulanteDAOImpl();

			Postulante postulante = new Postulante();
			postulante.setNombre("Nicole");
			postulante.setApelPaterno("Ternero");
			postulante.setApelMaterno("Areas");
			postulante.setDni("54712658");
			postulante.setFechaNacimiento("18/05/1998");
			postulante.setTlfFijo("2870344");
			postulante.setCelular("555555555");
			postulante.setEmail("nicole@desk.com");
			postulante.setGenero("F");
			postulante.setDireccion("Los Jardines");
			postulante.setDepartamento("Lima");
			postulante.setProvincia("Lima");
			postulante.setDistrito("Miraflores");
		
			if (postulanteDAO.agregar(postulante)) {
				System.out.println("Exito de registro");
			} else {
				System.err.println("Error de registro");
			}
		}  catch (DAOException e) {
			System.err.println(e.getMessage());
		} 

	}
	
	private static void modificar() throws PostulanteException{

		try {
			PostulanteDAO postulanteDAO = new PostulanteDAOImpl();

			Postulante postulante = new Postulante();
			postulante.setNombre("Rosario");;
			postulante.setApelPaterno("Cueva");
			postulante.setApelMaterno("Ramirez");
			postulante.setDni("99939999");
			postulante.setFechaNacimiento("");
			postulante.setTlfFijo("");
			postulante.setCelular("");
			postulante.setEmail("");
			postulante.setGenero("F");
			postulante.setDireccion("");
			postulante.setDepartamento("");
			postulante.setProvincia("");
			postulante.setDistrito("");
			
			postulante.setIdPostulante(4);

			if (postulanteDAO.modificar(postulante)) {
				System.out.println("Exito de actualizaci�n");
			} else {
				System.err.println("Error de actualizaci�n");
			}
		} catch (DAOException e) {

			System.err.println(e.getMessage());
		}

	}
	

	private static void eliminar() {
	
			try {
				PostulanteDAO postulanteDAO = new PostulanteDAOImpl();
	
				Postulante postulante = new Postulante();
				postulante.setIdPostulante(4);
	
				if (postulanteDAO.eliminar(postulante)) {
					System.out.println("Exito de eliminaci�n");
				} else {
					System.err.println("Error de eliminaci�n");
				}
			} catch (DAOException e) {
	
				System.err.println(e.getMessage());
			}
	
		}
		

}
