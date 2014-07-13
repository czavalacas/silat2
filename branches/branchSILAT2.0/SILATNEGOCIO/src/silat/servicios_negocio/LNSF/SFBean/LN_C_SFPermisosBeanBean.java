package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFPermisosLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUsuarioXPermisoLocal;
import silat.servicios_negocio.Beans.BeanPermisos;
import silat.servicios_negocio.LNSF.IL.LN_C_SFPermisosBeanLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFPermisosBeanRemote;
import silat.servicios_negocio.entidades.admin.ADPermiso;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_C_SFPermisosBean", mappedName = "mapLNPermisos")
public class LN_C_SFPermisosBeanBean implements LN_C_SFPermisosBeanRemote, 
                                                LN_C_SFPermisosBeanLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    @EJB
    protected BDL_C_SFPermisosLocal bdL_C_SFPermisosLocal;       
    
    public LN_C_SFPermisosBeanBean() {
    }
    
    public void print(Object o){
        System.out.println(o);
    }

    public boolean hayHijos(List<BeanPermisos> lista, String nombUser, BigDecimal nidRol){
            Iterator it = lista.iterator();
            int cant = 0;
            while(it.hasNext()){
                BeanPermisos papa = (BeanPermisos) it.next();
                List<ADPermiso> papHijos = bdL_C_SFPermisosLocal.getHijosByPadre(papa.getNidPermiso(),nombUser,nidRol);
                cant += papHijos.size();
            }
            return (cant > 0) ? true : false;
    }

    public List<BeanPermisos> getCrearArbolNuevo(BigDecimal nidRol,String nombUser) {
        int nivel = 0;
        List<BeanPermisos> listaMenu = new ArrayList<BeanPermisos>();
        BeanPermisos raiz = new BeanPermisos();
        List<BigDecimal> lstPermisos = new ArrayList<BigDecimal>();
        if(nidRol != null){
            ADPermiso e_raiz = bdL_C_SFPermisosLocal.getByNidPermiso(new BigDecimal("1"));
            raiz = setBean(e_raiz, "S",lstPermisos);
            raiz = crearArbolAux(nivel, null, null, raiz,nidRol,nombUser,lstPermisos);
            listaMenu.add(raiz);
        }
        return listaMenu;
    }
    
    public BeanPermisos crearArbolAux(int nivel, 
                                      List<BeanPermisos> hijos, 
                                      BeanPermisos padre, 
                                      BeanPermisos raiz,
                                      BigDecimal nidRol,
                                      String nombUser,
                                      List<BigDecimal> lstPermisos){
        if(nivel == bdL_C_SFPermisosLocal.getNiveles()){
            return raiz;
        }
        if(hijos == null){
            raiz = setearHijos(raiz,hijos,null,null,1,nidRol,nombUser,lstPermisos);
            if(hayHijos(raiz.getListaHijos(),nombUser,nidRol)){
                nivel += 1;
                raiz = crearArbolAux(nivel, raiz.getListaHijos(), padre, raiz,nidRol,nombUser,lstPermisos);
            }else{
                return raiz;
            }
        }else{
            BeanPermisos hijo = new BeanPermisos();
            for(int i = 0; i <hijos.size(); i++){
                hijo = hijos.get(i);
                hijo = setearHijos(raiz,hijos,hijo,hijo.getNidPermiso(),2,nidRol,nombUser,lstPermisos);
                if (hayHijos(hijo.getListaHijos(),nombUser,nidRol)) {
                    nivel += 1;
                    crearArbolAux(nivel, hijo.getListaHijos(), padre, raiz,nidRol,nombUser,lstPermisos);
                } 
            }
        }
        return raiz;
    }
    
    
    public BeanPermisos setearHijos(BeanPermisos raiz, 
                                    List<BeanPermisos> hijos,
                                    BeanPermisos padre,
                                    BigDecimal nidp, 
                                    int pv,
                                    BigDecimal nidRol,
                                    String nombUser,
                                    List<BigDecimal> lstPermisos){
        BigDecimal bd = (pv == 2) ? nidp : raiz.getNidPermiso();
        BeanPermisos hijosObj = new BeanPermisos();
        hijos = new ArrayList<BeanPermisos>();
        List<ADPermiso> lstPerm = bdL_C_SFPermisosLocal.getHijosByPadre(bd,nombUser,nidRol);
        Iterator it = lstPerm.iterator();
        while(it.hasNext()){
            ADPermiso objP = (ADPermiso) it.next();
            if(!objP.getHabilidad().equals("S")){
                hijosObj = setBean(objP,"N",lstPermisos);
                hijos.add(hijosObj);
            }else{
                lstPermisos.add(objP.getNidPermiso());
            }
        }
        if(pv == 1){
            raiz.setListaHijos(hijos);
            return raiz;
        }else{
            padre.setListaHijos(hijos);
            return padre;
        }
    }
    
    public List<BeanPermisos> getCrearArbolNuevoAdmPermisos(BigDecimal nidRol,String nombUser) {
        int nivel = 0;
        List<BeanPermisos> listaMenu = new ArrayList<BeanPermisos>();
        BeanPermisos raiz = new BeanPermisos();
        List<BigDecimal> lstPermisos = new ArrayList<BigDecimal>();
        if(nidRol != null){
            ADPermiso e_raiz = bdL_C_SFPermisosLocal.getByNidPermiso(new BigDecimal("1"));
            raiz = setBean(e_raiz, "S",lstPermisos);
            raiz = crearArbolAuxAdmPermisos(nivel, null, null, raiz,nidRol,nombUser,lstPermisos);
            listaMenu.add(raiz);
        }
        return listaMenu;
    }
    
    public BeanPermisos crearArbolAuxAdmPermisos(int nivel, 
                                                 List<BeanPermisos> hijos, 
                                                 BeanPermisos padre, 
                                                 BeanPermisos raiz,
                                                 BigDecimal nidRol,
                                                 String nombUser,
                                                 List<BigDecimal> lstPermisos){
        if(nivel == bdL_C_SFPermisosLocal.getNiveles()){
            return raiz;
        }
        if(hijos == null){
            raiz = setearHijosAdmPermisos(raiz,hijos,null,null,1,nidRol,nombUser,lstPermisos);
            if(hayHijos(raiz.getListaHijos(),nombUser,nidRol)){
                nivel += 1;
                raiz = crearArbolAuxAdmPermisos(nivel, raiz.getListaHijos(), padre, raiz,nidRol,nombUser,lstPermisos);
            }else{
                return raiz;
            }
        }else{
            BeanPermisos hijo = new BeanPermisos();
            for(int i = 0; i <hijos.size(); i++){
                hijo = hijos.get(i);
                hijo = setearHijosAdmPermisos(raiz,hijos,hijo,hijo.getNidPermiso(),2,nidRol,nombUser,lstPermisos);
                if (hayHijos(hijo.getListaHijos(),nombUser,nidRol)) {
                    nivel += 1;
                    crearArbolAuxAdmPermisos(nivel, hijo.getListaHijos(), padre, raiz,nidRol,nombUser,lstPermisos);
                } 
            }
        }
        return raiz;
    }
    
    public BeanPermisos setearHijosAdmPermisos(BeanPermisos raiz, 
                                               List<BeanPermisos> hijos,
                                               BeanPermisos padre,
                                               BigDecimal nidp, 
                                               int pv,
                                               BigDecimal nidRol,
                                               String nombUser,
                                               List<BigDecimal> lstPermisos){
        BigDecimal bd = (pv == 2) ? nidp : raiz.getNidPermiso();
        BeanPermisos hijosObj = new BeanPermisos();
        hijos = new ArrayList<BeanPermisos>();
        List<ADPermiso> lstPerm = bdL_C_SFPermisosLocal.getHijosByPadre(bd,nombUser,nidRol);
        Iterator it = lstPerm.iterator();
        while(it.hasNext()){
            ADPermiso objP = (ADPermiso) it.next();//UtilsGeneral.depurar("perm: "+objP.getNidPermiso()+" desc: "+objP.getCDescPermiso());
            hijosObj = setBean(objP,"N",lstPermisos);
            hijos.add(hijosObj);
            if(!lstPermisos.contains(objP.getNidPermiso())){
                lstPermisos.add(objP.getNidPermiso());
            }
        }
        if(pv == 1){
            raiz.setListaHijos(hijos);
            return raiz;
        }else{
            padre.setListaHijos(hijos);
            return padre;
        }
    }
    
    /**
     * @param permiso
     * @author dfloresgonz
     * @return string
     * @since 28.03.2013
     * @see LN_C_SFPermisosBeanBean
     */
    public BeanPermisos setBean(ADPermiso permiso,String indMostrar,List<BigDecimal> lstPermisos){
        BeanPermisos bean = new BeanPermisos();
        bean.setCDescPermiso(permiso.getCDescPermiso());
        bean.setCIcono(permiso.getCIcono());
        bean.setCIndFld(permiso.getCIndFld());
        bean.setCNombreObj(permiso.getCNombreObj());
        bean.setCUrl(permiso.getCUrl());
        bean.setHabilidad(permiso.getHabilidad());
        bean.setNEstadoPermiso(permiso.getNEstadoPermiso());
        bean.setNidPermiso(permiso.getNidPermiso());
        lstPermisos.add(permiso.getNidPermiso());
        bean.setNMenuPadre(permiso.getNMenuPadre());
      //  UtilsGeneral.depurar("id: "+permiso.getNidPermiso()+" - "+bean.getCDescPermiso());
        bean.setLstPermisos(lstPermisos);
        bean.setIndMostrar(indMostrar);
        return bean;
    }
    
    /*TODOS LOS PERMISOS*/
    public List<BeanPermisos> getCrearArbolNuevoAllPermisos(List<BigDecimal> permisosUser) {
        int nivel = 0;
        List<BeanPermisos> listaMenu = new ArrayList<BeanPermisos>();
        BeanPermisos raiz = new BeanPermisos();
        List<BigDecimal> lstPermisos = new ArrayList<BigDecimal>();
        ADPermiso e_raiz = bdL_C_SFPermisosLocal.getByNidPermiso(new BigDecimal("1"));
        raiz = setBean(e_raiz, "S",lstPermisos);
        raiz.setHasPermiso("true");
        raiz = crearArbolAuxAllPermisos(nivel, null, null, raiz,lstPermisos,permisosUser);
        listaMenu.add(raiz);
        return listaMenu;
    }
    
    public BeanPermisos crearArbolAuxAllPermisos(int nivel, 
                                                 List<BeanPermisos> hijos, 
                                                 BeanPermisos padre, 
                                                 BeanPermisos raiz,
                                                 List<BigDecimal> lstPermisos,
                                                 List<BigDecimal> permisosUser){
        if(nivel == bdL_C_SFPermisosLocal.getNiveles()){
            return raiz;
        }
        if(hijos == null){
            raiz = setearHijosAllPermisos(raiz,hijos,null,null,1,lstPermisos,permisosUser);
            if(hayHijosAll(raiz.getListaHijos())){
                nivel += 1;
                raiz = crearArbolAuxAllPermisos(nivel, raiz.getListaHijos(), padre, raiz,lstPermisos,permisosUser);
            }else{
                return raiz;
            }
        }else{
            BeanPermisos hijo = new BeanPermisos();
            for(int i = 0; i <hijos.size(); i++){
                hijo = hijos.get(i);
                hijo = setearHijosAllPermisos(raiz,hijos,hijo,hijo.getNidPermiso(),2,lstPermisos,permisosUser);
                if (hayHijosAll(hijo.getListaHijos())) {
                    nivel += 1;
                    crearArbolAuxAllPermisos(nivel, hijo.getListaHijos(), padre, raiz,lstPermisos,permisosUser);
                } 
            }
        }
        return raiz;
    }
    
    public BeanPermisos setearHijosAllPermisos(BeanPermisos raiz, 
                                               List<BeanPermisos> hijos,
                                               BeanPermisos padre,
                                               BigDecimal nidp, 
                                               int pv,
                                               List<BigDecimal> lstPermisos,
                                               List<BigDecimal> permisosUser){
        BigDecimal bd = (pv == 2) ? nidp : raiz.getNidPermiso();
        BeanPermisos hijosObj = new BeanPermisos();
        hijos = new ArrayList<BeanPermisos>();
        List<ADPermiso> lstPerm = bdL_C_SFPermisosLocal.getHijosByPadreAll(bd);
        Iterator it = lstPerm.iterator();
        while(it.hasNext()){
            ADPermiso objP = (ADPermiso) it.next();
            hijosObj = setBean(objP,"N",lstPermisos);
            if(permisosUser.contains(hijosObj.getNidPermiso())){
                hijosObj.setHasPermiso("true");
            }else{
                hijosObj.setHasPermiso("false");
            }
            hijos.add(hijosObj);
            lstPermisos.add(objP.getNidPermiso());
        }
        if(pv == 1){
            raiz.setListaHijos(hijos);
            return raiz;
        }else{
            padre.setListaHijos(hijos);
            return padre;
        }
    }
    
    public boolean hayHijosAll(List<BeanPermisos> lista){
            Iterator it = lista.iterator();
            int cant = 0;
            while(it.hasNext()){
                BeanPermisos papa = (BeanPermisos) it.next();
                List<ADPermiso> papHijos = bdL_C_SFPermisosLocal.getHijosByPadreAll(papa.getNidPermiso());
                cant += papHijos.size();
            }
            return (cant > 0) ? true : false;
    }
}