package siat.view.backing.transporte.factura;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanItemPreFactura;
import silat.servicios_negocio.Beans.BeanTRGuia;

public class SessionScopeBeanRegistrarPreFactura {
    
    private int exec = 0;
    private String razonCliente;
    private BigDecimal nidClie;
    private List<BeanEmpresa> lstClientes = new ArrayList<BeanEmpresa>();
    private List<BeanTRGuia> lstGuias = new ArrayList<BeanTRGuia>();
    private List<BeanTRGuia> lstGuiasInCombo = new ArrayList<BeanTRGuia>();
    private List<BeanTRGuia> lstGuiasInComboAux = new ArrayList<BeanTRGuia>();
    private List<BeanItemPreFactura> lstBeanItemPreFactura = new ArrayList<BeanItemPreFactura>();
    private List guiasCombo = new ArrayList();
    private BeanEmpresa empresaSelected = new BeanEmpresa();
    private BeanTRGuia guiaSelected = new BeanTRGuia();
    private BeanTRGuia guiaSelectedBorrar = new BeanTRGuia();
    private BeanItemPreFactura itemToEditar = new BeanItemPreFactura();
    private String itRazonSocial;
    private String destino;
    private String dep1;
    private String prov1;
    private String dist1;
    private String dep2;
    private String prov2;
    private String dist2;
    private List Departamentos;
    private List Provincias;
    private List Distritos;
    private List Departamentos2;
    private List Provincias2;
    private List Distritos2;
    private String ubigeoRemite;
    private String ubigeoDest;
    private String ubiDep;
    private String ubiPro;
    private String ubiDis;
    private BigDecimal subTotal;
    private String ubiDep2;
    private String ubiPro2;
    private String ubiDis2;
    private int tipEvento = 1;//Insertar = 1, Modificar = 2
    private int tipEventoGlobal = 1;//Insertar = 1, Modificar = 2
    private String codPedido;
    private String titulo;
    private boolean renderearComponente;
    private Long nidPreFactura;
    
    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setRazonCliente(String razonCliente) {
        this.razonCliente = razonCliente;
    }

    public String getRazonCliente() {
        return razonCliente;
    }

    public void setNidClie(BigDecimal nidClie) {
        this.nidClie = nidClie;
    }

    public BigDecimal getNidClie() {
        return nidClie;
    }

    public void setLstClientes(List<BeanEmpresa> lstClientes) {
        this.lstClientes = lstClientes;
    }

    public List<BeanEmpresa> getLstClientes() {
        return lstClientes;
    }

    public void setEmpresaSelected(BeanEmpresa empresaSelected) {
        this.empresaSelected = empresaSelected;
    }

    public BeanEmpresa getEmpresaSelected() {
        return empresaSelected;
    }

    public void setItRazonSocial(String itRazonSocial) {
        this.itRazonSocial = itRazonSocial;
    }

    public String getItRazonSocial() {
        return itRazonSocial;
    }

    public void setLstGuias(List<BeanTRGuia> lstGuias) {
        this.lstGuias = lstGuias;
    }

    public List<BeanTRGuia> getLstGuias() {
        return lstGuias;
    }

    public void setGuiasCombo(List guiasCombo) {
        this.guiasCombo = guiasCombo;
    }

    public List getGuiasCombo() {
        return guiasCombo;
    }

    public void setGuiaSelected(BeanTRGuia guiaSelected) {
        this.guiaSelected = guiaSelected;
    }

    public BeanTRGuia getGuiaSelected() {
        return guiaSelected;
    }

    public void setLstGuiasInCombo(List<BeanTRGuia> lstGuiasInCombo) {
        this.lstGuiasInCombo = lstGuiasInCombo;
    }

    public List<BeanTRGuia> getLstGuiasInCombo() {
        return lstGuiasInCombo;
    }

    public void setGuiaSelectedBorrar(BeanTRGuia guiaSelectedBorrar) {
        this.guiaSelectedBorrar = guiaSelectedBorrar;
    }

