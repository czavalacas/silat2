package utiles.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;

import java.sql.Connection;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;

import java.util.Locale;

import javax.annotation.Resource;

import javax.naming.InitialContext;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import siat.view.backing.utiles.Numero_letra;
import siat.view.backing.utiles.Utils;
import siat.view.backing.utiles.fecha.FechaUtiles;

public class ShowPdfServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    //private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
    private String filePath;
    //  @Resource(mappedName = "java:/app/jdbc/jdbc/ConLubalDS")
    @Resource(mappedName = "jdbc/ConLubalDS")
    private DataSource lubalDS;
    Connection conn = null;

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
       // response.setContentType("text/html;charset=client-handlable-encoding");
        request.setCharacterEncoding("UTF-8");//String decodedUrl = URLDecoder.decode(url, "UTF-8");
        try {
            Connection conn = lubalDS.getConnection();
            if(!conn.isClosed()){//ABIERTA
                HashMap parameters = new HashMap();
                String requestedFile = request.getParameter("jasper");
                String monto = request.getParameter("monto");
                String igv = request.getParameter("igv");
                String subTotal = request.getParameter("subTotal");
                String cliente = URLDecoder.decode(request.getParameter("cliente"),"UTF-8");
                String guias = request.getParameter("guias");
                String direccion = URLDecoder.decode(request.getParameter("direccion"),"UTF-8");
                String pedido = request.getParameter("pedido");
                String ruc = request.getParameter("ruc");
                String nidPreFact = request.getParameter("nidPreFact");
                String tipFacturaReporte = request.getParameter("tipFactRepo");
                String detalle = request.getParameter("detalle") != null ? URLDecoder.decode(request.getParameter("detalle"),"UTF-8") : "";
                String contenido = request.getParameter("contenido") != null ? URLDecoder.decode(request.getParameter("contenido"),"UTF-8") : "";
                String tipRepo = "";
                if(tipFacturaReporte.equals("2")){
                    tipRepo = "_tipo2";
                }else if(tipFacturaReporte.equals("3")){
                    tipRepo = "_tipo3";
                }else if(tipFacturaReporte.equals("4")){
                    tipRepo = "_tipo_1_2";
                }
                String timepath = request.getParameter("timepath");
                String fechaHoy = request.getParameter("fechaHoy");
                if(fechaHoy != null){
                if(!fechaHoy.equals("null")){
                         SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy",new Locale("en", "EN"));  
                         Date fecha = sdf.parse(fechaHoy);
                         parameters.put("FECHA",FechaUtiles.fechaLetras(fecha));
                     }else{
                         Date fecha = FechaUtiles.fechaActual();
                         parameters.put("FECHA",FechaUtiles.fechaLetras(fecha));
                     }
                }else{
                    Date fecha = FechaUtiles.fechaActual();
                    parameters.put("FECHA",FechaUtiles.fechaLetras(fecha));
                }
                String codFact = request.getParameter("codFact");
                String estado = request.getParameter("estado");
                String strFechaPago = request.getParameter("fecha_pago");
                 if(strFechaPago != null){
                     if(!strFechaPago.equals("null")){
                         SimpleDateFormat sdf= new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy",new Locale("en", "EN"));  
                         Date fecha_pago = sdf.parse(strFechaPago);
                         parameters.put("FECHA_PAGO", fecha_pago);
                     }else{
                         
                     }
                 }
                String tipo = "";
                if("previo".equals(estado)){
                    tipo = "PREVIO PREVIO";
                }else if("3".equals(estado)){
                    tipo = "ANULADA ANULADA";
                }
                System.out.println("showpdfservlet "+estado );
                String shortPath = "/WEB-INF/pdf-docs/";
                if (requestedFile == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                    return;
                }
                if(guias != null){
                    if(guias.lastIndexOf(",") + 1 == guias.length()){//el ultimo caracter es una coma
                        guias = guias.substring(0,guias.length() - 1);
                    }
                }
                ServletContext sc = getServletContext();
                String path = sc.getRealPath("/");
                JasperPrint jasperPrint;
                
                Numero_letra nl = new Numero_letra();
                monto = monto.replace(","," ");
                monto = monto.trim();
                String montoLetra = nl.Convertir(monto, true);
                Locale locale = new Locale("es", "ES");
                parameters.put(JRParameter.REPORT_LOCALE, locale);
                parameters.put("TOTAL", monto);
                parameters.put("CODFACT", codFact);
                parameters.put("TIPREP",tipRepo);
                parameters.put("DETALLE",detalle);                                     
                parameters.put("NIDPREFACT",nidPreFact);
                parameters.put("CIDREPOR", timepath);
                parameters.put("CONTENIDO", contenido);
                parameters.put("LETRAS", "SON: "+montoLetra);
                parameters.put("IGV", igv);
                parameters.put("PEDIDO", pedido);
                parameters.put("TIPO", tipo);
                parameters.put("SUBTOTAL", subTotal);
                parameters.put("CLIENTE", cliente);
                parameters.put("GUIAS",guias);
                parameters.put("DIRECCION", direccion);
                parameters.put("RUC", ruc);
                parameters.put("PATH", path + shortPath);
                
                String txtReporte = path+shortPath+requestedFile;
                JasperReport jasperReport = (JasperReport)JRLoader.loadObject(new File(txtReporte));

                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
                conn.close();
                String nombreArchivo = "";
                if(File.separator.equals("/")){
                    nombreArchivo = path+File.separator+"WEB-INF" + File.separator + "pdf-docs" + File.separator + timepath;   
                }else{
                    nombreArchivo = path+"WEB-INF" + File.separator + "pdf-docs" + File.separator + timepath;
                }
                ServletOutputStream outputStream = response.getOutputStream();
                JRExporter exporter = null;
                JRDocxExporter docxExporter = null;
                String tipoReporte = request.getParameter("formato_repo");
                String _contentType = "";
                if (tipoReporte.equalsIgnoreCase("EXCEL")) {
                    _contentType = "application/vnd.ms-excel";
                    exporter = new JRXlsExporter();
                } else if (tipoReporte.equalsIgnoreCase("PDF")) {
                    _contentType = "application/pdf";
                    exporter = new JRPdfExporter();
                    nombreArchivo += ".pdf";
                    JasperExportManager.exportReportToPdfFile(jasperPrint, nombreArchivo);
                }else if(tipoReporte.equalsIgnoreCase("WORD")){
                    //application/msword - este funca
                    //application/vnd.ms-word
                    //application/vnd.openxmlformats-officedocument.wordprocessingml.document
                    _contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                    docxExporter = new JRDocxExporter();
                    nombreArchivo += ".docx";
                }
                request.setAttribute("nombreArchivo", nombreArchivo);
                response.setContentType(_contentType);
                response.setHeader("content-disposition", "inline; filename=\""+codFact+".pdf\"");
                if(tipoReporte.equalsIgnoreCase("WORD")){
                    docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
                    docxExporter.exportReport();
                }else{
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
                    exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
                    exporter.exportReport();
                }
                outputStream.close();
              /*  ServletContext context = this.getServletContext();
                RequestDispatcher requestDispatcher = context.getRequestDispatcher("/mostrarPDF");
                requestDispatcher.forward(request, response);     */           
            }  
        } catch (Exception e) {
          //  e.printStackTrace(); //BOTA ERROR DE BROKEN PIPE, NI IDEA PORQUE
            try {
                if(conn != null){
                    if(!conn.isClosed()){
                        conn.close();
                    }
                }
            } catch (SQLException f) {
                Utils.depurar("NO PUDO CERRAR LA CONEXION REPORTE");
            }
            //e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
}