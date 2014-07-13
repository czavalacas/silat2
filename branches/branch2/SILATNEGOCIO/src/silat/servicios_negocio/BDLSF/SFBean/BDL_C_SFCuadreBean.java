package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.sql.DataSource;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFCuadreLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFCuadreRemote;
import silat.servicios_negocio.Beans.BeanCuadre;
import silat.servicios_negocio.util_formato.Fecha.FechaUtiles;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_C_SFCuadre", mappedName = "mapBDL_C_SFCuadre")
public class BDL_C_SFCuadreBean implements BDL_C_SFCuadreRemote, 
                                           BDL_C_SFCuadreLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    //  @Resource(mappedName = "java:/app/jdbc/jdbc/ConLubalDS")
    @Resource(mappedName = "jdbc/ConLubalDS")
    private DataSource lubalDS;
    Connection conn = null;
    
    public BDL_C_SFCuadreBean() {
    }
    
    public List<BeanCuadre> getReporteCuadre(Date fecMin, Date fecMax){
        try{
            List<BeanCuadre> lstCuadre = new ArrayList<BeanCuadre>();
            List<BeanCuadre> lstCuadreHijos = new ArrayList<BeanCuadre>();
            BeanCuadre resultado = new BeanCuadre();
            resultado.setDescCuadre("------------ Balance ------------");
            
            BeanCuadre cuadreIngreso = new BeanCuadre();
            BigDecimal[] b = this.call_Procedure_GET_TOTALES(fecMin,fecMax);
            cuadreIngreso.setDescCuadre("Ingresos");
            cuadreIngreso.setIngreso(b[0]);
            cuadreIngreso.setEgreso(new BigDecimal(0));
            
            List<BeanCuadre> lstCuadreIng = new ArrayList<BeanCuadre>();
            BeanCuadre cuadreIngreso_Fact = new BeanCuadre();
            BigDecimal[] b_ing = this.call_Procedure_GET_INGRESOS(fecMin,fecMax);
            cuadreIngreso_Fact.setDescCuadre("Facturas Pagadas");
            cuadreIngreso_Fact.setIngreso(b_ing[0]);
            cuadreIngreso_Fact.setEgreso(new BigDecimal(0));
            lstCuadreIng.add(cuadreIngreso_Fact);
            
            BeanCuadre cuadreIngreso_NotaDeb = new BeanCuadre();
            cuadreIngreso_NotaDeb.setDescCuadre("Notas de Debito");
            cuadreIngreso_NotaDeb.setIngreso(b_ing[1]);
            cuadreIngreso_NotaDeb.setEgreso(new BigDecimal(0));
            lstCuadreIng.add(cuadreIngreso_NotaDeb);
            
            cuadreIngreso.setLstSubCuadres(lstCuadreIng);
            /*--------------------------------------------------------------------------------------------------------*/
            BeanCuadre cuadreEgreso = new BeanCuadre();
            cuadreEgreso.setDescCuadre("Egresos");
            cuadreEgreso.setIngreso(new BigDecimal(0));
            cuadreEgreso.setEgreso(b[1]);
            cuadreEgreso.setLstSubCuadres(this.call_Procedure_GET_EGRESOS(fecMin, fecMax));
            /*--------------------------------------------------------------------------------------------------------*/
            
            resultado.setIngreso(b[0]);
            resultado.setEgreso(b[1]);
            lstCuadreHijos.add(cuadreIngreso);
            lstCuadreHijos.add(cuadreEgreso);
            resultado.setLstSubCuadres(lstCuadreHijos);
            lstCuadre.add(resultado);
            return lstCuadre;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanCuadre>(); 
        }
    }
    
    public List<BeanCuadre> call_Procedure_GET_EGRESOS(Date fecMin, Date fecMax){
        try {
            conn = lubalDS.getConnection();
            String query = "BEGIN GET_EGRESOS(?,?,?); END; ";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt.setString(2,FechaUtiles.getFechaStr(fecMax));
            stmt.registerOutParameter(3, OracleTypes.CURSOR);
            stmt.execute();
            
            List<BeanCuadre> lstBeanCuadre = new ArrayList<BeanCuadre>();
            ResultSet rs = ((OracleCallableStatement)stmt).getCursor(3);
            while (rs.next()) {
                BeanCuadre beanCuadre = new BeanCuadre();
                beanCuadre.setDescCuadre(rs.getString("DESCR"));
                beanCuadre.setIngreso(new BigDecimal(0));
                beanCuadre.setEgreso(rs.getBigDecimal("MONT"));
                lstBeanCuadre.add(beanCuadre);
            }
            String queryNotasCred = "BEGIN GET_EGRESOS_NOTAS_CREDITO(?,?,?,?); END; ";
            CallableStatement stmt2 = conn.prepareCall(queryNotasCred);
            stmt2.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt2.setString(2,FechaUtiles.getFechaStr(fecMax));
            stmt2.registerOutParameter(3, Types.VARCHAR);
            stmt2.registerOutParameter(4, Types.DECIMAL);
            stmt2.execute();
            String descr = stmt2.getString(3);
            BigDecimal egreNotaCre = stmt2.getBigDecimal(4);
            BeanCuadre beanCuadre = new BeanCuadre();
            beanCuadre.setDescCuadre(descr);
            beanCuadre.setIngreso(new BigDecimal(0));
            beanCuadre.setEgreso(egreNotaCre);
            lstBeanCuadre.add(beanCuadre);
            
            /*----------------------------------------EGRESO DE ADELANTO EN MANIFIESTOS -------------------------------*/
            String queryAdelantos = "BEGIN GET_EGRESOS_ADELANTO_MANIFI(?,?,?,?); END; ";
            CallableStatement stmt3 = conn.prepareCall(queryAdelantos);
            stmt3.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt3.setString(2,FechaUtiles.getFechaStr(fecMax));
            stmt3.registerOutParameter(3, Types.VARCHAR);
            stmt3.registerOutParameter(4, Types.DECIMAL);
            stmt3.execute();
            String descrAdel = stmt3.getString(3);
            BigDecimal egreAdela = stmt3.getBigDecimal(4);
            BeanCuadre beanCuadreAdela = new BeanCuadre();
            beanCuadreAdela.setDescCuadre(descrAdel);
            beanCuadreAdela.setIngreso(new BigDecimal(0));
            beanCuadreAdela.setEgreso(egreAdela);
            lstBeanCuadre.add(beanCuadreAdela);
            /*----------------------------------------FIN EGRESO DE ADELANTO EN MANIFIESTOS -------------------------------*/
            
            /*----------------------------------------EGRESO DE PAGO EN MANIFIESTOS -------------------------------*/
            String queryPago = "BEGIN GET_EGRESOS_PAGO_SALDO_MANIFI(?,?,?,?); END; ";
            CallableStatement stmt4 = conn.prepareCall(queryPago);
            stmt4.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt4.setString(2,FechaUtiles.getFechaStr(fecMax));
            stmt4.registerOutParameter(3, Types.VARCHAR);
            stmt4.registerOutParameter(4, Types.DECIMAL);
            stmt4.execute();
            String descrPago = stmt4.getString(3);
            BigDecimal egrePago = stmt4.getBigDecimal(4);
            BeanCuadre beanCuadrePagoManif = new BeanCuadre();
            beanCuadrePagoManif.setDescCuadre(descrPago);
            beanCuadrePagoManif.setIngreso(new BigDecimal(0));
            beanCuadrePagoManif.setEgreso(egrePago);
            lstBeanCuadre.add(beanCuadrePagoManif);
            /*----------------------------------------FIN EGRESO DE PAGO EN MANIFIESTOS -------------------------------*/
            
            conn.close();
            return lstBeanCuadre;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            return new ArrayList<BeanCuadre>();
        }
    }
    
    public BigDecimal[] call_Procedure_GET_TOTALES(Date fecMin, Date fecMax){
        try {
            conn = lubalDS.getConnection();
            String query = "BEGIN GET_TOTALES(?,?,?,?); END; ";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt.setString(2,FechaUtiles.getFechaStr(fecMax));
            stmt.registerOutParameter(3, Types.DECIMAL);
            stmt.registerOutParameter(4, Types.DECIMAL);
            stmt.execute();
            conn.close();
            BigDecimal ingre = stmt.getBigDecimal(3);
            BigDecimal egre = stmt.getBigDecimal(4);
            BigDecimal[] vec = new BigDecimal[]{ingre,egre};
            return vec;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            return null;
        }
    }
    
    public BigDecimal[] call_Procedure_GET_INGRESOS(Date fecMin, Date fecMax){
        try {
            conn = lubalDS.getConnection();
            String query = "BEGIN GET_INGRESOS(?,?,?,?); END; ";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1,FechaUtiles.getFechaStr(fecMin));
            stmt.setString(2,FechaUtiles.getFechaStr(fecMax));
            stmt.registerOutParameter(3, Types.DECIMAL);
            stmt.registerOutParameter(4, Types.DECIMAL);
            stmt.execute();
            conn.close();
            BigDecimal ingre_fact = stmt.getBigDecimal(3);
            BigDecimal ingre_not_deb = stmt.getBigDecimal(4);
            BigDecimal[] vec = new BigDecimal[]{ingre_fact,ingre_not_deb};
            return vec;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            return null;
        }
    }
}