package silat.servicios_negocio.Beans;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

public class BeanItemPreFactura implements Serializable {
    @SuppressWarnings("compatibility:6268001779640699236")
    private static final long serialVersionUID = 1L;
    
    private String destino;
    private BigDecimal igvSubtotal;
    private BigDecimal nidPrefitm;
    private BigDecimal subtotal;
    private BigDecimal totalItem;
    private BeanPreFactura preFactura;
    private List<BeanTRGuia> guiasLista; 
    private Integer orden;
    private String ubigeoOrigen;
    private String ubigeoDestino;
    private String ubigeoOrigenProv;
    private String ubigeoDestinoProv;
    private String ubigeoOrigenDist;
    private String ubigeoDestinoDist;
    private String guiasConcat;
    private String guiasItmsConcat;
    private String cliente;
    private String descDepOrigen;
    private String descDepDestino;
    private int cantItmsTotal;//para multiplicar y sacar el alto de la fila en excel
    private List<String> guiasToNullList = new ArrayList<String>();
    private String guiasToNullConcat;
    private String flgFromBD;//1,0

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof BeanItemPreFactura){
            BeanItemPreFactura item = (BeanItemPreFactura) obj;
            return this.getOrden().equals(item.getOrden());
        }else{
            return false;
        }
    }
 
    @Override
    public int hashCode(){
        if(this.getNidPrefitm() != null){
            return this.getNidPrefitm().hashCode();
        }else{
            return 0;    
        }
    }
    
    public int compareTo(Object obj){
        BeanItemPreFactura item = (BeanItemPreFactura) obj;
        Integer ordenObj = item.getOrden();
        Integer ordenThis = this.getOrden();
        return (ordenThis.compareTo(ordenObj));
    }
    
    public BeanItemPreFactura clonar(){
        BeanItemPreFactura clon = new BeanItemPreFactura();
        clon.setCliente(this.cliente);
        clon.setDestino(this.destino);
        clon.setGuiasConcat(this.guiasConcat);
        clon.setGuiasItmsConcat(this.guiasItmsConcat);
        clon.setGuiasLista(this.getGuiasLista());
        clon.setIgvSubtotal(this.igvSubtotal);
        clon.setNidPrefitm(this.nidPrefitm);
        clon.setOrden(this.orden);
        clon.setPreFactura(this.preFactura);
        clon.setSubtotal(this.subtotal);
        clon.setTotalItem(this.totalItem);
        clon.setUbigeoDestino(this.ubigeoDestino);
        clon.setUbigeoOrigen(this.ubigeoOrigen);
        clon.setUbigeoOrigenProv(this.ubigeoOrigenProv);
        clon.setUbigeoDestinoProv(this.ubigeoDestinoProv);
        clon.setUbigeoOrigenDist(this.ubigeoOrigenDist);
        clon.setUbigeoDestinoDist(this.ubigeoDestinoDist);
        clon.setDescDepOrigen(this.descDepOrigen);
        clon.setDescDepDestino(this.descDepDestino);
        clon.setCantItmsTotal(this.cantItmsTotal);
        clon.setGuiasToNullConcat(this.guiasToNullConcat);
        clon.setFlgFromBD(this.flgFromBD);
        clon.setGuiasToNullList(this.guiasToNullList);
        return clon;
    }
    
    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }

    public void setIgvSubtotal(BigDecimal igvSubtotal) {
        this.igvSubtotal = igvSubtotal;
    }

    public BigDecimal getIgvSubtotal() {
        return igvSubtotal;
    }

    public void setNidPrefitm(BigDecimal nidPrefitm) {
        this.nidPrefitm = nidPrefitm;
    }

    public BigDecimal getNidPrefitm() {
        return nidPrefitm;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setTotalItem(BigDecimal totalItem) {
        this.totalItem = totalItem;
    }

    public BigDecimal getTotalItem() {
        return totalItem;
    }

    public void setPreFactura(BeanPreFactura preFactura) {
        this.preFactura = preFactura;
    }

    public BeanPreFactura getPreFactura() {
        return preFactura;
    }

    public void setGuiasLista(List<BeanTRGuia> guiasLista) {
        this.guiasLista = guiasLista;
    }

    public List<BeanTRGuia> getGuiasLista() {
        return guiasLista;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setUbigeoOrigen(String ubigeoOrigen) {
        this.ubigeoOrigen = ubigeoOrigen;
    }

    public String getUbigeoOrigen() {
        return ubigeoOrigen;
    }

    public void setUbigeoDestino(String ubigeoDestino) {
        this.ubigeoDestino = ubigeoDestino;
    }

    public String getUbigeoDestino() {
        return ubigeoDestino;
    }

    public void setGuiasConcat(String guiasConcat) {
        this.guiasConcat = guiasConcat;
    }

    public String getGuiasConcat() {
        return guiasConcat;
    }

    public void setGuiasItmsConcat(String guiasItmsConcat) {
        this.guiasItmsConcat = guiasItmsConcat;
    }

    public String getGuiasItmsConcat() {
        return guiasItmsConcat;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setUbigeoOrigenProv(String ubigeoOrigenProv) {
        this.ubigeoOrigenProv = ubigeoOrigenProv;
    }

    public String getUbigeoOrigenProv() {
        return ubigeoOrigenProv;
    }

    public void setUbigeoDestinoProv(String ubigeoDestinoProv) {
        this.ubigeoDestinoProv = ubigeoDestinoProv;
    }

    public String getUbigeoDestinoProv() {
        return ubigeoDestinoProv;
    }

    public void setUbigeoOrigenDist(String ubigeoOrigenDist) {
        this.ubigeoOrigenDist = ubigeoOrigenDist;
    }

    public String getUbigeoOrigenDist() {
        return ubigeoOrigenDist;
    }

    public void setUbigeoDestinoDist(String ubigeoDestinoDist) {
        this.ubigeoDestinoDist = ubigeoDestinoDist;
    }

    public String getUbigeoDestinoDist() {
        return ubigeoDestinoDist;
    }

    public void setDescDepOrigen(String descDepOrigen) {
        this.descDepOrigen = descDepOrigen;
    }

    public String getDescDepOrigen() {
        return descDepOrigen;
    }

    public void setDescDepDestino(String descDepDestino) {
        this.descDepDestino = descDepDestino;
    }

    public String getDescDepDestino() {
        return descDepDestino;
    }

    public void setCantItmsTotal(int cantItmsTotal) {
        this.cantItmsTotal = cantItmsTotal;
    }

    public int getCantItmsTotal() {
        return cantItmsTotal;
    }

    public void setGuiasToNullList(List<String> guiasToNullList) {
        this.guiasToNullList = guiasToNullList;
    }

    public List<String> getGuiasToNullList() {
        return guiasToNullList;
    }

    public void setGuiasToNullConcat(String guiasToNullConcat) {
        this.guiasToNullConcat = guiasToNullConcat;
    }

    public String getGuiasToNullConcat() {
        return guiasToNullConcat;
    }

    public void setFlgFromBD(String flgFromBD) {
        this.flgFromBD = flgFromBD;
    }

    public String getFlgFromBD() {
        return flgFromBD;
    }
}
