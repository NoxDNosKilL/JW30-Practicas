package pe.edu.galaxy.training.java.web.presentacion;
// Sirve para inicio y cierre de sesion  **No es para crear ni altearar usuario**


import javax.annotation.PostConstruct;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.web.entity.Usuario;


@Scope("session")
@Controller(value="loginMB")

@Data 	
@EqualsAndHashCode(callSuper=false)

public class LoginMB extends GenericoMB {
	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		this.setUsuario(new Usuario());
		this.getUsuario().setNombre("Fernando");
		this.getUsuario().setUsuario("admin");
		this.getUsuario().setClave("123");
		
	}
	
	public String validarAcceso() {
		if(usuario.getUsuario().equals("admin")
				&& usuario.getClave().equals("123")) {
				return "panel";
		} else {
			super.msgAlerta("Usuario y/o clave incorrecto");
		}
		
		return "";
	}

}
