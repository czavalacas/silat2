package siat.view.backing.utiles.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.naming.Context;
import javax.naming.InitialContext;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFFlotaRemote;


public class ValidatorPlaca2 implements Validator {
    private final static String LOOKUP_NAME_SFFLOTA_REMOTO = "mapBDL_C_SFFlota";//#silat.servicios_negocio.BDLSF.IR.BDL_C_SFFlotaRemote
    private BDL_C_SFFlotaRemote bdl_C_SFFlotaRemote;
    
    
    public ValidatorPlaca2(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            bdl_C_SFFlotaRemote = (BDL_C_SFFlotaRemote) ctx.lookup(LOOKUP_NAME_SFFLOTA_REMOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String dato = object.toString();
        int num = 0;
       /* if (!dato.matches("[A-Za-z0-9]{2,}-[0-9]{3,}")) {
        //     ("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
            throw new ValidatorException(new FacesMessage("La placa tiene formato Incorrecto. Tipo de formato valido AA-XXXX o AAA-XXX"));
        }        
        if (dato.length() != 7) {
            FacesMessage fm = new FacesMessage("Placa debe contener exactamente 7 caracteres.");
            throw new ValidatorException(fm);
        }   
        Utils.depurar("OBJETO EN SESION :: "+Utils.getSession("OBJ_FLOTA")+"  ----  DATO:::" +dato);*/
        if(Utils.getSession("OBJ_FLOTA") != null){
            if(!Utils.getSession("OBJ_FLOTA").equals(dato)){
                    if(num != bdl_C_SFFlotaRemote.verificarExistePlaca(dato.toUpperCase())){
                    FacesMessage fm = new FacesMessage("El numero de placa ya Se Encuentra Registrado. ");
                    throw new ValidatorException(fm);
                }   
            }
        }
        if(Utils.getSession("OBJ_FLOTA") == null){
            if(num != bdl_C_SFFlotaRemote.verificarExistePlaca(dato.toUpperCase())){
            FacesMessage fm = new FacesMessage("El numero de placa ya Se Encuentra Registrado. ");
            throw new ValidatorException(fm);
        }
    }
}}
