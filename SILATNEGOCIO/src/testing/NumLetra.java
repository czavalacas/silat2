package testing;

import java.lang.reflect.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import silat.servicios_negocio.util_formato.Numero_letra;
import silat.servicios_negocio.util_formato.UtilsGeneral;

public class NumLetra {
    
    static  String[] convert(Object[] objects, Class type) {
        String[] convertedObjects = (String[]) Array.newInstance(type, objects.length);
        try {
            for(int i = 0; i < objects.length; i++) {
                convertedObjects[i] = (String)objects[i];
            }
        } catch (ClassCastException e) {
           // log.debug("Exception on convert() : ", e);
        }
     
        return convertedObjects;
    }

    static boolean isCPA(String x){
        if(x.equals("C") || x.equals("P") || x.equals("A")){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
       /* String arrr = "gggg,rrrrrr,ssss,ffff,gggg,ssss";
        String[] guiaArry = arrr.split(",");
        Set<String> s = new HashSet<String>(Arrays.asList(guiaArry));
        Object[] arr =  s.toArray();
        String[] ar = convert(arr,String.class);
        for(int i = 0; i <ar.length; i++){
            String cidGuia = (String) ar[i];
            UtilsGeneral.depurar("cidGuia :"+cidGuia);
        }*/
        String x = "C";
        if(isCPA(x)){
            UtilsGeneral.depurar("es C,P,A :");
        }else{
            UtilsGeneral.depurar("es otro :");
        }
       /* Numero_letra nl = new Numero_letra();
        String montoLetra = nl.Convertir("2,537.47", true);
        UtilsGeneral.depurar("mon: "+montoLetra);*/
    }
}
