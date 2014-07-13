package siat.view.backing.transporte.factura;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import siat.view.backing.utiles.Utils;

import siat.view.backing.utiles.fecha.FechaUtiles;

import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;

public class SessionScopeBeanRegistrarFactura { 
    
    private List<BeanTRGuia> lstGuias;
    private List lstDirecs = new ArrayList();
    private String direc;
    private List<BeanTRItem> lstItems = new ArrayList<BeanTRItem>();
    private List<BeanTRItem> lstItemsFinal = new ArrayList<BeanTRItem>();
    private List<BeanTRItem> lstItemsFinalAll = new ArrayList<BeanTRItem>();
    private List<BeanEmpresa> lstClientes = new ArrayList<BeanEmpresa>();
    private HashSet<BeanTRItem> hashItemsFinal = new HashSet<BeanTRItem>();
    private BeanEmpresa empresaSelected = new BeanEmpresa();
    private List items;
    private List itemsFin;
    private BigDecimal bdPrecio;
    private boolean disabBtnPrecio = true;
    private BeanTRGuia guia;
    private BigDecimal subTotal;
    private BigDecimal igv;
    private BigDecimal total;
    private String sourcePrevio;
    private String cidRepoToDelete;
    private List<BeanTRGuia> lstGuiasConPrecio = new ArrayList<BeanTRGuia>();
    private Date fecFactura = FechaUtiles.fechaActual();
    private List listaUNs = new ArrayList();
    private String codUN;
    private int exec = 0;
    private String codFactura;
    private BigDecimal nidParty;
    private String cliente;
    private String ruc;
    private List lstUbiDirecRemi = new ArrayList();
    private List lstUbiDirecDesti = new ArrayList();
    private String razonCliente;
    private BigDecimal nidClie;
    private String razonClienteForFact;
    private String nidOrdServicios;
    private boolean isDisableSitems;
    private int grupo;
    private String cidGuiaGrupo;
    private String tipFactura;
    private boolean noPrecioMalaSecuenciaGrupo;
    private String detalleFactura;
    //EDITABLES
    private boolean editable;
    private boolean editableIsVisible;
    private BigDecimal precioFinalEditable;
    private String contenido;
    
    public BigDecimal getSub_total(){
        BigDecimal subTotal = new BigDecimal("0");
        if(editable){
            subTotal = this.getPrecioFinalEditable();
            if(subTotal == null){
                subTotal = new BigDecimal("0");
            }
        }else{
            if(lstGuias != null){
                if(lstGuias.size() > 0){
                    Iterator it = lstGuias.iterator();
                    while(it.hasNext()){
                        BeanTRGuia guia = (BeanTRGuia) it.next();
                        String cidGuia = guia.getCidGuia().substring(guia.getCidGuia().indexOf("-")+1,guia.getCidGuia().length());
                        if(lstItemsFinalAll != null){
                            if(lstItemsFinalAll.size() > 0){
                                Iterator itItms = lstItemsFinalAll.iterator();
                                int contItemsGuia = 0;
                                while(itItms.hasNext()){
                                    BeanTRItem item = (BeanTRItem) itItms.next();
                                    String cid = "";
                                    if(item.getTrGuia().getCidGuia().indexOf("-") > 0){
                                        cid = item.getTrGuia().getCidGuia().substring(item.getTrGuia().getCidGuia().indexOf("-")+1,item.getTrGuia().getCidGuia().length());
                                    }else{
                                        cid = item.getTrGuia().getCidGuia();
                                    }
                                    if(cid.equals(cidGuia)){
                                        contItemsGuia++;
                                    }
                                }
                                if(contItemsGuia <= 0){
                                    guia.setPrecio(new BigDecimal("0"));
                                }else{
                                    BigDecimal prec = (guia.getPrecio() != null ? guia.getPrecio() : new BigDecimal("0"));
                                    subTotal = subTotal.add(prec);
                                }
                            }else{
                                guia.setPrecio(new BigDecimal("0"));
                            }
                        }else{
                            guia.setPrecio(new BigDecimal("0"));
                        }
                    }
                }
            }
        }
        BigDecimal _igv = subTotal.multiply(new BigDecimal("0.18"));
        setIgv(_igv);
        setTotal(subTotal.add(_igv));
        return subTotal;
    }
    
