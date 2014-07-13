package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.Beans.BeanNota;
import silat.servicios_negocio.entidades.trans.TRNota;

@Remote
public interface LN_T_SFNotaRemote {

    BeanNota registrarNota(String cTipoNota,
                           BigDecimal dMonto,
                           BeanFactura trFactura);
}
