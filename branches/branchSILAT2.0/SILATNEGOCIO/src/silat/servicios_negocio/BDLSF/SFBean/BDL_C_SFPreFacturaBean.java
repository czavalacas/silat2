package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;
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

import javax.persistence.Query;

import javax.persistence.TemporalType;

import javax.sql.DataSource;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFPreFacturaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFPreFacturaRemote;
import silat.servicios_negocio.Beans.BeanPreFactura;
import silat.servicios_negocio.entidades.trans.TrPreFactura;
import silat.servicios_negocio.util_formato.Fecha.FechaUtiles;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_C_SFPreFactura", mappedName = "mapBDL_C_SFPreFactura")
public class BDL_C_SFPreFacturaBean implements BDL_C_SFPreFacturaRemote,
                                               BDL_C_SFPreFacturaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    //  @Resource(mappedName = "java:/app/jdbc/jdbc/ConLubalDS")
    @Resource(mappedName = "jdbc/ConLubalDS")
    private DataSource lubalDS;
    Connection conn = null;

    public BDL_C_SFPreFacturaBean() {
    }

    /** <code>select o from TrPreFactura o</code> */
    public List<TrPreFactura> getTrPreFacturaFindAll() {
        return em.createNamedQuery("TrPreFactura.findAll").getResultList();
    }
    
    public List<TrPreFactura> findTrPreFacturaByAttributes_BD(BeanPreFactura beanPreFactura){
        try{
            String query = "SELECT distinct(pf) " +
                           "FROM TrPreFactura pf," +
                           "     TrItemPreFactura pfi " +
                           "WHERE pfi.preFactura.nidPrefact = pf.nidPrefact " +
                           "AND pf.cEstreg = '1' ";
            if(beanPreFactura.getCodpedido() != null){
                query = query.concat(" AND pf.codpedido like :codpedido ");
            }
            if(beanPreFactura.getFlgFactura() != null){
                query = query.concat(" AND pf.flgFactura = :flgFactura ");
            }
            if(beanPreFactura.getFechaCreacionMIN() != null && beanPreFactura.getFechaCreacionMAX() != null){
                query = query.concat(" AND pf.fechaCreacion BETWEEN :fecmin AND :fecmax ");
            }
            if(beanPreFactura.getCliente() != null){
                query = query.concat(" AND upper(pfi.cliente) like :cliente ");
            }
            if(beanPreFactura.getCidGuia() != null){
                query = query.concat(" AND pfi.guiasConcat like :guia ");
            }
            if(beanPreFactura.getNidPrefact() != null){
                query = query.concat(" AND pf.nidPrefact = :nidPrefact ");
            }
            query = query.concat(" ORDER BY pf.fechaCreacion DESC ");
            Query q = em.createQuery(query);
            if(beanPreFactura.getCodpedido() != null){
                q.setParameter("codpedido","%" + beanPreFactura.getCodpedido() + "%");
            }
            if(beanPreFactura.getFlgFactura() != null){
                q.setParameter("flgFactura",beanPreFactura.getFlgFactura());
            }
            if(beanPreFactura.getFechaCreacionMIN() != null && beanPreFactura.getFechaCreacionMAX() != null){
                q.setParameter("fecmin",beanPreFactura.getFechaCreacionMIN(),TemporalType.DATE).
                  setParameter("fecmax",beanPreFactura.getFechaCreacionMAX(),TemporalType.DATE);
            }
            if(beanPreFactura.getCliente() != null){
                q.setParameter("cliente","%" + beanPreFactura.getCliente().toUpperCase() + "%");
            }
            if(beanPreFactura.getCidGuia() != null){
                q.setParameter("guia","%" + beanPreFactura.getCidGuia() + "%");
            }
            if(beanPreFactura.getNidPrefact() != null){
                q.setParameter("nidPrefact",beanPreFactura.getNidPrefact());
            }
            return q.getResultList();
        }catch(Exception e){
             e.printStackTrace();
             return new ArrayList<TrPreFactura>();
        }
    }
    
    public TrPreFactura findTrPreFacturaById(Long nidPrefact) {
         try {
             TrPreFactura instance = em.find(TrPreFactura.class,nidPrefact);
             return instance;
         } catch (RuntimeException re) {
             throw re;
         }
     }
    
    public BigDecimal[] call_Procedure_GET_PREFACTURA_MONTOS(Long nidPF){
        try {
            conn = lubalDS.getConnection();
            String query = "BEGIN GET_PREFACTURA_MONTOS(?,?,?,?); END; ";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setLong(1,nidPF);
            stmt.registerOutParameter(2, Types.DECIMAL);
            stmt.registerOutParameter(3, Types.DECIMAL);
            stmt.registerOutParameter(4, Types.DECIMAL);
            stmt.execute();
            conn.close();
            BigDecimal subTotal = stmt.getBigDecimal(2);
            BigDecimal igv = stmt.getBigDecimal(3);
            BigDecimal total = stmt.getBigDecimal(4);
            BigDecimal[] vec = new BigDecimal[]{subTotal,igv,total};
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