package silat.servicios_negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFTipoGastoLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFTipoGastoLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanTipoGasto;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFTipoGastoLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFTipoGastoRemote;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;

@Stateless(name = "LN_T_SFTipoGasto", mappedName = "mapLN_T_SFTipoGasto")
public class LN_T_SFTipoGastoBean implements LN_T_SFTipoGastoRemote, 
                                             LN_T_SFTipoGastoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFTipoGastoLocal bdL_T_SFTipoGastoLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    @EJB
    private BDL_C_SFTipoGastoLocal bdL_C_SFTipoGastoLocal;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;

    public LN_T_SFTipoGastoBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanTipoGasto registrarBeanTipoGasto_LN(int tipEvento, 
                                                   String descTipGasto, 
                                                   String isRuta,
                                                   String estado,
                                                   int nidTipGasto){
        BeanError beanError = new BeanError();
        BeanTipoGasto bTipGasto = new BeanTipoGasto();
        ADTipoGasto eTipGasto = new ADTipoGasto();
        String error = "000";
        try{
            boolean isNuevo = true;
            int cantDesc = 0;
            if(tipEvento == 1){//Registrar
                cantDesc = bdL_C_SFUtilsLocal.findCountByProperty("descripcionTipoGasto",descTipGasto,"ADTipoGasto",true,isNuevo,"");
                if(cantDesc > 0){
                    error = "LUB-0030";
                }else{
                    eTipGasto.setEstadoRegistro("1");
                }
            }else{
                isNuevo = false;
                eTipGasto = bdL_C_SFTipoGastoLocal.findADTipoGastoById(nidTipGasto);
                cantDesc = bdL_C_SFUtilsLocal.findCountByProperty("descripcionTipoGasto",descTipGasto,"ADTipoGasto",true,isNuevo,eTipGasto.getDescripcionTipoGasto());
                if(cantDesc > 0){
                    error = "LUB-0030";
                }else{
                    eTipGasto.setEstadoRegistro(estado);
                }
            }
            if(error.equals("000")){
                eTipGasto.setDescripcionTipoGasto(descTipGasto);
                eTipGasto.setIsRuta(isRuta);
                if(tipEvento == 1){
                    bdL_T_SFTipoGastoLocal.persistADTipoGasto(eTipGasto);
                }else{
                    bdL_T_SFTipoGastoLocal.mergeADTipoGasto(eTipGasto);
                }
            }
        }catch(Exception e){
            error = "LUB-0031";
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bTipGasto.setBeanError(beanError);
        return bTipGasto;
    }
}