package pe.edu.galaxy.training.java.web.presentacion;

import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.ACTUALIZACION;
import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.MODIFICACION;
import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.ELIMINACION;
import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.LISTADO;
import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.REGISTRO;

import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.web.entity.Cliente;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.ClienteDAO;
import pe.edu.galaxy.training.java.web.presentacion.constantes.Mensajes;
import pe.edu.galaxy.training.java.web.servicio.excepcion.ServiceException;
import pe.edu.galaxy.training.java.web.servicio.interfases.ClienteService;


@Scope("session")
@Controller(value="clienteMB")

@Data 	
@EqualsAndHashCode(callSuper=false)

public class ClienteMB extends GenericoMB{
	
	@Autowired //IoC/DI
	private ClienteService clienteService;
	
	private ClienteDAO clienteDAO;
	
	private Cliente cliente;
	
	private Cliente clienteSelecteccionado;
	
	private List<Cliente> clientes;
	
	@PostConstruct
	public void init() {
		this.setCliente(new Cliente());
		this.listar();
	}
	
	public String nuevo() {
		this.setCliente(new Cliente());
		return "clientes_registro";
	}
	
	public String cancelar() {
		this.setCliente(new Cliente());
		this.listar();
		return "clientes_listado";
	}
	
	public void listar() {
		try {
			clientes = clienteService.listar(this.getCliente().getRazonSocial());
			clientes.forEach(System.out::println);
		} catch (Exception e) {
			super.msgCRUDError(LISTADO);
		}
	}
	
	public void grabar() {
		
		if (!validar()) {
			return;
		} 
		
		try {

			if (this.getCliente().getId()>0) {
				if (clienteService.modificar(this.getCliente())) {
					//System.out.println("Exito de actualizaci�n");
					super.msgCRUDExito(ACTUALIZACION);
				} else {
					//System.err.println("Error de actualizaci�n");
					super.msgCRUDError(ACTUALIZACION);

				}
					
			}else {
				if (clienteService.agregar(this.getCliente())) {
					//System.out.println("Exito de registro");
					super.msgCRUDExito(REGISTRO);

				} else {
					//System.err.println("Error de registro");
					super.msgCRUDError(REGISTRO);

				}
			}
			
		} catch (Exception e) {
			System.err.println(Mensajes.MSG_MAN_ERROR_GRA);
		}

	}
	
	public void eliminar(Cliente cliente) {

		try {
			
			if (clienteService.eliminar(cliente)) {
				this.listar();
				//System.out.println("Exito de eliminaci�n");
				super.msgCRUDExito(ELIMINACION);

			} else {
				//System.err.println("Error de eliminaci�n");
				super.msgCRUDError(ELIMINACION);

			}
		} catch (ServiceException e) {

			System.err.println(e.getMessage());
		}

	}
	
	
	public void eliminar() {

		try {

			if (clienteService.eliminar(this.getClienteSelecteccionado())) {
				this.setCliente(new Cliente());
				this.listar();
				super.msgCRUDExito(ELIMINACION);
				//System.out.println("Exito de eliminaci�n");
			} else {
				//System.err.println("Error de eliminaci�n");
				super.msgCRUDError(ELIMINACION);

			}
		} catch (ServiceException e) {

			System.err.println(e.getMessage());
		}

	}
	
	
	public String  modificar(Cliente cliente) {
		try {
			if(cliente == null) {
				System.out.println("Cliente nulo");
				return "";
			}			
			Cliente oCliente = this.getClienteService().buscarXCodigo(cliente.getId());

			if(oCliente!= null) {
				this.setCliente(oCliente);
				return "clientes_registro";
			}else {
				super.msgCRUDError(MODIFICACION);
				System.out.println("else");
			}
		} catch (Exception e) {
			super.msgCRUDError(MODIFICACION);
			System.out.println("catch");

		}		
		//this.setCliente(cliente);
		//return "clientes_registro";
		return "";

	}
	
	
	public void limpiar() {
		this.setCliente(new Cliente());
		this.listar();
		//System.out.println(this.getCliente().getRazonSocial());
	}

		
	public void seleccionar(Cliente cliente) {
		this.setClienteSelecteccionado(cliente);
	}
	
	
	private boolean validar () {
		
		String razonSocial = this.getCliente().getRazonSocial();
		
		if(razonSocial == null) {
			super.msgAlerta("Raz�n Social requerida");
			return false;
		}
		
		razonSocial = razonSocial.trim();
		
		if(razonSocial.length()<5 || razonSocial.length()>240) {
			super.msgAlerta("La Raz�n Social debe tener como minimo 5 caracteres y maximo 240");
			return false;
		}	
		
		if(this.getCliente().getRuc().trim().length()!=11) {
			super.msgAlerta("El precio debe contener 11 digitos");
			return false;
		}		
		if(this.getCliente().getDireccion().trim().length()<10) {
			super.msgAlerta("La Direcci�n debe tener como m�nimo 10 caracteres");
			return false;
		}	
		
		return true;
	}
}
