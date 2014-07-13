package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFPreFacturaLocal;
import silat.servicios_negocio.Beans.BeanItemPreFactura;
import silat.servicios_negocio.Beans.BeanPreFactura;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.LNSF.IL.LN_C_SFEmpresasLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFGuiaLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFItemPreFacturaLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFPreFacturaLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFPreFacturaRemote;
import silat.servicios_negocio.entidades.trans.TrItemPreFactura;
import silat.servicios_negocio.entidades.trans.TrPreFactura;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_C_SFPreFactura", mappedName = "mapLN_C_SFPreFactura")
public class LN_C_SFPreFacturaBean implements LN_C_SFPreFacturaRemote,
                                              LN_C_SFPreFacturaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFPreFacturaLocal bdL_C_SFPreFacturaLocal;
    @EJB
    private LN_C_SFEmpresasLocal ln_C_SFEmpresasLocal;
    @EJB
    private LN_C_SFItemPreFacturaLocal ln_C_SFItemPreFacturaLocal;
    
    public LN_C_SFPreFacturaBean() {
    }
    
    public List<BeanPreFactura> findBeanPreFacturaByAttr_LN(String codpedido,
                                                            String flgFactura,
                                                            Date fecMin,
                                                            Date fecMax,
                                                            String cliente,
                                                            String cidguia,
                                                            Long nidPrefact){
        try{
            BeanPreFactura beanPreFactura = new BeanPreFactura();
            beanPreFactura.setCidGuia(cidguia);
            beanPreFactura.setCliente(cliente);
            beanPreFactura.setCodpedido(codpedido);
            beanPreFactura.setFechaCreacionMAX(fecMax);
            beanPreFactura.setFechaCreacionMIN(fecMin);
            beanPreFactura.setFlgFactura(flgFactura);
            beanPreFactura.setNidPrefact(nidPrefact);
            return this.getListaTransformed(bdL_C_SFPreFacturaLocal.findTrPreFacturaByAttributes_BD(beanPreFactura));
        }catch(Exception e){
            return new ArrayList<BeanPreFactura>();
        }
    }
    
    public String getCodigoPedidoByPreFactura(Long nidPref){
        TrPreFactura pf = bdL_C_SFPreFacturaLocal.findTrPreFacturaById(nidPref);
        return pf.getCodpedido();
    }
    
    public List<BeanPreFactura> getListaTransformed(List<TrPreFactura> lstTrPreFactura){
        try{
            MapperIF mapper = new DozerBeanMapper();
            List<BeanPreFactura> lstReturn = new ArrayList<BeanPreFactura>();
            BeanPreFactura bPF = new BeanPreFactura();
            for(TrPreFactura pfs : lstTrPreFactura){
                bPF = (BeanPreFactura) mapper.map(pfs,BeanPreFactura.class);
                bPF.setEmpresa(ln_C_SFEmpresasLocal.selectedEmpresa(new BigDecimal(bPF.getNidCliente())));
                String vec[] = this.getGuiasToReporte(bPF.getNidPrefact());
                bPF.setGuiasToReporte(vec[0]);
                bPF.setOrdServ(vec[1]);
                bPF.setGuiasToTrigger(vec[2]);
                //bPF.setItemsPreFacturaList(this.getListaBeanItemPreFactura(pfs.getItemsPreFacturaLista()));
                lstReturn.add(bPF);
            }
            return lstReturn;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanPreFactura>();
        }
    }
    
    public String[] getGuiasToReporte(Long nidPreFactura){
        List<BeanItemPreFactura> lstItms = ln_C_SFItemPreFacturaLocal.findBeanItemPreFactura_LN(nidPreFactura);
        StringBuilder _final = new StringBuilder();
        StringBuilder _finalOS = new StringBuilder();
        StringBuilder _guias4Trigger = new StringBuilder();
        String returnar[] = new String[3];
        int c = 0;
        List<String> lstUns = new ArrayList<String>();
        List<Integer> lstOS = new ArrayList<Integer>();
        for(BeanItemPreFactura item : lstItms){
            for(BeanTRGuia g : item.getGuiasLista()){
                if(!lstOS.contains(g.getOrdenServicio().getNidOrdnServ())){
                    _finalOS.append(g.getOrdenServicio().getNidOrdnServ()+",");  
                    lstOS.add(g.getOrdenServicio().getNidOrdnServ());
                }
                if(c == 0){
                    lstUns.add(g.getCidUnidadNegocio());
                    _final.append(g.getCidUnidadNegocio()+"-");
                }
                if(!lstUns.contains(g.getCidUnidadNegocio())){
                    lstUns.add(g.getCidUnidadNegocio());
                    _final.append(g.getCidUnidadNegocio()+"-");
                }
                _final.append(this.getFinalCidGuia(g)+",");
                _guias4Trigger.append(g.getCidGuia()+",");
                c++;
            }
        }
        String _strFinal = _final.toString();
        String _strFinalOS = _finalOS.toString();
        String __guias4Trigger = _guias4Trigger.toString();
        _strFinal = UtilsGeneral.quitarUltimoCaracter(_strFinal,",");
        _strFinalOS = UtilsGeneral.quitarUltimoCaracter(_strFinalOS,",");
        __guias4Trigger = UtilsGeneral.quitarUltimoCaracter(__guias4Trigger,",");
        returnar[0] = _strFinal;
        returnar[1] = _strFinalOS;
        returnar[2] = __guias4Trigger;
        return returnar;
    }
    
    public String getFinalCidGuia(BeanTRGuia g){
        String onlyCidGuia = g.getCidGuia().substring(g.getCidGuia().indexOf("-")+1,g.getCidGuia().length());
        String cidFin = "";
        for(int i = 0; i < onlyCidGuia.length(); i++){
            String strCharacter = String.valueOf(onlyCidGuia.charAt(i));
            if(!strCharacter.equals("0")){
                cidFin = onlyCidGuia.substring(i,onlyCidGuia.length());
                return cidFin;
            }
        }
        return "";
    }
    
    public BigDecimal[] call_Procedure_GET_PREFACTURA_MONTOS_LN(Long nidPF){
        return bdL_C_SFPreFacturaLocal.call_Procedure_GET_PREFACTURA_MONTOS(nidPF);
    }
}