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
					name="cliente.listar",
					procedureName="PKG_CLIENTE.SP_LISTAR",
					resultClasses= ClienteEntity.class,
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_CURSOR", type=void.class ),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_RAZON_SOCIAL", type=String.class)	
						}					
				)
		}					
	)

@Entity(name="CLIENTE")
public class ClienteEntity extends GenericoEntity{
	
	@Id
	@Column(name="ID_CLIENTE")
	private Long codigo;
	
	@Column(name="RAZON_SOCIAL")
	private String razonSocial;
	
	@Column(name="RUC")
	private String ruc;
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@Column(name="TELEFONO")
	private String telefono;
	
	@Column(name="COMENTARIO")
	private String comentario;

	public ClienteEntity() {
		super();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}

