package pe.edu.galaxy.training.java.jd0.presentacion;
// Sirve para inicio y cierre de sesion  **No es para crear ni altearar usuario**


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.jd0.bean.Usuario;

@SessionScoped
@ManagedBean(name="loginMB")
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
