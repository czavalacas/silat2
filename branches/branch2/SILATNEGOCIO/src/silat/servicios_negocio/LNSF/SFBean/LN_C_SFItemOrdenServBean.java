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
    public List<BeanTrItemXOrds> getListaItemsBynidOrdS(int nidOrds){
        List<BeanTrItemXOrds> listaItms=new ArrayList<BeanTrItemXOrds>();
        List<TRItemXOrds> lisEntida=bdl_C_SFItemXOrdsLocal.getTrItemOrdenServicio_BD(nidOrds);
        for(TRItemXOrds entida : lisEntida ){
            BeanTrItemXOrds bean=new BeanTrItemXOrds();
            bean.setOrden(entida.getOrden());
            bean.setNCantidad(entida.getNCantidad());
            bean.setDPeso(entida.getDPeso());
            bean.setCUndMedida(entida.getCUndMedida());
            bean.setCDescItem(entida.getCDescItem());
            bean.setCCidGuiaRemitente(entida.getCCidGuiaRemitente()); 
            listaItms.add(bean);
        }
        return listaItms;
    }
}
