package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFRolLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFRolRemote;
import silat.servicios_negocio.entidades.audsis.STRol;

@Stateless(name = "BDL_T_SFRol", mappedName = "mapBDL_T_SFRol")
public class BDL_T_SFRolBean implements BDL_T_SFRolRemote, 
                                        BDL_T_SFRolLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFRolBean() {
    }

    public STRol persistSTRol(STRol STRol) {
        em.persist(STRol);
        return STRol;
    }

    public STRol mergeSTRol(STRol STRol) {
        return em.merge(STRol);
    }

    public void removeSTRol(STRol STRol) {
        STRol = em.find(STRol.class, STRol.getNidRole());
        em.remove(STRol);
    }
}
