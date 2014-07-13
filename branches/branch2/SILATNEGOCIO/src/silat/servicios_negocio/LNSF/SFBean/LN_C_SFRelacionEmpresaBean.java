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

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEmpresasLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFRelacionEmpresaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFRelacionEmpresaLocal;
import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.LNSF.IL.LN_C_SFRelacionEmpresaLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRelacionEmpresaRemote;
import silat.servicios_negocio.entidades.admin.ADDireccion;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADParty;
import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;
import silat.servicios_negocio.entidades.admin.ADTipoRelacion;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_C_SFRelacionEmpresa", mappedName = "mapLN_C_SFRelacionEmpresa")
public class LN_C_SFRelacionEmpresaBean implements LN_C_SFRelacionEmpresaRemote, 
                                                   LN_C_SFRelacionEmpresaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFRelacionEmpresaLocal bdL_C_SFRelacionEmpresaLocal;
    @EJB
    private BDL_T_SFRelacionEmpresaLocal bdL_T_SFRelacionEmpresaLocal;
    
    public LN_C_SFRelacionEmpresaBean() {
    }
    
    public List<BeanADRelacionEmpresa> getEmpresaProveedores(String razonSocial){
        BeanADRelacionEmpresa beanBusqueda = new BeanADRelacionEmpresa();
        beanBusqueda.setNidEmpresa2(5);
        beanBusqueda.setNidTipoRelacion(1);
        BeanEmpresa beanEmpresa = new BeanEmpresa();
        beanEmpresa.setCRazonSocial(razonSocial != null ? razonSocial.toUpperCase() : razonSocial);
        beanBusqueda.setAdEmpresa1(beanEmpresa);
        beanBusqueda.setNoLubal("N");//buscar los proveedores menos Lubal
        return bdL_C_SFRelacionEmpresaLocal.getRelacionesEmpresaByAttributes(beanBusqueda);
    }
    
    public List<BeanADRelacionEmpresa> getEmpresaProveedoresCliente(String razonSocial,String ruc){
        BeanADRelacionEmpresa beanBusqueda = new BeanADRelacionEmpresa();
        beanBusqueda.setNidEmpresa2(5);
        beanBusqueda.setNidTipoRelacion(3);
        BeanEmpresa beanEmpresa = new BeanEmpresa();
        beanEmpresa.setCRazonSocial(razonSocial != null ? razonSocial.toUpperCase() : razonSocial);
        beanEmpresa.setCRuc(ruc);
        beanBusqueda.setAdEmpresa1(beanEmpresa);
        return bdL_C_SFRelacionEmpresaLocal.getRelacionesEmpresaByAttributes(beanBusqueda);
    }
    
    /**
     * @autor:Jorge Cespedes
     * @date: 18/07/2013
     * @param nidParty,nidTire
     * @return ADRelacionEmpresa
     */
    
     public ADRelacionEmpresa grabarRelacion(BigDecimal nidParty,
                                             Integer nidTire){
         ADTipoRelacion tire = new ADTipoRelacion();
         ADParty lubal = new ADParty();
         ADParty empresa = new ADParty();
         ADEmpresa lubalemp = new ADEmpresa();
         
         ADEmpresa empresaemp = new ADEmpresa();
         tire.setNidTipoRelacion(nidTire);
         lubal.setNidParty(new BigDecimal(5));
         empresa.setNidParty(nidParty);
         lubalemp.setAdParty(lubal);
         empresaemp.setAdParty(empresa);
         
         ADRelacionEmpresa relaemp = new ADRelacionEmpresa();
         relaemp.setAdEmpresa1(empresaemp);
         relaemp.setAdEmpresa2(lubalemp);
         relaemp.setAdTipoRelacion(tire);
         //relaemp.setCorrelativoRelacionEmpresa(nidTire);
         relaemp.setEstadoRegistro("1");
         return bdL_T_SFRelacionEmpresaLocal.persistADRelacionEmpresa(relaemp);         
     }
    
    public ADRelacionEmpresa grabarRelacion_Aux(ADEmpresa empresaemp,
                                                Integer nidTire){
        ADTipoRelacion tire = new ADTipoRelacion();
        ADEmpresa lubalemp = new ADEmpresa();
        ADParty lubal = new ADParty();
       // ADEmpresa lubalemp = bdL_C_SFEmpresasLocal.getEmpresaById(new BigDecimal(5));
        tire.setNidTipoRelacion(nidTire);
        lubal.setNidParty(new BigDecimal(5));
        lubalemp.setAdParty(lubal);
        
        ADRelacionEmpresa relaemp = new ADRelacionEmpresa();
        relaemp.setAdEmpresa1(empresaemp);
        relaemp.setAdEmpresa2(lubalemp);
        relaemp.setAdTipoRelacion(tire);
        relaemp.setEstadoRegistro("1");
        return bdL_T_SFRelacionEmpresaLocal.persistADRelacionEmpresa(relaemp);         
    }
    
    
    public ADRelacionEmpresa borrarRelacion(BigDecimal nidParty){
        List<ADRelacionEmpresa> relaciones = bdL_C_SFRelacionEmpresaLocal.getRelacionOfEmpresa(nidParty);
        for(ADRelacionEmpresa relacion : relaciones){
            bdL_T_SFRelacionEmpresaLocal.removeADRelacionEmpresa(relacion);
        }
        return null;
    }
    
    public List<BeanADRelacionEmpresa> getRelacion(BigDecimal nidParty){
        List<ADRelacionEmpresa> relacionesEntity = bdL_C_SFRelacionEmpresaLocal.getRelacionOfEmpresa(nidParty);
        List<BeanADRelacionEmpresa> relacionesBean = new ArrayList<BeanADRelacionEmpresa>();
        for(ADRelacionEmpresa relacion : relacionesEntity){
            BeanADRelacionEmpresa relacionBean = new BeanADRelacionEmpresa();
            if(relacion.getAdTipoRelacion().getNidTipoRelacion() == 1){
                relacionBean.setDescRela("Proveedor");
            }            
            if(relacion.getAdTipoRelacion().getNidTipoRelacion() == 2){
                relacionBean.setDescRela("Cliente");
            }
            if(relacion.getAdTipoRelacion().getNidTipoRelacion() == 3){
                relacionBean.setDescRela("Proveedor de Cliente");
            }
            relacionBean.setNidTipoRelacion(relacion.getAdTipoRelacion().getNidTipoRelacion());
            relacionesBean.add(relacionBean);
        }
        return relacionesBean;
    }
}