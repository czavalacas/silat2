package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFItemPreFacturaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFItemPreFacturaRemote;
import silat.servicios_negocio.entidades.trans.TrItemPreFactura;

@Stateless(name = "BDL_T_SFItemPreFactura", mappedName = "mapBDL_T_SFItemPreFactura")
public class BDL_T_SFItemPreFacturaBean implements BDL_T_SFItemPreFacturaRemote,
                                                   BDL_T_SFItemPreFacturaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFItemPreFacturaBean() {
    }

    public TrItemPreFactura persistTrItemPreFactura(TrItemPreFactura trItemPreFactura) {
        em.persist(trItemPreFactura);
        return trItemPreFactura;
    }

    public TrItemPreFactura mergeTrItemPreFactura(TrItemPreFactura trItemPreFactura) {
        return em.merge(trItemPreFactura);
    }

    public void removeTrItemPreFactura(TrItemPreFactura trItemPreFactura) {
        trItemPreFactura = em.find(TrItemPreFactura.class, trItemPreFactura.getNidPrefitm());
        em.remove(trItemPreFactura);
    }
}
