package silat.servicios_negocio.BDLSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFDireccionLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFDireccionRemote;
import silat.servicios_negocio.entidades.admin.ADDireccion;

@Stateless(name = "BDL_T_SFDireccion", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_T_SFDireccion")
public class BDL_T_SFDireccionBean implements BDL_T_SFDireccionRemote,
                                              BDL_T_SFDireccionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFDireccionBean() {
    }

    public ADDireccion persistADDireccion(ADDireccion ADDireccion) {
        em.persist(ADDireccion);System.out.println("PERSISTIO DIREC");
        return ADDireccion;
    }

    public ADDireccion mergeADDireccion(ADDireccion ADDireccion) {
        return em.merge(ADDireccion);
    }

    public void removeADDireccion(ADDireccion ADDireccion) {
        ADDireccion = em.find(ADDireccion.class, ADDireccion.getNidDireccion());
        em.remove(ADDireccion);
    }
}
