package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFItemXOrdenServLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFItemXOrdenServRemoto;
import silat.servicios_negocio.entidades.trans.TRItemXOrds;

@Stateless(name = "BDL_T_SFItemXOrdenServ", mappedName = "map-BDL_T_SFItemXOrdenServ")
public class BDL_T_SFItemXOrdenServBean implements BDL_T_SFItemXOrdenServRemoto, BDL_T_SFItemXOrdenServLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFItemXOrdenServBean() {
    }

    public TRItemXOrds persistTRItemXOrds(TRItemXOrds TRItemXOrds) {
        em.persist(TRItemXOrds);
        return TRItemXOrds;
    }

    public TRItemXOrds mergeTRItemXOrds(TRItemXOrds TRItemXOrds) {
        return em.merge(TRItemXOrds);
    }

    public void removeTRItemXOrds(TRItemXOrds TRItemXOrds) {
        TRItemXOrds = em.find(TRItemXOrds.class, TRItemXOrds.getNidItem());
        em.remove(TRItemXOrds);
    }
}
