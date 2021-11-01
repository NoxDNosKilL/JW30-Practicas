package pe.edu.galaxy.training.java.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "usuario.validarAcceso",
        procedureName = "PKG_USUARIO.SP_VALIDAR_ACCESO",
        resultClasses = Usuario.class,
        parameters = {
          @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, name="P_CURSOR", type=void.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="P_USUARIO", type=String.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="P_CLAVE", type=String.class)
        })

})



@Table (name = "USUARIO")
@Entity(name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Usuario {
	
	@Id
	@Column(name = "ID_USUARIO")
	private String id;
		
	private String usuario;
	
	private String clave;
	
	private String nombre;
	
}
