package pe.edu.galaxy.training.java.web.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import pe.edu.galaxy.training.java.web.bean.EmpleadoBean;

public class empleado_listado_rpt_test {
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {
		
		List<EmpleadoBean> lstEmpleados = new ArrayList<EmpleadoBean>();
		
		try {
			
			EmpleadoBean empleado1 = new EmpleadoBean();
			empleado1.setCodigo(1);
			empleado1.setApellidoPaterno("NOVOA");
			empleado1.setApellidoPaterno("ARBILDO");
			empleado1.setNombre("Aristedes");
			empleado1.setNroDocumento("10611698");
			empleado1.setTelefono("950500641");;
			
			lstEmpleados.add(empleado1);
			
			EmpleadoBean empleado2 = new EmpleadoBean();
			empleado2.setCodigo(2);
			empleado2.setApellidoPaterno("TORRES");
			empleado2.setApellidoPaterno("FATIMA");
			empleado2.setNombre("Katherine");
			empleado2.setNroDocumento("10611678");
			empleado2.setTelefono("980500641");;
			
			lstEmpleados.add(empleado2);
			
			
		} catch (Exception e) {
			System.out.println("Error List" + e.getMessage());
		}
		
        String url="src/pe/edu/galaxy/training/java/web/presentacion/rpt/";
        try {
            JasperPrint jasperPrint;
            JasperDesign desing= null;
            try {
            	String reporte=url+"empleado_listado_rpt.jrxml";
				desing = JRXmlLoader.load(new FileInputStream(reporte));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		String reporte;
			JasperCompileManager.compileReportToFile(desing,url+"empleado_listado_rpt.jasper");
			reporte=url+"empleado_listado_rpt.jasper";
			
			@SuppressWarnings("rawtypes")
			
			Map map=new HashMap();
			map.put("prm_empresa", "Galaxy Training");
			map.put("prm_usuario", "Lucas Cueva");
			map.put("prm_filtro", "Situacion: Bloqueados");
			map.put("prm_logo_izquierda","WebContent/resources/img/galaxy-training-logo.png");		
			map.put("prm_sistema", "© Copyright 2020 - Sistema de Pedidos (SIPE) v1.0");
			map.put("prm_modulo", "General");
						
           jasperPrint = JasperFillManager.fillReport(reporte, map, new JRBeanCollectionDataSource(lstEmpleados));
           JasperViewer jv=new JasperViewer(jasperPrint,false);
           jv.show();
           
            System.out.println("Visualizando el reporte en Desktop");
            System.out.println("Agregado exitosamente");
            
        } catch (JRException e) {
            System.out.println("Error"+e.getMessage());
            //e.printStackTrace();
        }

		
		
	}

}
