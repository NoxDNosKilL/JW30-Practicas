package pe.edu.galaxy.bean;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pe.edu.galaxy.bean.exception.PostulanteException;
import pe.edu.galaxy.persistencia.utilitario.EmailValidator;
import pe.edu.galaxy.persistencia.utilitario.NumeroValidator;


/**
 * @author leo59
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString


public class Postulante extends Persona{
	private int idPostulante;
	private String celular; // opcional / necesario
	private String tlfFijo; // necesario / opcional
	private String email;
	

	@Override
	
	public String toString() {
		return super.toString()+ "Postulante [celular=" + celular + ", tlfFij=" + tlfFijo + ", email=" + email + "]";
	}
	
	/*
	public void setEmail(String email) throws PostulanteException {
		if(EmailValidator.validar(email)) {
			this.email = email;
		}
	}
	
	
	public void setCelular(String celular) throws PostulanteException {
		if(NumeroValidator.celular(celular)) {
			this.celular = celular;
		}
		
		if(celular.isEmpty()) {
			this.celular = "";
		}
	}
	
	public void setTlfFijo(String tlfFijo) throws PostulanteException{
		
		if(NumeroValidator.fijo(tlfFijo)) {
			this.tlfFijo = tlfFijo;
		}
		if(tlfFijo.isEmpty()) {
			this.tlfFijo = "";
		}
		
	}
	*/

	public void listar() {
		System.out.println("id: "+this.getIdPostulante()+" Nombre:"+super.nombre+", "+ "ApellidoPaterno:"+ super.apelPaterno );
	}
	






}

