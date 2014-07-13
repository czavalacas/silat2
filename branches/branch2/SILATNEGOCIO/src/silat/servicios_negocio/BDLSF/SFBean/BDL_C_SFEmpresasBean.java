package silat.servicios_negocio.BDLSF.SFBean;

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

import javax.persistence.Query;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEmpresasLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFEmpresasRemote;
import silat.servicios_negocio.Beans.BeanADUbigeo;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.audsis.STError;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_C_SFEmpresas", mappedName = "mapBDL_C_SFEmpresas")
public class BDL_C_SFEmpresasBean implements BDL_C_SFEmpresasRemote, 
                                             BDL_C_SFEmpresasLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;
    
    public BDL_C_SFEmpresasBean() {
        
    }

    /** <code>select o from ADEmpresa o</code> */
    public List<ADEmpresa> getADEmpresaFindAll() {
        return em.createNamedQuery("ADEmpresa.findAll").getResultList();
    }
    
    public ADEmpresa getEmpresaById(BigDecimal nidParty){
        String ejbQuery = "Select u from ADEmpresa u "+
                          "WHERE u.adParty.nidParty = :nidParty ";
        Query query = em.createQuery(ejbQuery).setParameter("nidParty", nidParty).setMaxResults(1);
        List<ADEmpresa> empresa = query.getResultList();
        if(empresa.isEmpty()){
            return null;
        }else{
            return empresa.get(0);
        }
    }
    
    public BeanEmpresa getADEmpresasbyNid(BigDecimal nidParty){
        BeanEmpresa beanEmpresa = new BeanEmpresa();
        try {
            MapperIF mapper = new DozerBeanMapper();
            String ejbQuery = "Select u from ADEmpresa u "+
                              "WHERE u.adParty.nidParty = :nidParty ";
            Query query = em.createQuery(ejbQuery).setParameter("nidParty", nidParty).setMaxResults(1);
            List<ADEmpresa> entityList = query.getResultList();
            for (ADEmpresa entity : entityList) {
                beanEmpresa = (BeanEmpresa)mapper.map(entity, BeanEmpresa.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return beanEmpresa;
    }

    public List<BeanEmpresa> getADEmpresabyName(String valor) {//EMPRESAS CLIENTE DE LUBAL
        String ejbQuery = "Select u " +
                          "from ADEmpresa u," +
                          "ADRelacionEmpresa rel " +
                          "where upper(u.cRazonSocial) like :valor " +
                          "and rel.adEmpresa2.nidParty = 5 " +
                          "and rel.estadoRegistro = '1'  "+
                          "and rel.adEmpresa1.nidParty = u.nidParty " +
                          "and rel.adTipoRelacion.nidTipoRelacion = 2 ";
        List<ADEmpresa> list = new ArrayList<ADEmpresa>();
        if(valor == null){
            valor = "";
        }
        list = em.createQuery(ejbQuery).setParameter("valor", "%"+valor.toUpperCase()+"%").getResultList();
        List<BeanEmpresa> lstBeanEmp = new ArrayList<BeanEmpresa>();
        Iterator it = list.iterator();
        while(it.hasNext()){
            BeanEmpresa bemp = new BeanEmpresa();
            ADEmpresa emp = (ADEmpresa)it.next();
            bemp.setCDireccion(emp.getCDireccion());
            bemp.setNidParty((emp.getNidParty()));
            bemp.setCRuc(emp.getCRuc());
            bemp.setCPagWeb(emp.getCDireccion());
            bemp.setCRazonSocial(emp.getCRazonSocial());
            lstBeanEmp.add(bemp);
        }
        return lstBeanEmp;
    }
    
    public BigDecimal getNidParty(String Nombre){
        String ejbQuery = "Select u.nidParty " +
                          "from ADEmpresa u " +
                          "where u.cRazonSocial = :Nombre";
        return (BigDecimal)em.createQuery(ejbQuery).setParameter("Nombre", Nombre).getSingleResult();
    }
    
    public List<BeanEmpresa> getADEmpresas(){
        List<BeanEmpresa> lstEmpresas = new ArrayList<BeanEmpresa>();
        try {
            MapperIF mapper = new DozerBeanMapper();
            lstEmpresas = new ArrayList<BeanEmpresa>();
            String ejbQuery = "Select u from ADEmpresa u "+
                              "WHERE u.adParty.cTipoParty = 'E' ";
            Query query = em.createQuery(ejbQuery);
            List<ADEmpresa> entityList = query.getResultList(); 
            for (ADEmpresa entity : entityList) {               
                lstEmpresas.add((BeanEmpresa)mapper.map(entity, BeanEmpresa.class));               
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lstEmpresas;
    }
    
    /**
     * Metodo reutilizable de busqueda por parametros de Empresa
     * @author dfloresgonz
     * @param beanEmpresa Seteado con los valores necesarios para la busqueda
     * @return Lista de BeanEmpresa
     */
    public List<BeanEmpresa> findEmpresasByAttributes(BeanEmpresa beanEmpresa){
        try{
            String ejbQl = "SELECT distinct(e) " +
                           "FROM ADEmpresa e, " +
                           "ADRelacionEmpresa rel " +
                           "WHERE rel.adEmpresa1.nidParty = e.nidParty and rel.estadoRegistro = '1' ";
            if(beanEmpresa.getCRazonSocial() != null){
                ejbQl = ejbQl.concat(" AND UPPER(e.cRazonSocial) like :razonsocial ");
            }
            if(beanEmpresa.getCRuc() != null){
                ejbQl = ejbQl.concat(" AND e.cRuc like :ruc ");
            }
            if(beanEmpresa.getRelaEmpresa() != null){
                ejbQl = ejbQl.concat(" AND rel.adTipoRelacion.nidTipoRelacion ="+beanEmpresa.getRelaEmpresa().getAdTipoRelacion().getNidTipoRelacion()+"");
            }
            Query query = em.createQuery(ejbQl);
            if(beanEmpresa.getCRazonSocial() != null){
                query.setParameter("razonsocial","%" + beanEmpresa.getCRazonSocial().toUpperCase() + "%");
            }
            if(beanEmpresa.getCRuc() != null){
                query.setParameter("ruc","%" + beanEmpresa.getCRuc() + "%");
            }
            List<ADEmpresa> lstEmpresas = query.getResultList();
            int size = lstEmpresas == null ? 0 : lstEmpresas.size();
            if (size > 0) {
                return getListaEmpresas(lstEmpresas);
            } else {
                return new ArrayList<BeanEmpresa>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanEmpresa>();
        }
    }
    
    /**
     * Metodo que mapea una lista de entidades Empresa a lista de Bean Empresa
     * @param lstEmpresas (entidades)
     * @return Lista de BeanEmpresa
     */
    public List<BeanEmpresa> getListaEmpresas(List<ADEmpresa> lstEmpresas){
        try{
            MapperIF mapper = new DozerBeanMapper();
            List<BeanEmpresa> lstBeanEmpresa = new ArrayList<BeanEmpresa>();
            BeanEmpresa beanEmpresa = null;
            for(ADEmpresa empresa : lstEmpresas){
                beanEmpresa = (BeanEmpresa) mapper.map(empresa,BeanEmpresa.class);
                lstBeanEmpresa.add(beanEmpresa);
            }
            return lstBeanEmpresa;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanEmpresa>();
        }
    }
}
