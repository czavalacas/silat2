package silat.servicios_negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_T_SFItemxOrdsLocal {
    void cambiarEstadoItemOrds(String nidItem);
}
