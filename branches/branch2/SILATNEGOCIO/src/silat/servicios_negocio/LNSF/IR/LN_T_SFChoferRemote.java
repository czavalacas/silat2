package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADChofer;

@Remote
public interface LN_T_SFChoferRemote {
    ADChofer guardarChofer(BigDecimal nidParty, String nombre, String licencia);
    ADChofer actualizarChofer(BigDecimal nidParty, Integer nidChofer, String nombres, String licencia);
    void removerChofer(Integer nidChofer);
}
