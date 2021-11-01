package pe.edu.galaxy.training.java.web.service.inf;

import java.util.List;

import pe.edu.galaxy.training.java.web.service.exception.ServicioException;

public interface GenericoService<T> {
	
	   public List<T> listar(T t) throws ServicioException;
	    
	    public T buscarXCodigo(T t) throws ServicioException;
	    
	    public boolean insertar(T t) throws ServicioException;
	    
	    public boolean actualizar(T t) throws ServicioException;
	    
	    public boolean eliminar(T t) throws ServicioException;
	    
}
