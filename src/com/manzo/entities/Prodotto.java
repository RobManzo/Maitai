package com.manzo.entities;

import java.math.BigDecimal;

public class Prodotto {

    private int idProdotto;
    private String nome;
    private String ingredienti;
    private String descrizione;
    private BigDecimal importo;
    private String categoria;
    private String imgurl;

    public Prodotto(int id, String nome, String ingredienti, String descrizione, BigDecimal importo, String categoria, String imgurl){
        this.idProdotto = id;
        this.nome = nome;
        this.ingredienti = ingredienti;
        this.descrizione = descrizione;
        this.importo = importo;
        this.categoria = categoria;
        this.imgurl = imgurl;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
