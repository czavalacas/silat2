package utiles.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.io.PrintWriter;

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

public class GastoServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "image/jpg; charset=utf-8";
    @SuppressWarnings("compatibility:3336092639722604058")
    private static final long serialVersionUID = 2463537405787077485L;
    //@Resource(mappedName = "java:/app/jdbc/jdbc/ConLubalDS")
    @Resource(mappedName = "jdbc/ConLubalDS")
    private DataSource lubalDS;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        String nidGasto = request.getParameter("nidGasto");
        OutputStream os = response.getOutputStream();
        Connection conn = null;
        try {
            conn = lubalDS.getConnection();
            String sql = "SELECT g.BL_IMGRECIBO " + 
                         "FROM ADDGAST g " + 
                         "WHERE g.NID_GASTO = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, new Integer(nidGasto));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("BL_IMGRECIBO"); //El artibuto en la tabla que deseo traer
                if (blob != null) {
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
