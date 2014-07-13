package silat.servicios_negocio.BDLSF.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFModalidadPagoLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFModalidadPago;
import silat.servicios_negocio.entidades.admin.ADModalidadPago;

@Stateless(name = "BDL_C_SFModalidadPago", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFModalidadPago")
public class BDL_C_SFModalidadPagoBean implements BDL_C_SFModalidadPago, 
                                                  BDL_C_SFModalidadPagoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFModalidadPagoBean() {
    }

    /** <code>select o from ADModalidadPago o</code> */
    public List<ADModalidadPago> getAdmmopaFindAll() {
        return em.createNamedQuery("Admmopa.findAll").getResultList();
    }
}
