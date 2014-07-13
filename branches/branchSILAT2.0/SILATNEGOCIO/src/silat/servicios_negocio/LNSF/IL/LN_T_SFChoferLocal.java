package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADChofer;

@Local
public interface LN_T_SFChoferLocal {
    ADChofer guardarChofer(BigDecimal nidParty, String nombre, String licencia);
    ADChofer actualizarChofer(BigDecimal nidParty, Integer nidChofer, String nombres, String licencia);
    void removerChofer(Integer nidChofer);
}
