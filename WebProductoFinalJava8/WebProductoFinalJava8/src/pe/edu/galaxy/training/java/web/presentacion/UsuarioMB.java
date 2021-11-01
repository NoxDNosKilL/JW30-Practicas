package pe.edu.galaxy.training.java.web.presentacion;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pe.edu.galaxy.training.java.web.bean.UsuarioBean;
import pe.edu.galaxy.training.java.web.service.exception.ServicioException;
import pe.edu.galaxy.training.java.web.service.impl.UsuarioServiceImpl;
import pe.edu.galaxy.training.java.web.service.inf.UsuarioService;

@Controller("usuarioMB")
@Scope(value="session")
public class UsuarioMB extends GenericoMB{

	private static final long serialVersionUID = -6468428214885243424L;

	private List<UsuarioBean> lstUsuarios;
	
	@Autowired 
	private UsuarioService usuarioService;
	
	private UsuarioBean usuario;

	@PostConstruct
	public void init() {
		this.setUsuario(new UsuarioBean());
		this.setLstUsuarios(new ArrayList<UsuarioBean>());
		this.buscar();

	}
	
	public void limpiar() {
		this.setUsuario(new UsuarioBean());
		this.buscar();
	}
	
	
	public void buscar() {
		try {

			this.lstUsuarios = this.getUsuarioService().listar(this.usuario);

		} catch (ServicioException e) {
			e.printStackTrace();
		}
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public List<UsuarioBean> getLstUsuarios() {
		return lstUsuarios;
	}

	public void setLstUsuarios(List<UsuarioBean> lstUsuarios) {
		this.lstUsuarios = lstUsuarios;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	
}
