package silat.servicios_negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRItem;

@Local
public interface LN_T_SFItemLocal {
    String registrarItem_LN(List<BeanTRItem> lstItems,TRGuia eGuia);
    void borrarItems(List<BeanTRItem> lstItems,List<TRItem> lstBD);
}
