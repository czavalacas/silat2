package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

public class BeanEstCliente  implements Serializable{
    @SuppressWarnings("compatibility:-2098619585142124167")
    private static final long serialVersionUID = 1L;
    private String razonSocial;
    private double conteo;
    private String year;
    private String mes;
    private String tipo;
    private boolean flag = false;

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setConteo(double conteo) {
        this.conteo = conteo;
    }

    public double getConteo() {
        return conteo;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getMes() {
        return mes;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }
}
