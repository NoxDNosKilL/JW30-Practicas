package pe.edu.galaxy.training.java.web.presentacion;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import pe.edu.galaxy.training.java.web.bean.ProductoBean;
import pe.edu.galaxy.training.java.web.bean.ProductoPrecioBean;
import pe.edu.galaxy.training.java.web.service.exception.ServicioException;
import pe.edu.galaxy.training.java.web.service.inf.ProductoService;

@Controller("productoMB")
@Scope(value="session")
public class ProductoMB extends GenericoMB {

	private static final long serialVersionUID = -3121157687796717675L;

	private List<ProductoBean> lstProductos;
	
	private List<ProductoPrecioBean> lstProductoPrecios;
	
	@Autowired 
	private ProductoService productoService;
	
	/*
	public ProductoMB(ProductoService productoService) {
		this.productoService=productoService;
	}*/

	private ProductoBean producto;
	

	@PostConstruct
	public void init() {
		this.setProducto(new ProductoBean());
		this.setLstProductos(new ArrayList<ProductoBean>());
		this.buscar();
		this.buscarPrecios();
	}


	public void limpiar() {
		this.setProducto(new ProductoBean());
		this.buscar();
	}
	
	public String nuevo() {
		this.setProducto(new ProductoBean());
		return "producto_registro";
	}

	public String cancelar() {
		this.setProducto(new ProductoBean());
		return "producto_listado";
	}

	public String modificar(ProductoBean producto) {
		try {
			this.producto = this.getProductoService().buscarXCodigo(producto);
			this.actualizarHistoricoPrecios();
		} catch (Exception e) {
			super.error("Error al modificar producto");
		}
		return "producto_registro";
	}

	private void actualizarHistoricoPrecios() {
		try {
			this.lstProductoPrecios=this.getProductoService().listarPreciosxCodigo(this.producto);

		} catch (Exception e) {
			super.error("Error al recuperar historico de precios del producto");
		}

	}
	
	public void eliminar(ProductoBean producto) {
		try {
			super.setAuditoria(producto);
			if (this.getProductoService().eliminar(producto)) {
				super.aviso("Exito al eliminar producto");
				this.buscar();
			} else {
				super.error("Error al eliminar producto");
			}
		} catch (Exception e) {
			super.error("Error al eliminar producto");
		}
	}

	public void buscar() {
		try {
			this.getProducto().setAudIdUsuario(super.getUsuarioActivo().getCodigo());
			this.lstProductos = this.getProductoService().listar(this.producto);

		} catch (ServicioException e) {
			e.printStackTrace();
		}
	}
	
	public void buscarPrecios() {
		try {

			this.lstProductoPrecios = this.getProductoService().listarPrecios(this.producto);

		} catch (ServicioException e) {
			e.printStackTrace();
		}
	}

