package silat.servicios_negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRItem;

@Remote
public interface LN_T_SFItemRemote {
    String registrarItem_LN(List<BeanTRItem> lstItems,TRGuia eGuia);
    void borrarItems(List<BeanTRItem> lstItems,List<TRItem> lstBD);
}
