package pe.edu.galaxy.training.java.jd0.presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import pe.edu.galaxy.training.java.jd0.presentacion.constantes.CrudEnum;
import pe.edu.galaxy.training.java.jd0.presentacion.constantes.Mensajes;

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
		default:
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