    public String[] getGuiasPDF(){
        String strGuia = "";
        String strGuiaForRepo = "";
        String vec[] = new String[2];
        String ordServ = "";
        int tam = 1;
        List<Integer> lstOS = new ArrayList<Integer>();
        for(BeanTRGuia g : lstGuiasConPrecio){
            strGuiaForRepo = strGuiaForRepo.concat(g.getCidGuia().substring(g.getCidGuia().indexOf("-")+1,g.getCidGuia().length()));
            strGuia = strGuia.concat(g.getCidGuia());
            if(!lstOS.contains(g.getOrdenServicio().getNidOrdnServ())){
                lstOS.add(g.getOrdenServicio().getNidOrdnServ());
            }
            if(tam < lstGuiasConPrecio.size()){
                strGuia = strGuia.concat(",");
                strGuiaForRepo = strGuiaForRepo.concat(", ");
            }
            tam++;
        }
        int tam2= 1;
        for(Integer nidOs : lstOS){
            ordServ = ordServ.concat(nidOs+"");
            if(tam2 < lstOS.size()){
                ordServ = ordServ.concat(",");
            }
            tam2++;
        }
        setNidOrdServicios(ordServ);
        vec[0] = strGuia;
        vec[1] = strGuiaForRepo;
        return vec;
    }
    
    public String[] getGuiasPDF_Editable(){
        String strGuia = "";
        String strGuiaForRepo = "";
        String vec[] = new String[2];
        String ordServ = "";
        int tam = 1;
        List<Integer> lstOS = new ArrayList<Integer>();
        int sizeGuiasSelected = 0;
        for(BeanTRGuia g : lstGuias){
            if(g.isSelected()){
                sizeGuiasSelected++;
            }
        }
        for(BeanTRGuia g : lstGuias){
            if(g.isSelected()){
                strGuiaForRepo = strGuiaForRepo.concat(g.getCidGuia().substring(g.getCidGuia().indexOf("-")+1,g.getCidGuia().length()));
                strGuia = strGuia.concat(g.getCidGuia());
                if(!lstOS.contains(g.getOrdenServicio().getNidOrdnServ())){
                    lstOS.add(g.getOrdenServicio().getNidOrdnServ());
                }
                if(tam < sizeGuiasSelected){
                    strGuia = strGuia.concat(",");
                    strGuiaForRepo = strGuiaForRepo.concat(", ");
                }
                tam++;
            }
        }
        int tam2= 1;
        for(Integer nidOs : lstOS){
            ordServ = ordServ.concat(nidOs+"");
            if(tam2 < lstOS.size()){
                ordServ = ordServ.concat(",");
            }
            tam2++;
        }
        setNidOrdServicios(ordServ);
        vec[0] = strGuia;
        vec[1] = strGuiaForRepo;
        return vec;
    }
    
