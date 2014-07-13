package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFChoferBeanLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFChoferBean;
import silat.servicios_negocio.entidades.admin.ADChofer;
import silat.servicios_negocio.entidades.admin.ADEmpresa;

@Stateless(name = "BDL_T_SFChoferBean", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_T_SFChoferBean")
public class BDL_T_SFChoferBeanBean implements BDL_T_SFChoferBean,
                                               BDL_T_SFChoferBeanLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFChoferBeanBean() {
    }
    public ADChofer persistADChofer(ADChofer ADChofer) {
        em.persist(ADChofer);
        return ADChofer;
    }

    public ADChofer mergeADChofer(ADChofer ADChofer) {
        return em.merge(ADChofer);
    }

    public void removeADChofer(ADChofer ADChofer) {
        ADChofer = em.find(ADChofer.class, ADChofer.getNidChofer());
        em.remove(ADChofer);
    }
}
