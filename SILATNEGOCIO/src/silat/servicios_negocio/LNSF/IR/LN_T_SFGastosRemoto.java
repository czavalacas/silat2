package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import java.util.Date;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.entidades.admin.ADGasto;

@Remote
public interface LN_T_SFGastosRemoto {
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
