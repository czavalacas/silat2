package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import java.util.Map;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADUsuario;

@Remote
public interface BDL_C_SFUsuarioRemote {
    List<ADUsuario> getADUsuarioFindAll();
    Map autenticarUsuario(String username, String clave);
    List<ADUsuario> getUsuariosNoAdmin();
    int countNombreUsuario(String nombreUsuario);
    ADUsuario findADUsuarioById(BigDecimal id);
}