package pe.edu.galaxy.training.java.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pe.edu.galaxy.training.java.web.model.Album;
import pe.edu.galaxy.training.java.web.persistencia.AlbumDAO;
import pe.edu.galaxy.training.java.web.persistencia.AlbumDAOImpl;

@ManagedBean(name="albumMB")
@SessionScoped
public class AlbumMB {
	
	private List<Album> albums= new ArrayList<>();
	private AlbumDAO albumDAO= new AlbumDAOImpl();
	
	private Album album;
	
	@PostConstruct
	public void init() {
		this.setAlbum(new Album());
		this.listar();
		
	}

	public void listar() {
		try {
			this.album.setDescripcion(this.getAlbum().getNombre());
			this.albums= albumDAO.listar(this.album);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String nuevo() {
		this.setAlbum(new Album("PrimeFaces CRUD","Trabajando con múltiples formularios..."));
		
		System.out.println("...nuevo");
		return "album_registro";
	}
	
	public String editar(Album album) {
		System.out.println("...editar");
		this.setAlbum(album);
		//this.album= album;
		return "album_registro";
	}
	
	public void eliminar(Album album) {
		System.out.println("...eliminar");
		try {
			if (this.albumDAO.delete(album)) {
				System.out.println("Exito al eliminar");
				this.listar();
			}else {
				System.err.println("Error al eliminar");
			}
		} catch (Exception e) {
		}
	}
	
	public void limpiar() {
		this.setAlbum(new Album());
		this.setAlbums(new ArrayList<Album>());
	}

	public String cancelar() {
		this.setAlbum(new Album());
		System.out.println("...cancelar");
		return "album_listado";
	}

	public void grabar() {
		boolean sw;
		try {
			if (this.getAlbum().getCodigo()>0) {
				sw= this.albumDAO.update(album);
			}else {
				sw= this.albumDAO.insert(album);
			}
			
			if (sw) {
				System.out.println("Exito al grabar");
			} else {
				System.err.println("Error al grabar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error al grabar");
		}
	}
	
	
	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
}
