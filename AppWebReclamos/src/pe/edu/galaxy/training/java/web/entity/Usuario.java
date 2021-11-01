package pe.edu.galaxy.training.java.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "usuario.validar",
        procedureName = "PKG_USUARIO_T.SP_VALIDAR_USUARIO",
        resultClasses = Producto.class,
        parameters = {
          @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, name="P_CURSOR", type=void.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="P_USUARIO", type=String.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="P_CLAVE", type=String.class)

    })
})

@Data
@Table (name = "USUARIO_T")
@Entity(name = "Usuario")
public class Usuario {
	
	@Id
	@Column(name = "ID_USUARIO")
	private long id;
	
	@Column(name = "USUARIO")
	private String usuario;
	
	@Column(name = "CLAVE")
	private String clave;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "NUM_DOC")
	private String numDoc;

	@Column(name = "TIPO_DOC")
	private String tipoDoc;

	@Column(name = "TELF")
	private String tlf;

	@Column(name = "EMAIL")
	private String email;	
}

