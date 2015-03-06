package silat.servicios_negocio.LNSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanCodigo;

@Local
public interface LN_T_SFCodigoLocal {
    BeanCodigo actualizarCodigo(String cidUnin,
                                String codigo,
                                String tipDoc);
    BeanCodigo actualizarCodigoManif(String codigo);
}
