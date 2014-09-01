package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFClaveLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFClaveRemoto;
import silat.servicios_negocio.entidades.admin.ADClave;
import silat.servicios_negocio.entidades.admin.ADFlota;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_T_SFClave", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_T_SFClave")
public class BDL_T_SFClaveBean implements BDL_T_SFClaveRemoto,
                                          BDL_T_SFClaveLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFClaveBean() {
    }
    
    public String actualizarClave(String cUsuario, String cClave, BigDecimal nidUsuario){
        String ejbQL =  "update addclav cla " +
                  //      "SET cla.c_clave = pkg_seguridad.get_hash('"+cUsuario+"','"+cClave+"','1') where cla.nid_clave= "+nidClave;
                          "SET cla.c_clave = '"+cClave+"' " +
                          "where cla.nid_usuario="+nidUsuario;
         em.createNativeQuery(ejbQL).executeUpdate();
        return null;      
    }

    public ADClave persistADClave(ADClave ADClave) {
        em.persist(ADClave);
        return ADClave;
    }

    public ADClave mergeADClave(ADClave ADClave) {
        return em.merge(ADClave);
    }

    public void removeADClave(ADClave ADClave) {
        ADClave = em.find(ADClave.class, ADClave.getNidClave());
        em.remove(ADClave);
    }
}
