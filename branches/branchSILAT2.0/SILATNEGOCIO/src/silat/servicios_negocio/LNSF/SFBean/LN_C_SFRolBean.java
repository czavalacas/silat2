package silat.servicios_negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFRolLocal;
import silat.servicios_negocio.Beans.BeanRol;
import silat.servicios_negocio.LNSF.IL.LN_C_SFRolLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRolRemote;
import silat.servicios_negocio.entidades.audsis.STRol;

@Stateless(name = "LN_C_SFRol", mappedName = "mapLN_C_SFRol")
public class LN_C_SFRolBean implements LN_C_SFRolLocal, 
                                       LN_C_SFRolRemote{
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    @EJB
    protected BDL_C_SFRolLocal bdL_C_SFRolLocal;
    
    public LN_C_SFRolBean() {
    }
    
    public List<BeanRol> getRolesActivosLN(){
        List<STRol> roles = bdL_C_SFRolLocal.getRolesActivos();
        Iterator it = roles.iterator();
        List<BeanRol> beanRoles = new ArrayList<BeanRol>();
        while(it.hasNext()){
            STRol rol = (STRol) it.next();
            BeanRol beanRol = new BeanRol();
            beanRol = setBeanFromEntity(rol);
            beanRoles.add(beanRol);
        }
        return beanRoles;
    }
    
    public List<BeanRol> getRolesActivosNoAdminLN(){
        List<STRol> roles = bdL_C_SFRolLocal.getRolesActivosNoAdmin();
        Iterator it = roles.iterator();
        List<BeanRol> beanRoles = new ArrayList<BeanRol>();
        while(it.hasNext()){
            STRol rol = (STRol) it.next();
            BeanRol beanRol = new BeanRol();
            beanRol = setBeanFromEntity(rol);
            beanRoles.add(beanRol);
        }
        return beanRoles;
    }
    
    public BeanRol setBeanFromEntity(STRol rol){
        BeanRol beanRol = new BeanRol();
        beanRol.setCDescRole(rol.getCDescRole());
        beanRol.setNEstado(rol.getNEstado());
        beanRol.setNidRole(rol.getNidRole());
        return beanRol;
    }
}