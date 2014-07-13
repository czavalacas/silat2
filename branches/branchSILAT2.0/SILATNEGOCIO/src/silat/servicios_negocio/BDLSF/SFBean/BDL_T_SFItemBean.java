package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFItemLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFItemRemote;
import silat.servicios_negocio.entidades.trans.TRItem;

@Stateless(name = "BDL_T_SFItem", mappedName = "mapBDL_T_SFItem")
public class BDL_T_SFItemBean implements BDL_T_SFItemRemote, 
                                         BDL_T_SFItemLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFItemBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRItem persistTRItem(TRItem trItem) {
        em.persist(trItem);
        em.flush();
        em.refresh(trItem);
        return trItem;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRItem mergeTRItem(TRItem trItem) {
        return em.merge(trItem);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeTRItem(TRItem trItem) {
        trItem = em.find(TRItem.class, trItem.getNidItem());
        em.remove(trItem);
    }
}
