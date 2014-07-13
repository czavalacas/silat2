package silat.servicios_negocio.BDLSF.SFBean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFChoferLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFChoferRemote;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.entidades.admin.ADChofer;

/**
 * @author dfloresgonz
 * @since 12.08.2013
 */
@Stateless(name = "BDL_C_SFChofer", mappedName = "mapBDL_C_SFChofer")
public class BDL_C_SFChoferBean implements BDL_C_SFChoferRemote, 
                                           BDL_C_SFChoferLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFChoferBean() {
    }

    /** <code>select o from ADChofer o</code> */
    public List<ADChofer> getADChoferFindAll() {
        return em.createNamedQuery("ADChofer.findAll").getResultList();
    }
    
    public List<BeanChofer> findChoferesByAttributes(BeanChofer beanChofer) {
        try{
            String ejbQl = "SELECT f " +
                           "FROM ADChofer f " +
                           "WHERE f.estadoRegistro = 1 ";
            if(beanChofer.getNidEmpresa() != null){
                ejbQl = ejbQl.concat(" AND f.empresa.nidParty = "+beanChofer.getNidEmpresa());
            }
            if(beanChofer.getNidChofer() != null){
                ejbQl = ejbQl.concat(" AND f.nidChofer = "+beanChofer.getNidChofer());
            }
            List<ADChofer> lstChofers = em.createQuery(ejbQl).getResultList();
            int size = lstChofers == null ? 0 : lstChofers.size();
            if (size > 0) {
                return getListaChofers(lstChofers);
            } else {
                return new ArrayList<BeanChofer>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanChofer>();
        }
    }
    
    public List<BeanChofer> getListaChofers(List<ADChofer> lstChofers){
        try{
            MapperIF mapper = new DozerBeanMapper();
            List<BeanChofer> lstBeanChofer = new ArrayList<BeanChofer>();
            BeanChofer beanChofer = null;
            for(ADChofer chofer : lstChofers){
                beanChofer = (BeanChofer) mapper.map(chofer,BeanChofer.class);
                lstBeanChofer.add(beanChofer);
            }
            return lstBeanChofer;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanChofer>();
        }
    }
}