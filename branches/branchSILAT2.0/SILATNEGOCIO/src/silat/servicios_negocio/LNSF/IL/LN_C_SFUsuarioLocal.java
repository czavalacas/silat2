package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanUsuario;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.entidades.admin.ADUsuario;

@Local
public interface LN_C_SFUsuarioLocal {
    BeanUsuarioAutenticado autenticarUsuario(String username, String clave/*,BigDecimal rol*/);
    List<BeanUsuarioAutenticado> getUsuarios();
    List<BeanUsuario> lstUsuariosNoAdmin();
    ADUsuario findADUsuarioById(BigDecimal id);
}