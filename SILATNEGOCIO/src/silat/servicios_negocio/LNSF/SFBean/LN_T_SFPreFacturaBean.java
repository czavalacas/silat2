package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

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

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFPreFacturaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFPreFacturaLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanItemPreFactura;
import silat.servicios_negocio.Beans.BeanPreFactura;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFGuiaLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFItemPreFacturaLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFPreFacturaLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFPreFacturaRemote;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TrItemPreFactura;
import silat.servicios_negocio.entidades.trans.TrPreFactura;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_T_SFPreFactura", mappedName = "mapLN_T_SFPreFactura")
public class LN_T_SFPreFacturaBean implements LN_T_SFPreFacturaRemote, 
                                              LN_T_SFPreFacturaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    @EJB
    private BDL_T_SFPreFacturaLocal bdL_T_SFPreFacturaLocal;
    @EJB
    private BDL_C_SFPreFacturaLocal bdL_C_SFPreFacturaLocal;
    @EJB
    private LN_C_SFItemPreFacturaLocal ln_C_SFItemPreFacturaLocal;
    @EJB
    private LN_C_SFGuiaLocal ln_C_SFGuiaLocal;
    
    public LN_T_SFPreFacturaBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanPreFactura registrarBeanPreFactura_LN(List<BeanItemPreFactura> lstItmsPreFactura,
                                                     Long nidCliente,
                                                     String codpedido,
                                                     String cliente){
        BeanError beanError = new BeanError();
        BeanPreFactura bPreFactura = new BeanPreFactura();
        TrPreFactura ePreFactura = new TrPreFactura();
        String error = "000";
        try{
            ePreFactura.setCEstreg("1");
            ePreFactura.setCodpedido(codpedido);
            ePreFactura.setFechaCreacion(new Date(System.currentTimeMillis()));
            ePreFactura.setFlgFactura("0");
            ePreFactura.setCliente(cliente);
            ePreFactura.setItemsPreFacturaLista(this.lstBeanToEntidad(lstItmsPreFactura, ePreFactura));
            ePreFactura.setNidCliente(nidCliente);
            ePreFactura.setPrefactTotal(new BigDecimal(0.0));
            ePreFactura = bdL_T_SFPreFacturaLocal.persistTrPreFactura(ePreFactura);
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0031";
        }finally{
            beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
            bPreFactura.setBeanError(beanError);
            return bPreFactura;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanPreFactura actualizarBeanPreFactura_LN(List<BeanItemPreFactura> lstItmsPreFactura,
                                                     String codpedido,
                                                     String cliente,
                                                     Long nidPreFact){
        BeanError beanError = new BeanError();
        BeanPreFactura bPreFactura = new BeanPreFactura();
        TrPreFactura ePreFactura = bdL_C_SFPreFacturaLocal.findTrPreFacturaById(nidPreFact);
        String error = "000";
        try{
            ePreFactura.setCodpedido(codpedido);
            ePreFactura.setCliente(cliente);
            List<TrItemPreFactura> itemsBefore = ePreFactura.getItemsPreFacturaLista();
            ePreFactura.setItemsPreFacturaLista(this.lstBeanToEntidad2(lstItmsPreFactura, ePreFactura));
            //ePreFactura.setGuiasToDelete(this.getGuiasActuales(itemsBefore));
            if(itemsBefore != null){
                if(itemsBefore.size() > 0){
                    ln_C_SFItemPreFacturaLocal.borrarItems(lstItmsPreFactura,itemsBefore);
                }
            }
            ePreFactura = bdL_T_SFPreFacturaLocal.mergeTrPreFactura(ePreFactura);
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0031";
        }finally{
            beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
            bPreFactura.setBeanError(beanError);
            return bPreFactura;
        }
    }
    
    public BeanPreFactura borrarPreFactura_LN(Long nidPreFact){
        BeanError beanError = new BeanError();
        BeanPreFactura bPreFactura = new BeanPreFactura();
        TrPreFactura ePreFactura = bdL_C_SFPreFacturaLocal.findTrPreFacturaById(nidPreFact);
        String error = "000";
        try {
            bdL_T_SFPreFacturaLocal.removeTrPreFactura(ePreFactura);
        } catch (Exception e) {
            e.printStackTrace();
            error = "LUB-0031";
        }finally{
            beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
            bPreFactura.setBeanError(beanError);
            return bPreFactura;
        }
    }
    
    public List<TrItemPreFactura> lstBeanToEntidad(List<BeanItemPreFactura> lstItmsPreFactura,
                                                   TrPreFactura ePreFactura){
        List<TrItemPreFactura> items = new ArrayList<TrItemPreFactura>();
        MapperIF mapper = new DozerBeanMapper();
        for(BeanItemPreFactura item : lstItmsPreFactura){
             TrItemPreFactura eItem = (TrItemPreFactura) mapper.map(item,TrItemPreFactura.class);
             eItem.setPreFactura(ePreFactura);
             items.add(eItem);
         }
        return items;
    }
    
    public List<TrItemPreFactura> lstBeanToEntidad2(List<BeanItemPreFactura> lstItmsPreFactura,
                                                    TrPreFactura ePreFactura){
        List<TrItemPreFactura> items = new ArrayList<TrItemPreFactura>();
        MapperIF mapper = new DozerBeanMapper();
        for(BeanItemPreFactura item : lstItmsPreFactura){
             TrItemPreFactura eItem = (TrItemPreFactura) mapper.map(item,TrItemPreFactura.class);
             eItem.setPreFactura(ePreFactura);
             eItem = this.setAttrParaAplicarNullAGuias(lstItmsPreFactura, eItem);
             items.add(eItem);
         }
        return items;
    }
    
    public TrItemPreFactura setAttrParaAplicarNullAGuias(List<BeanItemPreFactura> lstItmsPreFactura,
                                                         TrItemPreFactura eItem){
        for(BeanItemPreFactura itms : lstItmsPreFactura){
            if(itms.getNidPrefitm() != null){
                if(eItem.getNidPrefitm() != null){
                    if(itms.getNidPrefitm() == eItem.getNidPrefitm()){
                        List<String> bguia = new ArrayList<String>();
                        for(BeanItemPreFactura itmsGuia : lstItmsPreFactura){
                            for(BeanTRGuia gia : itmsGuia.getGuiasLista()){
                                bguia.add(gia.getCidGuia());
                            }
                        }
                         if(itms.getGuiasToNullList() != null){
                             Iterator iti = itms.getGuiasToNullList().iterator();
                             while(iti.hasNext()){//
                                 String strGuiaToNull = (String) iti.next();
                                 if(bguia.contains(strGuiaToNull)){
                                     itms.getGuiasToNullList().iterator().remove();
                                 }
                             }
                         }
                        String strGuiasToNullFinal = "";
                        if(itms.getGuiasToNullList() != null){
                            Iterator iti2 = itms.getGuiasToNullList().iterator();
                            while(iti2.hasNext()){//UtilsGeneral.depurar("iterando ahi ");
                                String strGuiaToNull = (String) iti2.next();
                                strGuiasToNullFinal = strGuiasToNullFinal.concat(strGuiaToNull);
                                if(itms.getGuiasToNullList().iterator().hasNext()){
                                    strGuiasToNullFinal = strGuiasToNullFinal.concat(",");
                                }
                            }
                            if(strGuiasToNullFinal.length() > 0){
                                if(strGuiasToNullFinal.lastIndexOf(",") + 1 == strGuiasToNullFinal.length()){//el ultimo caracter es una coma
                                    strGuiasToNullFinal = strGuiasToNullFinal.substring(0,strGuiasToNullFinal.length() - 1);
                                }
                                eItem.setFlgQuitarGuia("S");
                                eItem.setGuiasToNull(strGuiasToNullFinal);
                                return eItem;
                            }
                        }
                    }
                }
            }
        }
        return eItem;
    }
    
    public String getGuiasActuales(List<TrItemPreFactura> lstItms){
        StringBuilder guias = new StringBuilder();
        for(TrItemPreFactura itms : lstItms){
            for(TRGuia guia : itms.getGuiasList()){
                String codGuia = "";
                codGuia = guia.getCidUnidadNegocio()+"-"+guia.getCidGuia();
                guias.append(codGuia+",");
            }
        }
        String _final = guias.toString();
        if(_final.length() > 0){
            if(_final.lastIndexOf(",") + 1 == _final.length()){//el ultimo caracter es una coma
                _final = _final.substring(0,_final.length() - 1);
            }
        }
        return _final;
    }
}