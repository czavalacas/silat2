package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanPermisos;

@Remote
public interface LN_T_SFPermisosRemoto {
    BeanPermisos actualizarPermisos(BigDecimal nidUsuario,BigDecimal nidRole,BigDecimal nidPermiso);
    void removerPermisos(BigDecimal nidUsuario);
}
