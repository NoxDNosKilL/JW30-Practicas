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
						name="producto.listarPrecios", 
						procedureName="PKG_PRODUCTO.SP_LISTAR_PRECIOS",
						resultClasses= ProductoPrecioEntity.class,
						parameters={
									@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_CURSOR", type=void.class ),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class)	
							}					
					),
				@NamedStoredProcedureQuery(
						name="producto.listarPreciosxCodigo", 
						procedureName="PKG_PRODUCTO.SP_LISTAR_PRECIOS_X_CODIGO",
						resultClasses= ProductoPrecioEntity.class,
						parameters={
									@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_CURSOR", type=void.class ),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_CODIGO", type=Long.class)	
							}					
					)
		}
	)

@Entity(name="V_PRODUCTO_PRECIO")
public class ProductoPrecioEntity {

	@Id
	@Column(name="CODIGO")
	private Long codigo;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="PRECIO")
	private float precio;
	
	@Column(name="FECHA")
	private String fecha;
	
	@Column(name="USUARIO")
	private String usuario;
	
	@Column(name="NOMBRE_USUARIO")
	private String nombreUsuario;

	public ProductoPrecioEntity() {
		super();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}
