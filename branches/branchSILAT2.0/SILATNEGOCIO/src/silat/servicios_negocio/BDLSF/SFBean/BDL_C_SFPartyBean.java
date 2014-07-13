package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFPartyLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFPartyRemote;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADParty;

@Stateless(name = "BDL_C_SFParty", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFParty")
public class BDL_C_SFPartyBean implements BDL_C_SFPartyRemote, 
                                          BDL_C_SFPartyLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFPartyBean() {
    }
    
    public ADParty getADPartybyNid(BigDecimal nidParty){
        ADParty party = new ADParty();
        try {
            //MapperIF mapper = new DozerBeanMapper();
            String ejbQuery = "Select u from ADParty u "+
                              "WHERE u.nidParty = :nidParty ";
            Query query = em.createQuery(ejbQuery).setParameter("nidParty", nidParty);
            party = (ADParty)query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return party;
    }
}
