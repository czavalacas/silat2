package silat.servicios_negocio.LNSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanCodigo;

@Remote
public interface LN_T_SFCodigoRemote {
    BeanCodigo actualizarCodigo(String cidUnin,
                                String codigo,
                                String tipDoc);
    BeanCodigo actualizarCodigoManif(String codigo);
}
