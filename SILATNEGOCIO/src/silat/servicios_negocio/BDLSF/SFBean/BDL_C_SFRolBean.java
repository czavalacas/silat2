package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFRolLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFRolRemote;
import silat.servicios_negocio.entidades.audsis.STRol;

@Stateless(name = "BDL_C_SFRol", mappedName = "mapSFRol")
public class BDL_C_SFRolBean implements BDL_C_SFRolRemote,
                                        BDL_C_SFRolLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFRolBean() {
    }

    /** <code>select o from STRol o</code> */
    public List<STRol> getSTRolFindAll() {
        return em.createNamedQuery("STRol.findAll").getResultList();
    }
    
    public List<STRol> getRolesActivos(){
        String ejbQL = "select r " +
                       "from STRol r "+
                       "where r.nEstado = 1 " +
                       "order by r.cDescRole ASC ";
        return em.createQuery(ejbQL).getResultList();
    }
    
    public List<STRol> getRolesActivosNoAdmin(){
        String ejbQL = "select r " +
                       "from STRol r "+
                       "where r.nEstado = 1 " +
                       "and r.nidRole <> 1 " +
                       "order by r.cDescRole ASC   ";
        return em.createQuery(ejbQL).getResultList();
    }
    
    public STRol findSTRolById(BigDecimal id) {
         try {
             STRol instance = em.find(STRol.class, id);
             return instance;
         } catch (RuntimeException re) {
             throw re;
         }
     }
}