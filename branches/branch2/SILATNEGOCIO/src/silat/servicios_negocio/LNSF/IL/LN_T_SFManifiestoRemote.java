package silat.servicios_negocio.LNSF.IL;

import java.util.Date;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanManifiesto;

@Remote
public interface LN_T_SFManifiestoRemote {
    
    BeanManifiesto registrarManifiesto_LN(int nidEmpProvTrans,
                                          Date fecManif,
                                          Double fletePactado,
                                          Double adelanto,
                                          String tipDoc,
                                          String observ,
                                          int nidFlota,
                                          int nidChofer,
                                          int nidManif,
                                          int estadoManif,
                                          String estadoManifiestoNegocio);
    
    void cambiarEstadoManifiesto(int nidManif, String estadoManif);
    void depurar(Object o);
    BeanManifiesto anularManifiesto(int nidManif);
    String cambiarFechaManifiestoPermiso56(int nidManif,Date fechaManif);
    
    String actualizarManifiesto(String observ, double fPactado,double nAdelanto, int nidMan, int nidFlota, int nidChof);
}
