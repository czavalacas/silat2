package silat.servicios_negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanTrItemXOrds;

@Remote
public interface LN_C_SFItemOrdenServRemote {
    List<BeanTrItemXOrds> getListaItemsBynidOrdS(int nidOrds);
}
