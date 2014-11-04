package silat.servicios_negocio.BDLSF.SFBean;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.sql.DataSource;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEstaClienteLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFEstaClienteRemoto;
import silat.servicios_negocio.Beans.BeanEstCliente;
import silat.servicios_negocio.util_formato.Fecha.FechaUtiles;

@Stateless(name = "BDL_C_SFEstaCliente", mappedName = "map-BDL_C_SFEstaCliente")
public class BDL_C_SFEstaClienteBean implements BDL_C_SFEstaClienteRemoto, 
                                                BDL_C_SFEstaClienteLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    
    @Resource(mappedName = "jdbc/ConLubalDS")
        private DataSource lubalDS;
        Connection conn = null;

    public BDL_C_SFEstaClienteBean() {
    }
    
    public List<BeanEstCliente> call_Procedure_GET_GANANCIAS_CLIENTE(Date fecMin, Date fecMax, int limit){
        try {
            conn = lubalDS.getConnection();
            String query = "CALL GET_GANANCIAS_CLIENTE(?,?,?);";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt.setString(2,FechaUtiles.getFechaStr(fecMax));          
            stmt.setInt(3, limit);
            ResultSet  rs =   stmt.executeQuery();  
            List<BeanEstCliente> lstBeanEstCliente = new ArrayList<BeanEstCliente>();
            while (rs.next()) {
                BeanEstCliente beanEstCliente = new BeanEstCliente();
                beanEstCliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                beanEstCliente.setConteo(rs.getDouble("GANANCIA"));
                lstBeanEstCliente.add(beanEstCliente);
            }
            conn.close();
            return lstBeanEstCliente;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
                } 
            catch (SQLException f) {
                f.printStackTrace();
            }
            return null;
        }
    }

    public List<BeanEstCliente> call_Procedure_GET_GANANCIAS_CLIENTE_X_MES(Date fecMin, Date fecMax){
        try {
            conn = lubalDS.getConnection();
            String query = "CALL GET_GANANCIAS_CLIENTE_X_MES(?,?);";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt.setString(2,FechaUtiles.getFechaStr(fecMax));          
            ResultSet  rs =   stmt.executeQuery();  
            List<BeanEstCliente> lstBeanEstCliente = new ArrayList<BeanEstCliente>();
            while (rs.next()) {
                BeanEstCliente beanEstCliente = new BeanEstCliente();
                beanEstCliente.setYear(rs.getString("ANIO"));
                beanEstCliente.setMes(rs.getString("MES"));
                beanEstCliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                beanEstCliente.setConteo(rs.getDouble("GANANCIA"));
                lstBeanEstCliente.add(beanEstCliente);
            }
            conn.close();
            return lstBeanEstCliente;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
                } 
            catch (SQLException f) {
                f.printStackTrace();
            }
            return null;
        }
    }
    
    public List<BeanEstCliente> call_Procedure_GET_ORDENES_SERVICIO(Date fecMin, Date fecMax, int limit, String tipo){
        try {
            conn = lubalDS.getConnection();
            String query = "CALL GET_ORDENES_SERVICIO(?,?,?,?);";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt.setString(2,FechaUtiles.getFechaStr(fecMax));
            stmt.setString(3,tipo);
            stmt.setInt(4,limit);
            ResultSet  rs =   stmt.executeQuery();  
            List<BeanEstCliente> lstBeanEstCliente = new ArrayList<BeanEstCliente>();
            while (rs.next()) {
                BeanEstCliente beanEstCliente = new BeanEstCliente();
                beanEstCliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                beanEstCliente.setConteo(rs.getDouble("conteo"));
                lstBeanEstCliente.add(beanEstCliente);
            }
            conn.close();
            return lstBeanEstCliente;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
                } 
            catch (SQLException f) {
                f.printStackTrace();
            }
            return null;
        }
    }
    
    public List<BeanEstCliente> call_Procedure_GET_ORDENES_SERVICIO_X_FECHAS(Date fecMin, Date fecMax, String tipo){
        try {
            conn = lubalDS.getConnection();
            String query = "CALL GET_ORDENES_SERVICIO_X_FECHAS(?,?,?);";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt.setString(2,FechaUtiles.getFechaStr(fecMax));
            stmt.setString(3,tipo);
            ResultSet  rs =   stmt.executeQuery();  
            List<BeanEstCliente> lstBeanEstCliente = new ArrayList<BeanEstCliente>();
            while (rs.next()) {
                BeanEstCliente beanEstCliente = new BeanEstCliente();
                beanEstCliente.setYear(rs.getString("ANIO"));
                beanEstCliente.setMes(rs.getString("MES"));
                beanEstCliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                beanEstCliente.setConteo(rs.getDouble("conteo"));
                lstBeanEstCliente.add(beanEstCliente);
            }
            conn.close();
            return lstBeanEstCliente;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
                } 
            catch (SQLException f) {
                f.printStackTrace();
            }
            return null;
        }
    }
    
}
