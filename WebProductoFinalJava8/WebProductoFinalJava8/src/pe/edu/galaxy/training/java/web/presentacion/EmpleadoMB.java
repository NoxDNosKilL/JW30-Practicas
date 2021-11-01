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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.edu.galaxy.training.java.web.bean.EmpleadoBean;
import pe.edu.galaxy.training.java.web.service.exception.ServicioException;
import pe.edu.galaxy.training.java.web.service.inf.EmpleadoService;
import pe.edu.galaxy.training.java.web.util.Validador;

@Controller("empleadoMB")
@Scope(value="session")
public class EmpleadoMB extends GenericoMB{

	private static final long serialVersionUID = -6468428214885243424L;

	private List<EmpleadoBean> lstEmpleados;
	
	@Autowired 
	private EmpleadoService empleadoService;
	
	private EmpleadoBean empleadoBean;
	
	private EmpleadoBean selectedEmpleadoBean;

	@PostConstruct
	public void init() {
		this.setEmpleadoBean(new EmpleadoBean());
		this.setLstEmpleados(new ArrayList<EmpleadoBean>());
		this.buscar();

	}
	
	public void limpiar() {
		this.setEmpleadoBean(new EmpleadoBean());
		this.buscar();
	}
	
	public String nuevo() {
		this.setEmpleadoBean(new EmpleadoBean());
		return "empleado_registro";
	}
	
	public String cancelar() {
		this.setEmpleadoBean(new EmpleadoBean());
		return "empleado_listado";
	}
	
