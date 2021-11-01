package pe.edu.galaxy.persistencia.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pe.edu.galaxy.bean.Postulante;
import pe.edu.galaxy.bean.exception.PostulanteException;
import pe.edu.galaxy.persistencia.exception.DAOException;
import pe.edu.galaxy.persistencia.interfaces.PostulanteDAO;
import pe.edu.galaxy.persistencia.utilitario.BDUtil;



public class PostulanteDAOImpl implements PostulanteDAO {

	@Override
	public List<Postulante> listar() throws DAOException {
		List<Postulante> postulantes = new ArrayList<>();
		try {
			Connection cn = BDUtil.getConnection();

			String sql = "SELECT ID_POSTULANTE,NOMBRE,APEL_PATERNO,APEL_MATERNO,DNI, FECHA_NACIM, TLF_FIJO, CELULAR,"
						+ " EMAIL, SEXO, DIRECCION, DEPARTAMENTO, PROVINICIA,"
						+ " DISTRITO FROM POSTULANTE WHERE ESTADO=1";
			
			//String sql = "SELECT ID_POSTULANTE,NOMBRE, DIRECCION,APEL_PATERNO,APEL_MATERNO, DNI, FECHA_NACIM, FROM POSTULANTE WHERE ESTADO=1" ;
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			//int cont = 0;
			while (rs.next()) {
				//cont++;
				Postulante postulante = new Postulante();
				postulante.setIdPostulante(rs.getInt("ID_POSTULANTE"));
				postulante.setNombre(rs.getString("NOMBRE"));
				postulante.setDireccion(rs.getString("DIRECCION"));
				postulante.setApelPaterno(rs.getString("APEL_PATERNO"));
				postulante.setApelMaterno(rs.getString("APEL_MATERNO"));
				postulante.setDni(rs.getString("DNI"));
				postulante.setFechaNacimiento(rs.getString("FECHA_NACIM"));
				postulante.setTlfFijo(rs.getString("TLF_FIJO"));
				postulante.setCelular(rs.getString("CELULAR"));
				postulante.setEmail(rs.getString("EMAIL"));
				postulante.setGenero(rs.getString("SEXO"));
				postulante.setDireccion(rs.getString("DIRECCION"));
				postulante.setDepartamento(rs.getString("DEPARTAMENTO"));
				postulante.setProvincia(rs.getString("PROVINICIA"));
				postulante.setDistrito(rs.getString("DISTRITO"));
							
				postulantes.add(postulante);
			}
			//System.out.println("Cantidad : "+cont);
			rs.close();
			st.close();
			cn.close();

		} catch (Exception e) {
			System.err.println("Error de consulta " + e.getMessage());
		}

		return postulantes;
	}

	@Override
	public List<Postulante> listar(String nombre) throws DAOException {
		List<Postulante> postulantes= new ArrayList<>();
		
		try {
			Connection cn = BDUtil.getConnection();
			
			String sql="SELECT ID_POSTULANTE,NOMBRE,APEL_PATERNO,APEL_MATERNO,DNI, FECHA_NACIM, TLF_FIJO, CELULAR,"
					+ " EMAIL, SEXO, DIRECCION, DEPARTAMENTO, PROVINICIA,"
					+ " DISTRITO FROM POSTULANTE WHERE UPPER(NOMBRE) like ? AND ESTADO=1";
			
			PreparedStatement ps= cn.prepareStatement(sql);
			
			nombre=(nombre==null)?"":nombre;
			
			ps.setString(1, "%"+nombre.toUpperCase()+"%");
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {		
				Postulante postulante = new Postulante();
				postulante.setIdPostulante(rs.getInt("ID_POSTULANTE"));
				postulante.setNombre(rs.getString("NOMBRE"));
				postulante.setApelPaterno(rs.getString("APEL_PATERNO"));
				postulante.setApelMaterno(rs.getString("APEL_MATERNO"));
				postulante.setDni(rs.getString("DNI"));
				postulante.setFechaNacimiento(rs.getString("SEXO"));
				postulante.setTlfFijo(rs.getString("TLF_FIJO"));
				postulante.setCelular(rs.getString("CELULAR"));
				postulante.setEmail(rs.getString("EMAIL"));
				postulante.setGenero(rs.getString("SEXO"));
				postulante.setDireccion(rs.getString("DIRECCION"));
				postulante.setDepartamento(rs.getString("DEPARTAMENTO"));
				postulante.setProvincia(rs.getString("PROVINICIA"));
				postulante.setDistrito(rs.getString("DISTRITO"));				
				postulantes.add(postulante);
			}
			
			rs.close();
			ps.close();
			cn.close();
			
		} catch (Exception e) {
			System.err.println("Error de consulta " + e.getMessage());
		}
		
		return postulantes;
	}

