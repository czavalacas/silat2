package silat.servicios_negocio.Beans;

import java.io.Serializable;

public class BeanConstraint implements Serializable{

    @SuppressWarnings("compatibility:7266679987228835445")
    private static final long serialVersionUID = 1L;
    
    private String cAbrv;
    private String cCampo;
    private String cDescrip;
    private String cNombreTabla;
    private String cValorCampo;

    public void setCAbrv(String cAbrv) {
        this.cAbrv = cAbrv;
    }

    public String getCAbrv() {
        return cAbrv;
    }

    public void setCCampo(String cCampo) {
        this.cCampo = cCampo;
    }

    public String getCCampo() {
        return cCampo;
    }

    public void setCDescrip(String cDescrip) {
        this.cDescrip = cDescrip;
    }

    public String getCDescrip() {
        return cDescrip;
    }

    public void setCNombreTabla(String cNombreTabla) {
        this.cNombreTabla = cNombreTabla;
    }

    public String getCNombreTabla() {
        return cNombreTabla;
    }

    public void setCValorCampo(String cValorCampo) {
        this.cValorCampo = cValorCampo;
    }

    public String getCValorCampo() {
        return cValorCampo;
    }
}
