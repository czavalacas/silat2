package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFDireccionLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFDireccionLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;
import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFDireccionLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFDireccionRemote;
import silat.servicios_negocio.entidades.admin.ADDireccion;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADParty;
import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;
import silat.servicios_negocio.entidades.admin.ADTipoRelacion;
import silat.servicios_negocio.entidades.admin.ADUbigeo;

@Stateless(name = "LN_C_SFDireccion", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFDireccion")
public class LN_C_SFDireccionBean implements LN_C_SFDireccionRemote, 
                                             LN_C_SFDireccionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    @EJB
    protected BDL_T_SFDireccionLocal bdL_T_SFDireccionLocal;
    
    @EJB
    protected BDL_C_SFDireccionLocal bdL_C_SFDireccionLocal;
    
    @EJB
    protected BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    
    public LN_C_SFDireccionBean() {
        
        
    }
  
    public ADDireccion grabarDireccion(ADParty party,
                                       String cidUbigeo,
                                       String cDireccion){
        ADUbigeo ubigeo = new ADUbigeo();
        ADDireccion direc = new ADDireccion();
        ubigeo.setCidUbigeo(cidUbigeo);
        direc.setCidUbigeo(cidUbigeo);
        direc.setCDireccion(cDireccion);
        direc.setParty(party);
        return bdL_T_SFDireccionLocal.persistADDireccion(direc);
    }
    public ADDireccion insertarDireccion(BigDecimal nidParty,
                                       String cidUbigeo,
                                       String cDireccion){
        ADUbigeo ubigeo = new ADUbigeo();
        ADDireccion direc = new ADDireccion();
        ADParty party=new ADParty();
        ubigeo.setCidUbigeo(cidUbigeo);
        party.setNidParty(nidParty);        //direc.setUbigeo(ubigeo);
        direc.setCidUbigeo(cidUbigeo);
        direc.setCDireccion(cDireccion.toUpperCase());
        direc.setParty(party);
        return bdL_T_SFDireccionLocal.persistADDireccion(direc);
    }
    
    public ADDireccion grabarRelacion(BigDecimal nidParty,
                                      String cidUbigeo,
                                      String cDireccion){
        ADParty empresa = new ADParty();
        ADUbigeo ubigeo = new ADUbigeo();
        ubigeo.setCidUbigeo(cidUbigeo);
        empresa.setNidParty(nidParty);
        ADDireccion direc = new ADDireccion();
        direc.setUbigeo(ubigeo);
        direc.setCidUbigeo(cidUbigeo);
        direc.setCDireccion(cDireccion.toUpperCase());
        direc.setParty(empresa);
        return bdL_T_SFDireccionLocal.persistADDireccion(direc);
    }
    
    public ADDireccion borrarRelacion(BigDecimal nidParty){
        List<ADDireccion> direcciones = bdL_C_SFDireccionLocal.getDireccionOfParty(nidParty);
        for(ADDireccion direccion : direcciones){
            bdL_T_SFDireccionLocal.removeADDireccion(direccion);
        }
        return null;         
    }
    
    public ADDireccion modificarDireccion(BigDecimal nidDireccion,
                                          BigDecimal nidParty,
                                       String cidUbigeo,
                                       String cDireccion){
        ADUbigeo ubigeo = new ADUbigeo();
        ADDireccion direc = new ADDireccion();
        ADParty party=new ADParty();
        ubigeo.setCidUbigeo(cidUbigeo);
        party.setNidParty(nidParty);        //direc.setUbigeo(ubigeo);
        direc.setCidUbigeo(cidUbigeo);
        direc.setCDireccion(cDireccion);
        direc.setNidDireccion(nidDireccion);
        direc.setParty(party);
        return bdL_T_SFDireccionLocal.mergeADDireccion(direc);
    }
    public void  borrarDireccion(BigDecimal nidDireccion){
        ADDireccion direc=new ADDireccion();
        direc.setNidDireccion(nidDireccion);
        bdL_T_SFDireccionLocal.removeADDireccion(direc);
    }
    
    public List<BeanDireccion> getRelacion(BigDecimal nidParty){
        List<ADDireccion> direccionesEntity = bdL_C_SFDireccionLocal.getDireccionOfParty(nidParty);
        List<BeanDireccion> direccionesBean = new ArrayList<BeanDireccion>();
        for(ADDireccion direccion : direccionesEntity){            
            BeanDireccion direccionBean = new BeanDireccion();
            direccionBean.setNidDireccion(direccion.getNidDireccion().intValue());
            direccionBean.setCidUbigeo(direccion.getCidUbigeo());
            direccionBean.setDepartamento(bdL_C_SFUtilsLocal.getUbigeoByCid(direccion.getCidUbigeo().substring(0, 2)+"0000").getCDescUbigeo());
            direccionBean.setProvicia(bdL_C_SFUtilsLocal.getUbigeoByCid(direccion.getCidUbigeo().substring(0, 4)+"00").getCDescUbigeo());
            direccionBean.setDistrito(bdL_C_SFUtilsLocal.getUbigeoByCid(direccion.getCidUbigeo()).getCDescUbigeo());   
            direccionBean.setDescDepartamento(direccion.getCidUbigeo().substring(0, 2)+"0000");
            direccionBean.setDescProvincia(direccion.getCidUbigeo().substring(0, 4)+"00");
            direccionBean.setDescDistrito(direccion.getCidUbigeo());           
            direccionBean.setCDireccion(direccion.getCDireccion());
            direccionesBean.add(direccionBean);
        }
        return direccionesBean;
    }
    
    /**
     * @author dfloresgonz
     * @since 16.08.2013
     * @param nidDireccion
     * @param nidParty
     * @param cDireccion
     * @return lista de direcciones
     */
    public List<BeanDireccion> getDireccionByProp_LN(Integer nidDireccion,
                                                     Integer nidParty,
                                                     String cDireccion){
        BeanDireccion direccion = new BeanDireccion();
        direccion.setCDireccion(cDireccion);
        if(nidDireccion != null){
            direccion.setNidDireccion(nidDireccion);
        } 
        if(nidParty != null){
            BeanEmpresa empresa = new BeanEmpresa();
            empresa.setNidParty(new BigDecimal(nidParty));
            direccion.setParty(empresa);
        }
        return bdL_C_SFDireccionLocal.findDireccionessByAttributes(direccion);
    }
    
    public BeanDireccion getDireccionUbigeosDesc_Para_Factura(Integer nidDireccion){
        return bdL_C_SFDireccionLocal.getDireccionUbigeosDesc_Para_Factura(nidDireccion);
    }
    /***metodo perzonalizado para traer las descripciones de direccion en la guia , mejora la rapidez**/
    public String getDescripcionDirecByNid(Integer nidDireccion){
        return bdL_C_SFDireccionLocal.getDescripcionDireccionByNid(nidDireccion);
    }
}
