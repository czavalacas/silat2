package silat.servicios_negocio.LNSF.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanManifiesto;

@Local
public interface LN_C_SFManifiestoLocal {
    List<BeanManifiesto> findManifiestosByAttr_LN(Date fecMin,
                                                  Date fecMax,
                                                  Integer nidManif,
                                                  Integer nidEmpProv,
                                                  String razonSociall,
                                                  String estadoNegoManif);
    List<BeanManifiesto> _findManifiestosByAttr_LN(Date fecMin,
                                                   Date fecMax,
                                                   Integer nidManif,
                                                   Integer nidEmpProv,
                                                   String razonSocial,
                                                   Double flete,
                                                   Double adelanto,
                                                   String simboloFLete,
                                                   String simboloAdela,
                                                   String observ,
                                                   String estManif,
                                                   Integer nEstManifiesto);
    BeanError guiasOK(int nidManif, String codUN, String cidGuia,int todo);
    int cantManifiestosByChofer(int nidChofer);
    int cantManifiestosByFlota(int nidFlota);
    public List<BeanManifiesto> findManifiestoXPagar();
    List<BeanManifiesto> getListaManifsSinAsignar();
}
