package silat.servicios_negocio.Beans;

import java.io.Serializable;

public class BeanCodigo implements Serializable {
    @SuppressWarnings("compatibility:6564787380484239914")
    private static final long serialVersionUID = 1L;
    
    private String cidunin;
    private String codigo;
    private String tipdoc;
    private String descTipDoc;
    private BeanError beanError;
    
    public BeanCodigo(String cidunin,
                      String codigo,
                      String tipdoc){
        this.cidunin = cidunin;
        this.codigo = codigo;
        this.tipdoc = tipdoc;    
    }
    
    public BeanCodigo(){
        
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public void setCidunin(String cidunin) {
        this.cidunin = cidunin;
    }

    public String getCidunin() {
        return cidunin;
    }

    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }

    public String getTipdoc() {
        return tipdoc;
    }
    
    public void setDescTipDoc(String descTipDoc) {
        this.descTipDoc = descTipDoc;
    }

    public String getDescTipDoc() {
        return descTipDoc;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }
}
