package pe.edu.galaxy.training.java.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import pe.edu.galaxy.training.java.web.model.Producto;

@ManagedBean(name="productoMB")
public class ProductoMB {
	

	private Producto producto= new Producto(100, "Java Web - Oracle");
	private String autor="Galaxy";
	private List<Producto> productos= new ArrayList<>();
	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@PostConstruct
	public void init() {
		productos.add(producto);
		productos.add(new Producto(200, "Net Core con Azure"));
		productos.add(new Producto(300, "Angular Intermedio - Proyecto de Integracion"));
		productos.add(new Producto(400, "AWS Esentials- Certifications"));

	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public void imprimir() {
		System.out.println(this.getProducto().getNombre());
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
}
