package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

@Remote
public interface LN_C_SFClaveRemoto {
    BigDecimal claveDeUsuarioEnSesion(BigDecimal nidUsuario);
}
