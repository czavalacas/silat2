package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFRolXPermisoLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFPermisosLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFRolXPermisoLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFUsuarioXPermisoLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.Beans.BeanPermisos;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFPermisosLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFPermisosRemoto;
import silat.servicios_negocio.entidades.admin.ADGasto;
import silat.servicios_negocio.entidades.admin.ADPermiso;
import silat.servicios_negocio.entidades.admin.ADUsuario;
import silat.servicios_negocio.entidades.admin.ADUsuarioXPermiso;
import silat.servicios_negocio.entidades.audsis.STRol;
import silat.servicios_negocio.entidades.audsis.STRolXPermiso;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_T_SFPermisos", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_T_SFPermisos")
public class LN_T_SFPermisosBean implements LN_T_SFPermisosRemoto, 
                                            LN_T_SFPermisosLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    BDL_T_SFPermisosLocal bdl_T_SFPermisosLocal;
    @EJB
    private BDL_T_SFUsuarioXPermisoLocal bdl_T_SFUsuarioXPermisoLocal;
    @EJB
    private BDL_T_SFRolXPermisoLocal bdl_T_SFRolXPermisoLocal;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    @EJB
    private BDL_C_SFRolXPermisoLocal bdl_C_SFRolXPermisoLocal;

    public LN_T_SFPermisosBean() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void removerPermisos(BigDecimal nidUsuario) {
        bdl_T_SFUsuarioXPermisoLocal.borrarUsuarioXPermiso(nidUsuario);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanPermisos actualizarPermisos(BigDecimal nidUsuario, 
                                           BigDecimal nidRole,
                                           BigDecimal nidPermiso) {
        BeanPermisos bPermiso = new BeanPermisos();
        BeanError beanError = new BeanError();
        String error = "000";
        ADUsuario eUsuario=new ADUsuario();
        STRol eRol=new STRol();
        ADPermiso ePermiso=new ADPermiso();
        STRolXPermiso eRolXPermiso = new STRolXPermiso();
        
        eRol.setNidRole(nidRole);
        ePermiso.setNidPermiso(nidPermiso);
        eRolXPermiso.setAdPermiso(ePermiso);
        eRolXPermiso.setStRol(eRol);
        eRolXPermiso.setNEstRolXPermiso(new BigDecimal (1));
        eRolXPermiso.setNidPermiso(nidPermiso);
        eRolXPermiso.setNidRole(nidRole);       
        
        eUsuario.setNidUsuario(nidUsuario);
        ADUsuarioXPermiso eUsuarioXPermiso = new ADUsuarioXPermiso();
        
        eUsuarioXPermiso.setAdUsuario(eUsuario);
        eUsuarioXPermiso.setRolXPermiso(eRolXPermiso);
        eUsuarioXPermiso.setNEstadoRolXPermiso(new BigDecimal(1));
        eUsuarioXPermiso.setNEstadoUsuarioXPermiso("1");
        eUsuarioXPermiso.setNidPermiso(nidPermiso);
        eUsuarioXPermiso.setNidRole(nidRole);
        eUsuarioXPermiso.setNidUsuario(nidUsuario);        

        try {
            int num = 0;
            if (num == bdl_C_SFRolXPermisoLocal.verificarExisteRolPorPermiso(nidRole, nidPermiso)) {
                bdl_T_SFRolXPermisoLocal.persistSTRolXPermiso(eRolXPermiso);
            }
            bdl_T_SFUsuarioXPermisoLocal.persistADUsuarioXPermiso(eUsuarioXPermiso);
        } catch (Exception e) {
            error = "LUB-0018";
            e.printStackTrace();
            return null;
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bPermiso.setBeanError(beanError);
        return bPermiso;
    }
}
