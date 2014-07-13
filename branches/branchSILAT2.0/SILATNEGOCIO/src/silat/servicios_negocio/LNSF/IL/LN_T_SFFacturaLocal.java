package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import java.util.Date;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanFactura;

@Local
public interface LN_T_SFFacturaLocal {
    
    BeanFactura registrarFactura_LN(String tipoFactura,
                                    Date fechaFactura,
                                    BigDecimal subTotal,
                                    int estadoFactura,
                                    String cidSerie,
                                    String cidRepo,
                                    String guias,
                                    BigDecimal nidParty,
                                    String cliente,
                                    String ruc,
                                    String nidOServ,
                                    String direccion,
                                    String tipFactura,
                                    Long nidPreFactura,
                                    String guiasForReporte,
                                    boolean isEditable,
                                    String contenido);
    BeanFactura anularFactura_LN(String comentarioAnular,
                                 Long nidFactura);
    BeanFactura pagarFactura_LN(Long nidFactura);
    public BeanFactura actualizarFactura_LN(String detalle,
                                            Date fechaFactura,
                                            Long nidFactura,
                                            boolean editarFecha);
}
