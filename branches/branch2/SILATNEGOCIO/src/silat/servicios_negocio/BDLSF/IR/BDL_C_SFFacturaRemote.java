package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.entidades.trans.TRFactura;

@Remote
public interface BDL_C_SFFacturaRemote {
    List<TRFactura> getTRFacturaFindAll();
    TRFactura findTRFacturaById(Long id);
    List<BeanFactura> findFacturasByAttr_BD(BeanFactura fac);
    int cantNotaByFactura(Long nidFactura);
}
