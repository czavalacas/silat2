package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.entidades.trans.TRFactura;

@Local
public interface BDL_C_SFFacturaLocal {
    List<TRFactura> getTRFacturaFindAll();
    TRFactura findTRFacturaById(Long id);
    List<BeanFactura> findFacturasByAttr_BD(BeanFactura fac);
    int cantNotaByFactura(Long nidFactura);
}
