package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFPreFacturaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFPreFacturaRemote;
import silat.servicios_negocio.entidades.trans.TrPreFactura;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_T_SFPreFactura", mappedName = "mapBDL_T_SFPreFactura")
public class BDL_T_SFPreFacturaBean implements BDL_T_SFPreFacturaRemote, 
                                               BDL_T_SFPreFacturaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFPreFacturaBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TrPreFactura persistTrPreFactura(TrPreFactura trPreFactura) {
        em.persist(trPreFactura);
        return trPreFactura;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TrPreFactura mergeTrPreFactura(TrPreFactura trPreFactura) {
        return em.merge(trPreFactura);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeTrPreFactura(TrPreFactura trPreFactura) {
        trPreFactura = em.find(TrPreFactura.class, trPreFactura.getNidPrefact());
        em.remove(trPreFactura);
    }
}