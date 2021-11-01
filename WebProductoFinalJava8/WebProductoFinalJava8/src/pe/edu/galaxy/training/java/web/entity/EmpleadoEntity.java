package pe.edu.galaxy.training.java.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

@NamedStoredProcedureQueries(
		{
			
			@NamedStoredProcedureQuery(
					name="empleado.listar",
					procedureName="PKG_EMPLEADO.SP_LISTAR",
					resultClasses= EmpleadoEntity.class,
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_CURSOR", type=void.class ),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_APELLIDO_PATERNO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_APELLIDO_MATERNO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class)	
						}					
				),
			@NamedStoredProcedureQuery(
					name="empleado.buscarXCodigo",
					procedureName="PKG_EMPLEADO.SP_BUSCAR_X_ID",
					resultClasses= EmpleadoEntity.class,
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_CURSOR", type=void.class ),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_EMPLEADO", type=Long.class)
						}					
				),
			@NamedStoredProcedureQuery(
					name="empleado.insertar", 
					procedureName="PKG_EMPLEADO.SP_INSERTAR",
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.OUT, name="P_ID_EMPLEADO", type=Long.class ),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_APELLIDO_PATERNO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_APELLIDO_MATERNO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NRO_DOCUMENTO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_DIRECCION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_CORREO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_TELEFONO", type=String.class),
								
								// Auditoria
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Long.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)
								
								
						}					
				),
			@NamedStoredProcedureQuery(
					name="empleado.actualizar", 
					procedureName="PKG_EMPLEADO.SP_ACTUALIZAR",
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.IN, name="P_ID_EMPLEADO", type=Long.class ),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_APELLIDO_PATERNO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_APELLIDO_MATERNO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NRO_DOCUMENTO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_DIRECCION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_CORREO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_TELEFONO", type=String.class),
								
								// Auditoria
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Long.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)
								
								
						}					
				),
			@NamedStoredProcedureQuery(
					name="empleado.eliminar", 
					procedureName="PKG_EMPLEADO.SP_ELIMINAR",
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.IN, name="P_ID_EMPLEADO", type=Long.class ),
								
								// Auditoria
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Long.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)
								
								
						}					
				)
		}					
		)

@Entity(name="EMPLEADO")
public class EmpleadoEntity extends GenericoEntity {

	@Id
	@Column(name="ID_EMPLEADO")
	private Long codigo;
	
	@Column(name="APELLIDO_PATERNO")
	private String apellidoPaterno="";
	
	@Column(name="APELLIDO_MATERNO")
	private String apellidoMaterno="";
	
	@Column(name="NOMBRE")
	private String nombre="";
	
	@Column(name="NRO_DOCUMENTO")
	private String nroDocumento;
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@Column(name="CORREO")
	private String correo;
	
	@Column(name="TELEFONO")
	private String telefono;
	  
	
	public EmpleadoEntity() {
		super();
	}


	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public String getApellidoPaterno() {
		return apellidoPaterno;
	}


	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}


	public String getApellidoMaterno() {
		return apellidoMaterno;
	}


	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getNroDocumento() {
		return nroDocumento;
	}


	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	
	


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	@Override
	public String toString() {
		return "EmpleadoEntity [codigo=" + codigo + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno="
				+ apellidoMaterno + ", nombre=" + nombre + ", nroDocumento=" + nroDocumento + ", direccion=" + direccion
				+ ", correo=" + correo + ", telefono=" + telefono + "]";
	}
	
}
