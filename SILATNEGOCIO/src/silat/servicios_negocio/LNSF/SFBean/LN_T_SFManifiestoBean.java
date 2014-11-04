package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

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

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEmpresasLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFManifiestoLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_ManifiestoLocal;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFGuiaLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFManifiestoLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFManifiestoLocal;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.trans.TRManifiesto;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_T_SFManifiesto", mappedName = "mapLN_T_SFManifiesto")
public class LN_T_SFManifiestoBean implements LN_T_SFManifiestoRemote, 
                                              LN_T_SFManifiestoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    protected LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    @EJB
    private BDL_C_SFEmpresasLocal bdL_C_SFEmpresasLocal;
    @EJB
    private BDL_C_SFManifiestoLocal bdL_C_SFManifiestoLocal;
    @EJB
    private BDL_T_ManifiestoLocal bdL_T_ManifiestoLocal;

    public LN_T_SFManifiestoBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanManifiesto registrarManifiesto_LN(int nidEmpProvTrans,
                                                 Date fecManif,
                                                 Double fletePactado,
                                                 Double adelanto,
                                                 String tipDoc,
                                                 String observ,
                                                 int nidFlota,
                                                 int nidChofer,
                                                 int nidManif,
                                                 int estadoManif,
                                                 String estadoManifiestoNegocio){
        String error = "000";
        TRManifiesto eManifiesto = new TRManifiesto();
        BeanManifiesto bManifiesto = new BeanManifiesto();
        BeanError beanError = new BeanError();
        try{
            if(nidManif != 0){//Actualizar
                eManifiesto = bdL_C_SFManifiestoLocal.findTRManifiestoById(nidManif);
                eManifiesto.setNEstManifiesto(estadoManif);
            }else{
                eManifiesto.setNEstManifiesto(1);
                eManifiesto.setCTipoDoc(tipDoc);
                eManifiesto.setFechaManifiesto(fecManif);
                eManifiesto.setNidChof(nidChofer);
                eManifiesto.setNidFlota(nidFlota);
                ADEmpresa eEmpresa = bdL_C_SFEmpresasLocal.getEmpresaById(new BigDecimal(nidEmpProvTrans));
                eManifiesto.setTrManifiesto(eEmpresa);
            }
            eManifiesto.setNFletePactado(fletePactado);
            eManifiesto.setCObservaciones(observ);   
            if(adelanto != null){
                if(adelanto > fletePactado){
                    error = "LUB-0011";
                }
            }else{
                adelanto = 0.0;
            }
            eManifiesto.setNAdelanto(adelanto);
            if("000".equals(error)){
                eManifiesto.setEstadoManifiestoNegocio(estadoManifiestoNegocio);
               
                eManifiesto = bdL_T_ManifiestoLocal.registrarManifiesto(eManifiesto);
                MapperIF mapper = new DozerBeanMapper();
                bManifiesto = (BeanManifiesto)mapper.map(eManifiesto, BeanManifiesto.class);
            }
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0010";
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bManifiesto.setBeanError(beanError);
        return bManifiesto;
    }
    
    public BeanManifiesto anularManifiesto(int nidManif){
        String error = "000";
        BeanManifiesto bManifiesto = new BeanManifiesto();
        BeanError beanError = new BeanError();
        try{
            TRManifiesto eManifiesto = new TRManifiesto();
            eManifiesto = bdL_C_SFManifiestoLocal.findTRManifiestoById(nidManif);
            eManifiesto.setNEstManifiesto(0);
            eManifiesto = bdL_T_ManifiestoLocal.registrarManifiesto(eManifiesto);
        }catch(Exception e){
            error = "LUB-0036";
            e.printStackTrace();
        }finally{
            beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
            bManifiesto.setBeanError(beanError);
            return bManifiesto;
        }
    }
    
    public String cambiarFechaManifiestoPermiso56(int nidManif,Date fechaManif){
        try{
            TRManifiesto eManifiesto = bdL_C_SFManifiestoLocal.findTRManifiestoById(nidManif);
            eManifiesto.setFechaManifiesto(fechaManif);
            bdL_T_ManifiestoLocal.registrarManifiesto(eManifiesto);
            return "000";
        }catch(Exception e){
            e.printStackTrace();
            return "111";
        }
    }
    
    public void cambiarEstadoManifiesto(int nidManif, String estadoManif){
        TRManifiesto eManifiesto = new TRManifiesto();
        eManifiesto = bdL_C_SFManifiestoLocal.findTRManifiestoById(nidManif);
        eManifiesto.setEstadoManifiestoNegocio(estadoManif);
        eManifiesto = bdL_T_ManifiestoLocal.registrarManifiesto(eManifiesto);
    }
    
    public void depurar(Object o){
        System.out.println(o);
    }
}