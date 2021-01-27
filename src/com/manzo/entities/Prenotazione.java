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

    public Prenotazione(int idPrenotazione, LocalDate dataEsecuzione, LocalDate dataPrenotazione, String idPostazione, LocalTime oraIngresso, LocalTime oraUscita, int fasciaOraria) {
        this.idPrenotazione = idPrenotazione;
        this.dataEsecuzione = dataEsecuzione;
        this.dataPrenotazione = dataPrenotazione;
        this.idPostazione = idPostazione;
        this.oraIngresso = oraIngresso;
        this.oraUscita = oraUscita;
        this.fasciaOraria = fasciaOraria;
    }

    public Prenotazione(LocalDate dataPrenotazione, String idPostazione, int fasciaOraria) {
        this.dataPrenotazione = dataPrenotazione;
        this.idPostazione = idPostazione;
        this.fasciaOraria = fasciaOraria;
    }



    public int getIdPrenotazione() { return idPrenotazione; }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public LocalDate getDataEsecuzione() { return dataEsecuzione;}

    public void setDataEsecuzione(LocalDate dataEsecuzione) { this.dataEsecuzione = dataEsecuzione; }

    public LocalDate getDataPrenotazione() { return dataPrenotazione; }

    public void setDataPrenotazione(LocalDate dataPrenotazione) { this.dataPrenotazione = dataPrenotazione; }

    public String getIdPostazione() { return idPostazione; }

    public void setIdPostazione(String idPostazione) { this.idPostazione = idPostazione;}

    public LocalTime getOraIngresso() { return oraIngresso; }

    public void setOraIngresso(LocalTime oraIngresso) { this.oraIngresso = oraIngresso; }

    public LocalTime getOraUscita() { return oraUscita; }

    public void setOraUscita(LocalTime oraUscita) { this.oraUscita = oraUscita; }

    public int getFasciaOraria() { return fasciaOraria; }

    public void setFasciaOraria(int fasciaOraria) { this.fasciaOraria = fasciaOraria; }

}
