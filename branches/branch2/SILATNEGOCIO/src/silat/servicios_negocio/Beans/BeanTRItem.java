package silat.servicios_negocio.Beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BeanTRItem implements Serializable {

    @SuppressWarnings("compatibility:4729916666598171982")
    private static final long serialVersionUID = 1L;
    private String cCidGuiaRemitente;
    private String cDescItem;
    private String cUndMedida;
    private Integer dPeso;
    private Double nCantidad;
    private BigDecimal nidItem;
    private BigDecimal idItem;
    private BeanTRGuia trGuia;
    private Integer orden;
    private BigDecimal precioAux4AddToItmRepo;
    private int grupo;
    private String ruta;//tipo 2 factura
    private String descEstado;//adm cons BD   
    private String cidGuia;
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof BeanTRItem){
            BeanTRItem item = (BeanTRItem) obj;
            return this.getOrden().equals(item.getOrden());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        if(this.getNidItem() != null){
            return this.getNidItem().hashCode();
        }else{
            return 0;    
        }
    }
    
    public int compareTo(Object obj){
        BeanTRItem item = (BeanTRItem) obj;
        Integer ordenObj = item.getOrden();
        Integer ordenThis = this.getOrden();
        return (ordenThis.compareTo(ordenObj));
    }
    
    public BeanTRItem clonar(){
        BeanTRItem clon = new BeanTRItem();
        clon.setCCidGuiaRemitente(this.cCidGuiaRemitente);
        clon.setCDescItem(this.cDescItem);
        clon.setCUndMedida(this.cUndMedida);
        clon.setDPeso(this.dPeso);
        clon.setNCantidad(this.nCantidad);
        clon.setNidItem(this.nidItem);
        clon.setIdItem(this.idItem);
        clon.setTrGuia(this.trGuia);
        clon.setOrden(this.orden);
        return clon;
    }
    
    public void setCCidGuiaRemitente(String cCidGuiaRemitente) {
        this.cCidGuiaRemitente = cCidGuiaRemitente;
    }

    public String getCCidGuiaRemitente() {
        return cCidGuiaRemitente;
    }

    public void setCDescItem(String cDescItem) {
        this.cDescItem = cDescItem;
    }

    public String getCDescItem() {
        return cDescItem;
    }

    public void setCUndMedida(String cUndMedida) {
        this.cUndMedida = cUndMedida;
    }

    public String getCUndMedida() {
        return cUndMedida;
    }

    public void setDPeso(Integer dPeso) {
        this.dPeso = dPeso;
    }

    public Integer getDPeso() {
        return dPeso;
    }

    public void setNCantidad(Double nCantidad) {
        this.nCantidad = nCantidad;
    }

    public Double getNCantidad() {
        return nCantidad;
    }

    public void setNidItem(BigDecimal nidItem) {
        this.nidItem = nidItem;
    }

    public BigDecimal getNidItem() {
        return nidItem;
    }

    public void setTrGuia(BeanTRGuia trGuia) {
        this.trGuia = trGuia;
    }

    public BeanTRGuia getTrGuia() {
        return trGuia;
    }

    public void setIdItem(BigDecimal idItem) {
        this.idItem = idItem;
    }

    public BigDecimal getIdItem() {
        return idItem;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setPrecioAux4AddToItmRepo(BigDecimal precioAux4AddToItmRepo) {
        this.precioAux4AddToItmRepo = precioAux4AddToItmRepo;
    }

    public BigDecimal getPrecioAux4AddToItmRepo() {
        return precioAux4AddToItmRepo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setCidGuia(String cidGuia) {
        this.cidGuia = cidGuia;
    }

    public String getCidGuia() {
        return cidGuia;
    }
}
