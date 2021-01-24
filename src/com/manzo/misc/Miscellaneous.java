package com.manzo.misc;

import java.time.LocalTime;

public class Miscellaneous {

    public static String checkForm(String nome, String cognome, String email, String password, String confpass, String telefono, String birthdate, String codfisc) {
        if( (nome == null || cognome == null || email == null || password == null || confpass == null || birthdate == null) || (nome.replaceAll("\\s+","").contentEquals("") || cognome.replaceAll("\\s+","").contentEquals("") ||
                email.replaceAll("\\s+","").contentEquals("") || password.replaceAll("\\s+","").contentEquals("") || birthdate.replaceAll("\\s+","").contentEquals("")) )
            return "I campi sono tutti richiesti.";


        String regex = "^[A-Za-zèùàòé][a-zA-Z'èùàòé ]*$";
        if(!nome.matches(regex))
            return "Il nome non rispetta il formato richiesto.";

        if(!cognome.matches(regex))
            return "Il cognome non rispetta il formato richiesto.";

        regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+[.][a-zA-Z0-9-.]+$";
        if(!email.matches(regex))
            return "L'email non rispetta il formato richiesto.";

        regex = "^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        if(!password.matches(regex))
            return "La password non rispetta il formato richiesto.";

        if(!password.equals(confpass))
            return "Le password non corrispondono.";

        regex = "\\d{4}-\\d{2}-\\d{2}$";
        if(!birthdate.matches(regex))
            return "La data di birthdate non rispetta il formato richiesto.";

        regex = "[0-9]{10}$";
        if( (!telefono.replaceAll("\\s+","").contentEquals("") && !telefono.matches(regex)))
            return "Il telefono non rispetta il formato richiesto.";

        return null;
    }

    public static int getTimeslot(LocalTime timenow){
        LocalTime morning = LocalTime.parse("08:00:00");
        LocalTime lunch = LocalTime.parse("13:00:00");
        LocalTime afternoon = LocalTime.parse("13:00:01");
        LocalTime night = LocalTime.parse("18:00:00");

        if(timenow.compareTo(morning) >= 0 && timenow.compareTo(lunch) <= 0){
            return 1;
        } else if(timenow.compareTo(afternoon) >= 0 && timenow.compareTo(night) <= 0){
            return 2;
        } else return -1;
    }
}
