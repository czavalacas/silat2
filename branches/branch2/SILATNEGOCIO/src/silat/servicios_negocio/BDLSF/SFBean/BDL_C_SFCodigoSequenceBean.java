package silat.servicios_negocio.BDLSF.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFCodigoSequenceLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFCodigoSequenceRemoto;
import silat.servicios_negocio.entidades.trans.TRCodigo;
import silat.servicios_negocio.entidades.trans.TRGuia;

@Stateless(name = "BDL_C_SFCodigoSequence", mappedName = "map-SILATNEGOCIO-BDL_C_SFCodigoSequence")
public class BDL_C_SFCodigoSequenceBean implements BDL_C_SFCodigoSequenceRemoto, 
                                                   BDL_C_SFCodigoSequenceLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFCodigoSequenceBean() {
    }

    /** <code>select o from TRCodigo o</code> */
    public List<TRCodigo> getTRCodigoFindAll() {
        return em.createNamedQuery("TRCodigo.findAll").getResultList();
    }
    
    public TRCodigo findTRCodigoSequence(String id) {
        try {
            TRCodigo instance = em.find(TRCodigo.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
         

}
