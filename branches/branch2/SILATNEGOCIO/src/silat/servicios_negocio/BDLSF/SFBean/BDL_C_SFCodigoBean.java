package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFCodigoLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFCodigoRemote;
import silat.servicios_negocio.entidades.trans.Trmcodi;
import silat.servicios_negocio.entidades.trans.TrmcodiPK;

@Stateless(name = "BDL_C_SFCodigo", mappedName = "mapBDL_C_SFCodigo")
public class BDL_C_SFCodigoBean implements BDL_C_SFCodigoRemote,
                                           BDL_C_SFCodigoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFCodigoBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public Trmcodi findCodigoById(String cidUnin,
                                  String tipDoc){

        try {
            Trmcodi trmcodi = em.find(Trmcodi.class, new TrmcodiPK(cidUnin, tipDoc));
            return trmcodi;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}