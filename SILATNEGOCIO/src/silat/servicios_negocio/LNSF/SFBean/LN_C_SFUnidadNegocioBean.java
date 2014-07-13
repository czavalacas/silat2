package silat.servicios_negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUnidadNegocioLocal;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.Beans.BeanUnidadNegocio;
import silat.servicios_negocio.LNSF.IL.LN_C_SFUnidadNegocioLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUnidadNegocioRemote;
import silat.servicios_negocio.entidades.admin.ADChofer;
import silat.servicios_negocio.entidades.admin.ADUnidadNegocio;

@Stateless(name = "LN_C_SFUnidadNegocio", mappedName = "mapLN_C_SFUnidadNegocio")
public class LN_C_SFUnidadNegocioBean implements LN_C_SFUnidadNegocioRemote, 
                                                 LN_C_SFUnidadNegocioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFUnidadNegocioLocal bdL_C_SFUnidadNegocioLocal;

    public LN_C_SFUnidadNegocioBean() {
    }
    
    public List<BeanUnidadNegocio> getUnidadesNegocio_LN(){
        try{
            List<BeanUnidadNegocio> lstBeanUN = getListaUnidNegocio(bdL_C_SFUnidadNegocioLocal.getADUnidadNegocioFindAll());
            return lstBeanUN;
        }catch(Exception e){
            return null;
        }
    }
    
    public List<BeanUnidadNegocio> getListaUnidNegocio(List<ADUnidadNegocio> lstUN){
        try{
            MapperIF mapper = new DozerBeanMapper();
            List<BeanUnidadNegocio> lstBeanUN = new ArrayList<BeanUnidadNegocio>();
            BeanUnidadNegocio beanUN = null;
            for(ADUnidadNegocio unidNeg : lstUN){
                beanUN = (BeanUnidadNegocio) mapper.map(unidNeg,BeanUnidadNegocio.class);
                lstBeanUN.add(beanUN);
            }
            return lstBeanUN;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanUnidadNegocio>();
        }
    }
}