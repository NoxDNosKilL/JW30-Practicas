package pe.edu.galaxy.training.java.web.presentacion;

import java.io.IOException;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.edu.galaxy.training.java.web.entity.Usuario;
import pe.edu.galaxy.training.java.web.presentacion.constantes.Constantes;
import pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum;
import pe.edu.galaxy.training.java.web.presentacion.constantes.Mensajes;

public class GenericoMB {

	protected void msgAlerta(String mensaje) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", mensaje));
	}

	protected void msgAlerta(String titulo, String mensaje) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensaje));
	}

	protected void msgInfo(String mensaje) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", mensaje));
	}

	protected void msgInfo(String titulo, String mensaje) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje));
	}

	protected void msgError(String mensaje) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje));
	}

	protected void msgError(String titulo, String mensaje) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensaje));
	}
	
	protected String getStringJSF(String key) {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try {
			properties.load(cl.getResourceAsStream(Constantes.FILE_NAME_RESOURCE));
			return properties.getProperty(key); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	protected HttpServletRequest getRequest(){	
		FacesContext fctx = FacesContext.getCurrentInstance();
		ExternalContext ectx = fctx.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
		return request;
	}
	
	public Usuario getUsuarioActivo() {
		Usuario oUsuario = new Usuario();
		HttpSession session = this.getRequest().getSession();
		if (session != null) {
			Object obj = session.getAttribute("usuario");
			if (obj != null) {
				oUsuario = (Usuario) obj;
			}
		}
		return oUsuario;
	}
	
	protected HttpServletResponse getResponse(){	
		FacesContext fctx = FacesContext.getCurrentInstance();
		ExternalContext ectx = fctx.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
		return response;
	}

	protected void msgCRUDExito(CrudEnum opc) {
		switch (opc) {
		case REGISTRO:
			this.msgInfo(Mensajes.MSG_MAN_EXITO_REG);
			break;

		case ACTUALIZACION:
			this.msgInfo(Mensajes.MSG_MAN_EXITO_ACT);
			break;

		case ELIMINACION:
			this.msgInfo(Mensajes.MSG_MAN_EXITO_ELI);
			break;
		default:
			break;
		}
			
		
	}
	
	
	protected void msgCRUDError(CrudEnum opc) {
		switch (opc) {
		case REGISTRO:
			this.msgInfo(Mensajes.MSG_MAN_ERROR_REG);
			break;

		case ACTUALIZACION:
			this.msgInfo(Mensajes.MSG_MAN_ERROR_ACT);
			break;

		case ELIMINACION:
			this.msgInfo(Mensajes.MSG_MAN_ERROR_ELI);
			break;
			
		case LISTADO:
			this.msgInfo(Mensajes.MSG_MAN_ERROR_LIST);
			break;
			
		case MODIFICACION:
			this.msgInfo(Mensajes.MSG_MAN_ERROR_MOD);
			break;
			
		default:
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
