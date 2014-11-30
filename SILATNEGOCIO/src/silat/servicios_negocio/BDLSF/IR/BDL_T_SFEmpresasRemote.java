package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADParty;

@Remote
public interface BDL_T_SFEmpresasRemote {
    ADEmpresa persistADEmpresa(ADEmpresa ADEmpresa);

    ADEmpresa mergeADEmpresa(ADEmpresa ADEmpresa);

    void removeADEmpresa(ADEmpresa ADEmpresa);
    String registrarEmpresa_BDL(ADEmpresa adEmpresa,
                                           String[] cidUbigeo,
                                           String[] cDireccion,
                                           Integer[] nidTire,
                                           //String[] nidChof,
                                           String[] cNombChof,
                                           String[] cLicencia,
                                           String [] cMarcaFlota,
                                           String [] cPlacaFlota,
                                           String [] cConfFlota,
                                           String [] cDescripcionFlota,
                                           String [] cCerInsCripFlota);
    int borrarEmpresa(BigDecimal nidEmpresa);
}
