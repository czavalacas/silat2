package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFRolLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUsuarioLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanUsuario;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import silat.servicios_negocio.entidades.admin.ADUsuario;
import silat.servicios_negocio.entidades.audsis.STRol;

@Stateless(name = "LN_C_SFUsuario", mappedName = "mapLNSFUsuario")
public class LN_C_SFUsuarioBean implements LN_C_SFUsuarioRemote, 
                                           LN_C_SFUsuarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFUsuarioLocal bdL_C_SFUsuarioLocal;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    @EJB
    private BDL_C_SFRolLocal bdL_C_SFRolLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    
    public LN_C_SFUsuarioBean() {
    }
    
    public ADUsuario findADUsuarioById(BigDecimal id) {
        try {
            ADUsuario instance = em.find(ADUsuario.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public List<BeanUsuarioAutenticado> getUsuarios(){
         List<ADUsuario> listEntityUsuario = bdL_C_SFUsuarioLocal.getUsuariosNoAdmin();
         List<BeanUsuarioAutenticado> listBeanUsuario = new ArrayList<BeanUsuarioAutenticado>();
         Iterator it = listEntityUsuario.iterator();
         while(it.hasNext()){
             ADUsuario eUsuario = (ADUsuario)it.next();
             BeanUsuarioAutenticado bUsuario = new BeanUsuarioAutenticado();
             bUsuario.setCNombres(eUsuario.getAdPersona().getCNombres());
             ///////CONTINUARAAAA......//////////
             bUsuario.setNidRol(eUsuario.getNTipoUsuario());
             bUsuario.setCApellidos(eUsuario.getAdPersona().getCApellidos());
             bUsuario.setCUsuario(eUsuario.getCUsuario());
             bUsuario.setCCargo(eUsuario.getAdPersona().getCCargo());
             bUsuario.setNidUsuario(eUsuario.getNidUsuario());
             listBeanUsuario.add(bUsuario);
         }
    return listBeanUsuario;
    }
    
    public List<BeanUsuario> lstUsuariosNoAdmin(){
        MapperIF mapper = new DozerBeanMapper();
        try{
            List<ADUsuario> lstUsuarios = bdL_C_SFUsuarioLocal.getUsuariosNoAdmin();
            List<BeanUsuario> lstUsers = new ArrayList<BeanUsuario>();
            Iterator it = lstUsuarios.iterator();
            while(it.hasNext()){
                ADUsuario eUsuario = (ADUsuario) it.next();
                BeanUsuario bUsuario = (BeanUsuario) mapper.map(eUsuario,BeanUsuario.class);
                BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("N_ESTADO_USUARIO","ADMUSUA",bUsuario.getNEstadoUsuario().toString());
                bUsuario.setDescEstado(constr.getCDescrip());
                STRol rol = bdL_C_SFRolLocal.findSTRolById(bUsuario.getNTipoUsuario());
                String descTipoUsuario = rol.getCDescRole();
                bUsuario.setDescTipoUsuario(descTipoUsuario);
                lstUsers.add(bUsuario);
            }
            return lstUsers;
        }catch(Exception e){
            return new ArrayList<BeanUsuario>();
        }
    }
    
    public BeanUsuarioAutenticado autenticarUsuario(String username, 
                                                    String clave/*,
                                                    BigDecimal rol*/){
        BeanError beanError = new BeanError();
     //   DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
       // Calendar cal = Calendar.getInstance();
      //  System.out.println("inicio : "+dateFormat.format(cal.getTime()));
        BeanUsuarioAutenticado beanUsuario = new BeanUsuarioAutenticado();
        Map mapSalida = new HashMap();
        ADUsuario usuario = null;
        String error = "000";
        try{
            username = username.toLowerCase();
            clave = clave.toLowerCase();
            mapSalida = bdL_C_SFUsuarioLocal.autenticarUsuario(username, clave/*, rol*/);
            error = (String) mapSalida.get("error");
            if(error.equals("000")){
                usuario = (ADUsuario) mapSalida.get("usuario");
                beanUsuario.setCApellidos(usuario.getAdPersona().getCApellidos());
                beanUsuario.setCCargo(usuario.getAdPersona().getCCargo());
                beanUsuario.setCEmail(usuario.getAdPersona().getAdParty().getCEmail());
                beanUsuario.setCNombres(usuario.getAdPersona().getCNombres());
                beanUsuario.setCTelf(usuario.getAdPersona().getAdParty().getCTelf());
                beanUsuario.setCTipoParty(usuario.getAdPersona().getAdParty().getCTipoParty());
                beanUsuario.setCUsuario(usuario.getCUsuario());
                beanUsuario.setFecNac(usuario.getAdPersona().getFecNac());
                beanUsuario.setNidParty(usuario.getAdPersona().getNidParty());
                beanUsuario.setNidUsuario(usuario.getNidUsuario());
                beanUsuario.setRol(usuario.getUsuarioXPermisosList().get(0).getRolXPermiso().getStRol().getCDescRole());
                beanUsuario.setNidRol(usuario.getNTipoUsuario() /*usuario.getUsuarioXPermisosList().get(0).getRolXPermiso().getStRol().getNidRole()*/);
                beanUsuario.setOutput(error);
              //  Calendar cal2 = Calendar.getInstance();
              //  System.out.println("fin : " + dateFormat.format(cal2.getTime()));
                return beanUsuario;
            }
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0004";
            beanUsuario.setOutput("Hubo un error del sistema. Contactese con el Administrador.");
            System.out.println("Hubo un error del sistema. Contactese con el administrador.");
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        beanUsuario.setOutput(beanError.getCDescripcionError());
        return beanUsuario;
    }
}