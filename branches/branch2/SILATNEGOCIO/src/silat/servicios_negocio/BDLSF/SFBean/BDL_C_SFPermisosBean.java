package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFPermisosLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFPermisosRemote;
import silat.servicios_negocio.entidades.admin.ADPermiso;

@Stateless(name = "BDL_C_SFPermisos", mappedName = "mapSFPermisos")
public class BDL_C_SFPermisosBean implements BDL_C_SFPermisosRemote, 
                                             BDL_C_SFPermisosLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFPermisosBean() {
    }

    /** <code>select o from ADPermiso o</code> */
    public List<ADPermiso> getADPermisoFindAll() {
        return em.createNamedQuery("ADPermiso.findAll").getResultList();
    }
    
    public List<ADPermiso> getNodosByNivel(int nivel){
            String ejbQL =  "select o " +
                            "from ADPermiso o " +
                            "where o.nNivel = :nivel "+
                            "and o.nEstadoPermiso = 1";
            return em.createQuery(ejbQL)
                    .setParameter("nivel",nivel)
                    .getResultList();
    }
        
    public int getNiveles(){
        String ejbQL =  "select count(distinct(o.nNivel)) " +
                        "from ADPermiso o ";
        Object oNiveles = em.createQuery(ejbQL)
                            .getSingleResult();
        int iNiveles = 0;
        if(oNiveles != null){
            iNiveles = Integer.parseInt(oNiveles.toString());
        }
        return iNiveles;
    }

    /**
     *
     * @param nidPadre
     * @return
     */
     public List<ADPermiso> getHijosByPadre(BigDecimal nidPadre,
                                            String nombUser,
                                            BigDecimal nidRol){
         String  ejbQL = "select per " +
                         "from ADPermiso per, " +
                         "ADUsuarioXPermiso up "+
                         "where up.nEstadoRolXPermiso = 1 "+
                         "and up.nEstadoUsuarioXPermiso = 1 "+
                         "and up.adUsuario.cUsuario = :nombUser "+
                         "and up.nidPermiso = per.nidPermiso "+
                         "and up.rolXPermiso.nidRole = :nidRol "+
                         "and up.rolXPermiso.nidRole = up.nidRole "+
                         "and up.rolXPermiso.nidPermiso = per.nidPermiso "+
                         "and per.nMenuPadre = :nidPadre "+
                         "and up.rolXPermiso.adPermiso.nEstadoPermiso = 1";
         return em.createQuery(ejbQL)
                     .setParameter("nombUser", nombUser)
                     .setParameter("nidRol", nidRol)
                     .setParameter("nidPadre", nidPadre)
                     .getResultList();
     }
    
    public List<ADPermiso> getHijosByPadreAll(BigDecimal nidPadre){
        String  ejbQL = "select per " +
                        "from ADPermiso per "+
                        "where per.nEstadoPermiso = 1 "+
                        "and per.nMenuPadre = :nidPadre ";
        return em.createQuery(ejbQL).setParameter("nidPadre", nidPadre).getResultList();
    }
    
    public ADPermiso getByNidPermiso(BigDecimal nidPermiso){
        String ejbQL =  "select o " +
                        "from ADPermiso o " +
                        "where o.nidPermiso = :nidPermiso "+
                        "and o.nEstadoPermiso = 1";
        return (ADPermiso) em.createQuery(ejbQL)
                          .setParameter("nidPermiso", nidPermiso)
                          .getSingleResult();
    }
    public boolean isNodoFinalSFLBD(BigDecimal nidPermiso){
        String ejbQL = "select count(o.nidPermiso) " +
                       "from ADPermiso o " +
                       "where o.nidPermiso = :nidPermiso "+
                       "and o.nEstadoPermiso = 1";
        ADPermiso permiso = (ADPermiso)em.createQuery(ejbQL)
                                      .setParameter("nidPermiso", nidPermiso)
                                      .getSingleResult();
        if(permiso != null){
            return true;
        }else{
            return false;
        }
    }
}
