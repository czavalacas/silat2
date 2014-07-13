package silat.servicios_negocio.LNSF.SFBean;

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

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFFlotaLocal;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.LNSF.IL.LN_C_SFFlotaLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFFlotaRemote;
import silat.servicios_negocio.entidades.admin.ADFlota;

/**
 * @author dfloresgonz
 * @since 12.08.2013
 */
@Stateless(name = "LN_C_SFFlota", mappedName = "mapLN_C_SFFlota")
public class LN_C_SFFlotaBean implements LN_C_SFFlotaRemote, 
                                         LN_C_SFFlotaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public LN_C_SFFlotaBean() {
    }
    
    @EJB 
    private BDL_C_SFFlotaLocal bdL_C_SFFlotaLocal;
    
    public List<BeanFlota> findFlotasByAttr_LN(Integer nidEmpresa,
                                               Integer nidFlota){
        BeanFlota beanFlota = new BeanFlota();
        beanFlota.setNidEmpresa(nidEmpresa);
        beanFlota.setNidFlota(nidFlota);
        return bdL_C_SFFlotaLocal.findFlotasByAttributes(beanFlota);
    }
    
     public List<BeanFlota> getFlotasPorEmpresa(Integer nidEmpresa){
        MapperIF mapper = new DozerBeanMapper();
        List<ADFlota> listaFlotas = bdL_C_SFFlotaLocal.getFlotasPorEmpresa(nidEmpresa);
        List<BeanFlota> listaFlota = new ArrayList<BeanFlota>();
        Iterator it = listaFlotas.iterator();
        while(it.hasNext()){
            ADFlota entida = (ADFlota) it.next();
            BeanFlota beanFlota = (BeanFlota) mapper.map(entida, BeanFlota.class);
            listaFlota.add(beanFlota);
        }
        return listaFlota;
    }
}