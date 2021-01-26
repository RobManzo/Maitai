package com.manzo.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Prenotazione {

    private int idPrenotazione;
    private LocalDate dataEsecuzione;
    private LocalDate dataPrenotazione;
    private String idPostazione;
    private LocalTime oraIngresso;
    private LocalTime oraUscita;
    private int fasciaOraria;

    public Prenotazione(int idPrenotazione, LocalDate dataEsecuzione, LocalDate dataPrenotazione, String idPostazione, String oraIngresso, String oraUscita, String fasciaOraria) {
        this.idPrenotazione = idPrenotazione;
        this.dataEsecuzione = dataEsecuzione;
        this.dataPrenotazione = dataPrenotazione;
        this.idPostazione = idPostazione;
        this.oraIngresso = oraIngresso;
        this.oraUscita = oraUscita;
        this.fasciaOraria = fasciaOraria;
    }

    public Prenotazione(String dataPrenotazione, String idPostazione, String fasciaOraria) {
        this.dataPrenotazione = dataPrenotazione;
        this.idPostazione = idPostazione;
        this.fasciaOraria = fasciaOraria;
    }



    public int getIdPrenotazione() { return idPrenotazione; }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public String getDataEsecuzione() { return dataEsecuzione;}

    public void setDataEsecuzione(String dataEsecuzione) { this.dataEsecuzione = dataEsecuzione; }

    public String getDataPrenotazione() { return dataPrenotazione; }

    public void setDataPrenotazione(String dataPrenotazione) { this.dataPrenotazione = dataPrenotazione; }

    public String getIdPostazione() { return idPostazione; }

    public void setIdPostazione(String idPostazione) { this.idPostazione = idPostazione;}

    public String getOraIngresso() { return oraIngresso; }

    public void setOraIngresso(String oraIngresso) { this.oraIngresso = oraIngresso; }

    public String getOraUscita() { return oraUscita; }

    public void setOraUscita(String oraUscita) { this.oraUscita = oraUscita; }

    public String getFasciaOraria() { return fasciaOraria; }

    public void setFasciaOraria(String fasciaOraria) { this.fasciaOraria = fasciaOraria; }

}
