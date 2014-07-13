package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import java.util.Date;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.entidades.admin.ADGasto;

@Local
public interface LN_T_SFGastosLocal {
    BeanGasto insertarGasto(Integer nidTipoGasto,
                                     Integer nidModoPago,
                                     BigDecimal monto,
                                     Date fechaGasto,
                                     Integer nidFlota,
                                     Long nidProveedor,
                                     String cidFactura,
                                     String destino,
                                     BigDecimal nidServBasico,
                                     BigDecimal tipoCombustible,
                                     Integer cantPersonas,
                                     BigDecimal tipoMantenimiento,
                                     String numCheque,
                                     String rutaImagen,
                                     byte[] imagenFisica,
                                     String detalle,
                                     String banco,
                                    String estado);
  //  ADGasto anularGasto(Long nidGasto, String estado);
    BeanGasto anularGasto(Long nidGasto, String estado);
}
