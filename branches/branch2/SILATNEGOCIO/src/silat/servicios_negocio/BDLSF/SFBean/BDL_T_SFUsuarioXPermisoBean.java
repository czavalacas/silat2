package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUsuarioXPermisoLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFUsuarioXPermisoLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFUsuarioXPermisoRemote;
import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;
import silat.servicios_negocio.entidades.admin.ADUsuarioXPermiso;
import silat.servicios_negocio.entidades.admin.ADUsuarioXPermisoPK;

@Stateless(name = "BDL_T_SFUsuarioXPermiso", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_T_SFUsuarioXPermiso")
public class BDL_T_SFUsuarioXPermisoBean implements BDL_T_SFUsuarioXPermisoRemote,
                                                    BDL_T_SFUsuarioXPermisoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB 
    private BDL_C_SFUsuarioXPermisoLocal bdl_C_SFUsuarioXPermisoLocal;

    public BDL_T_SFUsuarioXPermisoBean() {
    }

    public ADUsuarioXPermiso persistADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso) {
        em.persist(ADUsuarioXPermiso);
        return ADUsuarioXPermiso;
    }

    public ADUsuarioXPermiso mergeADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso) {
        return em.merge(ADUsuarioXPermiso);
    }
    public ADUsuarioXPermiso borrarUsuarioXPermiso(BigDecimal nidUsuario){
        List<ADUsuarioXPermiso> relaciones = bdl_C_SFUsuarioXPermisoLocal.getPermisosPorUsuario(nidUsuario);
        for(ADUsuarioXPermiso relacion : relaciones){
            removeADUsuarioXPermiso(relacion);
        }
        return null;
    }

    public void removeADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso) {
        ADUsuarioXPermiso =
                em.find(ADUsuarioXPermiso.class, new ADUsuarioXPermisoPK(ADUsuarioXPermiso.getNEstadoRolXPermiso(),
                                                                         ADUsuarioXPermiso.getNidPermiso(),
                                                                         ADUsuarioXPermiso.getNidRole(),
                                                                         ADUsuarioXPermiso.getNidUsuario()));
        em.remove(ADUsuarioXPermiso);
    }    
  
}
