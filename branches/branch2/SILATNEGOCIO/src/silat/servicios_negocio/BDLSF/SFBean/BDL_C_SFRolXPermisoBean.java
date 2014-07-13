package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFRolXPermisoLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFRolXPermisoRemote;
import silat.servicios_negocio.entidades.audsis.STRolXPermiso;

@Stateless(name = "BDL_C_SFRolXPermiso", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFRolXPermiso")
public class BDL_C_SFRolXPermisoBean implements BDL_C_SFRolXPermisoRemote,
                                                BDL_C_SFRolXPermisoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFRolXPermisoBean() {
    }

    /** <code>select o from STRolXPermiso o</code> */
    public List<STRolXPermiso> getSTRolXPermisoFindAll() {
        return em.createNamedQuery("STRolXPermiso.findAll").getResultList();
    }
    
    public int verificarExisteRolPorPermiso(BigDecimal nidRol, BigDecimal nidPermiso) {
        int valor = 0;
        try {
            String ejbQl = "select count(e.nidRole) from STRolXPermiso e where e.nidRole='"+ nidRol
                           +"' AND e.nidPermiso='"+nidPermiso+"'";            
            Object resultado = em.createQuery(ejbQl).getSingleResult();
            valor = Integer.parseInt(resultado.toString());
            return valor;
        } catch (Exception e) {
            e.printStackTrace();
            return 55555;
        }
    }
}
