package pe.edu.galaxy.training.java.web.presentacion;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pe.edu.galaxy.training.java.web.bean.ClienteBean;
import pe.edu.galaxy.training.java.web.service.inf.ClienteService;

@Controller(value = "clienteMB")
@Scope(value = "session")
public class ClienteMB extends GenericoMB {

	private static final long serialVersionUID = 5799316448019518634L;
	private List<ClienteBean> lstClienteBean;
	private ClienteBean clienteBean;

	@Autowired
	private ClienteService clienteService;

	@PostConstruct
	public void init() {
		this.setLstClienteBean(new ArrayList<>());
		this.setClienteBean(new ClienteBean());
		this.buscar();
	}

	public void buscar() {
		try {
			this.lstClienteBean = this.getClienteService().listar(this.clienteBean);
			
			this.lstClienteBean.forEach(c -> {
				System.out.print(c);
			});
			
		} catch (Exception e) {

		}
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public List<ClienteBean> getLstClienteBean() {
		return lstClienteBean;
	}

	public void setLstClienteBean(List<ClienteBean> lstClienteBean) {
		this.lstClienteBean = lstClienteBean;
	}

	public ClienteBean getClienteBean() {
		return clienteBean;
	}

	public void setClienteBean(ClienteBean clienteBean) {
		this.clienteBean = clienteBean;
	}

}
