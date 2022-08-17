package mff.administracion.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class FuncionesGenerales {
	
	public byte[] generarReportePDF(String nomJasper, Map<String, Object> parametros, JRDataSource dataReporte) {
		byte[] bytes = null;
		JasperReport jasperReport = null;
		try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
			jasperReport = (JasperReport) JRLoader.loadObject(loadJasperFile(nomJasper));
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataReporte);
			bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	public URL loadJasperFile(String nomJasper) {
		try {
			URL uri = new URL(Constantes.rutaArchivos + "reportes/" + nomJasper + ".jasper");
			return uri;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