	public void grabar() {
		
		
		if (this.validar()==false) {
			return;
		}
		
		try {
			super.setAuditoria(this.producto);

			if (this.producto.getCodigo() == 0) {
				if (this.getProductoService().insertar(this.producto)) {
					// System.out.println("Exito al insertar");
					super.aviso("Exito al insertar producto");
					this.actualizarHistoricoPrecios();
				} else {
					// System.out.println("Error al insertar");
					super.error("Error al insertar producto");
				}
			} else {
				if (this.getProductoService().actualizar(this.producto)) {
					// System.out.println("Exito al insertar");
					super.aviso("Exito al actualizar producto");
					this.actualizarHistoricoPrecios();
				} else {
					// System.out.println("Error al insertar");
					super.error("Error al actualizar producto");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Error al insertar");
			super.error("Error al insertar producto");
		}
	}

	public List<ProductoBean> getLstProductos() {
		return lstProductos;
	}

	public void setLstProductos(List<ProductoBean> lstProductos) {
		this.lstProductos = lstProductos;
	}

	public ProductoBean getProducto() {
		return producto;
	}

	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}

	
	private boolean validar() {

		if (this.getProducto().getNombre().trim().length() < 3) {
			super.alerta("El nombre es requerido y debe tener como mínimo 3 letras");
			return false;
		}

		if (this.getProducto().getPrecio()<=0) {
			super.alerta("El precio debe ser mayor que cero");
			return false;
		}
		if (this.getProducto().getStock()< 0) {
			super.alerta("El stock debe ser mayor o igual a cero");
			return false;
		}

		return true;
	}
	
	 public void exportExcel() {

	        try {

	            HttpServletResponse response = super.getResponse();

	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "attachment; filename=Listado_Productos.xls");

	            HSSFWorkbook workbook = new HSSFWorkbook();

	            if (lstProductos != null) {
	                workbook = this.exportExcelFormato(lstProductos);

	            }

	            OutputStream out = response.getOutputStream();
	            workbook.write(out);
	            out.close();
	            FacesContext.getCurrentInstance().responseComplete();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private HSSFWorkbook exportExcelFormato(List<ProductoBean> lstProducto) {
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet("Listado de Producto");

	        int countRow = 0;

	        // CABECERA
	        Row row = sheet.createRow(countRow);

	        Cell cell = row.createCell(0);
	        cell.setCellValue("Item");
	        super.setStyleLisCabecera(workbook, cell);

	        cell = row.createCell(1);
	        cell.setCellValue("Código");
	        super.setStyleLisCabecera(workbook, cell);

	        cell = row.createCell(2);
	        cell.setCellValue("Nombre");
	        super.setStyleLisCabecera(workbook, cell);

	        cell = row.createCell(3);
	        cell.setCellValue("Precio");
	        super.setStyleLisCabecera(workbook, cell);

	        cell = row.createCell(4);
	        cell.setCellValue("Stock");
	        super.setStyleLisCabecera(workbook, cell);

	        // LISTADO
	        int item = 0;

	        for (ProductoBean producto : this.lstProductos) {

	            countRow++;
	            item++;

	            row = sheet.createRow(countRow);

	            cell = row.createCell(0);
	            cell.setCellValue(item);

	            // CODIGO
	            cell = row.createCell(1);
	            cell.setCellValue(producto.getCodigo());

	            // NOMBRE
	            cell = row.createCell(2);
	            cell.setCellValue(producto.getNombre());

	            // PRECIO
	            cell = row.createCell(3);
	            cell.setCellValue(producto.getPrecio());

	            // PRECIO
	            cell = row.createCell(4);
	            cell.setCellValue(producto.getStock());

	        }

	        return workbook;
	    }

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	
	   public void exportPDF() {
	        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	        httpServletResponse.addHeader("Content-disposition", "attachment; filename=producto_listado_rpt.pdf");
	        ServletOutputStream servletStream;
	        try {
	            servletStream = httpServletResponse.getOutputStream();
	            JasperPrint jasperPrint = this.buildReport();
	            try {
	                JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);

	            } catch (JRException e) {
	                e.printStackTrace();
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        FacesContext.getCurrentInstance().responseComplete();
	    }

	    
	    @SuppressWarnings("unchecked")
		private JasperPrint buildReport() {
	        JasperPrint jasperPrint = null;
	        ServletContext sc=(ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
	        String path="WEB-INF\\classes\\pe\\edu\\galaxy\\training\\java\\web\\presentacion\\rpt\\";
	        String realPath = sc.getRealPath("/")+path;
	        String reporte = realPath + "producto_listado_rpt.jasper";
	        @SuppressWarnings("rawtypes")
			Map map = new HashMap();
	        String pathSO=sc.getRealPath("/");
	        String logo =pathSO+ "resources\\img\\galaxy-training-logo.png";
	        System.out.println("logo "+logo);
	        map.put("prm_logo_izquierda", logo);
	        
	        map.put("prm_usuario", super.getUsuarioActivo().getNombre());
	        
	        String filtro="";
	        
	        if(this.getProducto().getNombre().trim().length()>0) {
	        	filtro=this.getProducto().getNombre();
	        }
	        
			map.put("prm_filtro", "Nombre: "+filtro);
			map.put("prm_sistema", super.getStringJSF("sistema.nombre"));

	        try {
	            jasperPrint = JasperFillManager.fillReport(reporte, map, new JRBeanCollectionDataSource(this.lstProductos));
	        } catch (JRException e) {
	            e.printStackTrace();
	        }

	        return jasperPrint;
	    }


		public List<ProductoPrecioBean> getLstProductoPrecios() {
			return lstProductoPrecios;
		}


		public void setLstProductoPrecios(List<ProductoPrecioBean> lstProductoPrecios) {
			this.lstProductoPrecios = lstProductoPrecios;
		}
}
