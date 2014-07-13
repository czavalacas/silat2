package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFGastosLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFGastosRemote;
import silat.servicios_negocio.entidades.admin.ADGasto;

@Stateless(name = "BDL_T_SFGastos", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_T_SFGastos")
public class BDL_T_SFGastosBean implements BDL_T_SFGastosRemote, 
                                           BDL_T_SFGastosLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFGastosBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public ADGasto persistADGasto(ADGasto ADGasto) {
        em.persist(ADGasto);
        return ADGasto;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public ADGasto mergeADGasto(ADGasto ADGasto) {
        return em.merge(ADGasto);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeADGasto(ADGasto ADGasto) {
        ADGasto = em.find(ADGasto.class, ADGasto.getNidGasto());
        em.remove(ADGasto);
    }
    
    public ADGasto findADGastoById(Long id) {
         try {
             ADGasto instance = em.find(ADGasto.class, id);
             return instance;
         } catch (RuntimeException re) {
             throw re;
         }
     }
}
