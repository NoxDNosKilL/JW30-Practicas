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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.edu.galaxy.training.java.web.bean.ProveedorBean;
import pe.edu.galaxy.training.java.web.service.exception.ServicioException;
import pe.edu.galaxy.training.java.web.service.inf.ProveedorService;
import pe.edu.galaxy.training.java.web.util.Validador;
import static pe.edu.galaxy.training.java.web.util.Validador.requerido;

@Controller(value = "proveedorMB")
@Scope(value = "session")
public class ProveedorMB extends GenericoMB {

	private static final long serialVersionUID = -7693130310577340155L;
	private List<ProveedorBean> lstProveedorBean;
	private ProveedorBean proveedorBean;
	private ProveedorBean selectProveedorBean;

	public ProveedorBean getSelectProveedorBean() {
		return selectProveedorBean;
	}

	public void setSelectProveedorBean(ProveedorBean selectProveedorBean) {
		this.selectProveedorBean = selectProveedorBean;
	}

	@Autowired
	private ProveedorService proveedorService;

	@PostConstruct
	public void init() {
		this.setProveedorBean(new ProveedorBean());
		this.listar();
	}

	public String nuevo() {
		return "proveedor_registro";
	}

	public String cancelar() {
		this.init();
		return "proveedor_listado";
	}

	public String modificar(ProveedorBean proveedorBean) {
		this.setProveedorBean(proveedorBean);
		return "proveedor_registro";
	}

	public void selectEliminar(ProveedorBean proveedorBean) {
		this.selectProveedorBean = proveedorBean;
	}

	public void eliminar() {
		try {
			super.setAuditoria(this.selectProveedorBean);

			if (this.getProveedorService().eliminar(this.selectProveedorBean)) {
				super.aviso("Exito al eliminar proveedor");
				this.listar();
			} else {
				super.error("Error al eliminar proveedor");
			}

		} catch (Exception e) {
			e.printStackTrace();
			super.error("Error al eliminar proveedor");
		}

	}

	public void limpiar() {
		this.setProveedorBean(new ProveedorBean());
		this.setLstProveedorBean(new ArrayList<>());
	}

	public void listar() {
		try {
			this.lstProveedorBean = this.getProveedorService().listar(this.proveedorBean);
		} catch (ServicioException e) {
			e.printStackTrace();
		}
	}

	public void grabar() {

		if (!this.validar()) {
			return;
		}

		try {
			super.setAuditoria(this.proveedorBean);

			if (this.proveedorBean.getCodigo() == 0) {
				if (this.getProveedorService().insertar(this.proveedorBean)) {
					super.aviso("Exito al insertar proveedor");
				} else {
					super.error("Error al insertar proveedor");
				}
			} else {
				if (this.getProveedorService().actualizar(this.proveedorBean)) {
					super.aviso("Exito al actualizar proveedor");
				} else {
					super.error("Error al actualizar proveedor");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			super.error("Error al insertar proveedor");
		}
	}

	private boolean validar() {

		String ret = Validador.ruc(this.getProveedorBean().getRuc());
		if (ret != null) {
			super.aviso(ret);
			return false;
		}

		if (this.getProveedorBean().getRazonSocial() == null
				|| this.getProveedorBean().getRazonSocial().trim().length() < 5) {
			super.aviso("La Razón Social es requerida y debe tener como mínimo 5 caracateres");
			return false;
		}

		if (requerido(this.getProveedorBean().getDireccion())) {
			super.aviso("La dirección es requerida");
			return false;
		}
		if (this.getProveedorBean().getCorreo() == null || this.getProveedorBean().getCorreo().trim().length() ==0) {
			super.aviso("El correo es requerido");
			return false;
		}
		if (this.getProveedorBean().getTelefono() == null || this.getProveedorBean().getTelefono().trim().length() ==0) {
			super.aviso("El teléfono es requerido");
			return false;
		}

		return true;
	}

	public List<ProveedorBean> getLstProveedorBean() {
		return lstProveedorBean;
	}

	public void setLstProveedorBean(List<ProveedorBean> lstProveedorBean) {
		this.lstProveedorBean = lstProveedorBean;
	}

	public ProveedorService getProveedorService() {
		return proveedorService;
	}

	public void setProveedorService(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public ProveedorBean getProveedorBean() {
		return proveedorBean;
	}

	public void setProveedorBean(ProveedorBean proveedorBean) {
		this.proveedorBean = proveedorBean;
	}

	public void exportExcel() {

		try {

			if (this.lstProveedorBean == null || this.lstProveedorBean.size() == 0) {
				super.alerta("No existen datos a exportar");
				return;
			}

			HttpServletResponse response = super.getResponse();

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=Listado_Proveedores.xls");

			HSSFWorkbook workbook = new HSSFWorkbook();

			HSSFSheet sheet = workbook.createSheet("Listado de Proveedor");

			// CABECERA
			int countRow = 0;
			Row row = sheet.createRow(countRow);
			Cell cell;
			cell = row.createCell(0);
			cell.setCellValue("Código");
			super.setStyleLisCabecera(workbook, cell);

			cell = row.createCell(1);
			cell.setCellValue("Razón Social");
			super.setStyleLisCabecera(workbook, cell);

			cell = row.createCell(2);
			cell.setCellValue("RUC");
			super.setStyleLisCabecera(workbook, cell);

			cell = row.createCell(3);
			cell.setCellValue("Dirección");
			super.setStyleLisCabecera(workbook, cell);

			cell = row.createCell(4);
			cell.setCellValue("Teléfono");
			super.setStyleLisCabecera(workbook, cell);

			// LISTADO

			for (ProveedorBean proveedor : this.lstProveedorBean) {

				countRow++;

				row = sheet.createRow(countRow);

				// CODIGO
				cell = row.createCell(0);
				cell.setCellValue(proveedor.getCodigo());

				// RAZON ZOCIAL
				cell = row.createCell(1);
				cell.setCellValue(proveedor.getRazonSocial());

				// RUC
				cell = row.createCell(2);
				cell.setCellValue(proveedor.getRuc());

				// DIRECCION
				cell = row.createCell(3);
				cell.setCellValue(proveedor.getDireccion());

				// TELEFONO
				cell = row.createCell(4);
				cell.setCellValue(proveedor.getTelefono());

			}

			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.close();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportPDF() {
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition", "attachment; filename=proveedores_listado_rpt.pdf");
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
		ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String path = "WEB-INF\\classes\\pe\\edu\\galaxy\\training\\java\\web\\presentacion\\rpt\\";
		String realPath = sc.getRealPath("/") + path;
		String reporte = realPath + "proveedores_listado_rpt.jasper";
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		String pathSO = sc.getRealPath("/");
		String logo = pathSO + "resources\\img\\galaxy-training-logo.png";
		System.out.println("logo " + logo);
		map.put("prm_logo_izquierda", logo);

		map.put("prm_usuario", super.getUsuarioActivo().getNombre());

		String filtro = "";

		if (this.getProveedorBean().getRazonSocial().trim().length() > 0) {
			filtro = this.getProveedorBean().getRazonSocial();
		}

		map.put("prm_filtro", "Nombre: " + filtro);
		map.put("prm_sistema", super.getStringJSF("sistema.nombre"));

		try {
			jasperPrint = JasperFillManager.fillReport(reporte, map,
					new JRBeanCollectionDataSource(this.lstProveedorBean));
		} catch (JRException e) {
			e.printStackTrace();
		}

		return jasperPrint;
	}

}
