package silat.servicios_negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFItemLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFItemLocal;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.LNSF.IL.LN_C_SFUtilsLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFItemLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFItemRemote;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRItem;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_T_SFItem", mappedName = "mapLN_T_SFItem")
public class LN_T_SFItemBean implements LN_T_SFItemRemote, 
                                        LN_T_SFItemLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private LN_C_SFUtilsLocal LN_C_SFUtilsLocal;
    @EJB
    private BDL_T_SFItemLocal bdL_T_SFItemLocal;
    @EJB
    private BDL_C_SFItemLocal bdL_C_SFItemLocal;
    
    public LN_T_SFItemBean() {
    }
    
    public String registrarItem_LN(List<BeanTRItem> lstItems,TRGuia eGuia){
        try{
            List<TRItem> eItems = LN_C_SFUtilsLocal.beanItemToEntity(lstItems,eGuia);
            for(TRItem eItem : eItems){
                eItem.setCCidGuiaRemitente(eGuia.getCidGuia());
                eItem.setTrGuia(eGuia);
                eItem = bdL_T_SFItemLocal.persistTRItem(eItem);
            }
            return "000";
        }catch(Exception e){
            return "LUB-0013";
        }
    }
    
    public void borrarItems(List<BeanTRItem> lstItems,List<TRItem> lstBD){
        for(TRItem item : lstBD){
            item = bdL_C_SFItemLocal.findTRItemtById(item.getNidItem());
            if(!contieneItem(item,lstItems)){
                bdL_T_SFItemLocal.removeTRItem(item);
            }
        }
    }
    
    public boolean contieneItem(TRItem eIteam,List<BeanTRItem> lstItem){
        for(BeanTRItem beanItem : lstItem){
            if(UtilsGeneral.comparaNidItems(eIteam,beanItem,"nidItem")){
                return true;
            }
        }
        return false;
    }
}