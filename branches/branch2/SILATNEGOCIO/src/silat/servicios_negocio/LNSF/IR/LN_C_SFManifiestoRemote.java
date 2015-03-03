package silat.servicios_negocio.LNSF.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanManifiesto;

@Remote
public interface LN_C_SFManifiestoRemote {
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
    int existeManif(int nidManifiesto);
}
