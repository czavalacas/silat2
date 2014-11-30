package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADParty;

@Local
public interface BDL_T_SFEmpresasLocal {
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
