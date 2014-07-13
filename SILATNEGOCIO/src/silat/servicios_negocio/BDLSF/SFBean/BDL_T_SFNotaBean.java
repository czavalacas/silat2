package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFNotaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFNotaRemote;
import silat.servicios_negocio.entidades.trans.TRNota;

@Stateless(name = "BDL_T_SFNota", mappedName = "mapBDL_T_SFNota")
public class BDL_T_SFNotaBean implements BDL_T_SFNotaRemote, 
                                         BDL_T_SFNotaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFNotaBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRNota persistTRNota(TRNota TRNota) {
        em.persist(TRNota);
        return TRNota;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRNota mergeTRNota(TRNota TRNota) {
        return em.merge(TRNota);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeTRNota(TRNota TRNota) {
        TRNota = em.find(TRNota.class, TRNota.getNidNota());
        em.remove(TRNota);
    }
}
