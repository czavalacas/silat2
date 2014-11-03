package silat.servicios_negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFItemXOrdsLocal;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanTrItemXOrds;
import silat.servicios_negocio.LNSF.IL.LN_C_SFItemOrdenServLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFItemOrdenServRemote;
import silat.servicios_negocio.entidades.trans.TRItemXOrds;

@Stateless(name = "LN_C_SFItemOrdenServ", mappedName = "map-LN_C_SFItemOrdenServ")
public class LN_C_SFItemOrdenServBean implements LN_C_SFItemOrdenServRemote, 
                                                 LN_C_SFItemOrdenServLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFItemXOrdsLocal bdl_C_SFItemXOrdsLocal;
    public LN_C_SFItemOrdenServBean() {
    }
    public List<BeanTRItem> getListaItemsBynidOrdS(int nidOrds){
        List<BeanTRItem> listaItms=new ArrayList<BeanTRItem>();
        List<TRItemXOrds> lisEntida=bdl_C_SFItemXOrdsLocal.getTrItemOrdenServicio_BD(nidOrds);
     //   for(TRItemXOrds entida : lisEntida ){
        if(lisEntida!=null){  
            for(int i=0; i<lisEntida.size(); i++){
            BeanTRItem bean=new BeanTRItem();                
            bean.setOrden(i+1);
            bean.setNCantidad(lisEntida.get(i).getNCantidad());
            bean.setDPeso(lisEntida.get(i).getDPeso());
            bean.setCUndMedida(lisEntida.get(i).getCUndMedida());
            bean.setCDescItem(lisEntida.get(i).getCDescItem());
            bean.setCCidGuiaRemitente(lisEntida.get(i).getCCidGuiaRemitente()); 
            listaItms.add(bean);
       // }
            }
        }
        return listaItms;
    }
}
