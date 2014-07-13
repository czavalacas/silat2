package silat.servicios_negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFUnidadMedidaLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFUnidadMedidaLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFUnidadMedidaRemote;
import silat.servicios_negocio.entidades.trans.TRUnidadMedida;

@Stateless(name = "LN_T_SFUnidadMedida", mappedName = "mapLN_T_SFUnidadMedida")
public class LN_T_SFUnidadMedidaBean implements LN_T_SFUnidadMedidaRemote,
                                                LN_T_SFUnidadMedidaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFUnidadMedidaLocal bdL_T_SFUnidadMedidaLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    
    public LN_T_SFUnidadMedidaBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanUnidadMedida registrarUnidadMedida(String descUnidadMedida,
                                                  String sigla){
        String error = "000";
        TRUnidadMedida eUM = new TRUnidadMedida();
        BeanUnidadMedida bUM = new BeanUnidadMedida();
        BeanError beanError = new BeanError();
        try{
            int cantDesc = 0;
            int cantValor = 0;
            cantDesc = bdL_C_SFUtilsLocal.findCountByProperty("descUnidadMedida",descUnidadMedida.toUpperCase(),"TRUnidadMedida",true,false,"");
            cantValor = bdL_C_SFUtilsLocal.findCountByProperty("sigla",sigla.toUpperCase(),"TRUnidadMedida",true,false,"");
            if((cantDesc + cantValor) > 0){
                error = "LUB-0033";
            }else{
                eUM.setDescUnidadMedida(descUnidadMedida);
                eUM.setSigla(sigla);
                bdL_T_SFUnidadMedidaLocal.persistTRUnidadMedida(eUM);
            }
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0031";
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bUM.setBeanError(beanError);
        return bUM;
    }
}