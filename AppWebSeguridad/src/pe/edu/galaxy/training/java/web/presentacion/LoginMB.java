package pe.edu.galaxy.training.java.web.presentacion;
// Sirve para inicio y cierre de sesion  **No es para crear ni altearar usuario**


import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.web.entity.Usuario;
import pe.edu.galaxy.training.java.web.persistencia.util.Encrypt;
import pe.edu.galaxy.training.java.web.servicio.interfases.UsuarioService;

@Slf4j
@Scope("session")
@Controller(value="loginMB")
@Data 
@EqualsAndHashCode(callSuper=false)
//@Slf4j
//@Scope("session")
//@Controller(value="loginMB")

//@Data 	
//@EqualsAndHashCode(callSuper=false)

public class LoginMB extends GenericoMB {
	private Usuario usuario;
	protected UsuarioService usuarioService;
	@PostConstruct
	public void init() {
		this.setUsuario(new Usuario());
		this.getUsuario().setNombre("Fernando");
		this.getUsuario().setUsuario("ADMIN");
		this.getUsuario().setClave("123");
		
	}

	public String validarAcceso() {
		String page = "login";

		String key = super.getStringJSF("Encrypt.key");
		Encrypt.init(key);

		try {
			
			String claveEncripatada = Encrypt.encrypt(this.getUsuario().getClave());

			System.err.println("claveEncripatada " + claveEncripatada);

			this.getUsuario().setClave(claveEncripatada);

			try {
				Usuario oUsuario = this.getUsuarioService().validarAcceso(this.getUsuario());
				System.out.println("oUsuario " + oUsuario);
				if (oUsuario != null) {
					HttpSession session = super.getRequest().getSession(true);
					System.out.println("session.getId() "+session.getId());
					session.setAttribute("ID", session.getId());
					System.out.println("oUsuario "+oUsuario);
					session.setAttribute("usuario", oUsuario);
					page = "panel";
				} else {

					super.msgAlerta("Usuario y/o clave incorrecto");
				}

			} catch (Exception e) {
				this.msgError("Error al validar usuario " + e.getMessage());
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}
	
/*	public void cerrarSesion() {
		try {
			HttpSession session = super.getRequest().getSession();
			session.removeAttribute("ID");
			session.removeAttribute("usuario");
			session.invalidate();
			FacesContext.getCurrentInstance().responseComplete();
			super.getResponse().sendRedirect("index");
		} catch (Exception e) {
			System.out.println("exception cerrarSesion");
			// e.printStackTrace();
		}*/
		
		public String cerrarSesion() {
			try {
				HttpSession session = super.getRequest().getSession();
				session.removeAttribute("ID");
				session.removeAttribute("usuario");
				//session.invalidate();
				//FacesContext.getCurrentInstance().responseComplete();
				return "login";
				//super.getResponse().sendRedirect(loginURL);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("exception cerrarSesion");
			}
			return "";

		}

	}


