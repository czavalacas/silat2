package utiles.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import javax.naming.InitialContext;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import siat.view.backing.utiles.Utils;
import siat.view.backing.utiles.reporte.BeanReporte;

public class UtilGeneradorReporte extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                                IOException {
        response.setContentType(CONTENT_TYPE);
        List lstMapaParametros = (List)request.getAttribute("lstMapaParametros");
        String tipoReporte = (String)request.getAttribute("tipoReporte");
        String strReporte = (String) request.getAttribute("strReporte");
        String timepath = (String) request.getAttribute("timepath");
        try {
            if (!"contype".equals(request.getHeader("User-Agent"))) {
                HashMap parameters = new HashMap();
                Iterator it = lstMapaParametros.iterator();
                while (it.hasNext()) {
                    BeanReporte param = (BeanReporte)it.next();
                    String nombreParam = param.getNombreParametro();
                    String valorParam = param.getValorParametro();
                    if(valorParam == null){
                       Object o = param.getValorPrimitivo();
                       if(o instanceof Integer){
                           int i = Integer.parseInt(o.toString());
                           parameters.put(nombreParam,i);
                       }
                    }else{
                        parameters.put(nombreParam, valorParam);
                    }
                }
                ServletContext sc = getServletContext();
                String root = sc.getRealPath("/");

                JasperPrint jasperPrint;
                String txtReporte = root +"/WEB-INF/pdf-docs/"+strReporte;
                parameters.put("PATH", root +"/WEB-INF/pdf-docs/");
                InitialContext c = new InitialContext();
                DataSource jdbcFactory = (DataSource)c.lookup("java:/app/jdbc/jdbc/lubalDS");
                Connection conn = jdbcFactory.getConnection();
                JasperReport jasperReport = (JasperReport)JRLoader.loadObject(new File(txtReporte));
                
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
                conn.close();
                String claveRandom = Utils.generarContrasena();
                String nombreArchivo = root +"/WEB-INF/pdf-docs/"+timepath+claveRandom;

                ServletOutputStream outputStream = response.getOutputStream();
                JRExporter exporter = null;

                tipoReporte = tipoReporte.trim();
                String contentType = "";

                if (tipoReporte.equalsIgnoreCase("EXCEL")) {
                    contentType = "application/vnd.ms-excel";
                    exporter = new JRXlsExporter();
                } else if (tipoReporte.equalsIgnoreCase("PDF")) {
                    contentType = "application/pdf";
                    exporter = new JRPdfExporter();
                    nombreArchivo += ".pdf";
                    JasperExportManager.exportReportToPdfFile(jasperPrint,nombreArchivo);
                }
                System.out.println("nombreArchivo: "+nombreArchivo);
                response.setContentType(contentType);
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
                exporter.exportReport();
                outputStream.close();
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }
    
    public static Object getSession(String k) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(k);
    }
    
    public String getParam(String req,HttpServletRequest request){
            String variable = request.getParameter(req) == null ? "" : request.getParameter(req);
            return variable;
    }
}