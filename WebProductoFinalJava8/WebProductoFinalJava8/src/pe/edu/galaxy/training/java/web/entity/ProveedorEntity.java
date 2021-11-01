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
					name="proveedor.listar",
					procedureName="PKG_PROVEEDOR.SP_LISTAR",
					resultClasses= ProveedorEntity.class,
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_CURSOR", type=void.class ),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_RAZON_SOCIAL", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_RUC", type=String.class)
						}					
				),
			@NamedStoredProcedureQuery(
					name="proveedor.insertar",
					procedureName="PKG_PROVEEDOR.SP_INSERTAR",
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.OUT, name="P_ID_PROVEEDOR", type=Long.class ),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_RAZON_SOCIAL", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_RUC", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_DIRECCION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_CORREO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_TELEFONO", type=String.class),
								
								// AUDITORIA
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Long.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)

						}					
				),
			@NamedStoredProcedureQuery(
					name="proveedor.actualizar",
					procedureName="PKG_PROVEEDOR.SP_ACTUALIZAR",
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_PROVEEDOR", type=Long.class ),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_RAZON_SOCIAL", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_RUC", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_DIRECCION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_CORREO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_TELEFONO", type=String.class),
								
								// AUDITORIA
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Long.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)

						}					
				),
			@NamedStoredProcedureQuery(
					name="proveedor.eliminar",
					procedureName="PKG_PROVEEDOR.SP_ELIMINAR",
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.IN, name="P_ID_PROVEEDOR", type=Long.class ),
								
								// AUDITORIA
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Long.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)

						}					
				)
		}					
		)
@Entity(name="PROVEEDOR")
public class ProveedorEntity extends GenericoEntity {

	@Id
	@Column(name="ID_PROVEEDOR")
	private Long codigo;
	
	@Column(name="RAZON_SOCIAL")
	private String razonSocial;
	
	@Column(name="RUC")
	private String ruc;
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@Column(name="CORREO")
	private String correo;

	@Column(name="TELEFONO")
	private String telefono;

	
	public ProveedorEntity() {
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

}
