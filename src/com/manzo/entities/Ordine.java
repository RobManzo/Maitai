package com.manzo.entities;

import javafx.util.Pair;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class Ordine {

    private int id;
    private LocalDate data;
    private LocalTime ora;
    private HashMap<Integer, Pair<Integer, BigDecimal>> prodotti;
    private BigDecimal importo;
    private String stato;
    private int idPrenotazione;

    public Ordine(int id, LocalDate data, LocalTime ora, HashMap<Integer, Pair<Integer, BigDecimal>> prodotti, BigDecimal importo, String stato, int idPrenotazione){
        this.id = id;
        this.data = data;
        this.ora = ora;
        this.prodotti = prodotti;
        this.importo = importo;
        this.stato = stato;
        this.idPrenotazione = idPrenotazione;
    }

    public Ordine(LocalDate data, LocalTime ora, HashMap<Integer, Pair<Integer, BigDecimal>> prodotti, BigDecimal importo, String stato, int idPrenotazione){
        this.data = data;
        this.ora = ora;
        this.prodotti = prodotti;
        this.importo = importo;
        this.stato = stato;
        this.idPrenotazione = idPrenotazione;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public HashMap<Integer, Pair<Integer, BigDecimal>> getProdotti() {
        return prodotti;
    }

    public void setProdotti(HashMap<Integer, Pair<Integer, BigDecimal>> prodotti) {
        this.prodotti = prodotti;
    }

    public LocalTime getOra() {return ora; }

    public void setOra(LocalTime ora) {this.ora = ora; }
}
