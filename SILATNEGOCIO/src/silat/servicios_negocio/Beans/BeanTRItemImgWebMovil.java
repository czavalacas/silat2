package silat.servicios_negocio.Beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BeanTRItemImgWebMovil implements Serializable {

    /**
     * Este Bean creado por motivos de uso de WebMovil
     * @author: Ricardo Vasquez
     * @Fecha: 14/10/2014
     */
    @SuppressWarnings("compatibility:4729916666598171982")
    private static final long serialVersionUID = 1L;
    private String descripimg;
    private String img;


    public void setDescripimg(String descripimg) {
        this.descripimg = descripimg;
    }

    public String getDescripimg() {
        return descripimg;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }
}
