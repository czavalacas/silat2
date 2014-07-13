package silat.servicios_negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_C_SFCodigoRemote {
    String validarNuevoCodigo(String cidUnin,
                              String tipDoc,
                              String codigo);
    int isCodigoExistente(String cidUnin,
                          String tipDoc,
                          String codigo);
}
