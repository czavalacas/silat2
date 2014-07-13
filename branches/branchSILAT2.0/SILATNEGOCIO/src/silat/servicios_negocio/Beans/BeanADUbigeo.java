package silat.servicios_negocio.Beans;

import java.io.Serializable;

/**
 * Bean de la entidad ADUbigeo
 * @author dfloresgonz
 */
public class BeanADUbigeo implements Serializable {
    @SuppressWarnings("compatibility:579533850122326361")
    private static final long serialVersionUID = 1L;

    private String cDescUbigeo;
    private String cidUbigeo;

    public void setCDescUbigeo(String cDescUbigeo) {
        this.cDescUbigeo = cDescUbigeo;
    }

    public String getCDescUbigeo() {
        return cDescUbigeo;
    }

    public void setCidUbigeo(String cidUbigeo) {
        this.cidUbigeo = cidUbigeo;
    }

    public String getCidUbigeo() {
        return cidUbigeo;
    }
}