package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFClaveLocal;
import silat.servicios_negocio.Beans.BeanClave;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFClaveLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFClaveRemoto;
import silat.servicios_negocio.entidades.admin.ADClave;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_T_SFClave", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_T_SFClave")
public class LN_T_SFClaveBean implements LN_T_SFClaveRemoto,
                                         LN_T_SFClaveLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    
    @EJB    
    BDL_T_SFClaveLocal bdlL_T_SFClaveLocal;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    
    public LN_T_SFClaveBean() {        
        
    }
    
    public BeanClave grabarNuevaContraseña(String cUsuario, String cClave, BigDecimal nidClave){
        BeanClave bClave = new BeanClave();
        BeanError beanError = new BeanError();
        String error = "000";
        try{
            bdlL_T_SFClaveLocal.actualizarClave(cUsuario, cClave, nidClave);
        }catch(Exception e){
            error = "LUB-0018";
            e.printStackTrace();
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bClave.setBeanError(beanError);
        return bClave;
    }
}