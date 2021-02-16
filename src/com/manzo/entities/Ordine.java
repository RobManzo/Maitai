package com.manzo.entities;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Ordine {

    private int id;
    private LocalDateTime data;
    private HashMap<String, Integer> prodotti;
    private double importo;
    private String stato;
    private String idPrenotazione;

    public Ordine(int id, LocalDateTime data, HashMap<String, Integer> prodotti, double importo, String stato, String idPrenotazione){
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }


    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
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

    public HashMap<String, Integer> getProdotti() {
        return prodotti;
    }

    public void setProdotti(HashMap<String, Integer> prodotti) {
        this.prodotti = prodotti;
    }
}
