package pe.edu.galaxy.training.java.jd0.persistencia.implemtacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edu.galaxy.training.java.jd0.bean.Producto;
import pe.edu.galaxy.training.java.jd0.persistencia.exception.DAOException;
import pe.edu.galaxy.training.java.jd0.persistencia.interfaces.ProductoDAO;
import pe.edu.galaxy.training.java.jd0.persistencia.util.BDUtil;

public class ProductoDAOImpl implements ProductoDAO{

	
	
	@Override
	public List<Producto> listar() throws DAOException {
		
		List<Producto> productos= new ArrayList<>();
		
		try {
			Connection cn = BDUtil.getConnection();
			
			String sql="SELECT ID_CODIGO,NOMBRE,STOCK,PRECIO FROM PRODUCTO WHERE ESTADO=1";
			Statement st= cn.createStatement();
			ResultSet rs= st.executeQuery(sql);
			
			while(rs.next()) {		
				Producto producto= new Producto();
				producto.setId(rs.getLong("ID_CODIGO"));
				producto.setNombre(rs.getString("NOMBRE"));
				producto.setPrecio(rs.getDouble("PRECIO"));
				producto.setStock(rs.getInt("STOCK"));
				productos.add(producto);
			}
			
			rs.close();
			st.close();
			cn.close();
			
		} catch (Exception e) {
			System.err.println("Error de consulta " + e.getMessage());
		}
		
		return productos;
	}

	@Override
	public List<Producto> listar(String nombre) throws DAOException {
		
		List<Producto> productos= new ArrayList<>();
		
		try {
			Connection cn = BDUtil.getConnection();
			
			String sql="SELECT ID_CODIGO,NOMBRE,STOCK,PRECIO FROM "
					+ " PRODUCTO WHERE UPPER(NOMBRE) like ? AND ESTADO=1";
			PreparedStatement ps= cn.prepareStatement(sql);
			
			nombre=(nombre==null)?"":nombre;
			
			ps.setString(1, "%"+nombre.toUpperCase()+"%");
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {		
				Producto producto= new Producto();
				producto.setId(rs.getLong("ID_CODIGO"));
				producto.setNombre(rs.getString("NOMBRE"));
				producto.setPrecio(rs.getDouble("PRECIO"));
				producto.setStock(rs.getInt("STOCK"));
				productos.add(producto);
			}
			
			rs.close();
			ps.close();
			cn.close();
			
		} catch (Exception e) {
			System.err.println("Error de consulta " + e.getMessage());
		}
		
		return productos;
	}

	@Override
	public boolean agregar(Producto producto) throws DAOException {
		boolean sw=false;
		try {
			Connection cn = BDUtil.getConnection();

			String sql="INSERT INTO PRODUCTO(ID_CODIGO,NOMBRE,STOCK,PRECIO)" + 
					"VALUES(SEQ_PRODUCTO.NEXTVAL,?,?,?)"; 
			
			PreparedStatement ps= cn.prepareStatement(sql);
			ps.setString(1, producto.getNombre());
			ps.setInt(2, producto.getStock());
			ps.setDouble(3, producto.getPrecio());
			
			int ret =ps.executeUpdate();
			
			if (ret>0) {

				sw= true;
			}
			ps.close();
			cn.close();
			
		} catch (Exception e) {
			throw new DAOException("Error de registro " + e.getMessage());
		}
	
		return sw;
	
	}

	@Override
	public boolean modificar(Producto producto) throws DAOException {
		boolean sw=false;
		try {
			Connection cn = BDUtil.getConnection();
						
			String sql="UPDATE PRODUCTO SET NOMBRE=?, STOCK=?, PRECIO=?" + 
					"WHERE ID_CODIGO=?"; 
			
			PreparedStatement ps= cn.prepareStatement(sql);
			ps.setString(1, producto.getNombre());
			ps.setInt(2, producto.getStock());
			ps.setDouble(3, producto.getPrecio());
			ps.setLong(4,producto.getId());
			
			int ret =ps.executeUpdate();
			
			if (ret>0) {
				sw=true;
			}
			
			ps.close();
			cn.close();
			
		} catch (Exception e) {
			throw new DAOException("Error en la actualización " + e.getMessage());
		}
	
		return sw;
	}

	@Override
	public boolean eliminar(Producto producto) throws DAOException {
		boolean sw=false;
		try {
			Connection cn = BDUtil.getConnection();
						
			String sql="UPDATE PRODUCTO SET ESTADO=0" + 
					"WHERE ID_CODIGO=?"; 
			
			PreparedStatement ps= cn.prepareStatement(sql);

			ps.setLong(1,producto.getId());
			
			int ret =ps.executeUpdate();
			
			if (ret>0) {
				sw=true;
			}
			
			ps.close();
			cn.close();
			
		} catch (Exception e) {
			throw new DAOException("Error en la eliminación " + e.getMessage());
		}
	
		return sw;
	}

}
