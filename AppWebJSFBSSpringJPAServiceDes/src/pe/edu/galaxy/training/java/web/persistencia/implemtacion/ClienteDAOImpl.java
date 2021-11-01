package pe.edu.galaxy.training.java.web.persistencia.implemtacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.galaxy.training.java.web.entity.Cliente;
import pe.edu.galaxy.training.java.web.persistencia.exception.DAOException;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.ClienteDAO;

//Anotaciones Spring
@Repository
@Transactional
public class ClienteDAOImpl implements ClienteDAO {
	@PersistenceContext

	private EntityManager em;

	

	@Override
	public List<Cliente> listar(String razonSocial) throws DAOException {

		List<Cliente> clientes = new ArrayList<>();

		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cliente.listar");
			
			spq.setParameter("P_RAZON_SOCIAL",( razonSocial==null?"":razonSocial));

			if (spq.execute()) {
				Object ret = spq.getOutputParameterValue("P_CURSOR");//el retorno 
				if (ret != null) {
					clientes = (List<Cliente>)ret;
				}
			}
		} catch (Exception e) {
			System.err.println("Error al listar " + e.getMessage());
			throw new DAOException(e);
		}

		return clientes;
	}

	@Override
	public boolean agregar(Cliente cliente) throws DAOException {
		boolean sw = false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cliente.insertar");

			spq.setParameter("P_RAZON_SOCIAL",cliente.getRazonSocial());
			spq.setParameter("P_RUC",cliente.getRuc());
			spq.setParameter("P_DIRECCION",cliente.getDireccion());
			spq.execute();
			Object ret = spq.getOutputParameterValue(1);//el retorno 

			if (ret != null) {
				cliente.setId(Long.valueOf(ret.toString()));
				sw = true;
			}
		} catch (Exception e) {
			System.err.println("Error al agregar " + e.getMessage());
			throw new DAOException(e);
		}
		
		return sw;

	}

	@Override
	public boolean modificar(Cliente cliente) throws DAOException {
		boolean sw = false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cliente.actualizar");
			
			spq.setParameter("P_ID_CLIENTE",cliente.getId());			
			spq.setParameter("P_RAZON_SOCIAL",cliente.getRazonSocial());
			spq.setParameter("P_RUC",cliente.getRuc());
			spq.setParameter("P_DIRECCION",cliente.getDireccion());
			
			spq.execute();
			
			sw = true;
			
		} catch (Exception e) {
			System.err.println("Error al modificar " + e.getMessage());
			throw new DAOException(e);
		}
		return sw;
	}

	@Override
	public boolean eliminar(Cliente cliente) throws DAOException {
		boolean sw = false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cliente.eliminar");
			
			spq.setParameter("P_ID_CLIENTE",cliente.getId());			
			spq.execute();
			
			sw = true;
			
		} catch (Exception e) {
			System.err.println("Error al eliminar " + e.getMessage());
			throw new DAOException(e);
		}
		return sw;
	}

	@Override
	public Cliente buscarXCodigo(Long codigo) throws DAOException {
		

		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cliente.buscarXCodigo");
			
			spq.setParameter("P_ID_CLIENTE",codigo);

			if (spq.execute()) {
				Object ret = spq.getOutputParameterValue("P_CURSOR");//el retorno 
				if (ret != null) {
					
					List<Cliente> lst = (List<Cliente>) ret;
					
					if(lst!=null) {
						return lst.get(0);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error de buscarXCodigo: " + e.getMessage());
			throw new DAOException(e);
		}
		
		return null;
	}

	

}
