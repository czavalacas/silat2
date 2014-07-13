package silat.servicios_negocio.LNSF.IR;

import java.util.Date;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanManifiesto;

@Local
public interface LN_T_SFManifiestoLocal {
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
}
