package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.naming.Context;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEmpresasLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFGuiaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFPermisosLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFOrdenServicioLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFEmpresasRemote;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFOrdenServicioLocal;
import silat.servicios_negocio.BDLSF.SFBean.BDL_C_SFEmpresasBean;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanTrItemXOrds;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFOrdenServicioLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFOrdenServicioRemote;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADEvento;
import silat.servicios_negocio.entidades.admin.ADParty;
import silat.servicios_negocio.entidades.audsis.TROrdenServicio;
import silat.servicios_negocio.entidades.trans.TRItemXOrds;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_C_SFOrdenServicio", mappedName = "mapLN_C_SFOrdenServicio")
public class LN_C_SFOrdenServicioBean implements LN_C_SFOrdenServicioRemote, 
                                                 LN_C_SFOrdenServicioLocal {
    @Resource
    SessionContext sessionContext;

    @EJB
    BDL_T_SFOrdenServicioLocal bdL_T_SFOrdenServicioLocal;
    private final static String NO_ERROR = "000";
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    @EJB
    private BDL_C_SFOrdenServicioLocal bdL_C_SFOrdenServicioLocal;
    @EJB
    private BDL_C_SFGuiaLocal bdL_C_SFGuiaLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    @EJB
    private BDL_C_SFEmpresasLocal bdL_C_SFEmpresasLocal;

    public LN_C_SFOrdenServicioBean() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanOrdenServicio grabarOrdenServicio(BigDecimal nidParty, String cDetalle, Date fecha,String direcCli, String direcRemi, int nidRemitente, List<BeanTRItem> lstItem) {
        String error = NO_ERROR;
        BeanOrdenServicio beanOS = new BeanOrdenServicio();
        BeanError beanError = new BeanError();
        try {
            TROrdenServicio entidad = new TROrdenServicio();
            ADEmpresa empresa = new ADEmpresa();
            ADParty party = new ADParty();
            party.setNidParty(nidParty);
            empresa.setAdParty(party);
            //empresa.setNidParty((nidParty));
            entidad.setAdEmpresa(empresa);
       /*     if(cDetalle!=null){
            entidad.setCDetalle(cDetalle.toUpperCase());
            }*/
            entidad.setCDetalle("SIN GUIAS");
            entidad.setFecOrdnServ(fecha);
            entidad.setNEstadoOrden(new BigDecimal(1));
            entidad.setCEstord("P");
            entidad.setFlgVisto("0");
            entidad.setNidDirecCli(direcCli);
            entidad.setNidDirecProv(direcRemi);
            if(nidRemitente!=0){
            entidad.setNidRemitente(nidRemitente);    
            }                 
            error = bdL_T_SFOrdenServicioLocal.grabarOrdenServicio(entidad, lstItem);
            //MostrarOrdenServNF();
        } catch (Exception e) {
            e.printStackTrace();
            error = "LUB-0006";
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        beanOS.setOutput(beanError.getCDescripcionError());
        beanOS.setCidError(error);
        return beanOS;
    }

    public List<BeanOrdenServicio> MostrarOrdenServ(Date fecOrdnServ) {
        List<TROrdenServicio> list = bdL_C_SFOrdenServicioLocal.getOrdenServiciobyNombreEmpresa(fecOrdnServ);
        Iterator it2 = list.iterator();
        List<BeanOrdenServicio> listbos = new ArrayList<BeanOrdenServicio>();
        while (it2.hasNext()) {
            BeanOrdenServicio bos = new BeanOrdenServicio();
            TROrdenServicio os = (TROrdenServicio)it2.next();
            bos.setNidEmpresa(os.getAdEmpresa().getNidParty());
            bos.setNidOrdnServ(os.getNidOrdnServ());
            bos.setCRazonSocial(os.getAdEmpresa().getCRazonSocial());
            bos.setFecOrdnServ(os.getFecOrdnServ());
            int cantGuiasVig = bdL_C_SFGuiaLocal.getCountGuiasVigentesByOrdenServ(bos.getNidOrdnServ());
            String flagVist = "1";
            if(cantGuiasVig == 0){
                flagVist = "0";
            }
            bos.setFlgVisto(flagVist);
            bos.setCDetalle(os.getCDetalle());
            bos.setCEstord(os.getCEstord().equals("P") ? "Pendiente" : "Finalizado");
            listbos.add(bos);
        }
        return listbos;
    }

    public List<BeanOrdenServicio> MostrarOrdenServNF() {
        List<TROrdenServicio> list = bdL_C_SFOrdenServicioLocal.getTROrdenServicioFindAll();
        Iterator it2 = list.iterator();
        List<BeanOrdenServicio> listbos = new ArrayList<BeanOrdenServicio>();
        while (it2.hasNext()) {
            BeanOrdenServicio bos = new BeanOrdenServicio();
            TROrdenServicio os = (TROrdenServicio)it2.next();
            bos.setNidEmpresa(os.getAdEmpresa().getNidParty());
            bos.setNidOrdnServ(os.getNidOrdnServ());
            bos.setCRazonSocial(os.getAdEmpresa().getCRazonSocial());
            bos.setFecOrdnServ(os.getFecOrdnServ());
            bos.setCDetalle(os.getCDetalle());
            int cantGuiasVig = bdL_C_SFGuiaLocal.getCountGuiasVigentesByOrdenServ(bos.getNidOrdnServ());
            String flagVist = "1";
            if(cantGuiasVig == 0){
                flagVist = "0";
            }
            bos.setFlgVisto(flagVist);
            bos.setCEstord(os.getCEstord().equals("P") ? "Pendiente" : "Finalizado");
            listbos.add(bos);
        }
        return listbos;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanOrdenServicio ModificarOrdenServicio(BigDecimal nidParty, 
                                                    String cDetalle, 
                                                    Date fecha,
                                                    Integer nidOrdenServ,
                                                    String estado,
                                                    boolean isCambiarEmpresa,
                                                    String comentario) {
        String error = NO_ERROR;
        BeanOrdenServicio beanOS = new BeanOrdenServicio();
        BeanError beanError = new BeanError();
        try {
            //dflores 06.05.2013  se agrega el metodo findByCorrelativo
            TROrdenServicio entidad = bdL_C_SFOrdenServicioLocal.findByCorrelativo(nidOrdenServ).get(0);
            entidad.setCDetalle(cDetalle.toUpperCase());
            entidad.setFecOrdnServ(fecha);
            entidad.setNEstadoOrden(new BigDecimal(1));
            entidad.setCEstord(estado);
            entidad.setComentario(comentario);
            if(isCambiarEmpresa){
                ADEmpresa emp = bdL_C_SFEmpresasLocal.getEmpresaById(nidParty);
                entidad.setAdEmpresa(emp);
            }
            error = bdL_T_SFOrdenServicioLocal.ModificarOrdenServicio(entidad);
        } catch (Exception e) {
            e.printStackTrace();
            error = "LUB-0006";
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        beanOS.setOutput(beanError.getCDescripcionError());
        beanOS.setBeanError(beanError);
        return beanOS;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String cambiarFechaOS_Permiso56(Integer nidOrdenServ,Date fechaOS) {
        String error = NO_ERROR;
        try {
            TROrdenServicio entidad = bdL_C_SFOrdenServicioLocal.findByCorrelativo(nidOrdenServ).get(0);
            entidad.setFecOrdnServ(fechaOS);
            error = bdL_T_SFOrdenServicioLocal.ModificarOrdenServicio(entidad);
        } catch (Exception e) {
            e.printStackTrace();
            error = "111";
        }
        return error;
    }

    public List<BeanOrdenServicio> findOrdenServicioByAttributesAux_Para_Guia(BeanOrdenServicio beanOrdServ) {
        List<TROrdenServicio> listOrdServ = bdL_C_SFOrdenServicioLocal.findOrdenServicioPendientesByAttributes_ParaGuia(beanOrdServ);
        List<BeanOrdenServicio> listaOrdServBean = new ArrayList<BeanOrdenServicio>();
        Iterator it = listOrdServ.iterator();
        MapperIF mapper = new DozerBeanMapper();
        while(it.hasNext()){
            TROrdenServicio entida = (TROrdenServicio)it.next();
            BeanOrdenServicio bean = (BeanOrdenServicio)mapper.map(entida, BeanOrdenServicio.class);
            BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("C_ESTORD","TRMORDS",entida.getCEstord());
            bean.setCEstadoOrdenDesc(constr.getCDescrip());
            bean.setCEstord(entida.getCEstord());
            int cantGuiasVig = bdL_C_SFGuiaLocal.getCountGuiasVigentesByOrdenServ(entida.getNidOrdnServ());
            String flagVist = "1";
            if(cantGuiasVig == 0){
                flagVist = "0";
            }
            bean.setFlgVisto(flagVist);
            bean.setCRazonSocial(entida.getAdEmpresa().getCRazonSocial());
        listaOrdServBean.add(bean);
        }
    return listaOrdServBean;
    }
    
    public List<BeanOrdenServicio> findOrdenServicioByAttributesAux(BeanOrdenServicio beanOrdServ) {
        List<TROrdenServicio> listOrdServ = bdL_C_SFOrdenServicioLocal.findOrdenServicioPendientesByAttributes(beanOrdServ);
        List<BeanOrdenServicio> listaOrdServBean = new ArrayList<BeanOrdenServicio>();
        Iterator it = listOrdServ.iterator();
       // MapperIF mapper = new DozerBeanMapper();
        while(it.hasNext()){
            TROrdenServicio entida = (TROrdenServicio)it.next();
        //    BeanOrdenServicio bean = (BeanOrdenServicio)mapper.map(entida, BeanOrdenServicio.class);
            BeanOrdenServicio bean=new BeanOrdenServicio();
            bean.setNidOrdnServ(entida.getNidOrdnServ());
            bean.setCDetalle(entida.getCDetalle());
            BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("C_ESTORD","TRMORDS",entida.getCEstord());
            bean.setCEstadoOrdenDesc(constr.getCDescrip());
            bean.setCEstord(entida.getCEstord());
            bean.setFecOrdnServ(entida.getFecOrdnServ());
            bean.setComentario(entida.getComentario());
            bean.setNidEmpresa(entida.getAdEmpresa().getNidParty());
            bean.setDetalleWebmovilComentario(entida.getCDetalle());
            bean.setNidRemitente(entida.getNidRemitente());
            bean.setNidDirecCli(entida.getNidDirecCli());
            bean.setNidDirecProv(entida.getNidDirecProv());
            bean.setDetalleWebmovilEmpresa((entida.getAdEmpresa().getCRazonSocial()));
            bean.setDetalleWebmovilNIDEmpresa(entida.getAdEmpresa().getNidParty()+"");
            int cantGuiasVig = bdL_C_SFGuiaLocal.getCountGuiasVigentesByOrdenServ(entida.getNidOrdnServ());
            String flagVist = "1";
            if(cantGuiasVig == 0){
                flagVist = "0";
            }
            bean.setFlgVisto(flagVist);
            bean.setCRazonSocial(entida.getAdEmpresa().getCRazonSocial());     
            listaOrdServBean.add(bean);
        }
    return listaOrdServBean;
    }

    public List<BeanOrdenServicio> findOrdenServicioGuiasOK(String detalle, String razonSocial) {
        BeanOrdenServicio beanParametro = new BeanOrdenServicio();
        beanParametro.setCDetalle(detalle);
        beanParametro.setCRazonSocial(razonSocial);
        return bdL_C_SFOrdenServicioLocal.findOrdenServicioAllGuiasOK(beanParametro);
    }
    
    public int countNuevasOS_LN(){
        return bdL_C_SFOrdenServicioLocal.countNuevasOS();
    }
    
    public int traerSiguienteValorSequence(){
        return bdL_C_SFUtilsLocal.traerSiguienteValorCodigo("trorden.nid_orden");// valor del app_seq_name de la tabla codigo 
    }
    public List<BeanOrdenServicio> ordenServicioPendiente() {
        List<TROrdenServicio> list = bdL_C_SFOrdenServicioLocal.getTROrdenServicioFindAll();
        List<BeanOrdenServicio> listbos = new ArrayList<BeanOrdenServicio>();
        for(TROrdenServicio entidad:list){
            BeanOrdenServicio bean = new BeanOrdenServicio();
            bean.setCDetalle(entidad.getCDetalle());
            bean.setCRazonSocial(entidad.getAdEmpresa().getCRazonSocial());
            bean.setComentario(entidad.getComentario());
            bean.setFecOrdnServ(entidad.getFecOrdnServ());
            bean.setNidOrdnServ(entidad.getNidOrdnServ());
            bean.setNidEmpresa(entidad.getAdEmpresa().getNidParty());
            bean.setCEstord(entidad.getCEstord().equals("P") ? "Pendiente" : "Finalizado");
            
            bean.setDetalleWebmovilComentario(entidad.getComentario());
            bean.setDetalleWebmovilEmpresa(entidad.getAdEmpresa().getCRazonSocial());
            
            listbos.add(bean);
        }
        
        return listbos;
    }
    
    public List<BeanTrItemXOrds> ItemsbyOrdenServicio(String nidOrds){
        List<TRItemXOrds> items = bdL_C_SFOrdenServicioLocal.getItemsbyOrd(nidOrds);
        List<BeanTrItemXOrds> listaItems = new ArrayList<BeanTrItemXOrds>();
        int i = 1;
        for(TRItemXOrds entida : items)
        {
            BeanTrItemXOrds bean = new BeanTrItemXOrds();
            bean.setNidItem(entida.getNidItem());
            bean.setDPeso(entida.getDPeso());
            bean.setDetalleWebmovilCantidad(entida.getNCantidad());
            bean.setDetalleWebmovilDescripcion(entida.getCDescItem());
            bean.setDetalleWebmovilUmedida(entida.getCUndMedida());
            bean.setIndex(i);
            i++;
            
            listaItems.add(bean);
        }
        
        return listaItems;
    }
}
