package silat.servicios_negocio.LNSF.IL;

import java.util.Date;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;

@Local
public interface LN_T_SFGuiaLocal {
    BeanTRGuia registrarGuia_LN(String cidGuia,
                                int numPaquetes,
                                String obs,
                                String conf,
                                String estGuia,
                                Date fecEmis,
                                Date fecDesp,
                                int nidRemitente,
                                int nidOS,
                                int nidManif,
                                int nidFlota,
                                int nidChofer,
                                int nidDirecRemi,
                                int nidDirecDest,
                                int opc,
                                List<BeanTRItem> lstItems,
                                String codUn,
                                String estadoManif,
                                String imgGuiaProv,                                       
                                boolean cerrarOS,
                                boolean enTransManif);
    BeanTRGuia registrarGuiaAnulada(String cidGuia,
                                    String codUn,
                                    Date fecEmis,
                                    Date fecDesp);
    String validarFechasEmiDesp(Date fecEmis,//max 1 semana antes y <= fecDesp
                                Date fecDesp);
    BeanTRGuia anularGuia(BeanTRGuia guia);
    String cambiarFechasGuiaPermiso56(String cidGuia,
                                      String codUn,
                                      Date fecEmis,
                                      Date fecDesp);
    
}
