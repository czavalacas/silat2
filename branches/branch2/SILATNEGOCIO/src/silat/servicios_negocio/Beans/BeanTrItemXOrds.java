package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

public class BeanTrItemXOrds implements Serializable {

    @SuppressWarnings("compatibility:-1361348386700401298")
    private static final long serialVersionUID = 1L;
    private String cCidGuiaRemitente;
    private String cDescItem;
    private String cUndMedida;
    private Integer dPeso;
    private Double nCantidad;
    private BigDecimal nidItem;
    private BigDecimal idItem;
    private BeanOrdenServicio trOrdenServicio;
    private Integer orden;
    private String ruta;//tipo 2 factura
    
    private Double detalleWebmovilCantidad;
    private String detalleWebmovilUmedida;
    private String detalleWebmovilDescripcion;
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof BeanTrItemXOrds){
            BeanTrItemXOrds item = (BeanTrItemXOrds) obj;
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
        BeanTrItemXOrds item = (BeanTrItemXOrds) obj;
        Integer ordenObj = item.getOrden();
        Integer ordenThis = this.getOrden();
        return (ordenThis.compareTo(ordenObj));
    }
    
    public BeanTrItemXOrds clonar(){
        BeanTrItemXOrds clon = new BeanTrItemXOrds();
        clon.setCCidGuiaRemitente(this.cCidGuiaRemitente);
        clon.setCDescItem(this.cDescItem);
        clon.setCUndMedida(this.cUndMedida);
        clon.setDPeso(this.dPeso);
        clon.setNCantidad(this.nCantidad);
        clon.setNidItem(this.nidItem);
        clon.setIdItem(this.idItem);
        clon.setTrOrdenServicio(this.trOrdenServicio);
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



    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setTrOrdenServicio(BeanOrdenServicio trOrdenServicio) {
        this.trOrdenServicio = trOrdenServicio;
    }

    public BeanOrdenServicio getTrOrdenServicio() {
        return trOrdenServicio;
    }

    public void setDetalleWebmovilUmedida(String detalleWebmovilUmedida) {
        this.detalleWebmovilUmedida = detalleWebmovilUmedida;
    }

    public String getDetalleWebmovilUmedida() {
        return detalleWebmovilUmedida;
    }

    public void setDetalleWebmovilDescripcion(String detalleWebmovilDescripcion) {
        this.detalleWebmovilDescripcion = detalleWebmovilDescripcion;
    }

    public String getDetalleWebmovilDescripcion() {
        return detalleWebmovilDescripcion;
    }

    public void setDetalleWebmovilCantidad(Double detalleWebmovilCantidad) {
        this.detalleWebmovilCantidad = detalleWebmovilCantidad;
    }

    public Double getDetalleWebmovilCantidad() {
        return detalleWebmovilCantidad;
    }
}
