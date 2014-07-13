package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFTipoGastoLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFTipoGastoRemote;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;

@Stateless(name = "BDL_T_SFTipoGasto", mappedName = "mapBDL_T_SFTipoGasto")
public class BDL_T_SFTipoGastoBean implements BDL_T_SFTipoGastoRemote, 
                                              BDL_T_SFTipoGastoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFTipoGastoBean() {
    }

    public ADTipoGasto persistADTipoGasto(ADTipoGasto ADTipoGasto) {
        em.persist(ADTipoGasto);
        return ADTipoGasto;
    }

    public ADTipoGasto mergeADTipoGasto(ADTipoGasto ADTipoGasto) {
        return em.merge(ADTipoGasto);
    }

    public void removeADTipoGasto(ADTipoGasto ADTipoGasto) {
        ADTipoGasto = em.find(ADTipoGasto.class, ADTipoGasto.getNidTiga());
        em.remove(ADTipoGasto);
    }
}
