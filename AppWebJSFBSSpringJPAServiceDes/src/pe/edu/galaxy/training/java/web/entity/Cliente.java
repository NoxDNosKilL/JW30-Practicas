package pe.edu.galaxy.training.java.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "cliente.listar",
        procedureName = "PKG_CLIENTE.SP_LISTAR",
        resultClasses = Cliente.class,
        parameters = {
          @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, name="P_CURSOR", type=void.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="P_RAZON_SOCIAL", type=String.class)
    }),
    
    @NamedStoredProcedureQuery(
            name = "cliente.buscarXCodigo",
            procedureName = "PKG_CLIENTE.SP_BUSCAR_X_ID",
            resultClasses = Cliente.class,
            parameters = {
              @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, name="P_CURSOR", type=void.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="P_ID_CLIENTE", type=Long.class)
        }),
    
    @NamedStoredProcedureQuery(
            name = "cliente.insertar",
            procedureName = "PKG_CLIENTE.SP_INSERTAR",
            parameters = {
              @StoredProcedureParameter(mode=ParameterMode.OUT, name="P_ID_CLIENTE", type=Long.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="P_RAZON_SOCIAL", type=String.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="P_RUC", type=String.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="P_DIRECCION", type=String.class)
        }),
    
    @NamedStoredProcedureQuery(
            name = "cliente.actualizar",
            procedureName = "PKG_CLIENTE.SP_ACTUALIZAR",
            parameters = {
              @StoredProcedureParameter(mode=ParameterMode.IN, name="P_ID_CLIENTE", type=Long.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="P_RAZON_SOCIAL", type=String.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="P_RUC", type=String.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="P_DIRECCION", type=String.class)
        }),
    @NamedStoredProcedureQuery(
            name = "cliente.eliminar",
            procedureName = "PKG_CLIENTE.SP_ELIMINAR",
            parameters = {
              @StoredProcedureParameter(mode=ParameterMode.IN, name="P_ID_CLIENTE", type=Long.class)
        })
    
    
})

@Table (name = "CLIENTE")
@Entity(name = "Cliente")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class Cliente {
	@Id
	@Column(name = "ID_CLIENTE")
	private long id;
	
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	
	@Column(name = "RUC")
	private String ruc;

	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "ESTADO")
	private boolean estado;
	
	
	


}
