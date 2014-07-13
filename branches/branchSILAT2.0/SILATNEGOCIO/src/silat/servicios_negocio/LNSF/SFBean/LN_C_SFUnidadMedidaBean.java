package silat.servicios_negocio.LNSF.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUnidadMedidaLocal;
import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.LNSF.IL.LN_C_SFUnidadMedidaLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUnidadMedidaRemote;

@Stateless(name = "LN_C_SFUnidadMedida", mappedName = "mapLN_C_SFUnidadMedida")
public class LN_C_SFUnidadMedidaBean implements LN_C_SFUnidadMedidaRemote,
                                                LN_C_SFUnidadMedidaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    @EJB
    private BDL_C_SFUnidadMedidaLocal bdL_C_SFUnidadMedidaLocal;
    
    public LN_C_SFUnidadMedidaBean() {
    }
    
    public List<BeanUnidadMedida> getUnidadesMedida_LN(){
        return bdL_C_SFUnidadMedidaLocal.getUnidadesMedida_BDL();
    }
}