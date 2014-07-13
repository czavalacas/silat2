package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFEmpresasLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFEmpresaLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFEmpresaRemote;

@Stateless(name = "LN_T_SFEmpresa", mappedName = "mapLN_T_SFEmpresa")
public class LN_T_SFEmpresaBean implements LN_T_SFEmpresaRemote, 
                                           LN_T_SFEmpresaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFEmpresasLocal bdL_T_SFEmpresasLocal;
    @EJB
    protected LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    
    public LN_T_SFEmpresaBean() {
    }
    
    public BeanError borrarEmpresa(BigDecimal nidEmpresa){
        String error = "000";
        BeanError beanError = new BeanError();
        try{
            int salida = bdL_T_SFEmpresasLocal.borrarEmpresa(nidEmpresa);
            if(salida == 1){
                error = "LUB-0029";
            }
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0029";
        }finally{
            beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
            return beanError;
        }
    }
}