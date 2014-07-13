package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFClaveLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFClaveRemoto;
import silat.servicios_negocio.entidades.admin.ADChofer;
import silat.servicios_negocio.entidades.admin.ADClave;

@Stateless(name = "BDL_C_SFClave", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFClave")
public class BDL_C_SFClaveBean implements BDL_C_SFClaveRemoto, 
                                          BDL_C_SFClaveLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFClaveBean() {
    }

    /** <code>select o from ADClave o</code> */
    public List<ADClave> getADClaveFindAll() {
        return em.createNamedQuery("ADClave.findAll").getResultList();
    }
    
    public ADClave getClavePorUsuario(BigDecimal nidUsuario){
        ADClave clave = new ADClave();
        try {
            String ejbQuery = "Select u from ADClave u "+
                              "WHERE u.adUsuario.nidUsuario = :nidCUsuario ";
            Query query = em.createQuery(ejbQuery).setParameter("nidCUsuario", nidUsuario);
            clave = (ADClave)query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return clave;
    }
}
