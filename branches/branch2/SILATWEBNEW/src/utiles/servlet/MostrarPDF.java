package utiles.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.URLDecoder;

import javax.faces.context.FacesContext;

import javax.servlet.*;
import javax.servlet.http.*;

public class MostrarPDF extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    //private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
    private String filePath;
    
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
        String requestedFile = (String)request.getAttribute("nombreArchivo");
        ServletContext sc = getServletContext();
        String path = sc.getRealPath("/");
        try {
            if (!"contype".equals(request.getHeader("User-Agent"))) {
                this.filePath = path + File.separator+"WEB-INF"+File.separator+"pdf-docs";
                File file = new File(filePath, URLDecoder.decode(requestedFile, "UTF-8"));
                if (!file.exists()) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                    return;
                }
                String contentType = getServletContext().getMimeType(file.getName());
                if (contentType == null) {
                    contentType = "application/pdf";//octet-stream
                }
                // Init servlet response.
                response.reset();
                response.setBufferSize(DEFAULT_BUFFER_SIZE);
                response.setContentType(contentType);
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Disposition", "filename=\"" + file.getName() + "\"");
                // Prepare streams.
                BufferedInputStream input = null;
                BufferedOutputStream output = null;
                try {
                    // Open streams.
                    input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
                    output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
                    // Write file contents to response.
                    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
                    int length;
                    while ((length = input.read(buffer)) > 0) {
                        output.write(buffer, 0, length);
                    }
                } finally {
                    // Gently close streams.
                    close(output);
                    close(input);
                }
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
    
    public String getParam(String req, HttpServletRequest request) {
        String variable = request.getParameter(req) == null ? "" : request.getParameter(req);
        return variable;
    }
    
    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
