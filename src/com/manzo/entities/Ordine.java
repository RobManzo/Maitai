package com.manzo.entities;

import javafx.util.Pair;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringJoiner;

public class Ordine {

    private int id;
    private Timestamp data;
    private HashMap<Integer, Pair<Integer, BigDecimal>> prodotti;
    private BigDecimal importo;
    private String stato;
    private String idPrenotazione;

    public Ordine(int id, Timestamp data, HashMap<Integer, Pair<Integer, BigDecimal>> prodotti, BigDecimal importo, String stato, String idPrenotazione){
        this.id = id;
        this.data = data;
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

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
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

    public String getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(String idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public HashMap<Integer, Pair<Integer, BigDecimal>> getProdotti() {
        return prodotti;
    }

    public void setProdotti(HashMap<Integer, Pair<Integer, BigDecimal>> prodotti) {
        this.prodotti = prodotti;
    }

}
