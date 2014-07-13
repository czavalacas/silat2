package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFRolXPermisoLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFRolXPermisoRemoto;
import silat.servicios_negocio.entidades.audsis.STRolXPermiso;
import silat.servicios_negocio.entidades.audsis.STRolXPermisoPK;

@Stateless(name = "BDL_T_SFRolXPermiso", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_T_SFRolXPermiso")
public class BDL_T_SFRolXPermisoBean implements BDL_T_SFRolXPermisoRemoto,
                                                BDL_T_SFRolXPermisoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFRolXPermisoBean() {
    }

    public STRolXPermiso persistSTRolXPermiso(STRolXPermiso STRolXPermiso) {
        em.persist(STRolXPermiso);
        return STRolXPermiso;
    }

    public STRolXPermiso mergeSTRolXPermiso(STRolXPermiso STRolXPermiso) {
        return em.merge(STRolXPermiso);
    }

    public void removeSTRolXPermiso(STRolXPermiso STRolXPermiso) {
        STRolXPermiso =
                em.find(STRolXPermiso.class, new STRolXPermisoPK(STRolXPermiso.getNEstRolXPermiso(), STRolXPermiso.getNidPermiso(),
                                                                 STRolXPermiso.getNidRole()));
        em.remove(STRolXPermiso);
    }
}
