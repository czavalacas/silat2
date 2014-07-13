package silat.servicios_negocio.BDLSF.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFFlotaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFFlotaRemote;
import silat.servicios_negocio.entidades.admin.ADFlota;

@Stateless(name = "BDL_T_SFFlota", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_T_SFFlota")
public class BDL_T_SFFlotaBean implements BDL_T_SFFlotaRemote, 
                                          BDL_T_SFFlotaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFFlotaBean() {
    }

    public ADFlota persistADFlota(ADFlota ADFlota) {
        em.persist(ADFlota);
        return ADFlota;
    }

    public ADFlota mergeADFlota(ADFlota ADFlota) {
        return em.merge(ADFlota);
    }

    public void removeADFlota(ADFlota ADFlota) {
        ADFlota = em.find(ADFlota.class, ADFlota.getNidFlota());
        em.remove(ADFlota);
    }

    /** <code>select o from ADFlota o</code> */
    public List<ADFlota> getADFlotaFindAll() {
        return em.createNamedQuery("ADFlota.findAll").getResultList();
    }
}
