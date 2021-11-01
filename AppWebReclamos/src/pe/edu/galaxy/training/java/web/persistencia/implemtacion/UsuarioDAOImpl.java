package pe.edu.galaxy.training.java.web.persistencia.implemtacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;
import pe.edu.galaxy.training.java.web.entity.Cliente;
import pe.edu.galaxy.training.java.web.entity.Producto;
import pe.edu.galaxy.training.java.web.entity.Usuario;
import pe.edu.galaxy.training.java.web.persistencia.exception.DAOException;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.ProductoDAO;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.UsuarioDAO;

//Anotaciones Spring
@Repository
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO {
	@PersistenceContext

	private EntityManager em;

	@Override
	public List<Usuario> validar(String usuario, String clave) throws DAOException {
		List <Usuario> usuarios = new ArrayList<>();
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("usuario.validar");
			
			spq.setParameter("P_USUARIO",( usuario==null?"":usuario));
			spq.setParameter("P_CLAVE",( clave==null?"":clave));


			if (spq.execute()) {
				Object ret = spq.getOutputParameterValue("P_CURSOR");//el retorno 
				if (ret != null) {	
					usuarios = (List<Usuario>) ret;
				}
			}
		} catch (Exception e) {
			System.err.println("Error al validar usuario " + e.getMessage());
			throw new DAOException(e);
		}
		
		return usuarios;
	}

	

}
