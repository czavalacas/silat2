package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

public class BeanParty implements Serializable{
    @SuppressWarnings("compatibility:-5826425688929566614")
    private static final long serialVersionUID = 1L;
    private String cDetalle;
    private String cEmail;
    private String cTelf;
    private String cTipoParty;
    private BigDecimal nidParty;
    //private BeanADUbigeo adUbigeo;

    public void setCDetalle(String cDetalle) {
        this.cDetalle = cDetalle;
    }

    public String getCDetalle() {
        return cDetalle;
    }

    public void setCEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getCEmail() {
        return cEmail;
    }

    public void setCTelf(String cTelf) {
        this.cTelf = cTelf;
    }

    public String getCTelf() {
        return cTelf;
    }

    public void setCTipoParty(String cTipoParty) {
        this.cTipoParty = cTipoParty;
    }

    public String getCTipoParty() {
        return cTipoParty;
    }

    public void setNidParty(BigDecimal nidParty) {
        this.nidParty = nidParty;
    }

    public BigDecimal getNidParty() {
        return nidParty;
    }

   /* public void setAdUbigeo(BeanADUbigeo adUbigeo) {
        this.adUbigeo = adUbigeo;
    }

    public BeanADUbigeo getAdUbigeo() {
        return adUbigeo;
    }*/
}
