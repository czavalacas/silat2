package silat.servicios_negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;
import net.sf.dozer.util.mapping.MappingException;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFChoferBeanLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFChoferLocal;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.LNSF.IL.LN_C_SFChoferLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFChoferRemote;
import silat.servicios_negocio.entidades.admin.ADChofer;

/**
 * @author dfloresgonz
 * @since 12.08.2013
 */
@Stateless(name = "LN_C_SFChofer", mappedName = "mapLN_C_SFChofer")
public class LN_C_SFChoferBean implements LN_C_SFChoferRemote, 
                                          LN_C_SFChoferLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB 
    private BDL_C_SFChoferLocal bdL_C_SFChoferLocal;
    @EJB
    private BDL_C_SFChoferBeanLocal bdl_C_SFChoferBeanLocal;
    
    public LN_C_SFChoferBean() {
    }
    
    public List<BeanChofer> findChofersByAttr_LN(Integer nidEmpresa,
                                                 Integer nidChofer){
        BeanChofer beanChofer = new BeanChofer();
        beanChofer.setNidEmpresa(nidEmpresa);
        beanChofer.setNidChofer(nidChofer);
        return bdL_C_SFChoferLocal.findChoferesByAttributes(beanChofer);
    }
    
    public List<BeanChofer> traerChoferesPorEmpresa(Integer nidEmpresa){
        MapperIF mapper = new DozerBeanMapper();
        List<BeanChofer> listaChoferes = new ArrayList<BeanChofer>();
        try {
            List<ADChofer> listChofer = bdl_C_SFChoferBeanLocal.getChoferesPorEmpresa(nidEmpresa);
            Iterator it = listChofer.iterator();
            while (it.hasNext()) {
                ADChofer entida = (ADChofer)it.next();
                BeanChofer beanChofer = (BeanChofer)mapper.map(entida, BeanChofer.class);
                //   beanChofer.setNidEmpresa(nidEmpresa);
                /*    beanChofer.setNidChofer(entida.getNidChofer());
            beanChofer.setNombres(entida.getNombres());
            beanChofer.setLicencia(entida.getLicencia());
            beanChofer.setEstadoRegistro(entida.getEstadoRegistro());
            beanChofer.setNidEmpresa(entida.getEmpresa().getNidParty().intValue());*/
                listaChoferes.add(beanChofer);
            }
        } catch (MappingException me) {
            me.printStackTrace();
            return new ArrayList<BeanChofer>();
        }
        return listaChoferes;
    }
}