package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

@Local
public interface LN_C_SFClaveLocal {
    BigDecimal claveDeUsuarioEnSesion(BigDecimal nidUsuario);
}