    public BeanTRGuia getGuiaSelectedBorrar() {
        return guiaSelectedBorrar;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    public String generarDestino(){
        String dest1 = "";
        String dest2 = "";
        String itm = " > ";
        String fin = "";
        if(getDist1() == null){
            dest1 = getProv1();
            if(getProv1() == null){
                dest1 = getDep1();
            }
        }else{
            dest1 = getDist1();
        }
        if(getDist2() == null){
            dest2 = getProv2();
            if(getProv2() == null){
                dest2 = getDep2();
            }
        }else{
            dest2 = getDist2();
        }
        if(dest1 == null || dest2 == null){
            return "";
        }
        if(dest1.equals("") || dest2.equals("")){
            return "";
        }
        fin = dest1+itm+dest2;
        return fin;
    }
    
    public String getDestino() {
        return destino;
    }

    public void setDep1(String dep1) {
        this.dep1 = dep1;
    }

    public String getDep1() {
        return dep1;
    }

    public void setProv1(String prov1) {
        this.prov1 = prov1;
    }

    public String getProv1() {
        return prov1;
    }

    public void setDist1(String dist1) {
        this.dist1 = dist1;
    }

    public String getDist1() {
        return dist1;
    }

    public void setDep2(String dep2) {
        this.dep2 = dep2;
    }

    public String getDep2() {
        return dep2;
    }

    public void setProv2(String prov2) {
        this.prov2 = prov2;
    }

    public String getProv2() {
        return prov2;
    }

    public void setDist2(String dist2) {
        this.dist2 = dist2;
    }

    public String getDist2() {
        return dist2;
    }

    public String getDestino1() {
        return destino;
    }

    public void setDepartamentos(List Departamentos) {
        this.Departamentos = Departamentos;
    }

    public List getDepartamentos() {
        return Departamentos;
    }

    public void setProvincias(List Provincias) {
        this.Provincias = Provincias;
    }

    public List getProvincias() {
        return Provincias;
    }

    public void setDistritos(List Distritos) {
        this.Distritos = Distritos;
    }

    public List getDistritos() {
        return Distritos;
    }

    public void setDepartamentos2(List Departamentos2) {
        this.Departamentos2 = Departamentos2;
    }

    public List getDepartamentos2() {
        return Departamentos2;
    }

    public void setProvincias2(List Provincias2) {
        this.Provincias2 = Provincias2;
    }

    public List getProvincias2() {
        return Provincias2;
    }

    public void setDistritos2(List Distritos2) {
        this.Distritos2 = Distritos2;
    }

    public List getDistritos2() {
        return Distritos2;
    }

    public void setUbigeoRemite(String ubigeoRemite) {
        this.ubigeoRemite = ubigeoRemite;
    }

    public String getUbigeoRemite() {
        return ubigeoRemite;
    }

    public void setUbigeoDest(String ubigeoDest) {
        this.ubigeoDest = ubigeoDest;
    }

    public String getUbigeoDest() {
        return ubigeoDest;
    }

    public void setUbiDep(String ubiDep) {
        this.ubiDep = ubiDep;
    }

    public String getUbiDep() {
        return ubiDep;
    }

    public void setUbiPro(String ubiPro) {
        this.ubiPro = ubiPro;
    }

    public String getUbiPro() {
        return ubiPro;
    }

    public void setUbiDis(String ubiDis) {
        this.ubiDis = ubiDis;
    }

    public String getUbiDis() {
        return ubiDis;
    }

    public void setLstBeanItemPreFactura(List<BeanItemPreFactura> lstBeanItemPreFactura) {
        this.lstBeanItemPreFactura = lstBeanItemPreFactura;
    }

    public List<BeanItemPreFactura> getLstBeanItemPreFactura() {
        return lstBeanItemPreFactura;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setUbiDep2(String ubiDep2) {
        this.ubiDep2 = ubiDep2;
    }

    public String getUbiDep2() {
        return ubiDep2;
    }

    public void setUbiPro2(String ubiPro2) {
        this.ubiPro2 = ubiPro2;
    }

    public String getUbiPro2() {
        return ubiPro2;
    }

    public void setUbiDis2(String ubiDis2) {
        this.ubiDis2 = ubiDis2;
    }

    public String getUbiDis2() {
        return ubiDis2;
    }

    public void setTipEvento(int tipEvento) {
        this.tipEvento = tipEvento;
    }

    public int getTipEvento() {
        return tipEvento;
    }

    public void setLstGuiasInComboAux(List<BeanTRGuia> lstGuiasInComboAux) {
        this.lstGuiasInComboAux = lstGuiasInComboAux;
    }

    public List<BeanTRGuia> getLstGuiasInComboAux() {
        return lstGuiasInComboAux;
    }

    public void setItemToEditar(BeanItemPreFactura itemToEditar) {
        this.itemToEditar = itemToEditar;
    }

    public BeanItemPreFactura getItemToEditar() {
        return itemToEditar;
    }

    public void setCodPedido(String codPedido) {
        this.codPedido = codPedido;
    }

    public String getCodPedido() {
        return codPedido;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setRenderearComponente(boolean renderearComponente) {
        this.renderearComponente = renderearComponente;
    }

    public boolean isRenderearComponente() {
        return renderearComponente;
    }

    public void setNidPreFactura(Long nidPreFactura) {
        this.nidPreFactura = nidPreFactura;
    }

    public Long getNidPreFactura() {
        return nidPreFactura;
    }

    public void setTipEventoGlobal(int tipEventoGlobal) {
        this.tipEventoGlobal = tipEventoGlobal;
    }

    public int getTipEventoGlobal() {
        return tipEventoGlobal;
    }
}
