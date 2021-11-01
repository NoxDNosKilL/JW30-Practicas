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
	public List<Album> listar(Album oAlbum) throws SQLException {
		
		String sql="SELECT CODIGO,NOMBRE,DESCRIPCION,ESTADO FROM ALBUM "
				+ " WHERE (UPPER(NOMBRE) LIKE ? "
				+ " OR UPPER(DESCRIPCION) LIKE ? )"
				+ " AND ESTADO='1'";
		try {
			Connection cn= BD.conectar();
			PreparedStatement ps= cn.prepareStatement(sql);
			
			if (oAlbum==null) {
				oAlbum= new Album("","");
			}
			if (oAlbum.getNombre()==null) {
				oAlbum.setNombre("");	
			}
			if (oAlbum.getDescripcion()==null) {
				oAlbum.setDescripcion("");
			}
			
			ps.setString(1, "%"+oAlbum.getNombre().toUpperCase()+"%");
			ps.setString(2, "%"+oAlbum.getDescripcion().toUpperCase()+"%");
			
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

	@Override
	public boolean insert(Album album) throws SQLException {
		
		String sql="INSERT INTO ALBUM(CODIGO,NOMBRE,DESCRIPCION)" + 
				"VALUES(SEQ_ALBUM.NEXTVAL,?,?)";
		try {
			Connection cn= BD.conectar();
			PreparedStatement ps= cn.prepareStatement(sql);
			ps.setString(1, album.getNombre());
			ps.setString(2, album.getDescripcion());
			ps.executeUpdate();
			ps.close();
			cn.close();
			return true;
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public boolean update(Album album) throws SQLException {
		
		String sql="UPDATE ALBUM SET NOMBRE=?,DESCRIPCION=?" + 
				" WHERE CODIGO=?";
		try {
			Connection cn= BD.conectar();
			PreparedStatement ps= cn.prepareStatement(sql);
			ps.setString(1, album.getNombre());
			ps.setString(2, album.getDescripcion());
			ps.setLong(3, album.getCodigo());
			ps.executeUpdate();
			ps.close();
			cn.close();
			return true;
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public boolean delete(Album album) throws SQLException {
		
		String sql="UPDATE ALBUM SET ESTADO=0" + 
				" WHERE CODIGO=?";
		try {
			Connection cn= BD.conectar();
			PreparedStatement ps= cn.prepareStatement(sql);
			ps.setLong(1, album.getCodigo());
			ps.executeUpdate();
			ps.close();
			cn.close();
			return true;
		} catch (SQLException e) {
			throw e;
		}
	}

}
