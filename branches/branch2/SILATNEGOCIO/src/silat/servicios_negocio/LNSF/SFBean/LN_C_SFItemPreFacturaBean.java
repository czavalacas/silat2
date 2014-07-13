package silat.servicios_negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFItemPreFacturaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFGuiaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFItemPreFacturaLocal;
import silat.servicios_negocio.Beans.BeanItemPreFactura;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.LNSF.IL.LN_C_SFGuiaLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFItemPreFacturaLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFItemPreFacturaRemote;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRItem;
import silat.servicios_negocio.entidades.trans.TrItemPreFactura;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_C_SFItemPreFactura", mappedName = "mapLN_C_SFItemPreFactura")
public class LN_C_SFItemPreFacturaBean implements LN_C_SFItemPreFacturaRemote, 
                                                  LN_C_SFItemPreFacturaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFItemPreFacturaLocal bdL_C_SFItemPreFacturaLocal;
    @EJB
    private LN_C_SFGuiaLocal ln_C_SFGuiaLocal;
    @EJB 
    private BDL_T_SFItemPreFacturaLocal bdL_T_SFItemPreFacturaLocal;

    public LN_C_SFItemPreFacturaBean() {
    }
    
    public List<BeanItemPreFactura> findBeanItemPreFactura_LN(Long nidPF){
        try{
            return this.getListaBeanItemPreFactura(bdL_C_SFItemPreFacturaLocal.getTrItemPreFacturaByPreFactura_BD(nidPF));
        }catch(Exception e){
            return new ArrayList<BeanItemPreFactura>();
        }
    }
    
    public List<BeanItemPreFactura> getListaBeanItemPreFactura(List<TrItemPreFactura> itemsPreFacturaLista){
        try{
            MapperIF mapper = new DozerBeanMapper();
            List<BeanItemPreFactura> lstReturn = new ArrayList<BeanItemPreFactura>();
            BeanItemPreFactura bPFi = new BeanItemPreFactura();
            for(TrItemPreFactura itms : itemsPreFacturaLista){
                bPFi = (BeanItemPreFactura) mapper.map(itms,BeanItemPreFactura.class);
                bPFi.setGuiasLista(ln_C_SFGuiaLocal.getListaGuias_PorFacturar(itms.getGuiasList()));
                StringBuilder itemsGuia = new StringBuilder();
                int cantItms = 0;
                int j = 1;
                for(BeanTRGuia guia : bPFi.getGuiasLista()){
                    int i = 1;
                    for(BeanTRItem item : guia.getItemsLista()){
                        cantItms++;
                        String cidGRemi = item.getCCidGuiaRemitente() == null ? "" : item.getCCidGuiaRemitente();
                        itemsGuia.append(item.getNCantidad() + " - " + item.getCUndMedida() + " - " + item.getCDescItem() + " - " + cidGRemi);
                        if(i < guia.getItemsLista().size()){
                            itemsGuia.append("\n");
                        }
                        i++;
                    }
                    if(j < bPFi.getGuiasLista().size()){
                        itemsGuia.append("\n");
                    }
                }
                cantItms++;
                bPFi.setCantItmsTotal(cantItms);
                bPFi.setFlgFromBD("1");
                bPFi.setGuiasItmsConcat(itemsGuia.toString());
                lstReturn.add(bPFi);
            }
            return lstReturn; 
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanItemPreFactura>();
        }
    }
    
    public void borrarItems(List<BeanItemPreFactura> lstNew,List<TrItemPreFactura> lstBD){
        for(TrItemPreFactura item : lstBD){
            if(!contieneItem(item,lstNew)){
                bdL_T_SFItemPreFacturaLocal.removeTrItemPreFactura(item);
            }
        }
    }
    
    public boolean contieneItem(TrItemPreFactura eIteam,List<BeanItemPreFactura> lstItem){
        for(BeanItemPreFactura beanItem : lstItem){
            if(UtilsGeneral.comparaNidItems(eIteam,beanItem,"nidPrefitm")){
                return true;
            }
        }
        return false;
    }
}