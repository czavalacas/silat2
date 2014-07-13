package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.Beans.BeanNota;
import silat.servicios_negocio.entidades.trans.TRNota;

@Local
public interface LN_T_SFNotaLocal {

    BeanNota registrarNota(String cTipoNota, BigDecimal dMonto, BeanFactura trFactura);
}
