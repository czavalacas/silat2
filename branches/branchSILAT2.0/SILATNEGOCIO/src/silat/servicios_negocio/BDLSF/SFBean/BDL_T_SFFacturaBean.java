package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFFacturaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFFacturaRemote;
import silat.servicios_negocio.entidades.trans.TRFactura;

@Stateless(name = "BDL_T_SFFactura", mappedName = "mapBDL_T_SFFactura")
public class BDL_T_SFFacturaBean implements BDL_T_SFFacturaRemote, 
                                            BDL_T_SFFacturaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFFacturaBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRFactura persistTRFactura(TRFactura trFactura) {
        em.persist(trFactura);
        em.flush();
        return trFactura;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRFactura mergeTRFactura(TRFactura TRFactura) {
        return em.merge(TRFactura);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeTRFactura(TRFactura TRFactura) {
        TRFactura = em.find(TRFactura.class, TRFactura.getNidFactura());
        em.remove(TRFactura);
    }
}
