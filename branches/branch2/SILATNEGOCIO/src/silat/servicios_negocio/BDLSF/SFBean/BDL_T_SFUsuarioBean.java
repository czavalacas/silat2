package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFUsuarioLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFUsuarioRemote;
import silat.servicios_negocio.entidades.admin.ADUsuario;

@Stateless(name = "BDL_T_SFUsuario", mappedName = "mapBDL_T_SFUsuario")
public class BDL_T_SFUsuarioBean implements BDL_T_SFUsuarioRemote,
                                            BDL_T_SFUsuarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFUsuarioBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public ADUsuario persistADUsuario(ADUsuario adUsuario) {
        em.persist(adUsuario);
        em.flush();
        em.refresh(adUsuario);
        return adUsuario;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public ADUsuario mergeADUsuario(ADUsuario adUsuario) {
        return em.merge(adUsuario);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeADUsuario(ADUsuario adUsuario) {
        adUsuario = em.find(ADUsuario.class, adUsuario.getNidUsuario());
        em.remove(adUsuario);
    }
}