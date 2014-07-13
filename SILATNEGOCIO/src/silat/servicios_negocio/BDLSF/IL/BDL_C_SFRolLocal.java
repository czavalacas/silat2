package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.audsis.STRol;

@Local
public interface BDL_C_SFRolLocal {
    List<STRol> getSTRolFindAll();
    List<STRol> getRolesActivos();
    List<STRol> getRolesActivosNoAdmin();
    STRol findSTRolById(BigDecimal id);
}