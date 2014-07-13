package silat.servicios_negocio.Beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BeanPersona implements Serializable {
    @SuppressWarnings("compatibility:-9056793341377968269")
    private static final long serialVersionUID = 1L;
    
    private String cApellidos;
    private String cCargo;
    private String cNombres;
    private Date fecNac;
    private BigDecimal nidParty;
    private BeanParty adParty;

    public void setCApellidos(String cApellidos) {
        this.cApellidos = cApellidos;
    }

    public String getCApellidos() {
        return cApellidos;
    }

    public void setCCargo(String cCargo) {
        this.cCargo = cCargo;
    }

    public String getCCargo() {
        return cCargo;
    }

    public void setCNombres(String cNombres) {
        this.cNombres = cNombres;
    }

    public String getCNombres() {
        return cNombres;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setNidParty(BigDecimal nidParty) {
        this.nidParty = nidParty;
    }

    public BigDecimal getNidParty() {
        return nidParty;
    }

    public void setAdParty(BeanParty adParty) {
        this.adParty = adParty;
    }

    public BeanParty getAdParty() {
        return adParty;
    }
}
