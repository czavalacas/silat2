package siat.view.backing.transporte.cliente;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanFlota;

public class SessionScopeBeanActualizarCliente implements Serializable {
    
    private List<String> relaValue=new ArrayList<String>();
    private List<BeanADRelacionEmpresa> beanListRela = new ArrayList<BeanADRelacionEmpresa>();
    private List<BeanDireccion> beanListDire = new ArrayList<BeanDireccion>();    
    private BeanDireccion beanDireccion=new BeanDireccion();
    private List<BeanChofer> beanListChof= new ArrayList<BeanChofer>();
    private BeanChofer beanChofer=new BeanChofer();
    private List<BeanFlota> beanListFlota=new ArrayList<BeanFlota>();
    private BeanFlota beanFlota=new BeanFlota();
    private List<BeanEmpresa> beanListEmpresas =new ArrayList<BeanEmpresa>();
    private BeanEmpresa beanEmpresa=new BeanEmpresa();
    private boolean estadoRequieredInputText=false;
    private boolean estadoVisibleInputText=false;
    private List bool;
    private BigDecimal nidParty;
    private Integer nidChofer;
    private String cPagWeb;
    private String cRazonSocial;
    private String cRuc;
    private String cDetalle;
    private String cEmail;
    private String cTelf;
    private String nombreEmpresa;
    private String nombreChofer;
    private String licenciaChofer;
    private String confVehicular;
    private String marcaVehiculo;
    private String placaVehiculo;
    private String descripcionVehiculo;
    private String direccionEmpresa;
    private String departamentoEmpresa;
    private String provinciaEmpresa;
    private String distritoEmpresa;
    private String certificadoInscripcion;
    private boolean estadoItem=true;
    private boolean estadocheckBox=false;
    private boolean isDepartamentoSeteado=false;
    private boolean isProvinciaSeteado=false;
    private int accion = 0;//1 registrar, 2 modificar, 3 borrar
    private String razonSocial;
    private String ruc;
    private String relacionEmpresa;
    private int exec = 0;
    
    public void setBeanListDire(List<BeanDireccion> beanListDire) {
        this.beanListDire = beanListDire;
    }

    public List<BeanDireccion> getBeanListDire() {
        return beanListDire;
    }

    public void setBeanListRela(List<BeanADRelacionEmpresa> beanListRela) {
        this.beanListRela = beanListRela;
    }

    public List<BeanADRelacionEmpresa> getBeanListRela() {
        return beanListRela;
    }

    public void setNidParty(BigDecimal nidParty) {
        this.nidParty = nidParty;
    }

    public BigDecimal getNidParty() {
        return nidParty;
    }

    public void setCPagWeb(String cPagWeb) {
        this.cPagWeb = cPagWeb;
    }

    public String getCPagWeb() {
        return cPagWeb;
    }

    public void setCRazonSocial(String cRazonSocial) {
        this.cRazonSocial = cRazonSocial;
    }

    public String getCRazonSocial() {
        return cRazonSocial;
    }

    public void setCRuc(String cRuc) {
        this.cRuc = cRuc;
    }

    public String getCRuc() {
        return cRuc;
    }

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

    public void setBool(List bool) {
        this.bool = bool;
    }

    public List getBool() {
        return bool;
    }

    public void setRelaValue(List<String> relaValue) {
        this.relaValue = relaValue;
    }

    public List<String> getRelaValue() {
        return relaValue;
    }

    public void setEstadoItem(boolean estadoItem) {
        this.estadoItem = estadoItem;
    }

    public boolean isEstadoItem() {
        return estadoItem;
    }

    public void setEstadocheckBox(boolean estadocheckBox) {
        this.estadocheckBox = estadocheckBox;
    }

