package silat.servicios_negocio.Beans;

import java.io.Serializable;

public class BeanTipoGasto implements Serializable {
    @SuppressWarnings("compatibility:-696355028241197222")
    private static final long serialVersionUID = 1L;
    private String estadoRegistro;
    private String descripcionTipoGasto;
    private Integer nidTiga;
    private String isRuta;
    private String descEstado;
    BeanError beanError = new BeanError();

    public BeanTipoGasto(String estadoRegistro,String descripcionTipoGasto,Integer nidTiga,String isRuta,String desc){
        this.estadoRegistro = estadoRegistro;
        this.descripcionTipoGasto = descripcionTipoGasto;
        this.nidTiga = nidTiga;
        this.isRuta = isRuta;
        this.descEstado = desc;
    }
    
    public BeanTipoGasto(){
        
    }
    
    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setDescripcionTipoGasto(String descripcionTipoGasto) {
        this.descripcionTipoGasto = descripcionTipoGasto;
    }

    public String getDescripcionTipoGasto() {
        return descripcionTipoGasto;
    }

    public void setNidTiga(Integer nidTiga) {
        this.nidTiga = nidTiga;
    }

    public Integer getNidTiga() {
        return nidTiga;
    }

    public void setIsRuta(String isRuta) {
        this.isRuta = isRuta;
    }

    public String getIsRuta() {
        return isRuta;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }
}
