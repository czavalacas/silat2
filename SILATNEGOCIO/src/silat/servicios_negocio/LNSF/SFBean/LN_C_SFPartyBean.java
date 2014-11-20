package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEmpresasLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFPartyLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFPartyLocal;
import silat.servicios_negocio.Beans.BeanADUbigeo;
import silat.servicios_negocio.Beans.BeanParty;
import silat.servicios_negocio.LNSF.IL.LN_C_SFPartyLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFPartyRemote;
import silat.servicios_negocio.entidades.admin.ADParty;
import silat.servicios_negocio.entidades.admin.ADUbigeo;

@Stateless(name = "LN_C_SFParty", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFParty")
public class LN_C_SFPartyBean implements LN_C_SFPartyRemote, 
                                         LN_C_SFPartyLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    
    @EJB
    protected BDL_T_SFPartyLocal bdL_T_SFPartyLocal;

    @EJB
    protected BDL_C_SFPartyLocal bdL_C_SFPartyLocal;
    
    public LN_C_SFPartyBean() {
    }
    public ADParty grabarParty( String cidUbigeo,
                                String cDetalle,
                                String cEmail,
                                String cTelf){
        BeanParty party = new BeanParty();
        party.setCDetalle(cDetalle);
        party.setCEmail(cEmail);
        party.setCTelf(cTelf);
        party.setCTipoParty("E");
        MapperIF mapper = new DozerBeanMapper();
        ADParty entidadparty = (ADParty) mapper.map(party, ADParty.class);
        entidadparty = bdL_T_SFPartyLocal.persistADParty(entidadparty);
        return entidadparty;
    }
    
    public ADParty modificarParty(  BigDecimal nidParty,
                                    String cidUbigeo,
                                    String cDetalle,
                                    String cEmail,
                                    String cTelf){
        ADParty party = bdL_C_SFPartyLocal.getADPartybyNid(nidParty);
        party.setCDetalle(cDetalle);
        party.setCEmail(cEmail);
        party.setCTelf(cTelf);
        party.setCTipoParty("E");
        party = bdL_T_SFPartyLocal.mergeADParty(party);
        System.out.println("::MERGE PARTY:::");
        return party;
    }
}