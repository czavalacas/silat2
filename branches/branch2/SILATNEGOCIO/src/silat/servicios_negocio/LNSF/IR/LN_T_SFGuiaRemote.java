package silat.servicios_negocio.LNSF.IR;

import java.util.Date;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;

@Remote
public interface LN_T_SFGuiaRemote {
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
    String validarFechasEmiDesp(Date fecEmis,//max 1 semana antes y <= fecDesp
                                Date fecDesp);
    BeanTRGuia anularGuia(BeanTRGuia guia);
    BeanTRGuia registrarGuiaAnulada(String cidGuia,
                                    String codUn,
                                    Date fecEmis,
                                    Date fecDesp);
    String cambiarFechasGuiaPermiso56(String cidGuia,
                                      String codUn,
                                      Date fecEmis,
                                      Date fecDesp);
    void cambiarEstadoWebMovil(String cidGuia, String ruta, byte[] bytes, int valoracion , String comentario);
}
