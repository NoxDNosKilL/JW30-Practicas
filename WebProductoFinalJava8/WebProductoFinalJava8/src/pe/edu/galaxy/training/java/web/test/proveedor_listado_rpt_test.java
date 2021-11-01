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
import pe.edu.galaxy.training.java.web.bean.ProveedorBean;

public class proveedor_listado_rpt_test {
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {
		
		List<ProveedorBean> lstProveedors = new ArrayList<ProveedorBean>();
		
		try {
			
			ProveedorBean proveedor1 = new ProveedorBean();
			proveedor1.setCodigo(1);
			proveedor1.setRazonSocial("Galaxy Training");
			proveedor1.setRuc("20544987306");
			proveedor1.setTelefono("567-2056");
			
			lstProveedors.add(proveedor1);
			
			ProveedorBean proveedor2 = new ProveedorBean();
			proveedor2.setCodigo(2);
			proveedor2.setRazonSocial("Galaxy Net");
			proveedor2.setRuc("20544987307");
			proveedor2.setTelefono("567-2058");
			
			lstProveedors.add(proveedor2);
			
			
		} catch (Exception e) {
			System.out.println("Error List" + e.getMessage());
		}
		
        String url="src/pe/edu/galaxy/training/java/web/presentacion/rpt/";
        try {
            JasperPrint jasperPrint;
            JasperDesign desing= null;
            try {
            	String reporte=url+"proveedores_listado_rpt.jrxml";
				desing = JRXmlLoader.load(new FileInputStream(reporte));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
            
			JasperCompileManager.compileReportToFile(desing,url+"proveedores_listado_rpt.jasper");
			
			String reporte=url+"proveedores_listado_rpt.jasper";
			
			@SuppressWarnings("rawtypes")
			Map map=new HashMap();
		
			map.put("prm_empresa", "Galaxy Training");
			map.put("prm_usuario", "Lucas Cueva");
			map.put("prm_filtro", "Situacion: Bloqueados");
			map.put("prm_logo_izquierda","WebContent/resources/img/galaxy-training-logo.png");		
			map.put("prm_sistema", "© Copyright 2016 - Sistema de Pedidos (SIPE) v1.0");
						
           jasperPrint = JasperFillManager.fillReport(reporte, map, new JRBeanCollectionDataSource(lstProveedors));
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
