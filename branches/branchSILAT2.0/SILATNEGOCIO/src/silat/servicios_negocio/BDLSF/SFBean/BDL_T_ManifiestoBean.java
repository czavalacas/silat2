package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_ManifiestoLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_ManifiestoRemote;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.entidades.trans.TRManifiesto;

@Stateless(name = "BDL_T_Manifiesto", mappedName = "mapBDL_T_Manifiesto")
public class BDL_T_ManifiestoBean implements BDL_T_ManifiestoRemote, 
                                             BDL_T_ManifiestoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_ManifiestoBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRManifiesto persistTRManifiesto(TRManifiesto trManifiesto) {
        em.persist(trManifiesto);
        em.flush();
        em.refresh(trManifiesto);
        return trManifiesto;
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRManifiesto mergeTRManifiesto(TRManifiesto TRManifiesto) {
        return em.merge(TRManifiesto);
    }

    public void removeTRManifiesto(TRManifiesto TRManifiesto) {
        TRManifiesto = em.find(TRManifiesto.class, TRManifiesto.getNidManifiesto());
        em.remove(TRManifiesto);
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRManifiesto registrarManifiesto(TRManifiesto eManifiesto){
        try{
            if(eManifiesto.getNidManifiesto() != null){//MERGE/UPDATE
                this.mergeTRManifiesto(eManifiesto);
            }else{//PERSIST/INSERT
                this.persistTRManifiesto(eManifiesto);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return eManifiesto;
    }
}
