package silat.servicios_negocio.BDLSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.TemporalType;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFGastoLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFGastoRemoto;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.entidades.admin.ADGasto;
import silat.servicios_negocio.entidades.trans.TRManifiesto;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_C_SFGasto", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFGasto")
public class BDL_C_SFGastoBean implements BDL_C_SFGastoRemoto, 
                                          BDL_C_SFGastoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    public BDL_C_SFGastoBean() {
    }

    /** <code>select o from ADGasto o</code> */
    public List<ADGasto> getAddgastFindAll() {
        return em.createNamedQuery("Addgast.findAll").getResultList();
    }
    
    public List<ADGasto> findGastosByAttributes(BeanGasto beanGasto) {
        try{
            String ejbQl = "SELECT NEW silat.servicios_negocio.entidades.admin.ADGasto(" +
              "m.estadoRegistro," +
              "m.cantper," +
              "m.cidFactura," +
              "m.cimgrecibo," +
              "m.dMontoGeneral," +
              "m.destino," +
              "m.c_detalle," +
              "m.c_banco," +
              "m.fechaGasto," +
              "m.adFlota," +
              "m.nidGasto," +
              "m.nidProtra," +
              "m.nroCheque," +
              "m.modalidadPago," +
              "m.utilServicioBasico," +
              "m.utilTipoCombustible," +
              "m.tipoGasto," +
              "m.utilTipoMantenimiento) " +
                           "FROM ADGasto m " +
                           "WHERE m.estadoRegistro = 1 ";
            if(beanGasto.getFechaGastoMIN() != null && beanGasto.getFechaGastoMAX() != null){
                ejbQl = ejbQl.concat(" AND m.fechaGasto BETWEEN :min AND :max ");
            }
            if(beanGasto.getTipoGasto() != null){
                if(beanGasto.getTipoGasto().getNidTiga() != 0){
                    ejbQl = ejbQl.concat(" AND m.tipoGasto.nidTiga = "+beanGasto.getTipoGasto().getNidTiga());
                }
            }
            if(beanGasto.getModalidadPago() != null){
                if(beanGasto.getModalidadPago().getNidModalidadPago() != 0){
                ejbQl = ejbQl.concat(" AND m.modalidadPago.nidModalidadPago ="+beanGasto.getModalidadPago().getNidModalidadPago());
                }
            }
            if(beanGasto.getNidProtra() != null){
                ejbQl = ejbQl.concat(" AND m.nidProtra ="+beanGasto.getNidProtra());
            }
            if(beanGasto.getSimboloMonto() != null){
                if(beanGasto.getDMontoGeneral() != null){
                    ejbQl = ejbQl.concat(" AND m.dMontoGeneral "+beanGasto.getSimboloMonto()+" "+beanGasto.getDMontoGeneral());
                }
            }
            if(beanGasto.getAdFlota() != null){
                if(beanGasto.getAdFlota().getNidFlota() != null){
                    ejbQl = ejbQl.concat(" AND m.adFlota.nidFlota = "+beanGasto.getAdFlota().getNidFlota()+" ");
                }
            }
            if(beanGasto.getCidFactura() != null){
                
                    ejbQl = ejbQl.concat(" AND upper(m.cidFactura) like '%"+beanGasto.getCidFactura().toUpperCase()+"%' ");   
            }
                if(beanGasto.getC_detalle() != null){
                    ejbQl = ejbQl.concat(" AND upper(m.c_detalle) like '%"+beanGasto.getC_detalle().toUpperCase()+"%' ");
            }
                  
           // List<ADGasto> lstGasto = new ArrayList<ADGasto>();
            if(beanGasto.getFechaGastoMIN() != null && beanGasto.getFechaGastoMAX() != null){
                List<ADGasto> lstGastos = em.createQuery(ejbQl)
                                             .setParameter("min", beanGasto.getFechaGastoMIN(), TemporalType.DATE)
                                             .setParameter("max", beanGasto.getFechaGastoMAX(), TemporalType.DATE).getResultList();
                return lstGastos;
            }else{
                List<ADGasto> lstGastos = em.createQuery(ejbQl).getResultList();
                return lstGastos;
            }
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    public int cantGastosByFlota(int nidFlota){
        try{
            String query = "SELECT count(g.nidGasto) " +
                           "FROM ADGasto g " +
                           "WHERE g.estadoRegistro = 1 " +
                           "AND g.adFlota.nidFlota = :nidFlota ";
            Object o = em.createQuery(query).setParameter("nidFlota",nidFlota).getSingleResult();
            int cant = 0;
            if(o != null){
                cant = Integer.parseInt(o.toString());
            }
            return cant;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}