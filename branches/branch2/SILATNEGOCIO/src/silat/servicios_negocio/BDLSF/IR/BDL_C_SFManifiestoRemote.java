package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.entidades.trans.TRManifiesto;

@Remote
public interface BDL_C_SFManifiestoRemote {
    List<TRManifiesto> getTRManifiestoFindAll();
    TRManifiesto findTRManifiestoById(Integer id);
    List<BeanManifiesto> findManifiestosByAttributes(BeanManifiesto beanManifiesto);
    int cantManifiestosByChofer(int nidChofer);
    int cantManifiestosByFlota(int nidFlota);
    List<TRManifiesto> findManifiestobyEstadoporAsignar();
}