    public void actualizarListaFinal(List<BeanTRItem> lstFinal,int noQueda){
        List<BeanTRItem> lstRemove = new ArrayList<BeanTRItem>();
        List<BeanTRItem> lstAdd = new ArrayList<BeanTRItem>();
        if(noQueda == 1){
            lstRemove = lstFinal;
            if(lstRemove != null){
                if(!lstRemove.isEmpty()){
                    if(hashItemsFinal != null){
                        hashItemsFinal.removeAll(lstRemove);
                    }
                }
            }
        }else{
            if(hashItemsFinal != null){
                if(!hashItemsFinal.isEmpty()){
                    Iterator hashIt = hashItemsFinal.iterator();
                    while(hashIt.hasNext()){
                        BeanTRItem hashItem = (BeanTRItem) hashIt.next();
                        if(lstFinal != null){
                            if(!lstFinal.isEmpty()){
                                Iterator it = lstFinal.iterator();
                                int ifCeroBorrar = 0;
                                while(it.hasNext()){
                                    BeanTRItem itm = (BeanTRItem) it.next();
                                    if(hashItem.getTrGuia().getCidGuia().equals(itm.getTrGuia().getCidGuia())){
                                        if(hashItem.getNidItem().equals(itm.getNidItem())){
                                            ifCeroBorrar++;
                                        }
                                        if(!it.hasNext()){
                                            if(ifCeroBorrar == 0){
                                                lstRemove.add(hashItem);
                                            }
                                        }
                                    }              
                                    lstAdd.add(itm);
                                }
                            }
                        }
                    }
                }else{
                    if(lstFinal != null){
                        if(!lstFinal.isEmpty()){
                            Iterator it = lstFinal.iterator();
                            while(it.hasNext()){
                                BeanTRItem itm = (BeanTRItem) it.next();
                                hashItemsFinal.add(itm);
                            }
                        }
                    }
                }
                if(lstRemove != null){
                    if(!lstRemove.isEmpty()){
                        if(hashItemsFinal != null){
                            hashItemsFinal.removeAll(lstRemove);
                        }
                    }
                }
                if(lstAdd != null){
                    if(!lstAdd.isEmpty()){
                        if(hashItemsFinal != null){
                            hashItemsFinal.addAll(lstAdd);
                        }
                    }
                }
            }
        }
    }
    
    public void setSub_total(){
        getSub_total();
    }
    
    public void resetearGuia(BeanTRGuia guia){
        
    }

    public void setLstGuias(List<BeanTRGuia> lstGuias) {
        this.lstGuias = lstGuias;
    }

    public List<BeanTRGuia> getLstGuias() {
        return lstGuias;
    }

    public void setLstItems(List<BeanTRItem> lstItems) {
        this.lstItems = lstItems;
    }

