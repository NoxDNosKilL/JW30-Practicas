package pe.edu.galaxy.presentacion;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Data;
import pe.edu.galaxy.bean.Postulante;
import pe.edu.galaxy.persistencia.exception.DAOException;
import pe.edu.galaxy.persistencia.implementacion.PostulanteDAOImpl;
import pe.edu.galaxy.persistencia.interfaces.PostulanteDAO;

@SessionScoped
@Data
@ManagedBean (name = "postulanteMB")
public class PostulanteMB {
	
	private Postulante postulante;
	private List<Postulante> postulantes;
	
	public PostulanteMB() {
		this.setPostulante(new Postulante());	
		//this.getPostulante().setNombre("nat");
		this.listar();
	}
	
	public String nuevo() {
		this.setPostulante(new Postulante());	
		return "postulantes-registro";
	}
	
	public String cancelar() {
		this.setPostulante(new Postulante());	
		return "postulantes-listado";
	}
	
		
	public  void listar() {
		System.out.println("Nombre ->" + this.postulante.getNombre());
		try {
			PostulanteDAO postulanteDAO= new PostulanteDAOImpl();
			
			 postulantes= postulanteDAO.listar(this.getPostulante().getNombre());
			 
			 postulantes.forEach(System.out::println);
			
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		}

	}
	
	public void limpiar() {
		this.setPostulante(new Postulante());	
		//this.setPostulantes(new ArrayList<Postulante>());
		this.listar();
	}
	
	public  void grabar() {
		System.out.println("this.getPostulante().getIdPostulante()->" + this.getPostulante().getIdPostulante());
		
		try {
			PostulanteDAO postulanteDAO = new PostulanteDAOImpl();
			
			if(this.getPostulante().getIdPostulante()>0) {
				if (postulanteDAO.modificar(this.getPostulante())) {
					System.out.println("Exito de actualizacion");
				} else {
					System.err.println("Error de registro");
				}
			}	else {

					if (postulanteDAO.agregar(this.getPostulante())) {
						System.out.println("Exito de registro");
					} else {
						System.err.println("Error de registro");
					}
				}
		}  catch (DAOException e) {
			System.err.println(e.getMessage());
		} 

	}
	
	public void eliminar(Postulante postulante) {
		
		try {
			PostulanteDAO postulanteDAO = new PostulanteDAOImpl();

			if (postulanteDAO.eliminar(postulante)) {
				this.listar();
				System.out.println("Exito de eliminacion");
			} else {
				System.err.println("Error de eliminacion");
				
			}
		} catch (DAOException e) {

			System.err.println(e.getMessage());
			
		}

	}
	
	public String modificar(Postulante postulante) {
		
		this.setPostulante(postulante);
		return "postulantes-registro";
		
	/*	
		try {
			PostulanteDAO postulanteDAO = new PostulanteDAOImpl();

			if (postulanteDAO.eliminar(postulante)) {
				this.listar();
				System.out.println("Exito de eliminacion");
			} else {
				System.err.println("Error de eliminacion");
				
			}
		} catch (DAOException e) {

			System.err.println(e.getMessage());
			
		}
	*/
	}
	
	
	
}
