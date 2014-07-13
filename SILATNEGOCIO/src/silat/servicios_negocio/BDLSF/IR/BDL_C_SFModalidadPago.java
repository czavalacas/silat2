package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADModalidadPago;

@Remote
public interface BDL_C_SFModalidadPago {
    List<ADModalidadPago> getAdmmopaFindAll();
}
