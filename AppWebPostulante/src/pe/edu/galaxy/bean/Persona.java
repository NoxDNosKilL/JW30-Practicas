package pe.edu.galaxy.bean;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import pe.edu.galaxy.bean.exception.PostulanteException;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Persona {
	
	protected String nombre; 
	protected String apelMaterno; 	// opcional
	protected String apelPaterno;	// opcional
	protected String dni; 
	protected String genero;
	private String direccion;
	protected String departamento;		// opcional
	protected String provincia; 	// opcional
	protected String distrito;		// opcional
	protected String fechaNacimiento;
	
	
	
	/*
	 * public void setNombre(String nombre) throws PostulanteException { this.nombre
	 * = DatosValidator.validarCaracteres(nombre); }
	 * 
	 * public void setApelMaterno (String apelMaterno) throws PostulanteException {
	 * 
	 * this.apelMaterno = DatosValidator.validarCaracteres(apelMaterno); }
	 * 
	 * public void setApelPaterno(String apelPaterno) throws PostulanteException {
	 * this.apelPaterno = DatosValidator.validarCaracteres(apelPaterno);
	 * 
	 * }
	 * 
	 * public void setDni(String dni)throws PostulanteException {
	 * if(DNIValidator.validar(dni)) { this.dni = dni; } }
	 * 
	 * public void setGenero(String genero) throws PostulanteException {
	 * if(GeneroValidator.validar(genero)) { this.genero = genero.toUpperCase(); }
	 * 
	 * }
	 * 
	 * public void setSexo(String sexo) { this.genero = sexo; }
	 */




	
	
	
}
