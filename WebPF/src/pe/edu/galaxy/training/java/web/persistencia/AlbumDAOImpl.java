package pe.edu.galaxy.training.java.web.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.galaxy.training.java.web.model.Album;
import pe.edu.galaxy.training.java.web.util.BD;

public class AlbumDAOImpl implements AlbumDAO{

	@Override
	public List<Album> listar() throws SQLException {
		
		String sql="SELECT CODIGO,NOMBRE,DESCRIPCION,ESTADO FROM ALBUM";
		try {
			Connection cn= BD.conectar();
			PreparedStatement ps= cn.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			List<Album> albums= new ArrayList<Album>();
			while(rs.next()) {
				
				Album album= new Album();
				album.setCodigo(rs.getLong("CODIGO"));
				album.setNombre(rs.getString("NOMBRE"));
				album.setDescripcion(rs.getString("DESCRIPCION"));
				album.setEstado(rs.getString("ESTADO"));
				
				albums.add(album);
			}
			rs.close();
			ps.close();
			cn.close();
			return albums;
		} catch (SQLException e) {
			throw e;
		}
	}

}
