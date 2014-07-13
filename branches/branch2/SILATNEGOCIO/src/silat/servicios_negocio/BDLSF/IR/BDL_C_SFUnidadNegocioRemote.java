package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADUnidadNegocio;

@Remote
public interface BDL_C_SFUnidadNegocioRemote {
    List<ADUnidadNegocio> getADUnidadNegocioFindAll();
}
