package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.entidades.trans.TRManifiesto;

@Local
public interface BDL_C_SFManifiestoLocal {
    List<TRManifiesto> getTRManifiestoFindAll();
    TRManifiesto findTRManifiestoById(Integer id);
    List<BeanManifiesto> findManifiestosByAttributes(BeanManifiesto beanManifiesto);
    int cantManifiestosByChofer(int nidChofer);
    int cantManifiestosByFlota(int nidFlota);
    List<TRManifiesto> findManifiestobyEstadoporAsignar();
}