    public List<BeanTRItem> getLstItems() {
        return lstItems;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public List getItems() {
        return items;
    }

    public void setLstItemsFinal(List<BeanTRItem> lstItemsFinal) {
        this.lstItemsFinal = lstItemsFinal;
    }

    public List<BeanTRItem> getLstItemsFinal() {
        return lstItemsFinal;
    }

    public void setItemsFin(List itemsFin) {
        this.itemsFin = itemsFin;
    }

    public List getItemsFin() {
        return itemsFin;
    }

    public void setBdPrecio(BigDecimal bdPrecio) {
        this.bdPrecio = bdPrecio;
    }

    public BigDecimal getBdPrecio() {
        return bdPrecio;
    }

    public void setDisabBtnPrecio(boolean disabBtnPrecio) {
        this.disabBtnPrecio = disabBtnPrecio;
    }

    public boolean isDisabBtnPrecio() {
        return disabBtnPrecio;
    }

    public void setGuia(BeanTRGuia guia) {
        this.guia = guia;
    }

    public BeanTRGuia getGuia() {
        return guia;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setSourcePrevio(String sourcePrevio) {
        this.sourcePrevio = sourcePrevio;
    }

    public String getSourcePrevio() {
        return sourcePrevio;
    }

    public void setCidRepoToDelete(String cidRepoToDelete) {
        this.cidRepoToDelete = cidRepoToDelete;
    }

    public String getCidRepoToDelete() {
        return cidRepoToDelete;
    }

    public void setLstGuiasConPrecio(List<BeanTRGuia> lstGuiasConPrecio) {
        this.lstGuiasConPrecio = lstGuiasConPrecio;
    }

    public List<BeanTRGuia> getLstGuiasConPrecio() {
        return lstGuiasConPrecio;
    }

    public void setLstDirecs(List lstDirecs) {
        this.lstDirecs = lstDirecs;
    }

    public List getLstDirecs() {
        return lstDirecs;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getDirec() {
        return direc;
    }

    public void setFecFactura(Date fecFactura) {
        this.fecFactura = fecFactura;
    }

    public Date getFecFactura() {
        return fecFactura;
    }

    public void setListaUNs(List listaUNs) {
        this.listaUNs = listaUNs;
    }

    public List getListaUNs() {
        return listaUNs;
    }

    public void setCodUN(String codUN) {
        this.codUN = codUN;
    }

    public String getCodUN() {
        return codUN;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setCodFactura(String codFactura) {
        this.codFactura = codFactura;
    }

    public String getCodFactura() {
        return codFactura;
    }

    public void setNidParty(BigDecimal nidParty) {
        this.nidParty = nidParty;
    }

    public BigDecimal getNidParty() {
        return nidParty;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRuc() {
        return ruc;
    }

    public void setLstUbiDirecRemi(List lstUbiDirecRemi) {
        this.lstUbiDirecRemi = lstUbiDirecRemi;
    }

    public List getLstUbiDirecRemi() {
        return lstUbiDirecRemi;
    }

    public void setLstUbiDirecDesti(List lstUbiDirecDesti) {
        this.lstUbiDirecDesti = lstUbiDirecDesti;
    }

    public List getLstUbiDirecDesti() {
        return lstUbiDirecDesti;
    }

    public void setLstClientes(List<BeanEmpresa> lstClientes) {
        this.lstClientes = lstClientes;
    }

    public List<BeanEmpresa> getLstClientes() {
        return lstClientes;
    }

    public void setRazonCliente(String razonCliente) {
        this.razonCliente = razonCliente;
    }

    public String getRazonCliente() {
        return razonCliente;
    }

    public void setEmpresaSelected(BeanEmpresa empresaSelected) {
        this.empresaSelected = empresaSelected;
    }

    public BeanEmpresa getEmpresaSelected() {
        return empresaSelected;
    }

    public void setNidClie(BigDecimal nidClie) {
        this.nidClie = nidClie;
    }

    public BigDecimal getNidClie() {
        return nidClie;
    }

    public void setRazonClienteForFact(String razonClienteForFact) {
        this.razonClienteForFact = razonClienteForFact;
    }

    public String getRazonClienteForFact() {
        return razonClienteForFact;
    }

    public void setNidOrdServicios(String nidOrdServicios) {
        this.nidOrdServicios = nidOrdServicios;
    }

    public String getNidOrdServicios() {
        return nidOrdServicios;
    }

    public void setHashItemsFinal(HashSet<BeanTRItem> hashItemsFinal) {
        this.hashItemsFinal = hashItemsFinal;
    }

    public HashSet<BeanTRItem> getHashItemsFinal() {
        return hashItemsFinal;
    }

    public void setLstItemsFinalAll(List<BeanTRItem> lstItemsFinalAll) {
        this.lstItemsFinalAll = lstItemsFinalAll;
    }

    public List<BeanTRItem> getLstItemsFinalAll() {
        return lstItemsFinalAll;
    }

    public void setIsDisableSitems(boolean isDisableSitems) {
        this.isDisableSitems = isDisableSitems;
    }

    public boolean isIsDisableSitems() {
        return isDisableSitems;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setCidGuiaGrupo(String cidGuiaGrupo) {
        this.cidGuiaGrupo = cidGuiaGrupo;
    }

    public String getCidGuiaGrupo() {
        return cidGuiaGrupo;
    }

    public void setTipFactura(String tipFactura) {
        this.tipFactura = tipFactura;
    }

    public String getTipFactura() {
        return tipFactura;
    }

    public void setNoPrecioMalaSecuenciaGrupo(boolean noPrecioMalaSecuenciaGrupo) {
        this.noPrecioMalaSecuenciaGrupo = noPrecioMalaSecuenciaGrupo;
    }

    public boolean isNoPrecioMalaSecuenciaGrupo() {
        return noPrecioMalaSecuenciaGrupo;
    }

    public void setDetalleFactura(String detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public String getDetalleFactura() {
        return detalleFactura;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditableIsVisible(boolean editableIsVisible) {
        this.editableIsVisible = editableIsVisible;
    }

    public boolean isEditableIsVisible() {
        return editableIsVisible;
    }

    public void setPrecioFinalEditable(BigDecimal precioFinalEditable) {
        this.precioFinalEditable = precioFinalEditable;
    }

    public BigDecimal getPrecioFinalEditable() {
        return precioFinalEditable;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }
}
