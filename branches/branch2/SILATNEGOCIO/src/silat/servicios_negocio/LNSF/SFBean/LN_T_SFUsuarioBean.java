package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUsuarioLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFUsuarioLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanUsuario;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFUsuarioLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFUsuarioRemote;
import silat.servicios_negocio.entidades.admin.ADClave;
import silat.servicios_negocio.entidades.admin.ADUsuario;
import silat.servicios_negocio.entidades.trans.TRManifiesto;

@Stateless(name = "LN_T_SFUsuario", mappedName = "mapLN_T_SFUsuario")
public class LN_T_SFUsuarioBean implements LN_T_SFUsuarioRemote, 
                                           LN_T_SFUsuarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    @EJB
    private BDL_T_SFUsuarioLocal bdL_T_SFUsuarioLocal;
    @EJB
    private LN_C_SFUsuarioLocal ln_C_SFUsuarioLocal;
    @EJB
    private BDL_C_SFUsuarioLocal bdL_C_SFUsuarioLocal;

    public LN_T_SFUsuarioBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanUsuario registrarUsuario(BeanUsuario beanUser){
        String error = "000";
        ADUsuario eUser = new ADUsuario();
        BeanError beanError = new BeanError();
        MapperIF mapper = new DozerBeanMapper();
        try{
            int cant = bdL_C_SFUsuarioLocal.countNombreUsuario(beanUser.getCUsuario());
            if(cant > 0){
                error = "LUB-0026";
            }else if(cant == 0){
                eUser = (ADUsuario) mapper.map(beanUser,ADUsuario.class);
                ADClave clave = new ADClave();
                clave.setAdUsuario(eUser);
                clave.setCClave(eUser.getCUsuario());
                clave.setNEstadoClave(new BigDecimal(1));
                clave.setUser(eUser.getCUsuario());
                List<ADClave> clavesList = new ArrayList<ADClave>();
                clavesList.add(clave);
                eUser.setClavesList(clavesList);
                eUser = bdL_T_SFUsuarioLocal.persistADUsuario(eUser);
            }
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0024";
        }
        System.out.println("LN_T_SFUsuarioBean: "+error);
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        System.out.println("LN_T_SFUsuarioBean "+error+" beanError: "+beanError+ " beanError: "+(beanError != null ? beanError.getCDescripcionError() : "NO Bean error"));
        beanUser.setBeanError(beanError);
        return beanUser;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanUsuario actualizarUsuario(BeanUsuario beanUser){
        String error = "000";
        BeanError beanError = new BeanError();
        try{
            ADUsuario eUser = bdL_C_SFUsuarioLocal.findADUsuarioById(beanUser.getNidUsuario());
            eUser.getAdPersona().setCApellidos(beanUser.getAdPersona().getCApellidos());
            eUser.getAdPersona().setCNombres(beanUser.getAdPersona().getCNombres());
            eUser.getAdPersona().setFecNac(beanUser.getAdPersona().getFecNac());
            eUser.getAdPersona().getAdParty().setCEmail(beanUser.getAdPersona().getAdParty().getCEmail());
            eUser.getAdPersona().getAdParty().setCTelf(beanUser.getAdPersona().getAdParty().getCTelf());
            eUser = bdL_T_SFUsuarioLocal.mergeADUsuario(eUser);
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0024";
        }
        System.out.println("actualizarUsuario: "+error);
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        System.out.println("actualizarUsuario "+error+" beanError: "+beanError+ " beanError: "+(beanError != null ? beanError.getCDescripcionError() : "NO Bean error"));
        beanUser.setBeanError(beanError);
        return beanUser;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanUsuario desactivarUsuario(BigDecimal nidUsuario, int est){
        String error = "000";
        BeanError beanError = new BeanError();
        BeanUsuario beanUser = new BeanUsuario();
        try{
            ADUsuario usu = ln_C_SFUsuarioLocal.findADUsuarioById(nidUsuario);
            usu.setNEstadoUsuario(new BigDecimal(est));
            bdL_T_SFUsuarioLocal.mergeADUsuario(usu);
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0024";
        }
        System.out.println("desactivarUsuario LN_T_SFUsuarioBean: "+error);
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        System.out.println("LN_T_SFUsuarioBean "+error+" beanError: "+beanError+ " beanError: "+(beanError != null ? beanError.getCDescripcionError() : "NO Bean error"));
        beanUser.setBeanError(beanError);
        return beanUser;
    }
}
