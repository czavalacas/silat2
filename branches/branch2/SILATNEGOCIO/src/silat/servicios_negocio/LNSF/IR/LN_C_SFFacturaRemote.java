package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanFactura;

@Remote
public interface LN_C_SFFacturaRemote {
    List<BeanFactura> findFacturasByAttr_LN(Date fecMin,
                                            Date fecMax,
                                            BigDecimal subTotal,
                                            BigDecimal  total,
                                            int estado,
                                            String codFact,
                                            String cidUN,
                                            String guias,
                                            String cliente,
                                            String simboloSubTotal,
                                            String simboloTotal, 
                                            String busqTipNota,
                                            String simbNota, 
                                            BigDecimal busqMontoNota);
    int cantNotaByFactura_LN(Long nidFactura);
}
