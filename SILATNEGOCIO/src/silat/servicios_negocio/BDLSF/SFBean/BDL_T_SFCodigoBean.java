package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFCodigoLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFCodigoRemote;
import silat.servicios_negocio.entidades.trans.Trmcodi;
import silat.servicios_negocio.entidades.trans.TrmcodiPK;

@Stateless(name = "BDL_T_SFCodigo", mappedName = "mapBDL_T_SFCodigo")
public class BDL_T_SFCodigoBean implements BDL_T_SFCodigoRemote,
                                           BDL_T_SFCodigoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFCodigoBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY) 
    public Trmcodi persistTrmcodi(Trmcodi trmcodi) {
        em.persist(trmcodi);
        return trmcodi;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY) 
    public Trmcodi mergeTrmcodi(Trmcodi trmcodi) {
        return em.merge(trmcodi);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY) 
    public void removeTrmcodi(Trmcodi trmcodi) {
        trmcodi = em.find(Trmcodi.class, new TrmcodiPK(trmcodi.getCidunin(), 
                                                       trmcodi.getTipdoc()));
        em.remove(trmcodi);
    }
}
