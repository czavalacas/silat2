package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFCodigoSequenceLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFCodigoSequenceRemoto;
import silat.servicios_negocio.entidades.trans.TRCodigo;

@Stateless(name = "BDL_T_SFCodigoSequence", mappedName = "map-BDL_T_SFCodigoSequence")
public class BDL_T_SFCodigoSequenceBean implements BDL_T_SFCodigoSequenceRemoto, 
                                                   BDL_T_SFCodigoSequenceLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFCodigoSequenceBean() {
    }

    public TRCodigo persistTRCodigo(TRCodigo TRCodigo) {
        em.persist(TRCodigo);
        return TRCodigo;
    }

    public TRCodigo mergeTRCodigo(TRCodigo TRCodigo) {
        return em.merge(TRCodigo);
    }

    public void removeTRCodigo(TRCodigo TRCodigo) {
        TRCodigo = em.find(TRCodigo.class, TRCodigo.getAppSeqName());
        em.remove(TRCodigo);
    }
}
