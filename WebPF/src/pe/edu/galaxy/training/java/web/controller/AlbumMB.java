package pe.edu.galaxy.training.java.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import pe.edu.galaxy.training.java.web.model.Album;
import pe.edu.galaxy.training.java.web.persistencia.AlbumDAO;
import pe.edu.galaxy.training.java.web.persistencia.AlbumDAOImpl;

@ManagedBean(name="albumMB")
public class AlbumMB {
	
	private List<Album> albums= new ArrayList<>();
	private AlbumDAO albumDAO= new AlbumDAOImpl();
	
	@PostConstruct
	public void init() {
		this.listar();
	}

	public void listar() {
		try {
			this.albums= albumDAO.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
}
