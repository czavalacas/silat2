package silat.servicios_negocio.LNSF.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanCodigo;
import silat.servicios_negocio.Beans.BeanCombo;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRItem;

@Local
public interface LN_C_SFUtilsLocal {
    
    String generarCorrelativoLN(String entidad, 
                                String atributo, 
                                int tamano,
                                String cidUN);
    BeanError registrarGuia(List<BeanTRItem> lstItems);
    List<TRItem> beanItemToEntity(List<BeanTRItem> lstItems,TRGuia eGuia);
    List<BeanConstraint> getListADMCONS(String nombreTabla, String nombreCampo);
    List<BeanCombo> getListaParaCombo_LN(String nombreEntidad, String nombreCampo, String idCampo);
    List<BeanCodigo> getCodigos();
    int traerSiguienteValorSequence(String sequence);
}