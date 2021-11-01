package pe.edu.galaxy.training.java.web.persistencia.implemtacion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.galaxy.training.java.web.entity.Usuario;
import pe.edu.galaxy.training.java.web.persistencia.exception.DAOException;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.UsuarioDAO;
@Repository
@Transactional
 
public class UsuarioDAOImpl implements UsuarioDAO {
	@PersistenceContext
	private EntityManager em;
	@Override
	public Usuario validarAcceso(Usuario usuario) throws DAOException {
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.buscarXCodigo");
			
			spq.setParameter("P_USUARIO",usuario.getUsuario());
			spq.setParameter("P_CLAVE",usuario.getClass());


			if (spq.execute()) {
				Object ret = spq.getOutputParameterValue("P_CURSOR");//el retorno 
				if (ret != null) {
					
					List<Usuario> lst = (List<Usuario>) ret;
					
					if(lst!=null) {
						return lst.get(0);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error de validar acceso: " + e.getMessage());
			throw new DAOException(e);
		}
		
		return null;
	}
	

	

	@Override
	public List<Usuario> listar(String nombre) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario buscarXCodigo(Long codigo) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean agregar(Usuario usuario) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Usuario usuario) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Usuario usuario) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	

}