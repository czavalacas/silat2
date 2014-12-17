package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.sql.SQLException;

import java.util.HashMap;

import java.util.List;

import javax.ejb.Remote;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.Beans.BeanADUbigeo;
import silat.servicios_negocio.Beans.BeanCodigo;
import silat.servicios_negocio.Beans.BeanCombo;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanReportePrevio;
import silat.servicios_negocio.entidades.admin.ADConstraint;
import silat.servicios_negocio.entidades.admin.ADConstraintPK;
import silat.servicios_negocio.entidades.admin.ADFlota;
import silat.servicios_negocio.entidades.admin.ADUbigeo;
import silat.servicios_negocio.entidades.admin.ADUtil;
import silat.servicios_negocio.entidades.trans.TRFactura;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.Trmcodi;

@Remote
public interface BDL_C_SFUtilsRemote {
    List<ADUtil> getUtilPorTipoObjeto(BigDecimal tipobj);
    List<ADUtil> getAdutilFindAll();
    BigDecimal call_Function_validar_usuario(String usuario, String clave,String tipoKey);
    HashMap generarCorrelativo(String entidad,String atributo, int tamano,String cidUN);
    BeanConstraint getCatalogoConstraints(String cCampo, 
                                          String cNombreTabla, 
                                          String cValorCampo);
    ADConstraint findConstraintById(ADConstraintPK id);
    List<BeanADUbigeo> getUbigeos(int tipUbigeo, 
                                  String dep, 
                                  String prov);
    BeanADUbigeo getUbigeoByCid(String cidUbigeo);
    List<BeanConstraint> getListConstraints(String cCampo, 
                                            String cNombreTabla);   
    int grabarItemReporte(List<BeanReportePrevio> lstReportItem);
    void removerItmsRepo(String cidRepo);
    String call_Procedure_GET_COD_FACTURA_BY_UN(String cidUn);
    List<TRGuia> actualizarGuias(String guias,
                         TRFactura factura,
                         String cidSerie);
    List<BeanCombo> getListaParaCombo(String nombreEntidad, String nombreCampo, String idCampo);
    int findCountByProperty(String propertyName, Object value, String entidad, boolean changeCase,boolean isUpdate, String oldValue);
    List<BeanCodigo> getCodigos();
    String getMaxCodigoDocByCidUnin(String cidUnin,String atributo, String entidad);
    int isCodigoExistente(String cidUnin,
                          String atributo,
                          String entidad,
                          String codigo);
    int traerSiguienteValorSequence(String sequence);
    int traerSiguienteValorCodigo(String app_seq_name);
    void call_Procedure_PagarManifiesto(int nidManifiesto, Double flete);
}