package silat.servicios_negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFOrdenServicioLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFOrdenServicioLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFOrdenServicioRemote;

@Stateless(name = "LN_T_SFOrdenServicio", mappedName = "mapLN_T_SFOrdenServicio")
public class LN_T_SFOrdenServicioBean implements LN_T_SFOrdenServicioRemote,
                                                 LN_T_SFOrdenServicioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFOrdenServicioLocal bdL_T_SFOrdenServicioLocal;

    public LN_T_SFOrdenServicioBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void flgVistoNuevasOrdenServicio_LN(){
        bdL_T_SFOrdenServicioLocal.flgVistoNuevasOrdenServicio_BDL();
    }
}