	public void selectEmpleado(EmpleadoBean empleadoBean) {
		this.setSelectedEmpleadoBean(empleadoBean);
	}
	
	
	public String modificar(EmpleadoBean empleadoBean) {
		//this.setEmpleadoBean(empleadoBean);
		
		try {
			EmpleadoBean oEmpleadoBean = this.getEmpleadoService().buscarXCodigo(empleadoBean);
			this.setEmpleadoBean(oEmpleadoBean);
			return "empleado_registro";
		} catch (ServicioException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public void eliminar() {
		try {
			super.setAuditoria(this.selectedEmpleadoBean);
			if (this.getEmpleadoService().eliminar(this.selectedEmpleadoBean)) {
				super.aviso("Exito al eliminar empleado");
				this.buscar();
			} else {
				super.error("Error al eliminar empleado");
			}
		} catch (Exception e) {
			super.error("Error al eliminar empleado");
		}
	}
	
	public void grabar() {
		
		
		if (!this.validar()) {
			return;
		}
		
		try {
			super.setAuditoria(this.empleadoBean);

			if (this.empleadoBean.getCodigo() == 0) {
				if (this.getEmpleadoService().insertar(this.empleadoBean)) {
					super.aviso("Exito al insertar empleado");

				} else {
					super.error("Error al insertar empleado");
				}
			} else {
				if (this.getEmpleadoService().actualizar(this.empleadoBean)) {
					super.aviso("Exito al actualizar empleado");
				} else {
					super.error("Error al actualizar empleado");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			super.error("Error al insertar empleado");
		}
	}


	private boolean validar() {

	if (this.getEmpleadoBean().getApellidoPaterno().trim().length() < 3) {
		super.alerta("El Apellido Paterno es requerido y debe tener como mínimo 3 letras");
		return false;
	}
	if (this.getEmpleadoBean().getNombre().trim().length() < 3) {
		super.alerta("El Nombre es requerido y debe tener como mínimo 3 letras");
		return false;
	}
	
	if (this.getEmpleadoBean().getDireccion().trim().length() < 3) {
		super.alerta("La Dirección es requerida y debe tener como mínimo 5 caracteres");
		return false;
	}
	String nroDoc=this.getEmpleadoBean().getNroDocumento();
	//nroDoc=(nroDoc==null)?"":nroDoc;
	String ret= Validador.dni(nroDoc);
	
	if (ret!=null) {
		super.alerta(ret);
		return false;
	}
	
	ret=Validador.correo(this.getEmpleadoBean().getCorreo());
	if (ret!=null) {
		super.alerta(ret);
		return false;
	}
	
	if (this.getEmpleadoBean().getTelefono().trim().length() < 7) {
		super.alerta("El Teléfono es requerido y debe tener como mínimo 7 dígitos");
		return false;
	}

	return true;
}
	public void buscar() {
		try {
			this.lstEmpleados = this.getEmpleadoService().listar(this.getEmpleadoBean());

		} catch (ServicioException e) {
			e.printStackTrace();
		}
	}
	
	 public void exportExcel() {

	        try {

	            HttpServletResponse response = super.getResponse();

	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "attachment; filename=Listado_empleado.xls");

	            HSSFWorkbook workbook = new HSSFWorkbook();

		        HSSFSheet sheet = workbook.createSheet("Listado de Empleado");

		        int countRow = 0;

		        // CABECERA
		        Row row = sheet.createRow(countRow);

		        Cell cell = row.createCell(0);
		        cell.setCellValue("Código");
		        super.setStyleLisCabecera(workbook, cell);

		        cell = row.createCell(1);
		        cell.setCellValue("Apellido Paterno");
		        super.setStyleLisCabecera(workbook, cell);

		        cell = row.createCell(2);
		        cell.setCellValue("Apellido Materno");
		        super.setStyleLisCabecera(workbook, cell);

		        cell = row.createCell(3);
		        cell.setCellValue("Nombre");
		        super.setStyleLisCabecera(workbook, cell);

		        cell = row.createCell(4);
		        cell.setCellValue("Nro Documento");
		        super.setStyleLisCabecera(workbook, cell);

		        // LISTADO

		        for (EmpleadoBean empleadoBean : this.lstEmpleados) {

		            countRow++;

		            row = sheet.createRow(countRow);

		            cell = row.createCell(0);
		            cell.setCellValue(empleadoBean.getCodigo());
		            
		            cell = row.createCell(1);
		            cell.setCellValue(empleadoBean.getApellidoPaterno());

		            cell = row.createCell(2);
		            cell.setCellValue(empleadoBean.getApellidoPaterno());

		            cell = row.createCell(3);
		            cell.setCellValue(empleadoBean.getNombre());

		            cell = row.createCell(4);
		            cell.setCellValue(empleadoBean.getNroDocumento());

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
	        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	        httpServletResponse.addHeader("Content-disposition", "attachment; filename=empleado_listado_rpt.pdf");
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
	        String realPath = sc.getRealPath("/");
	        System.out.println("realPath " +realPath);
	        
	        realPath+=path;
	        
	        String reporte = realPath + "empleado_listado_rpt.jasper";
	        System.out.println("reporte " +reporte);
	        @SuppressWarnings("rawtypes")
			Map map = new HashMap();
	        String pathSO=sc.getRealPath("/");
	        
	        
	        String logo =pathSO+ "resources\\img\\galaxy-training-logo.png";
	        System.out.println("logo "+logo);
	        map.put("prm_logo_izquierda", logo);
	        
	        map.put("prm_usuario", super.getUsuarioActivo().getNombre());
	        
	        String filtro="";
	        
	        String ap=this.getEmpleadoBean().getApellidoPaterno().trim();
	        if(ap.length()>0) {
	        	filtro="Apellido Paterno = "+ap +", ";
	        }
	        String am=this.getEmpleadoBean().getApellidoMaterno().trim();
	        if(am.length()>0) {
	        	filtro=filtro +"Apellido Materno = "+am+", ";
	        }
	        String n=this.getEmpleadoBean().getNombre().trim();
	        if(n.length()>0) {
	        	filtro=filtro +"Nombre = "+n+", ";
	        }
	    	if (filtro.length() > 1) {
				filtro = filtro.substring(0, filtro.length() - 2);
			}
	        
			map.put("prm_filtro", filtro);
			map.put("prm_sistema", super.getStringJSF("sistema.nombre"));
			map.put("prm_modulo", super.getStringJSF("modulo.maestro"));
			
	        try {
	            jasperPrint = JasperFillManager.fillReport(reporte, map, new JRBeanCollectionDataSource(this.lstEmpleados));
	        } catch (JRException e) {
	            e.printStackTrace();
	        }

	        return jasperPrint;
	    }


	public EmpleadoBean getEmpleadoBean() {
		
		return empleadoBean;
	}

	public void setEmpleadoBean(EmpleadoBean empleadoBean) {
		this.empleadoBean = empleadoBean;
	}

	public List<EmpleadoBean> getLstEmpleados() {
		this.buscar();
		return lstEmpleados;
	}

	public void setLstEmpleados(List<EmpleadoBean> lstEmpleados) {
		
		this.lstEmpleados = lstEmpleados;
	}

	public EmpleadoService getEmpleadoService() {
		return empleadoService;
	}

	public void setEmpleadoService(EmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}

	public EmpleadoBean getSelectedEmpleadoBean() {
		return selectedEmpleadoBean;
	}

	public void setSelectedEmpleadoBean(EmpleadoBean selectedEmpleadoBean) {
		this.selectedEmpleadoBean = selectedEmpleadoBean;
	}
		
}
