package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUsuarioXPermisoLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUsuarioXPermisoRemote;
import silat.servicios_negocio.entidades.admin.ADUsuarioXPermiso;

@Stateless(name = "BDL_C_SFUsuarioXPermiso", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFUsuarioXPermiso")
public class BDL_C_SFUsuarioXPermisoBean implements BDL_C_SFUsuarioXPermisoRemote, 
                                                    BDL_C_SFUsuarioXPermisoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFUsuarioXPermisoBean() {
    }

    /** <code>select o from ADUsuarioXPermiso o</code> */
    public List<ADUsuarioXPermiso> getADUsuarioXPermisoFindAll() {
        return em.createNamedQuery("ADUsuarioXPermiso.findAll").getResultList();
    }
    
    public List<BigDecimal> getPermisosByUsuario(){
        String  ejbQL = "select up.nidPermiso " +
                        "from ADUsuarioXPermiso up "+
                        "where up.nEstadoRolXPermiso = 1 "+
                        "and up.nEstadoUsuarioXPermiso = 1 "+
                        "and up.nidUsuario = 1 "+
                        "and up.rolXPermiso.nidRole = 1";
        return em.createQuery(ejbQL).getResultList();
    }
    
    public List<BigDecimal> getRolesByUsuario(BigDecimal nidUsuario){
        String ejbQL = "select distinct(up.rolXPermiso.nidRole) "+
                       "from ADUsuarioXPermiso up "+
                       "where up.nidUsuario = :nidUsu "+
                       "and up.nEstadoRolXPermiso = 1 "+
                       "and up.nEstadoUsuarioXPermiso = '1' ";
        return em.createQuery(ejbQL).setParameter("nidUsu", nidUsuario)
                                    .getResultList();
    }
    
    public List<ADUsuarioXPermiso> getPermisosPorUsuario(BigDecimal nidUsuario){
        String ejbQL = "select up "+
                       "from ADUsuarioXPermiso up "+
                       "where up.nidUsuario = :nidUsu "+
                       "and up.nEstadoRolXPermiso = 1 "+
                       "and up.nEstadoUsuarioXPermiso = '1' ";
        return em.createQuery(ejbQL).setParameter("nidUsu", nidUsuario)
                                    .getResultList();
        
    }
    
}