    public boolean isEstadocheckBox() {
        return estadocheckBox;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setBeanListChof(List<BeanChofer> beanListChof) {
        this.beanListChof = beanListChof;
    }

    public List<BeanChofer> getBeanListChof() {
        return beanListChof;
    }

    public void setBeanListFlota(List<BeanFlota> beanListFlota) {
        this.beanListFlota = beanListFlota;
    }

    public List<BeanFlota> getBeanListFlota() {
        return beanListFlota;
    }

    public void setBeanChofer(BeanChofer beanChofer) {
        this.beanChofer = beanChofer;
    }

    public BeanChofer getBeanChofer() {
        return beanChofer;
    }

    public void setNombreChofer(String nombreChofer) {
        this.nombreChofer = nombreChofer;
    }

    public String getNombreChofer() {
        return nombreChofer;
    }

    public void setLicenciaChofer(String licenciaChofer) {
        this.licenciaChofer = licenciaChofer;
    }

    public String getLicenciaChofer() {
        return licenciaChofer;
    }

    public void setNidChofer(Integer nidChofer) {
        this.nidChofer = nidChofer;
    }

    public Integer getNidChofer() {
        return nidChofer;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public int getAccion() {
        return accion;
    }

    public void setBeanFlota(BeanFlota beanFlota) {
        this.beanFlota = beanFlota;
    }

    public BeanFlota getBeanFlota() {
        return beanFlota;
    }

    public void setConfVehicular(String confVehicular) {
        this.confVehicular = confVehicular;
    }

    public String getConfVehicular() {
        return confVehicular;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setDescripcionVehiculo(String descripcionVehiculo) {
        this.descripcionVehiculo = descripcionVehiculo;
    }

    public String getDescripcionVehiculo() {
        return descripcionVehiculo;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDepartamentoEmpresa(String departamentoEmpresa) {
        this.departamentoEmpresa = departamentoEmpresa;
    }

    public String getDepartamentoEmpresa() {
        return departamentoEmpresa;
    }

    public void setProvinciaEmpresa(String provinciaEmpresa) {
        this.provinciaEmpresa = provinciaEmpresa;
    }

    public String getProvinciaEmpresa() {
        return provinciaEmpresa;
    }

    public void setDistritoEmpresa(String distritoEmpresa) {
        this.distritoEmpresa = distritoEmpresa;
    }

    public String getDistritoEmpresa() {
        return distritoEmpresa;
    }

    public void setIsDepartamentoSeteado(boolean isDepartamentoSeteado) {
        this.isDepartamentoSeteado = isDepartamentoSeteado;
    }

    public boolean isIsDepartamentoSeteado() {
        return isDepartamentoSeteado;
    }

    public void setIsProvinciaSeteado(boolean isProvinciaSeteado) {
        this.isProvinciaSeteado = isProvinciaSeteado;
    }

    public boolean isIsProvinciaSeteado() {
        return isProvinciaSeteado;
    }

    public void setBeanDireccion(BeanDireccion beanDireccion) {
        this.beanDireccion = beanDireccion;
    }

    public BeanDireccion getBeanDireccion() {
        return beanDireccion;
    }

   

    public void setBeanEmpresa(BeanEmpresa beanEmpresa) {
        this.beanEmpresa = beanEmpresa;
    }

    public BeanEmpresa getBeanEmpresa() {
        return beanEmpresa;
    }

    public void setBeanListEmpresas(List<BeanEmpresa> beanListEmpresas) {
        this.beanListEmpresas = beanListEmpresas;
    }

    public List<BeanEmpresa> getBeanListEmpresas() {
        return beanListEmpresas;
    }

    public void setCertificadoInscripcion(String certificadoInscripcion) {
        this.certificadoInscripcion = certificadoInscripcion;
    }

    public String getCertificadoInscripcion() {
        return certificadoInscripcion;
    }

    public void setEstadoRequieredInputText(boolean estadoRequieredInputText) {
        this.estadoRequieredInputText = estadoRequieredInputText;
    }

    public boolean isEstadoRequieredInputText() {
        return estadoRequieredInputText;
    }

    public void setEstadoVisibleInputText(boolean estadoVisibleInputText) {
        this.estadoVisibleInputText = estadoVisibleInputText;
    }

    public boolean isEstadoVisibleInputText() {
        return estadoVisibleInputText;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRelacionEmpresa(String relacionEmpresa) {
        this.relacionEmpresa = relacionEmpresa;
    }

    public String getRelacionEmpresa() {
        return relacionEmpresa;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }
}
