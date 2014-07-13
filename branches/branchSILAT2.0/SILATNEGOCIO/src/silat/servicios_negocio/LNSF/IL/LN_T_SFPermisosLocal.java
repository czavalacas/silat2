package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanPermisos;

@Local
public interface LN_T_SFPermisosLocal {
    BeanPermisos actualizarPermisos(BigDecimal nidUsuario,BigDecimal nidRole,BigDecimal nidPermiso);
    void removerPermisos(BigDecimal nidUsuario);
}
