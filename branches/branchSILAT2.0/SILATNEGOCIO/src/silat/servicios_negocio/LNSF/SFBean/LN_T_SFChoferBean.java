package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFChoferBeanLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFChoferLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFChoferRemote;
import silat.servicios_negocio.entidades.admin.ADChofer;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADParty;

@Stateless(name = "LN_T_SFChofer", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_T_SFChofer")
public class LN_T_SFChoferBean implements LN_T_SFChoferRemote, 
                                          LN_T_SFChoferLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    BDL_T_SFChoferBeanLocal bDL_T_SFChoferlocal;
    
    public LN_T_SFChoferBean() {
    }
    
    public ADChofer guardarChofer(BigDecimal nidParty, 
                                  String nombre, 
                                  String licencia){
        ADParty party= new ADParty();
        party.setNidParty(nidParty);            
        ADEmpresa empresa = new ADEmpresa();
        empresa.setAdParty(party);
        ADChofer entidad=new ADChofer();
        entidad.setLicencia(licencia.toUpperCase());
        entidad.setNombres(nombre.toUpperCase());  //upper
        entidad.setEstadoRegistro("1");
        entidad.setEmpresa(empresa);
        return bDL_T_SFChoferlocal.persistADChofer(entidad);
    }
    
    public ADChofer actualizarChofer(BigDecimal nidParty, 
                                     Integer nidChofer, 
                                     String nombres,
                                     String licencia){
        ADParty party= new ADParty();
        party.setNidParty(nidParty);            
        ADEmpresa empresa = new ADEmpresa();
        empresa.setAdParty(party);
        ADChofer entidad=new ADChofer();
        entidad.setNidChofer(nidChofer);
        entidad.setLicencia(licencia.toUpperCase());
        entidad.setNombres(nombres.toUpperCase());
        entidad.setEstadoRegistro("1");
        entidad.setEmpresa(empresa);
        return bDL_T_SFChoferlocal.mergeADChofer(entidad);
    }
    
    public void removerChofer(Integer nidChofer){        
        ADChofer entidad=new ADChofer();
        entidad.setNidChofer(nidChofer);
         bDL_T_SFChoferlocal.removeADChofer(entidad);
    }
}