package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRItem;

@Local
public interface LN_C_SFGuiaLocal {
    List<BeanTRGuia> findGuiasByAttr_LN(String cidGuia,
                                        Date fecEmisMin,
                                        Date fecEmisMax,
                                        Date fecDespMin,
                                        Date fecDespMax,
                                        String empCliente,
                                        String empProvCarga, 
                                        String estGuia,
                                        String hasManif,
                                        Integer nidManif,
                                        String prov,
                                        String cObservaciones,
                                        String nEstadoGuia,
                                        int nidOS,
                                        String detOS,
                                        String hasFactura,
                                        String codFactura,
                                        int nEstadoFactura,
                                        BigDecimal nidParty,
                                        String descCidGuiaRemi_ITEM);
    List<BeanConstraint> getListConformidad();
    List<BeanTRItem> entityItemListtoBean(List<TRItem> itemsList);
    List<BeanConstraint> getListADMCONS(String nombreTabla, String nombreCampo);
    List<BeanTRGuia> getGuiasByOrdenServicio(int nidOS);
    int cantGuiasVigentesByManifiesto_LN(int nidOS);
    List<BeanTRGuia> getGuiasByManifiesto_LN(int nidManifiesto);
    List<BeanTRGuia> findGuiasByNidCliente_LN(int nidCliente);
    List<TRGuia> beanListGuiasToEntity(List<BeanTRGuia> guiasList);
    List<BeanTRGuia> getListaGuias(List<TRGuia> lstGuia);
    List<BeanTRGuia> getListaGuias_PorFacturar(List<TRGuia> lstGuia);
    List<BeanTRGuia> findGuiasByNidClienteParaPreFactura_LN(int nidCliente);
    boolean contieneItemGuia(BeanTRGuia eIteam,List<BeanTRGuia> lstItem);
    boolean contieneItemGuiaSolo4RegFactura(BeanTRGuia eIteam,List<BeanTRGuia> lstItem);
    boolean contieneItem_Guia(BeanTRItem eIteam,List<BeanTRItem> lstItem);
    int cantGuiasByDireccion(int nidDireccion);
    int cantGuiasByChofer(int nidChofer);
    int cantGuiasByFlota(int nidFlota);
    List<BeanTRGuia> findGuiasByManifiesto_LN(Integer nidManif);
    boolean manifiestoHasGuiasActivas_LN(int nidManifesto);
    List<BeanTRGuia> guiasByNidPartyOK(int nidCliente);
    List<BeanTRGuia> guiasByNidParty(int nidCliente);
    List<BeanTRItem> passItems(List<TRItem> items);
    List<BeanTRItem> getListaItemsByCidGuia(String cidGuia);
    int contieneGuiasPendientesByOS(int nidOS);
}