	@Override
	public boolean agregar(Postulante postulante) throws DAOException {
		boolean sw=false;
		try {
			Connection cn = BDUtil.getConnection();

			String sql="INSERT INTO POSTULANTE(ID_POSTULANTE,NOMBRE,APEL_PATERNO,APEL_MATERNO,"
						+ "DNI,FECHA_NACIM,TLF_FIJO, CELULAR,EMAIL,SEXO, DIRECCION, DEPARTAMENTO, "
						+ "PROVINICIA,DISTRITO)" + 
						"VALUES(SEQ_POSTULANTE.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
			
			PreparedStatement ps= cn.prepareStatement(sql);
			ps.setString(1, postulante.getNombre());
			ps.setString(2, postulante.getApelPaterno());
			ps.setString(3, postulante.getApelMaterno());
			ps.setString(4, postulante.getDni());
			ps.setString(5, postulante.getFechaNacimiento());
			ps.setString(6, postulante.getTlfFijo());
			ps.setString(7, postulante.getCelular());
			ps.setString(8, postulante.getEmail());
			ps.setString(9, postulante.getGenero());
			ps.setString(10, postulante.getDireccion());
			ps.setString(11, postulante.getDepartamento());
			ps.setString(12, postulante.getProvincia());
			ps.setString(13, postulante.getDistrito());

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
	public boolean modificar(Postulante postulante) throws DAOException {
		boolean sw=false;
		try {
			Connection cn = BDUtil.getConnection();
						
			String sql=	"UPDATE POSTULANTE SET NOMBRE=?, APEL_PATERNO=?,APEL_MATERNO=?, "
						+ "DNI=?,FECHA_NACIM=?,TLF_FIJO=?, CELULAR=?,EMAIL=?,SEXO=?,"
						+ "DIRECCION=?, DEPARTAMENTO=?,PROVINICIA=?,DISTRITO=?" + 
						"WHERE ID_POSTULANTE=?"; 
			
			PreparedStatement ps= cn.prepareStatement(sql);
			ps.setString(1, postulante.getNombre());
			ps.setString(2, postulante.getApelPaterno());
			ps.setString(3, postulante.getApelMaterno());
			ps.setString(4, postulante.getDni());
			ps.setString(5, postulante.getFechaNacimiento());
			ps.setString(6, postulante.getTlfFijo());
			ps.setString(7, postulante.getCelular());
			ps.setString(8, postulante.getEmail());
			ps.setString(9, postulante.getGenero());
			ps.setString(10, postulante.getDireccion());
			ps.setString(11, postulante.getDepartamento());
			ps.setString(12, postulante.getProvincia());
			ps.setString(13, postulante.getDistrito());
			ps.setInt(14, postulante.getIdPostulante());
			
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
	public boolean eliminar(Postulante postulante) throws DAOException {
		boolean sw=false;
		try {
			Connection cn = BDUtil.getConnection();
						
			String sql="UPDATE POSTULANTE SET ESTADO=0" + 
					"WHERE ID_POSTULANTE=?"; 
			
			PreparedStatement ps= cn.prepareStatement(sql);

			ps.setLong(1,postulante.getIdPostulante());
			
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
