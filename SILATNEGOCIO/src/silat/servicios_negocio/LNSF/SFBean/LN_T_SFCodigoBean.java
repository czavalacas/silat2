package silat.servicios_negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFCodigoLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFCodigoLocal;
import silat.servicios_negocio.Beans.BeanCodigo;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCodigoLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFCodigoLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFCodigoRemote;
import silat.servicios_negocio.entidades.trans.Trmcodi;

@Stateless(name = "LN_T_SFCodigo", mappedName = "mapLN_T_SFCodigo")
public class LN_T_SFCodigoBean implements LN_T_SFCodigoRemote, 
                                          LN_T_SFCodigoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    @EJB
    private LN_C_SFCodigoLocal ln_C_SFCodigoLocal;
    @EJB
    private BDL_C_SFCodigoLocal bdL_C_SFCodigoLocal;
    @EJB
    private BDL_T_SFCodigoLocal bdL_T_SFCodigoLocal;

    public LN_T_SFCodigoBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanCodigo actualizarCodigo(String cidUnin,
                                       String codigo,
                                       String tipDoc){
        String error = "000";
        BeanError beanError = new BeanError();
        BeanCodigo beanCodigo = new BeanCodigo();
        Trmcodi eCodigo = new Trmcodi();
        try{
            // error = ln_C_SFCodigoLocal.validarNuevoCodigo(cidUnin, tipDoc, codigo);
            int hayDocumento = ln_C_SFCodigoLocal.isCodigoExistente(cidUnin, tipDoc, codigo);
            if(hayDocumento > 0){
                error = "LUB-0040";
            }
            if(error.equals("000")){
                eCodigo = bdL_C_SFCodigoLocal.findCodigoById(cidUnin,tipDoc);
                eCodigo.setCodigo(codigo);
                bdL_T_SFCodigoLocal.mergeTrmcodi(eCodigo);
            }
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0031";
        }finally{
            beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
            beanCodigo.setBeanError(beanError);
            return beanCodigo;
        }
    }
}