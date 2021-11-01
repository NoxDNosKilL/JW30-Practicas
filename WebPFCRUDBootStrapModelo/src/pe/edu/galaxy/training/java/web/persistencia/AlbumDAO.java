package pe.edu.galaxy.training.java.web.persistencia;

import java.sql.SQLException;
import java.util.List;
import pe.edu.galaxy.training.java.web.model.Album;

public interface AlbumDAO {

	List<Album> listar(Album album) throws SQLException;
	
	boolean insert(Album album) throws SQLException;

	boolean update(Album album) throws SQLException;
	
	boolean delete(Album album) throws SQLException;
	
}
