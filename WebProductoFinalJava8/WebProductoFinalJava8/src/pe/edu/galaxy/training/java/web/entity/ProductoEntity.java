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
					name="producto.listar", 
					procedureName="PKG_PRODUCTO.SP_LISTAR",
					resultClasses= ProductoEntity.class,
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_CURSOR", type=void.class ),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_USUARIO", type=Long.class)
						}					
				),
				@NamedStoredProcedureQuery(
						name="producto.buscarXCodigo", 
						procedureName="PKG_PRODUCTO.SP_BUSCAR_X_ID",
						resultClasses= ProductoEntity.class,
						parameters={
									@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_CURSOR", type=void.class ),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_CODIGO", type=Long.class)	
							}					
					),
				
				@NamedStoredProcedureQuery(
						name="producto.insertar", 
						procedureName="PKG_PRODUCTO.SP_INSERTAR",
						parameters={
									@StoredProcedureParameter(mode=ParameterMode.OUT, name="P_CODIGO", type=Long.class ),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_PRECIO", type=Float.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_STOCK", type=Integer.class),
									
									// Auditoria
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Long.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)
									
									
							}					
					),
				
				@NamedStoredProcedureQuery(
						name="producto.actualizar", 
						procedureName="PKG_PRODUCTO.SP_ACTUALIZAR",
						parameters={
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_CODIGO", type=Long.class ),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_PRECIO", type=Float.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_STOCK", type=Integer.class),
									
									// Auditoria
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Long.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)
									
									
							}					
					),
				
				@NamedStoredProcedureQuery(
						name="producto.eliminar", 
						procedureName="PKG_PRODUCTO.SP_ELIMINAR",
						parameters={
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_CODIGO", type=Long.class ),
									
									// Auditoria
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Long.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)
									
									
							}					
					)
		}
	)


@Entity(name="TBL_PRODUCTO")
public class ProductoEntity {

	@Id
	@Column(name="CODIGO")
	private Long codigo;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="PRECIO")
	private float precio;
	
	@Column(name="STOCK")
	private int stock;

	public ProductoEntity() {
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductoEntity [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock
				+ "]";
	}
	
}
