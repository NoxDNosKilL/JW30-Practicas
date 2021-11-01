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
					name="usuario.listar",
					procedureName="PKG_USUARIO.SP_LISTAR",
					resultClasses= UsuarioEntity.class,
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_CURSOR", type=void.class ),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_USUARIO", type=String.class)
								
						}					
				),
			@NamedStoredProcedureQuery(
						name="usuario.validarAcceso",
						procedureName="PKG_USUARIO.SP_VALIDAR_ACCESO",
						resultClasses= UsuarioEntity.class,
						parameters={
									@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_CURSOR", type=void.class ),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_USUARIO", type=String.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_CLAVE", type=String.class)
									
							}					
					)
		}					
		)

@Entity(name="USUARIO")
public class UsuarioEntity extends GenericoEntity {

	@Id
	@Column(name="ID_USUARIO")
	private Long codigo;
	
	@Column(name="USUARIO")
	private String usuario;
	
	@Column(name="CLAVE")
	private String clave;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	public UsuarioEntity() {
		super();
	}


	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "UsuarioEntity [codigo=" + codigo + ", usuario=" + usuario + ", clave=" + clave + ", nombre=" + nombre
				+ "]";
	}


	
}
