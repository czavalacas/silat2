package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanFactura;

@Local
public interface LN_C_SFFacturaLocal {
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
    List<BeanFactura> findFacturaByAttr_LN(BeanFactura bean);
}
