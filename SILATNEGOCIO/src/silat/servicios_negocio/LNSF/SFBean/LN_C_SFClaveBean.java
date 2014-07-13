package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFClaveLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFClaveLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFClaveRemoto;
import silat.servicios_negocio.entidades.admin.ADClave;

@Stateless(name = "LN_C_SFClave", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFClave")
public class LN_C_SFClaveBean implements LN_C_SFClaveRemoto, LN_C_SFClaveLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB    
    BDL_C_SFClaveLocal bdl_C_SFClaveLocal;
    public LN_C_SFClaveBean() {
    }
    
    public BigDecimal claveDeUsuarioEnSesion(BigDecimal nidUsuario){
       BigDecimal nidClave;
       ADClave datosUsuario= bdl_C_SFClaveLocal.getClavePorUsuario(nidUsuario);//TODO trae a todo la entidad solo por 1 campo
       nidClave=datosUsuario.getNidClave();     
     return nidClave;
    }
}
