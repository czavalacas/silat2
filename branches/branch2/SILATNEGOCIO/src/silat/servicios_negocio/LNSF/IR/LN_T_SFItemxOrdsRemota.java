package silat.servicios_negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFItemxOrdsRemota {
    void cambiarEstadoItemOrds(String nidItem);
}
