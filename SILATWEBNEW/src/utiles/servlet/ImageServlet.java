package utiles.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.sql.Blob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import javax.annotation.Resource;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

/**
 * @author dfloresgonz
 * @since 29.08.2013
 */
public class ImageServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "image/jpg; charset=utf-8";
    @SuppressWarnings("compatibility:3427077105946479104")
    private static final long serialVersionUID = -4437010923797142193L;
    //@Resource(mappedName = "java:/app/jdbc/jdbc/ConLubalDS")
    @Resource(mappedName = "jdbc/ConLubalDS")
    private DataSource lubalDS;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                       IOException{
        response.setContentType(CONTENT_TYPE);
        String cidguia = request.getParameter("cidguia");
        String cidunin = request.getParameter("cidunin");
        OutputStream os = response.getOutputStream();
        Connection conn = null; 
        try {
            conn = lubalDS.getConnection();
            String sql = "SELECT g.IMG_GR " + 
                         "FROM TRMGUIA g " + 
                         "WHERE g.CID_GUIA = '"+cidguia+"' " + 
                         "AND g.CIDUNIN = '"+cidunin+"' ";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("IMG_GR");//El artibuto en la tabla que deseo traer
                if(blob != null){
                    BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
                    int b;
                    byte[] buffer = new byte[10240];
                    while ((b = in.read(buffer, 0, 10240)) != -1) {
                        os.write(buffer, 0, b);
                    }
                    os.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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