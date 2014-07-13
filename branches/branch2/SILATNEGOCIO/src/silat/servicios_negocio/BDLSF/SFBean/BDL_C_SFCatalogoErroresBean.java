package silat.servicios_negocio.BDLSF.SFBean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFCatalogoErroresRemote;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.entidades.audsis.STError;

@Stateless(name = "BDL_C_SFCatalogoErrores", mappedName = "mapBDL_C_SFCatalogoErrores")
public class BDL_C_SFCatalogoErroresBean implements BDL_C_SFCatalogoErroresRemote, 
                                                    BDL_C_SFCatalogoErroresLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFCatalogoErroresBean() {
        
    }

    /** <code>select o from STError o</code> */
    public List<STError> getSTErrorFindAll() {
        return em.createNamedQuery("STError.findAll").getResultList();
    }
    
    public STError getErrorByCodigo(String cidError){
        STError stError = new STError();
        try{
            String ejbQl = "SELECT e "+
                           "FROM STError e "+
                           "WHERE e.cidError = '"+cidError+"' "+
                           "AND e.cEstadoError = '1' ";
            stError = (STError) em.createQuery(ejbQl)
                                .getSingleResult();
        }catch(NoResultException nre){
            System.out.println("\n\n\nEl Codigo de Error no existe!!!!!!!!!!!!!!!!!!!! nre. \n\n\n");
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;   
        }
        return stError;
    }

    public List<BeanError> findAll() {
        List<BeanError> dtoList = new ArrayList<BeanError>();
        try {
            MapperIF mapper = new DozerBeanMapper();
            dtoList = new ArrayList<BeanError>();
            
            Query query = em.createQuery("SELECT e " +
                                         "FROM STError e " +
                                         "WHERE e.cEstadoError = '1' ");
            List<STError> entityList = query.getResultList();
            for (STError entity : entityList) {
                dtoList.add((BeanError)mapper.map(entity, BeanError.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return dtoList;
    }
}
