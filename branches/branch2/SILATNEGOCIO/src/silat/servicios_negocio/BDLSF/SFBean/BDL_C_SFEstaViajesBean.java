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

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEstaViajesLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFEstaViajesRemote;
import silat.servicios_negocio.Beans.BeanEstCliente;
import silat.servicios_negocio.util_formato.Fecha.FechaUtiles;

@Stateless(name = "BDL_C_SFEstaViajes", mappedName = "map-BDL_C_SFEstaViajes")
public class BDL_C_SFEstaViajesBean implements BDL_C_SFEstaViajesRemote, BDL_C_SFEstaViajesLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    
    @Resource(mappedName = "jdbc/ConLubalDS")
        private DataSource lubalDS;
        Connection conn = null;

    public BDL_C_SFEstaViajesBean() {
    }
    
    public List<BeanEstCliente> call_Procedure_GET_VIAJES_EXITOSOS(Date fecMin, Date fecMax, int limitInf, int limitSup){
        try {
            conn = lubalDS.getConnection();
            String query = "CALL GET_VIAJES_EXITOSOS(?,?,?,?);";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt.setString(2,FechaUtiles.getFechaStr(fecMax));          
            System.out.println(FechaUtiles.getFechaStr(fecMin)+" al "+FechaUtiles.getFechaStr(fecMax));
            stmt.setInt(3, limitInf);
            stmt.setInt(4, limitSup);
            ResultSet  rs =   stmt.executeQuery();  
            List<BeanEstCliente> lstBeanEstCliente = new ArrayList<BeanEstCliente>();
            while (rs.next()) {
                BeanEstCliente beanEstCliente = new BeanEstCliente();
                beanEstCliente.setYear(rs.getString("ANIO"));
                beanEstCliente.setMes(rs.getString("MES"));
                if(limitInf == 1){
                    beanEstCliente.setRazonSocial("No Exitoso");
                }
                else{
                    beanEstCliente.setRazonSocial("Exitoso");
                }
                if(rs.getInt("TRANSPORTISTA") == 1){
                    beanEstCliente.setTipo("Proveedor");
                }
                else{
                    beanEstCliente.setTipo("Propio");
                }
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
    
    public List<BeanEstCliente> call_Procedure_GET_VIAJES_MES(Date fecMin, Date fecMax){
        try {
            conn = lubalDS.getConnection();
            String query = "CALL GET_VIAJES_MES(?,?);";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt.setString(2,FechaUtiles.getFechaStr(fecMax));          
            ResultSet  rs =   stmt.executeQuery();  
            List<BeanEstCliente> lstBeanEstCliente = new ArrayList<BeanEstCliente>();
            while (rs.next()) {
                BeanEstCliente beanEstCliente = new BeanEstCliente();
                beanEstCliente.setYear(rs.getString("ANIO"));
                beanEstCliente.setMes(rs.getString("MES"));
                beanEstCliente.setConteo(rs.getDouble("conteo"));
                beanEstCliente.setRazonSocial("Viajes");
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
    
    public List<BeanEstCliente> call_Procedure_GET_VIAJES_MES_PROV_PROP(Date fecMin, Date fecMax,int tipo){
        try {
            conn = lubalDS.getConnection();
            String query = "CALL GET_VIAJES_MES_PROV_PROP(?,?,?);";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt.setString(2,FechaUtiles.getFechaStr(fecMax));
            stmt.setInt(3, tipo);
            ResultSet  rs =   stmt.executeQuery();  
            List<BeanEstCliente> lstBeanEstCliente = new ArrayList<BeanEstCliente>();
            while (rs.next()) {
                BeanEstCliente beanEstCliente = new BeanEstCliente();
                beanEstCliente.setYear(rs.getString("ANIO"));
                beanEstCliente.setMes(rs.getString("MES"));
                if(tipo == 1){
                    beanEstCliente.setRazonSocial("Proveedor");
                }
                else{
                    beanEstCliente.setRazonSocial("Propio");
                }
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
