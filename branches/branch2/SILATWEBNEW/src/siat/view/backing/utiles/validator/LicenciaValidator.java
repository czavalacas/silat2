package siat.view.backing.utiles.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.naming.Context;
import javax.naming.InitialContext;

import siat.view.backing.transporte.cliente.SessionScopedBeanRegistrarCliente;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFChoferRemote;

public class LicenciaValidator implements Validator {
                private final static String LOOKUP_NAME_SFCHOFER_REMOTO = "mapBDL_C_SFChofer";
                private BDL_C_SFChoferRemote bdl_C_SFChoferRemote;
                private SessionScopedBeanRegistrarCliente sessionScopedBeanRegistrarCliente;
                
    public LicenciaValidator(){
                    try{
                        final Context ctx;
                        ctx = new InitialContext();
                        bdl_C_SFChoferRemote = (BDL_C_SFChoferRemote) ctx.lookup(LOOKUP_NAME_SFCHOFER_REMOTO);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
            String cantidad = object.toString(); 
            int num=0;
            if (cantidad.length() != 9) {
                FacesMessage fm = new FacesMessage("El Número de Licencia debe tener exactamente 9 caracteres.");
                throw new ValidatorException(fm);
            } 
                if (!cantidad.matches("[a-zA-Z][0-9]{8,}")) {
                
                    throw new ValidatorException(new FacesMessage("La Licencia tiene formato Incorrecto. Tipo de formato Valido X12345678"));
                }
                if(num != bdl_C_SFChoferRemote.verificarLicenciaPlaca(cantidad.toUpperCase())){
                    FacesMessage fm = new FacesMessage("El numero de licencia ya Se Encuentra Registrado. ");
                    throw new ValidatorException(fm);
                }
            }
            }
