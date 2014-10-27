package siat.view.backing.utiles.fecha;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.Locale;

import siat.view.backing.utiles.Utils;

public class FechaUtiles {

    public static final String DATE_FORMAT_NOW = "dd-MM-yyyy";
    
    public boolean isBetween(Timestamp fecEva, 
                             Timestamp limInf, 
                             Timestamp limSup) {
        if (fecEva.before(limInf) || fecEva.after(limSup)) { //esta fuera del intervalo
            return false;
        } else if ((fecEva.after(limInf) || fecEva.equals(limInf)) &&
                   (fecEva.before(limSup) || fecEva.equals(limSup))) {
            return true; //dentro del intervalo
        } else {
            return false;
        }
    }

    public void swapFechas(Timestamp fecMenor, 
                           Timestamp fecMayor) {
        Timestamp temp = null;
        temp = fecMenor;
        fecMenor = fecMayor;
        fecMayor = temp;
    }
    
    public static String fechaHoy() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime()).toString();
    }
    
    public static Date fechaPast7() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return cal.getTime();
    }
    
    public static Date fechaFoward7() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return cal.getTime();
    }
    
    public static Date fechaActual() {
        String fechaHoy = fechaHoy(); //fecha actual
        int dia = Integer.parseInt(fechaHoy.substring(0, 2));
        int mes = Integer.parseInt(fechaHoy.substring(3, 5));
        int year = Integer.parseInt(fechaHoy.substring(6, 10));
        Date fechaSistema = new Date(year - 1900, mes - 1, dia);//fecha actual en formato DATE  FORMATO: Mon Nov 14 00:00:00 COT 2011
        return fechaSistema;
    }
    
    public static Date stringDate(String fecha){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
            Date date = sdf.parse(fecha);
            return date;
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
    }
    
    public static String fechaLetras(Date fechaDate){
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy", new Locale("es","PE"));
        String fecha = formateador.format(fechaDate);
        int id = fecha.indexOf(" de ") + 4;
        String letr = fecha.charAt(id)+"";
        letr = letr.toUpperCase();
        String fec1 = fecha.substring(0,id);
        String fec2 = fecha.substring(id+1,fecha.length());
        fecha = fecha.replace(fecha.charAt(id),letr.charAt(0));
        String fechaLetras = fec1+letr+fec2;
        return fechaLetras;
    }
    
    public static Date parsearFecha(Date fecha){
        Locale locale = Locale.getDefault();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String dateLocalizedFormatPattern = simpleDateFormat.toLocalizedPattern();
        Utils.depurar("dateLocalizedFormatPattern: "+dateLocalizedFormatPattern+" hoy "+new Date());
        
        
        String dateStr = fecha.toString();System.out.println("dateStr: "+dateStr+" fecha: "+fecha);
        Date dateReturn = new Date();
        String curr = "EEE MMM dd hh:mm:ss zzz yyyy";
        DateFormat readFormat = new SimpleDateFormat(curr,new Locale("en", "EN"));
        DateFormat writeFormat = new SimpleDateFormat("dd/MM/yyyy",new Locale("en", "EN"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",new Locale("en", "EN"));
        Date date = null;
        try {
           date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            Utils.depurar(fecha+" :::: no se pudo parsear con formato "+readFormat);
            e.printStackTrace();
        }

        String formattedDate = "";
        if( date != null ) {
            formattedDate = writeFormat.format(date);
            SimpleDateFormat sddf = new SimpleDateFormat("dd/MM/yyyy");  
            try {
               Date dt = sddf.parse(formattedDate);Utils.depurar("dt: "+dt);
                return dt;
            } catch (ParseException e) {
            }
        }
        return dateReturn;
    }
}
