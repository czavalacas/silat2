package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

public class BeanReportePrevio implements Serializable {
    @SuppressWarnings("compatibility:-1878442957782385184")
    private static final long serialVersionUID = 1L;
    
    private Long nidReporte;
    private String cidRepo;
    private String cDescItem;
    private String cUndMedida;
    private Double nCantidad;
    private BigDecimal precio;
    private String ruta;
    private String cidGuiaFull;
    private int grupo;
    
    public void setNidReporte(Long nidReporte) {
        this.nidReporte = nidReporte;
    }

    public Long getNidReporte() {
        return nidReporte;
    }

    public void setCidRepo(String cidRepo) {
        this.cidRepo = cidRepo;
    }

    public String getCidRepo() {
        return cidRepo;
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

    public void setNCantidad(Double nCantidad) {
        this.nCantidad = nCantidad;
    }

    public Double getNCantidad() {
        return nCantidad;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setCidGuiaFull(String cidGuiaFull) {
        this.cidGuiaFull = cidGuiaFull;
    }

    public String getCidGuiaFull() {
        return cidGuiaFull;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getGrupo() {
        return grupo;
    }
}
