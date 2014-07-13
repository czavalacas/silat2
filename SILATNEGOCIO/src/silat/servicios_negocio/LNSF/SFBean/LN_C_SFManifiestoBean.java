package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFManifiestoLocal;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFGuiaLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFManifiestoLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFManifiestoRemote;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_C_SFManifiesto", mappedName = "mapLN_C_SFManifiesto")
public class LN_C_SFManifiestoBean implements LN_C_SFManifiestoRemote, 
                                              LN_C_SFManifiestoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFManifiestoLocal bdL_C_SFManifiestoLocal;
    @EJB
    private LN_C_SFGuiaLocal ln_C_SFGuiaLocal;
    @EJB
    protected LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    
    public LN_C_SFManifiestoBean() {
    }
    
    public List<BeanManifiesto> findManifiestosByAttr_LN(Date fecMin,
                                                         Date fecMax,
                                                         Integer nidManif,
                                                         Integer nidEmpProv,
                                                         String razonSocial,
                                                         String estadoNegoManif){
        BeanManifiesto manif = new BeanManifiesto();
        BeanEmpresa emp = new BeanEmpresa();
        manif.setEstadoManifiestoNegocio(estadoNegoManif);
        manif.setFechaManifiestoMIN(fecMin);
        manif.setFechaManifiestoMAX(fecMax);
        manif.setNidManifiesto(nidManif);
        if(nidEmpProv != null){
            emp.setNidParty(new BigDecimal(nidEmpProv));
        }
        emp.setCRazonSocial(razonSocial);
        manif.setTrManifiesto(emp);
        return bdL_C_SFManifiestoLocal.findManifiestosByAttributes(manif);
    }
    
    public List<BeanManifiesto> _findManifiestosByAttr_LN(Date fecMin,
                                                         Date fecMax,
                                                         Integer nidManif,
                                                         Integer nidEmpProv,
                                                         String razonSocial,
                                                         Double flete,
                                                         Double adelanto,
                                                         String simboloFLete,
                                                         String simboloAdela,
                                                         String observ,
                                                         String estManif,
                                                         Integer nEstManifiesto){
        BeanManifiesto manif = new BeanManifiesto();
        BeanEmpresa emp = new BeanEmpresa();
        manif.setFechaManifiestoMIN(fecMin);
        manif.setFechaManifiestoMAX(fecMax);
        manif.setNidManifiesto(nidManif);
        manif.setSimboloAdelanto(simboloAdela);
        manif.setSimboloFLete(simboloFLete);
        manif.setNFletePactado(flete);
        manif.setNAdelanto(adelanto);
        manif.setCObservaciones(observ);
        manif.setEstadoManifiestoNegocio(estManif);
        manif.setNEstManifiesto(nEstManifiesto);
        if(nidEmpProv != null){
            if(nidEmpProv != 0){
                emp.setNidParty(new BigDecimal(nidEmpProv));
            }           
        }
        emp.setCRazonSocial(razonSocial);
        manif.setTrManifiesto(emp);
        return bdL_C_SFManifiestoLocal.findManifiestosByAttributes(manif);
    }
    
    public BeanError guiasOK(int nidManif, String codUN, String cidGuia, int todo){//todo = 1 / excepto guia = 0
        BeanError beanError = new BeanError();
        try{
            List<BeanTRGuia> guiasManifs = ln_C_SFGuiaLocal.getGuiasByManifiesto_LN(nidManif);
            int cont = 0;
            for(int i = 0; i < guiasManifs.size(); i++){
                BeanTRGuia guia = guiasManifs.get(i);
                if(todo == 0){
                    if("2".equals(guia.getCConformidad()) && !guia.getCidGuia().equals(codUN+"-"+cidGuia)){//PENDIENTE
                        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores("000");
                        return beanError;
                    }else{
                        cont++;
                    }
                }else{
                    if("2".equals(guia.getCConformidad())){//PENDIENTE
                        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores("LUB-0017");
                        return beanError;
                    }else{
                        cont++;
                    }
                } 
            }
            if(cont == guiasManifs.size()){//TODAS SUS GUIAS ESTAN OK
                String err = "";
                if(todo == 0){
                    err = "LUB-0016";
                }else{
                    err = "000";
                }
                beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(err);
                return beanError;
            }
        }catch(Exception e){
            return null;
        }
        return beanError;
    }
    
    public int cantManifiestosByChofer(int nidChofer){
        return bdL_C_SFManifiestoLocal.cantManifiestosByChofer(nidChofer);
    }
    
    public int cantManifiestosByFlota(int nidFlota){
        return bdL_C_SFManifiestoLocal.cantManifiestosByFlota(nidFlota);
    }
}