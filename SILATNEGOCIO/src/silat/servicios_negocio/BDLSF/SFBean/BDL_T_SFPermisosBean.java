package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFPermisosLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFPermisosRemoto;
import silat.servicios_negocio.entidades.admin.ADGasto;
import silat.servicios_negocio.entidades.admin.ADPermiso;

@Stateless(name = "BDL_T_SFPermisos", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_T_SFPermisos")
public class BDL_T_SFPermisosBean implements BDL_T_SFPermisosRemoto,
                                             BDL_T_SFPermisosLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFPermisosBean() {
    }

    public ADPermiso persistADPermiso(ADPermiso ADPermiso) {
        em.persist(ADPermiso);
        return ADPermiso;
    }

    public ADPermiso mergeADPermiso(ADPermiso ADPermiso) {
        return em.merge(ADPermiso);
    }

    public void removeADPermiso(ADPermiso ADPermiso) {
        ADPermiso = em.find(ADPermiso.class, ADPermiso.getNidPermiso());
        em.remove(ADPermiso);
    }
    public ADPermiso findADPermiso(BigDecimal id) {
         try {
             ADPermiso instance = em.find(ADPermiso.class, id);
             return instance;
         } catch (RuntimeException re) {
             throw re;
         }
     }
}
