package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import java.util.Map;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADUsuario;

@Local
public interface BDL_C_SFUsuarioLocal {
    List<ADUsuario> getADUsuarioFindAll();
    Map autenticarUsuario(String username, String clave/*, BigDecimal rol*/);
    List<ADUsuario> getUsuariosNoAdmin();
    int countNombreUsuario(String nombreUsuario);
    ADUsuario findADUsuarioById(BigDecimal id);
}