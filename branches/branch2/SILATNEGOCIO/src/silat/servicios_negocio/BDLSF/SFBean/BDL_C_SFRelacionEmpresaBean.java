package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEmpresasLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFRelacionEmpresaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFRelacionEmpresaRemote;
import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;

@Stateless(name = "BDL_C_SFRelacionEmpresa", mappedName = "mapBDL_C_SFRelacionEmpresa")
public class BDL_C_SFRelacionEmpresaBean implements BDL_C_SFRelacionEmpresaRemote, 
                                                    BDL_C_SFRelacionEmpresaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFEmpresasLocal bdL_C_SFEmpresasLocal;
    
    public BDL_C_SFRelacionEmpresaBean() {
    }
    
    public List<BeanADRelacionEmpresa> getRelacionesEmpresaByAttributes(BeanADRelacionEmpresa beanBusqueda){
        try{
            String ejbQl = "SELECT rel " +
                           "FROM ADRelacionEmpresa rel " +
                           "WHERE rel.estadoRegistro = '1' ";
            if(beanBusqueda.getNidEmpresa1() != null && beanBusqueda.getNidTipoRelacion() != null && beanBusqueda.getAdEmpresa2() == null){//conocer a quienes provee, clientes y socios de la empresa
                ejbQl = ejbQl.concat(" AND rel.adEmpresa1.nidParty = "+beanBusqueda.getNidEmpresa1())
                             .concat(" AND rel.adTipoRelacion.nidTipoRelacion = "+beanBusqueda.getNidTipoRelacion()+" ");
            }
            if(beanBusqueda.getNidEmpresa2() != null && beanBusqueda.getNidTipoRelacion() != null && beanBusqueda.getAdEmpresa1() == null){//Conocer los proveedores,clientes o socios de la empresa consultada
                ejbQl = ejbQl.concat(" AND rel.adEmpresa2.nidParty = "+beanBusqueda.getNidEmpresa2())
                             .concat(" AND rel.adTipoRelacion.nidTipoRelacion = "+beanBusqueda.getNidTipoRelacion()+" ");
            }
            if(beanBusqueda.getNidTipoRelacion() != null && beanBusqueda.getAdEmpresa1().getCRazonSocial() != null && beanBusqueda.getNidEmpresa2() != null){
                ejbQl = ejbQl.concat(" AND rel.adTipoRelacion.nidTipoRelacion = "+beanBusqueda.getNidTipoRelacion()+" ")
                             .concat(" AND upper(rel.adEmpresa1.cRazonSocial) like :name OR rel.adEmpresa1.cRuc like :name ");//CAMABIADO CZVALACAS
            }
            if(beanBusqueda.getNidTipoRelacion() != null && beanBusqueda.getAdEmpresa1().getCRuc() != null && beanBusqueda.getNidEmpresa2() != null){
                //dfloresgonz 26.04.2014 Se agrega este IF para buscar x RUC no buscaba a la hora de buscar remitente en registrar Guia
                if(beanBusqueda.getAdEmpresa1().getCRuc() != null){
                    ejbQl = ejbQl.concat(" AND rel.adEmpresa1.cRuc like :ruc ");
                }
                //FIN dfloresgonz 26.04.2014 Se agrega este parametro para buscar x RUC no buscaba a la hora de buscar remitente en registrar Guia
            }
            if(beanBusqueda.getNoLubal() != null){
                if(beanBusqueda.getNoLubal().equalsIgnoreCase("N")){//No traer a lubal
                    ejbQl = ejbQl.concat(" AND rel.adEmpresa1.nidParty <> 5");
                }
            }
            Query query = em.createQuery(ejbQl);
            if(beanBusqueda.getNidTipoRelacion() != null && beanBusqueda.getAdEmpresa1().getCRazonSocial() != null && beanBusqueda.getNidEmpresa2() != null){
                String name = beanBusqueda.getAdEmpresa1().getCRazonSocial();
                name = name.replaceAll("\'","\\'");
                query.setParameter("name","%"+name+"%");
            }
            //dfloresgonz 26.04.2014 Se agrega este parametro para buscar x RUC no buscaba a la hora de buscar remitente en registrar Guia
            if(beanBusqueda.getNidTipoRelacion() != null && beanBusqueda.getAdEmpresa1().getCRuc() != null && beanBusqueda.getNidEmpresa2() != null){//conocer a quienes provee, clientes y socios de la empresa
                if(beanBusqueda.getAdEmpresa1().getCRuc() != null){
                    query.setParameter("ruc","%"+beanBusqueda.getAdEmpresa1().getCRuc()+"%");
                }
            }
            //FIN dfloresgonz 26.04.2014 Se agrega este parametro para buscar x RUC no buscaba a la hora de buscar remitente en registrar Guia
            List<ADRelacionEmpresa> lstRelacionEmp = query.getResultList();
            
            int size = lstRelacionEmp == null ? 0 : lstRelacionEmp.size();
            if (size > 0) {
                return getListRelaciones(lstRelacionEmp);
            } else {
                return new ArrayList<BeanADRelacionEmpresa>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanADRelacionEmpresa>();
        }
    }
    
    public List<BeanADRelacionEmpresa> getListRelaciones(List<ADRelacionEmpresa> lstRelacionEmp){
        try{
            MapperIF mapper = new DozerBeanMapper();
            List<BeanADRelacionEmpresa> lstBeanRelacion = new ArrayList<BeanADRelacionEmpresa>();
            BeanADRelacionEmpresa beanRelacion = null;
            for(ADRelacionEmpresa relacion : lstRelacionEmp){
                beanRelacion = (BeanADRelacionEmpresa) mapper.map(relacion,BeanADRelacionEmpresa.class);
               // beanRelacion.setAdEmpresa1(bdL_C_SFEmpresasLocal.setUbigeos(beanRelacion.getAdEmpresa1()));
                lstBeanRelacion.add(beanRelacion);
            }
            return lstBeanRelacion;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanADRelacionEmpresa>();
        }
    }

    
    public List<ADRelacionEmpresa> getRelacionOfEmpresa(BigDecimal nidParty){
        String ejbQuery = "Select u from ADRelacionEmpresa u where u.adEmpresa1.nidParty = :nidParty";
        return em.createQuery(ejbQuery).setParameter("nidParty", nidParty).getResultList();
    }
}