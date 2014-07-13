package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.ArrayList;
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

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEmpresasLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFGastoLocal;
import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.LNSF.IL.LN_C_SFGastosLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGastosRemoto;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADGasto;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_C_SFGastos", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFGastos")
public class LN_C_SFGastosBean implements LN_C_SFGastosRemoto,
                                          LN_C_SFGastosLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    BDL_C_SFGastoLocal bdl_C_SFGastoLocal;
    @EJB
    protected BDL_C_SFEmpresasLocal bdL_C_SFEmpresasLocal;
    
    public LN_C_SFGastosBean() {
    }
    
    public List<BeanGasto> getGastosByAttributes(BeanGasto beanGasto){
        List <ADGasto> listaGastos = bdl_C_SFGastoLocal.findGastosByAttributes(beanGasto);
        List<BeanGasto> listaGasatosBean = new ArrayList<BeanGasto>();
        Iterator it = listaGastos.iterator();
        MapperIF mapper = new DozerBeanMapper();
        while(it.hasNext()){
            ADGasto entida = (ADGasto)it.next();
            //beanOS = (BeanOrdenServicio)mapper.map(ordenServ,BeanOrdenServicio.class);
            BeanGasto bean = (BeanGasto)mapper.map(entida,BeanGasto.class);
            ////metodo para traer datos del provedor Debido a que nidProtra no es de tipo ADEMPRESA
            if(entida.getNidProtra() != null){
                ADEmpresa entida2 = new ADEmpresa(); 
                entida2 = bdL_C_SFEmpresasLocal.getEmpresaById(new BigDecimal(entida.getNidProtra()));
            }
            if(entida.getNidProtra() == null){
                bean.setCRazonSocial(null);
            }
            listaGasatosBean.add(bean);            
        }
        return listaGasatosBean;
    }
    
    public int cantGastosByFlota(int nidFlota){
        return bdl_C_SFGastoLocal.cantGastosByFlota(nidFlota);
    }
}