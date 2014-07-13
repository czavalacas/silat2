package silat.servicios_negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFCatalogoErroresRemote;
import silat.servicios_negocio.entidades.audsis.STError;

@Stateless(name = "LN_C_SFCatalogoErrores", mappedName = "mapLN_C_SFCatalogoErrores")
public class LN_C_SFCatalogoErroresBean implements LN_C_SFCatalogoErroresRemote,
                                                   LN_C_SFCatalogoErroresLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;

    @EJB
    protected BDL_C_SFCatalogoErroresLocal bdL_C_SFCatalogoErroresLocal;
    
    public LN_C_SFCatalogoErroresBean() {
    }
    
    public BeanError getCatalogoErrores(String cidError){
        BeanError beanError = new BeanError();
        try{
            STError stError = bdL_C_SFCatalogoErroresLocal.getErrorByCodigo(cidError);
            beanError = setBeanEntity(stError);
        }catch(Exception e){
            beanError.setCDescripcionError("Error LN_C_SFCatalogoErroresBean.getCatalogoErrores");
        }
        return beanError;
    }
    
    public BeanError setBeanEntity(STError stError){
        try{
            BeanError beanError = new BeanError();
            beanError.setCAbreviatura(stError.getCAbreviatura());
            beanError.setCDescripcionError(stError.getCDescripcionError());
            beanError.setCEstadoError(stError.getCEstadoError());
            beanError.setCidError(stError.getCidError());
            return beanError;
        }catch(Exception e){
            System.out.println("Error al setear entidad Error a BeanError.");
            return null;
        }
        
    }
}