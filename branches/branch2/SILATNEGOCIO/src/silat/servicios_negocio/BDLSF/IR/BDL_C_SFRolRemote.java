package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.audsis.STRol;

@Remote
public interface BDL_C_SFRolRemote {
    List<STRol> getSTRolFindAll();
    List<STRol> getRolesActivos();
    List<STRol> getRolesActivosNoAdmin();
    STRol findSTRolById(BigDecimal id);
}