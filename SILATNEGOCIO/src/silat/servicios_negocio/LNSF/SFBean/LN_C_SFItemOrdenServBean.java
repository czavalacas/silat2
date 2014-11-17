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
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanTrItemXOrds;
import silat.servicios_negocio.LNSF.IL.LN_C_SFItemOrdenServLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFItemOrdenServRemote;
import silat.servicios_negocio.entidades.trans.TRItemXOrds;

@Stateless(name = "LN_C_SFItemOrdenServ", mappedName = "map-LN_C_SFItemOrdenServ")
/**czavalacas 10.11.2014*/
public class LN_C_SFItemOrdenServBean implements LN_C_SFItemOrdenServRemote, 
                                                 LN_C_SFItemOrdenServLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFItemXOrdsLocal bdl_C_SFItemXOrdsLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdl_C_SFUtilsLocal;
    public LN_C_SFItemOrdenServBean() {
    }
    public List<BeanTRItem> getListaItemsBynidOrdS(int nidOrds, int opc){
        List<BeanTRItem> listaItms=new ArrayList<BeanTRItem>();
        System.out.println("OPCION FIRST ::::: " +opc);
        List<TRItemXOrds> lisEntida=bdl_C_SFItemXOrdsLocal.getTrItemOrdenServicio_BD(nidOrds, opc);     
        if(lisEntida!=null){  
            for(int i=0; i<lisEntida.size(); i++){
            BeanTRItem bean=new BeanTRItem();                
            bean.setOrden(i+1);
            bean.setNCantidad(lisEntida.get(i).getNCantidad());
            bean.setDPeso(lisEntida.get(i).getDPeso());
            bean.setCUndMedida(lisEntida.get(i).getCUndMedida());
            bean.setCDescItem(lisEntida.get(i).getCDescItem());
            bean.setCCidGuiaRemitente(lisEntida.get(i).getCCidGuiaRemitente()); 
            bean.setNidItem(lisEntida.get(i).getNidItem());
            BeanConstraint cons=bdl_C_SFUtilsLocal.getCatalogoConstraints("C_ESTADO", "TRDITEMXORDS", lisEntida.get(i).getCEstado());
            bean.setDescEstado(cons.getCDescrip());
            bean.setCidGuia(lisEntida.get(i).getCidGuia());            
            listaItms.add(bean);   
            }
        }
        return listaItms;
    }
}
