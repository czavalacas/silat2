package silat.servicios_negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.Beans.BeanADUbigeo;
import silat.servicios_negocio.Beans.BeanCodigo;
import silat.servicios_negocio.Beans.BeanCombo;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.LNSF.IL.LN_C_SFUtilsLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote;
import silat.servicios_negocio.entidades.admin.ADUbigeo;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRItem;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_C_SFUtils", mappedName = "mapLN_C_SFUtils")
public class LN_C_SFUtilsBean implements LN_C_SFUtilsRemote,
                                         LN_C_SFUtilsLocal{
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em; 
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    private final static String NO_ERROR = "000";
    
    public LN_C_SFUtilsBean() {
    }    
   
    
    public String generarCorrelativoLN(String entidad, 
                                       String atributo,
                                       int tamano,
                                       String cidUN){
        HashMap output = new HashMap();
        output = bdL_C_SFUtilsLocal.generarCorrelativo(entidad, 
                                                       atributo,
                                                       tamano,
                                                       cidUN);
        String error = (String) output.get("error");
        String correlativo = "NO CORR";
        if(error.equals(NO_ERROR)){
            correlativo = (String)output.get("correlativo");
        }
        return correlativo;
    }
 
    public BeanError registrarGuia(List<BeanTRItem> lstItems){
        BeanError beanError = new BeanError();
        beanError.setCDescripcionError("errorzaso");
        for(BeanTRItem items : lstItems){
            System.out.println("items: "+items.getCDescItem());
        }
        return beanError;
    }
    
    public List<TRItem> beanItemToEntity(List<BeanTRItem> lstItems,TRGuia eGuia){
        List<TRItem> items = new ArrayList<TRItem>();
        MapperIF mapper = new DozerBeanMapper();
        for(BeanTRItem item : lstItems){
            System.out.println("Bean Ordenes : " +item.getOrden());
            TRItem eItem = (TRItem) mapper.map(item,TRItem.class);
            System.out.println("Entidad Ordenes : " +eItem.getOrden());
            eItem.setTrGuia(eGuia);
            eItem.setNidItem(null);//Para que no chape el NidDel Item de OS
            items.add(eItem);
        }
        return items;
    }
    
    public List<BeanConstraint> getListADMCONS(String nombreTabla, String nombreCampo){
        return bdL_C_SFUtilsLocal.getListConstraints(nombreCampo,nombreTabla);
    }
    
    public List<BeanCombo> getListaParaCombo_LN(String nombreEntidad, String nombreCampo, String idCampo){
        return bdL_C_SFUtilsLocal.getListaParaCombo(nombreEntidad, nombreCampo, idCampo);
    }
    
    public List<BeanCodigo> getCodigos(){
        return bdL_C_SFUtilsLocal.getCodigos();
    }
    
    public int traerSiguienteValorSequence(String sequence){
        return bdL_C_SFUtilsLocal.traerSiguienteValorCodigo(sequence);
    }  
}