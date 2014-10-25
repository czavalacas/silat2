package silat.servicios_negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanTrItemXOrds;

@Local
public interface LN_C_SFItemOrdenServLocal {
    List<BeanTrItemXOrds> getListaItemsBynidOrdS(int nidOrds);
}
