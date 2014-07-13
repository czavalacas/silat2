package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanUsuario;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.entidades.admin.ADUsuario;

@Remote
public interface LN_C_SFUsuarioRemote {
    BeanUsuarioAutenticado autenticarUsuario(String username, String clave/*,BigDecimal rol*/);
    List<BeanUsuarioAutenticado> getUsuarios();
    List<BeanUsuario> lstUsuariosNoAdmin();
    ADUsuario findADUsuarioById(BigDecimal id);
}