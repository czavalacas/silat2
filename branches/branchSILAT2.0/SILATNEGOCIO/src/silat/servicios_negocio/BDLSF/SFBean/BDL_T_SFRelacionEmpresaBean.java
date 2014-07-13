package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFRelacionEmpresaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFRelacionEmpresaRemote;
import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;

@Stateless(name = "BDL_T_SFRelacionEmpresa", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_T_SFRelacionEmpresa")
public class BDL_T_SFRelacionEmpresaBean implements BDL_T_SFRelacionEmpresaRemote,
                                                    BDL_T_SFRelacionEmpresaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFRelacionEmpresaBean() {
    }

    public ADRelacionEmpresa persistADRelacionEmpresa(ADRelacionEmpresa ADRelacionEmpresa) {
        em.persist(ADRelacionEmpresa);
        return ADRelacionEmpresa;
    }

    public ADRelacionEmpresa mergeADRelacionEmpresa(ADRelacionEmpresa ADRelacionEmpresa) {
        return em.merge(ADRelacionEmpresa);
    }

    public void removeADRelacionEmpresa(ADRelacionEmpresa ADRelacionEmpresa) {
        ADRelacionEmpresa = em.find(ADRelacionEmpresa.class, ADRelacionEmpresa.getCorrelativoRelacionEmpresa());
        em.remove(ADRelacionEmpresa);
    }
}
