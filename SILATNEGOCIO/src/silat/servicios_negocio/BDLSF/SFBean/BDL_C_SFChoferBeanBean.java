package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFChoferBeanLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFChoferBean;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.entidades.admin.ADChofer;
import silat.servicios_negocio.entidades.admin.ADDireccion;
import silat.servicios_negocio.entidades.admin.ADParty;
import silat.servicios_negocio.entidades.admin.ADPermiso;

@Stateless(name = "BDL_C_SFChoferBean", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFChoferBean")
public class BDL_C_SFChoferBeanBean implements BDL_C_SFChoferBean,
                                               BDL_C_SFChoferBeanLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFChoferBeanBean() {
    }
    
    public List<ADChofer> getADChoferFindAll() {
        return em.createNamedQuery("ADChofer.findAll").getResultList();
    }
    
    /**
     *
     * @param nidChofer -Id del chofer que se va a regitrar
     * @return retrorna ka entudad q fue registra
     */
    public ADChofer getChoferById(Integer nidChofer){
        ADChofer chofer = new ADChofer();
        try {
            String ejbQuery = "Select u from ADChofer u "+
                              "WHERE u.nidChofer = :nidChofer ";
            Query query = em.createQuery(ejbQuery).setParameter("nidChofer", nidChofer);
            chofer = (ADChofer)query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return chofer;
    }
    
    public List<ADChofer> getChoferesPorEmpresa(Integer nidEmpresa){
            String ejbQL =  "select o " +
                            "from ADChofer o " +
                            "where o.empresa.nidParty = :nidEmpresa ";
            return em.createQuery(ejbQL)
                    .setParameter("nidEmpresa",nidEmpresa)
                    .getResultList();
    }       
}