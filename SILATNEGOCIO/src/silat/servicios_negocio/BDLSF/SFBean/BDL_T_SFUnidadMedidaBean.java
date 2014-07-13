package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFUnidadMedidaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFUnidadMedidaRemote;
import silat.servicios_negocio.entidades.trans.TRUnidadMedida;

@Stateless(name = "BDL_T_SFUnidadMedida", mappedName = "mapBDL_T_SFUnidadMedida")
public class BDL_T_SFUnidadMedidaBean implements BDL_T_SFUnidadMedidaRemote, 
                                                 BDL_T_SFUnidadMedidaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFUnidadMedidaBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRUnidadMedida persistTRUnidadMedida(TRUnidadMedida trUnidadMedida) {
        em.persist(trUnidadMedida);
        return trUnidadMedida;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRUnidadMedida mergeTRUnidadMedida(TRUnidadMedida TRUnidadMedida) {
        return em.merge(TRUnidadMedida);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeTRUnidadMedida(TRUnidadMedida TRUnidadMedida) {
        TRUnidadMedida = em.find(TRUnidadMedida.class, TRUnidadMedida.getNidUnidadMedida());
        em.remove(TRUnidadMedida);
    }
}