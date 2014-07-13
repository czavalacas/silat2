package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFPartyLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFPartyRemote;
import silat.servicios_negocio.entidades.admin.ADParty;

@Stateless(name = "BDL_T_SFParty", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_T_SFParty")
public class BDL_T_SFPartyBean implements BDL_T_SFPartyRemote, 
                                          BDL_T_SFPartyLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFPartyBean() {
    }

    public ADParty persistADParty(ADParty adParty) {
        try{
            em.persist(adParty);
            // em.flush();
            return adParty;
        }catch(Exception e){
            System.out.println("FALLO ADPARTY");
            return null;
        }
    }

    public ADParty mergeADParty(ADParty adParty) {
        return em.merge(adParty);
    }

    public void removeADParty(ADParty adParty) {
        adParty = em.find(ADParty.class, adParty.getNidParty());
        em.remove(adParty);
    }
}
