package silat.servicios_negocio.BDLSF.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUnidadNegocioLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUnidadNegocioRemote;
import silat.servicios_negocio.entidades.admin.ADUnidadNegocio;

@Stateless(name = "BDL_C_SFUnidadNegocio", mappedName = "mapBDL_C_SFUnidadNegocio")
public class BDL_C_SFUnidadNegocioBean implements BDL_C_SFUnidadNegocioRemote, 
                                                  BDL_C_SFUnidadNegocioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFUnidadNegocioBean() {
    }

    /** <code>select o from ADUnidadNegocio o</code> */
    public List<ADUnidadNegocio> getADUnidadNegocioFindAll() {
        return em.createNamedQuery("ADUnidadNegocio.findAll").getResultList();
    }
}