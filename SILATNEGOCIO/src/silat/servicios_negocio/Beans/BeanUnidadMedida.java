package silat.servicios_negocio.Beans;

import java.io.Serializable;

public class BeanUnidadMedida implements Serializable{
    @SuppressWarnings("compatibility:8666756620526434441")
    private static final long serialVersionUID = 1L;
    
    private String descUnidadMedida;
    private String sigla;
    private Integer nidUnidadMedida;
    BeanError beanError = new BeanError();

    public BeanUnidadMedida(){
        
    }
    
    public BeanUnidadMedida(String descUnidadMedida,
                            String sigla,
                            Integer nidUnidadMedida){
        this.descUnidadMedida = descUnidadMedida;
        this.sigla = sigla;
        this.nidUnidadMedida = nidUnidadMedida;
    }
    
    public void setDescUnidadMedida(String descUnidadMedida) {
        this.descUnidadMedida = descUnidadMedida;
    }

    public String getDescUnidadMedida() {
        return descUnidadMedida;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    public void setNidUnidadMedida(Integer nidUnidadMedida) {
        this.nidUnidadMedida = nidUnidadMedida;
    }

    public Integer getNidUnidadMedida() {
        return nidUnidadMedida;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }
}
