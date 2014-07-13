package silat.servicios_negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_C_SFCodigoLocal {
    String validarNuevoCodigo(String cidUnin,
                              String tipDoc,
                              String codigo);
    int isCodigoExistente(String cidUnin,
                          String tipDoc,
                          String codigo);
}
